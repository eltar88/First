package com.example.AlcoCalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created with IntelliJ IDEA.
 * User: Сергей
 * Date: 12.05.13
 * Time: 14:27
 * To change this template use File | Settings | File Templates.
 */
public class AlcoCalculatorDatabaseHelper implements BaseColumns {
    public static final int DATABASE_VERSION = 6;
    public static final String DATABASE_NAME = "AlcoCalculator.db";
    private AlcoCalculatorOpenHelper openHelper;
    private SQLiteDatabase database;


    public AlcoCalculatorDatabaseHelper(Context context) {
        openHelper = new AlcoCalculatorOpenHelper(context);
        database = openHelper.getWritableDatabase();
    }

    public void saveDrink(String time, String name, float percent, float volume, String image) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DrinksTable.DRINKSTABLE_COLUMN_TIME, time);
        contentValues.put(DrinksTable.DRINKSTABLE_COLUMN_NAME, name);
        contentValues.put(DrinksTable.DRINKSTABLE_COLUMN_PERCENT, percent);
        contentValues.put(DrinksTable.DRINKSTABLE_COLUMN_VOLUME, volume);
        contentValues.put(DrinksTable.DRINKSTABLE_COLUMN_IMAGE, image);
        database.insert(DrinksTable.TABLE_NAME, null, contentValues);
    }

    public void saveDrink(Drink drink) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DrinksTable.DRINKSTABLE_COLUMN_TIME, drink.getTime());
        contentValues.put(DrinksTable.DRINKSTABLE_COLUMN_NAME, drink.getName());
        contentValues.put(DrinksTable.DRINKSTABLE_COLUMN_PERCENT, drink.getPercent());
        contentValues.put(DrinksTable.DRINKSTABLE_COLUMN_VOLUME, drink.getVolume());
        contentValues.put(DrinksTable.DRINKSTABLE_COLUMN_IMAGE, drink.getImage());
        database.insert(DrinksTable.TABLE_NAME, null, contentValues);
    }

    public void saveDrinkTemplate(String time, String name, float percent, float volume, String image) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DrinksTemplatesTable.DRINKSTEMPLATESTABLE_COLUMN_NAME, name);
        contentValues.put(DrinksTemplatesTable.DRINKSTEMPLATESTABLE_COLUMN_PERCENT, percent);
        contentValues.put(DrinksTemplatesTable.DRINKSTEMPLATESTABLE_COLUMN_VOLUME, volume);
        contentValues.put(DrinksTemplatesTable.DRINKSTEMPLATESTABLE_COLUMN_IMAGE, image);
        database.insert(DrinksTemplatesTable.TABLE_NAME, null, contentValues);
    }

    public void updateDrink(String id, String time, String name, float percent, float volume, String image) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DrinksTable.DRINKSTABLE_COLUMN_TIME, time);
        contentValues.put(DrinksTable.DRINKSTABLE_COLUMN_NAME, name);
        contentValues.put(DrinksTable.DRINKSTABLE_COLUMN_PERCENT, percent);
        contentValues.put(DrinksTable.DRINKSTABLE_COLUMN_VOLUME, volume);
        contentValues.put(DrinksTable.DRINKSTABLE_COLUMN_IMAGE, image);
        database.update(DrinksTable.TABLE_NAME, contentValues, "_id=" + id, null);
    }

    public void updateDrink(Drink drink) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DrinksTable.DRINKSTABLE_COLUMN_TIME, drink.getTime());
        contentValues.put(DrinksTable.DRINKSTABLE_COLUMN_NAME, drink.getName());
        contentValues.put(DrinksTable.DRINKSTABLE_COLUMN_PERCENT, drink.getPercent());
        contentValues.put(DrinksTable.DRINKSTABLE_COLUMN_VOLUME, drink.getVolume());
        contentValues.put(DrinksTable.DRINKSTABLE_COLUMN_IMAGE, drink.getImage());
        database.update(DrinksTable.TABLE_NAME, contentValues, "_id=" + drink.getId(), null);
    }

    public void updateDrinkTemplate(String id, String time, String name, float percent, float volume, String image) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DrinksTemplatesTable.DRINKSTEMPLATESTABLE_COLUMN_NAME, name);
        contentValues.put(DrinksTemplatesTable.DRINKSTEMPLATESTABLE_COLUMN_PERCENT, percent);
        contentValues.put(DrinksTemplatesTable.DRINKSTEMPLATESTABLE_COLUMN_VOLUME, volume);
        contentValues.put(DrinksTemplatesTable.DRINKSTEMPLATESTABLE_COLUMN_IMAGE, image);
        database.update(DrinksTemplatesTable.TABLE_NAME, contentValues, DrinksTemplatesTable._ID + "=" + id, null);
    }

    public void DeleteDrink(String id) {
        database.delete(DrinksTable.TABLE_NAME, DrinksTable._ID + "=" + id, null);
    }

    public void DeleteDrinkTemplate(String id) {
        database.delete(DrinksTemplatesTable.TABLE_NAME, DrinksTemplatesTable._ID + "=" + id, null);
    }

    public Cursor getAllDrinksCursor() {
        String[] projection = {
                DrinksTable._ID,
                DrinksTable.DRINKSTABLE_COLUMN_TIME,
                DrinksTable.DRINKSTABLE_COLUMN_PERCENT,
                DrinksTable.DRINKSTABLE_COLUMN_NAME,
                DrinksTable.DRINKSTABLE_COLUMN_VOLUME,
                DrinksTable.DRINKSTABLE_COLUMN_IMAGE

        };
        Cursor c = database.query(
                DrinksTable.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        return c;
    }

    public Cursor getAllDrinksTemplatesCursor() {
        String[] projection = {
                DrinksTemplatesTable._ID,
                DrinksTemplatesTable.DRINKSTEMPLATESTABLE_COLUMN_PERCENT,
                DrinksTemplatesTable.DRINKSTEMPLATESTABLE_COLUMN_NAME,
                DrinksTemplatesTable.DRINKSTEMPLATESTABLE_COLUMN_VOLUME,
                DrinksTemplatesTable.DRINKSTEMPLATESTABLE_COLUMN_IMAGE

        };
        Cursor c = database.query(
                DrinksTemplatesTable.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        return c;
    }
    public DrinkTemplate getDrinkTemplateById(String id)
    {
        DrinkTemplate result = new DrinkTemplate();
        String[] projection = {
                DrinksTemplatesTable._ID,
                DrinksTemplatesTable.DRINKSTEMPLATESTABLE_COLUMN_PERCENT,
                DrinksTemplatesTable.DRINKSTEMPLATESTABLE_COLUMN_NAME,
                DrinksTemplatesTable.DRINKSTEMPLATESTABLE_COLUMN_VOLUME,
                DrinksTemplatesTable.DRINKSTEMPLATESTABLE_COLUMN_IMAGE

        };
        Cursor c = database.query(
                DrinksTemplatesTable.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                DrinksTemplatesTable._ID + "=" + id,      // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        if(c.moveToFirst()){
            result.setId(c.getString(c.getColumnIndex(DrinksTemplatesTable._ID)));
            result.setPercent(c.getFloat(c.getColumnIndex(DrinksTemplatesTable.DRINKSTEMPLATESTABLE_COLUMN_PERCENT)));
            result.setName(c.getString(c.getColumnIndex(DrinksTemplatesTable.DRINKSTEMPLATESTABLE_COLUMN_NAME)));
            result.setVolume(c.getFloat(c.getColumnIndex(DrinksTemplatesTable.DRINKSTEMPLATESTABLE_COLUMN_VOLUME)));
            result.setImage(c.getString(c.getColumnIndex(DrinksTemplatesTable.DRINKSTEMPLATESTABLE_COLUMN_IMAGE)));
        }

        return result;
    }
    public Drink getDrinkById(String id)
    {
        Drink result = new Drink();
        String[] projection = {
                DrinksTable._ID,
                DrinksTable.DRINKSTABLE_COLUMN_TIME,
                DrinksTable.DRINKSTABLE_COLUMN_PERCENT,
                DrinksTable.DRINKSTABLE_COLUMN_NAME,
                DrinksTable.DRINKSTABLE_COLUMN_VOLUME,
                DrinksTable.DRINKSTABLE_COLUMN_IMAGE

        };
        Cursor c = database.query(
                DrinksTable.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                DrinksTable._ID + "=" + id,      // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        if(c.moveToFirst()){
            result.setId(c.getString(c.getColumnIndex(DrinksTable._ID)));
            result.setPercent(c.getFloat(c.getColumnIndex(DrinksTable.DRINKSTABLE_COLUMN_PERCENT)));
            result.setName(c.getString(c.getColumnIndex(DrinksTable.DRINKSTABLE_COLUMN_NAME)));
            result.setVolume(c.getFloat(c.getColumnIndex(DrinksTable.DRINKSTABLE_COLUMN_VOLUME)));
            result.setImage(c.getString(c.getColumnIndex(DrinksTable.DRINKSTABLE_COLUMN_IMAGE)));
            result.setTime(c.getString(c.getColumnIndex(DrinksTable.DRINKSTABLE_COLUMN_TIME)));
        }

        return result;
    }
}

class AlcoCalculatorOpenHelper extends SQLiteOpenHelper {
    AlcoCalculatorOpenHelper(Context context) {
        super(context, AlcoCalculatorDatabaseHelper.DATABASE_NAME, null, AlcoCalculatorDatabaseHelper.DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DrinksTable.DRINKSTABLE_CREATE);
        database.execSQL(DrinksTemplatesTable.DRINKSTEMPLATESTABLE_CREATE);

    }

    public void onUpgrade(SQLiteDatabase database,
                          int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + DrinksTable.TABLE_NAME);
        database.execSQL("DROP TABLE IF EXISTS " + DrinksTemplatesTable.TABLE_NAME);
        onCreate(database);
    }
}

