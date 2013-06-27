package com.example.AlcoCalculator;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.actionbarsherlock.app.SherlockDialogFragment;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Сергей
 * Date: 19.06.13
 * Time: 23:26
 * To change this template use File | Settings | File Templates.
 */
public class AddEditDrinkFragment extends SherlockDialogFragment {
    private AlcoCalculatorDatabaseHelper databaseHelper;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void SetControlsByTemplateDrink(DrinkTemplate drinkTemplate) {

        TextView name = (TextView) this.getView().findViewById(R.id.editDrinkName);
        name.setText(drinkTemplate.getName());

        TextView percent = (TextView) this.getView().findViewById(R.id.editAlcoPercent);
        percent.setText(String.valueOf(drinkTemplate.getPercent()));

        TextView volume = (TextView) this.getView().findViewById(R.id.editVolume);
        volume.setText(String.valueOf(drinkTemplate.getVolume()));

        ImageView img = (ImageView) this.getView().findViewById(R.id.imageViewAddEdit);
        int id = this.getActivity().getResources().getIdentifier("drawable/" + drinkTemplate.getImage(), "drawable", this.getActivity().getPackageName());
        img.setImageResource(id);
    }

    public void SetControlsByDrink(Drink drink) {

        TextView name = (TextView) this.getView().findViewById(R.id.editDrinkName);
        name.setText(drink.getName());

        TextView percent = (TextView) this.getView().findViewById(R.id.editAlcoPercent);
        percent.setText(String.valueOf(drink.getPercent()));

        TextView volume = (TextView) this.getView().findViewById(R.id.editVolume);
        volume.setText(String.valueOf(drink.getVolume()));

        TextView date = (TextView) this.getView().findViewById(R.id.datetime);
        date.setText(String.valueOf(drink.getTime()));

        ImageView img = (ImageView) this.getView().findViewById(R.id.imageViewAddEdit);
        int id = this.getActivity().getResources().getIdentifier("drawable/" + drink.getImage(), "drawable", this.getActivity().getPackageName());
        img.setImageResource(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.addeditdrink, container);
        getDialog().setTitle("Hello");

        long id=0;
        if (getArguments() != null) {
            id = getArguments().getLong("id", 0);
            Toast.makeText(getActivity().getApplicationContext(),
                    String.valueOf("id sent"), Toast.LENGTH_LONG).show();
        }
        Button save = (Button) view.findViewById(R.id.ButtonSaveDrink);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),
                        String.valueOf("Save"), Toast.LENGTH_LONG).show();
                getDialog().cancel();
            }
        });

        Button cancel = (Button) view.findViewById(R.id.ButtonCancelSaveDrink);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),
                        String.valueOf("Cancel"), Toast.LENGTH_LONG).show();
                getDialog().cancel();
            }
        });
        //set spinner
        databaseHelper = new AlcoCalculatorDatabaseHelper(getActivity());
        Spinner s = (Spinner) view.findViewById(R.id.drinkstemplates);
        s.setAdapter(new DrinksTemplatesDropdownListAdapter(getActivity(), databaseHelper.getAllDrinksTemplatesCursor()));
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView textid = (TextView) view.findViewById(R.id.drinktemplate_id);

                if (textid != null) {
                    String templateId = String.valueOf(textid.getText());
                    Toast.makeText(getActivity().getApplicationContext(),
                            templateId, Toast.LENGTH_LONG).show();

                    DrinkTemplate t = databaseHelper.getDrinkTemplateById(templateId);
                    SetControlsByTemplateDrink(t);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });


        TextView datetime = (TextView) view.findViewById(R.id.datetime);
        datetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View dialoglayout = inflater.inflate(R.layout.datetime, (ViewGroup) getDialog().findViewById(R.id.datetimelayout));

                final DatePicker date = (DatePicker) dialoglayout.findViewById(R.id.datePicker);
                final TimePicker time = (TimePicker) dialoglayout.findViewById(R.id.timePicker);

                final TextView datetime = (TextView) view.findViewById(R.id.datetime);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(dialoglayout);
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //To change body of implemented methods use File | Settings | File Templates.
                    }
                });
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Date d = new Date(date.getYear() - 1900, date.getMonth(), date.getDayOfMonth());
                        d.setHours(time.getCurrentHour());
                        d.setMinutes(time.getCurrentMinute());
                        datetime.setText(d.toString());
                    }
                });
                builder.show();
            }
        });

        if (id != 0) {
            Drink d = databaseHelper.getDrinkById(String.valueOf(id));
            SetControlsByDrink(d);
        }
        return view;
    }

    public class DrinksTemplatesDropdownListAdapter extends CursorAdapter {
        Context context;

        public DrinksTemplatesDropdownListAdapter(Context context, Cursor cursor) {
            super(context, cursor, true);

            this.context = context;
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.dropdown_drinks_templates, parent, false);
            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView idTextView = (TextView) view.findViewById(R.id.drinktemplate_id);
            idTextView.setText(cursor.getString(cursor.getColumnIndex(DrinksTemplatesTable._ID)));


            TextView nameTextView = (TextView) view.findViewById(R.id.drinktemplate_name);
            nameTextView.setText(cursor.getString(cursor.getColumnIndex(DrinksTemplatesTable.DRINKSTEMPLATESTABLE_COLUMN_NAME)));

            ImageView image = (ImageView) view.findViewById(R.id.imageView_drinktemplate);
            int id = context.getResources().getIdentifier("drawable/" + cursor.getString(cursor.getColumnIndex(DrinksTemplatesTable.DRINKSTEMPLATESTABLE_COLUMN_IMAGE)), "drawable", context.getPackageName());
            image.setImageResource(id);
        }
    }
}
