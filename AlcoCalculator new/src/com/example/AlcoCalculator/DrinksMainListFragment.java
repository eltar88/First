package com.example.AlcoCalculator;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.*;
import android.widget.*;
import com.actionbarsherlock.app.SherlockListFragment;

/**
 * Created with IntelliJ IDEA.
 * User: Сергей
 * Date: 12.06.13
 * Time: 19:50
 * To change this template use File | Settings | File Templates.
 */
public class DrinksMainListFragment extends SherlockListFragment {
    private AlcoCalculatorListAdapter listAdapter;
    private AlcoCalculatorDatabaseHelper databaseHelper;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        databaseHelper = new AlcoCalculatorDatabaseHelper(getActivity());

/*        databaseHelper.saveDrink(new java.util.Date().toString(), "1111", 666, 666, "beer");
        databaseHelper.saveDrink(new java.util.Date().toString(), "2222", 666, 666, "beer");

        databaseHelper.saveDrinkTemplate(new java.util.Date().toString(), "template 2", 222, 666, "beer");
        databaseHelper.saveDrinkTemplate(new java.util.Date().toString(), "template 1", 111, 666, "beer");*/

        Cursor c = databaseHelper.getAllDrinksCursor();
        listAdapter = new AlcoCalculatorListAdapter(getActivity(), c);

        setListAdapter(listAdapter);
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        registerForContextMenu(getListView());


    }



    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.edit_drink: {
                FragmentManager fm = getFragmentManager();

                Bundle bundle=new Bundle();
                bundle.putLong("id",info.id);

                AddEditDrinkFragment editNameDialog = new AddEditDrinkFragment();
                editNameDialog.setArguments(bundle);
                editNameDialog.show(fm, "fragment_edit_name");

                DrinksMainListFragment fragmentById = (DrinksMainListFragment) getFragmentManager().findFragmentById(R.id.DrinksMainListfragment);

                return true;
            }
            case R.id.delete_drink:
                Toast.makeText(getActivity().getApplicationContext(),
                        String.valueOf("Delete"), Toast.LENGTH_LONG).show();
                    //TODO : Delete
                return true;
        }
        return super.onContextItemSelected(item);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);    //To change body of overridden methods use File | Settings | File Templates.
        MenuInflater inflater =getActivity().getMenuInflater();
        inflater.inflate(R.menu.drinks_list_context_menu, menu);

    }

    public class AlcoCalculatorListAdapter extends CursorAdapter {

        Context context;

        public AlcoCalculatorListAdapter(Context context, Cursor cursor) {
            super(context, cursor);
            this.context = context;
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.drinks_list_item, parent, false);
            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView idTextView = (TextView) view.findViewById(R.id.drinkId);
            idTextView.setText(cursor.getString(0));
            view.setId(Integer.valueOf(cursor.getString(0)));

            TextView nameTextView = (TextView) view.findViewById(R.id.drinkName);
            nameTextView.setText(cursor.getString(3));

            TextView percentTextView = (TextView) view.findViewById(R.id.alcPercent);
            percentTextView.setText(cursor.getString(2));

            TextView volumeTextView = (TextView) view.findViewById(R.id.drinkVolume);
            volumeTextView.setText(cursor.getString(4));

            TextView timeTextView = (TextView) view.findViewById(R.id.drinkTime);
            timeTextView.setText(cursor.getString(1));

            ImageView image = (ImageView) view.findViewById(R.id.drinkImage);
            int id = context.getResources().getIdentifier("drawable/" + cursor.getString(5), "drawable", context.getPackageName());
            image.setImageResource(id);
        }

    }
}