class DrinksTable implements BaseColumns {
    public static final String TABLE_NAME = "drinks";
    public static final String DRINKSTABLE_COLUMN_NAME = "name";
    public static final String DRINKSTABLE_COLUMN_TIME = "time";
    public static final String DRINKSTABLE_COLUMN_PERCENT = "percent";
    public static final String DRINKSTABLE_COLUMN_VOLUME = "volume";
    public static final String DRINKSTABLE_COLUMN_IMAGE = "image";
    public static final String DRINKSTABLE_COLUMN_ISFROMTEMPLATE = "isfromtemplate";
    public static final String DRINKSTABLE_CREATE = "CREATE TABLE " + DrinksTable.TABLE_NAME + "( "
            + DrinksTable._ID + " INTEGER PRIMARY KEY, "
            + DrinksTable.DRINKSTABLE_COLUMN_TIME + " TEXT, "
            + DrinksTable.DRINKSTABLE_COLUMN_NAME + " TEXT, "
            + DrinksTable.DRINKSTABLE_COLUMN_VOLUME + " REAL, "
            + DrinksTable.DRINKSTABLE_COLUMN_IMAGE + " TEXT, "
            + DrinksTable.DRINKSTABLE_COLUMN_PERCENT + " REAL, "
            + DrinksTable.DRINKSTABLE_COLUMN_ISFROMTEMPLATE + " INTEGER "
            + ")";

