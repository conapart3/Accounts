package com.tndo.multiaccounts.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tndo.multiaccounts.account.Subaccount;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Conal on 16/04/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = Constants.DB_NAME;

    private final String SQL_CREATE_MAINACCOUNTS = "CREATE TABLE " + Constants.TABLE_MAINACCOUNTS + " (" + Constants.COLUMN_ID + " INTEGER PRIMARY KEY, " + Constants.COLUMN_NAME + " varchar(32), " + Constants.COLUMN_BALANCE + " REAL);";
    private final String SQL_CREATE_SUBACCOUNTS = "CREATE TABLE " + Constants.TABLE_SUBACCOUNTS + " (" + Constants.COLUMN_ID + " INTEGER PRIMARY KEY, " + Constants.COLUMN_NAME + " varchar(32), " + Constants.COLUMN_BALANCE + " REAL, " + Constants.COLUMN_MAIN_ACCOUNT + " varchar(32), " + Constants.COLUMN_ICON + " varchar(32), FOREIGN KEY(" + Constants.COLUMN_MAIN_ACCOUNT + ") REFERENCES " + Constants.TABLE_MAINACCOUNTS + "(" + Constants.COLUMN_NAME + "));";
    private final String SQL_DROP_ALL = "DROP TABLE IF EXISTS " + Constants.TABLE_MAINACCOUNTS + "; DROP TABLE IF EXISTS " + Constants.TABLE_SUBACCOUNTS + ";";

    public DatabaseHelper( Context context )
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate( SQLiteDatabase db )
    {
        db.execSQL(SQL_CREATE_MAINACCOUNTS);
        db.execSQL(SQL_CREATE_SUBACCOUNTS);
    }

    /**
     * Temporary basis we just delete ALL data and create new db.
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion )
    {
        db.execSQL(SQL_DROP_ALL);
        onCreate(db);
    }

    /**
     * Add a main account
     *
     * @param name
     * @param balance
     */
    public long addMainAccount( final String name, final double balance )
    {
        // Get the database in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Constants.COLUMN_NAME, name);
        values.put(Constants.COLUMN_BALANCE, balance);

        // Insert the new row returning the primary key value of the new row
        final long newRowId = db.insert(Constants.TABLE_MAINACCOUNTS, null, values);

        // Close any open databases.
        close();
        return newRowId;
    }

    /**
     * Add a sub account
     *
     * @param name
     * @param balance
     */
    public long addSubAccount( final String name, final Double balance, final String mainAccountName, final String icon )
    {
        // Get the database in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Constants.COLUMN_NAME, name);
        values.put(Constants.COLUMN_BALANCE, balance);
        values.put(Constants.COLUMN_MAIN_ACCOUNT, mainAccountName);
        values.put(Constants.COLUMN_ICON, icon);

        // Insert the new row returning the primary key value of the new row
        final long newRowId = db.insert(Constants.TABLE_SUBACCOUNTS, null, values);

        // Close any open databases.
        close();
        return newRowId;
    }

    public List<Subaccount> getSubAccounts( final String mainaccount )
    {
        // Get the database in read mode
        SQLiteDatabase db = getReadableDatabase();

        List<Subaccount> subaccountList = new ArrayList<>();

        // The columns we will actually use and want to be returned
        String[] projection = {Constants.COLUMN_ID, Constants.COLUMN_NAME, Constants.COLUMN_BALANCE, Constants.COLUMN_MAIN_ACCOUNT, Constants.COLUMN_ICON};

        // Set up the selection and args
        String selection = Constants.COLUMN_MAIN_ACCOUNT + " = ?";
        String[] selectionArgs = {mainaccount};
        String sortOrder = Constants.COLUMN_NAME + " DESC";

        Cursor cursor = db.query(
                Constants.TABLE_SUBACCOUNTS,    //The table to query
                projection,                     // The columns to return
                selection,                      // The columns for the WHERE clause
                selectionArgs,                  // The values for the WHERE clause
                null,                           // don't group the rows
                null,                           // don't filter by row groups
                sortOrder                       // the sort order
        );

        while ( cursor.moveToNext() ) {
            Subaccount subacc = new Subaccount(
                    cursor.getInt(cursor.getColumnIndexOrThrow(Constants.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_NAME)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(Constants.COLUMN_BALANCE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_ICON)),
                    cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_MAIN_ACCOUNT))
            );
            subaccountList.add(subacc);
        }
        cursor.close();
        return subaccountList;
    }
}
