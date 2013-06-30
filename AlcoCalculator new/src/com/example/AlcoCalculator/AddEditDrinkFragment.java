package com.example.AlcoCalculator;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
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

        TextView date = (TextView) this.getView().findViewById(R.id.datetime);
        date.setText("");

        ImageView img = (ImageView) this.getView().findViewById(R.id.imageViewAddEdit);
        int id = this.getActivity().getResources().getIdentifier("drawable/" + drinkTemplate.getImage(), "drawable", this.getActivity().getPackageName());
        img.setImageResource(id);
    }

    public void SetControlsByDrink(Drink drink) {
        TextView id = (TextView) this.getView().findViewById(R.id.EditDrinkId);
        id.setText(drink.getId());

       TextView name = (TextView) this.getView().findViewById(R.id.editDrinkName);
        name.setText(drink.getName());

        TextView percent = (TextView) this.getView().findViewById(R.id.editAlcoPercent);
        percent.setText(String.valueOf(drink.getPercent()));

        TextView volume = (TextView) this.getView().findViewById(R.id.editVolume);
        volume.setText(String.valueOf(drink.getVolume()));

        TextView date = (TextView) this.getView().findViewById(R.id.datetime);
        date.setText(String.valueOf(drink.getTime()));

        ImageView img = (ImageView) this.getView().findViewById(R.id.imageViewAddEdit);
        int ImageId = this.getActivity().getResources().getIdentifier("drawable/" + drink.getImage(), "drawable", this.getActivity().getPackageName());
        img.setImageResource(ImageId);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (getArguments() != null) {
            // Get value from previous action with id of drink
            long id;
            id = getArguments().getLong("id", 0);
            Toast.makeText(getActivity().getApplicationContext(),
                    String.valueOf("id sent"), Toast.LENGTH_LONG).show();
            Drink d = databaseHelper.getDrinkById(String.valueOf(id));
            SetControlsByDrink(d);
        }
        super.onActivityCreated(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.addeditdrink, container);
        getDialog().setTitle("Hello");
        databaseHelper = new AlcoCalculatorDatabaseHelper(getActivity());

        Button save = (Button) view.findViewById(R.id.ButtonSaveDrink);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),
                        String.valueOf("Save"), Toast.LENGTH_LONG).show();

                TextView id= (TextView) view.findViewById(R.id.EditDrinkId);
                TextView name = (TextView) view.findViewById(R.id.editDrinkName);
                TextView percent = (TextView) view.findViewById(R.id.editAlcoPercent);
                TextView volume = (TextView) view.findViewById(R.id.editVolume);
                TextView date = (TextView) view.findViewById(R.id.datetime);
                ImageView img = (ImageView) view.findViewById(R.id.imageViewAddEdit);
                if (name.getText() .toString().trim().equals("")) {
                    name.setError("Name required!");
                } else if (volume.getText() .toString().trim().equals("")) {
                    volume.setError("volume Required");
                } else if (date.getText() .toString().trim().equals("")) {
                    date.setError("Date required!");
                }else if(percent.getText() .toString().trim().equals("")) {
                    percent.setError("Percent of alcohol required");
                }else
                {
                   if(!id.getText().toString().trim().equals(""))
                   {
                      databaseHelper.updateDrink(id.getText().toString(),date.getText().toString(),name.getText().toString(),Float.valueOf(percent.getText().toString()),Float.valueOf(volume.getText().toString()),"");
                 getDialog().cancel();
                   }
                }
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


        Button b = (Button) view.findViewById(R.id.buttonSelectFromTeplates);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                final DrinksTemplatesDropdownListAdapter adapter = new DrinksTemplatesDropdownListAdapter(getActivity(), databaseHelper.getAllDrinksTemplatesCursor());
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, final int which) {
                        //To change body of implemented methods use File | Settings | File Templates.
                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity().getApplicationContext(),
                                        String.valueOf(adapter.getItemId(which)), Toast.LENGTH_LONG).show();
                                DrinkTemplate t = databaseHelper.getDrinkTemplateById(String.valueOf(adapter.getItemId(which)));
                                SetControlsByTemplateDrink(t);
                            }
                        });
                    }
                });
                builder.show();
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