    public DrinksTable() {
    }
}

class DrinksTemplatesTable implements BaseColumns {
    public static final String TABLE_NAME = "drinkstemplates";
    public static final String DRINKSTEMPLATESTABLE_COLUMN_NAME = "name";
    public static final String DRINKSTEMPLATESTABLE_COLUMN_PERCENT = "percent";
    public static final String DRINKSTEMPLATESTABLE_COLUMN_VOLUME = "volume";
    public static final String DRINKSTEMPLATESTABLE_COLUMN_IMAGE = "image";

    public static final String DRINKSTEMPLATESTABLE_CREATE =
            "CREATE TABLE " + DrinksTemplatesTable.TABLE_NAME + "( "
                    + DrinksTemplatesTable._ID + " INTEGER PRIMARY KEY, "
                    + DrinksTemplatesTable.DRINKSTEMPLATESTABLE_COLUMN_NAME + " TEXT, "
                    + DrinksTemplatesTable.DRINKSTEMPLATESTABLE_COLUMN_VOLUME + " REAL, "
                    + DrinksTemplatesTable.DRINKSTEMPLATESTABLE_COLUMN_IMAGE + " TEXT, "
                    + DrinksTemplatesTable.DRINKSTEMPLATESTABLE_COLUMN_PERCENT + " REAL "
                    + ")";

    public DrinksTemplatesTable() {
    }
}