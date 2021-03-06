package pl.byd.wsg.promand.project5.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import pl.byd.wsg.promand.project5.model.ExpenseEntry;

/**
 * Created by Le on 3/17/14.
 */
public class DataSource {

    /*the idea of DataSource will be that any activity in the application can open
    or close the database connection anytime it wants to
     */
    SQLiteOpenHelper dbHelper; //
    SQLiteDatabase database;

    private static final String[] allColumns={
            DatabaseOpenHelper.COLUMN_ID,
            DatabaseOpenHelper.COLUMN_PROJECT,
            DatabaseOpenHelper.COLUMN_CATEGORY,
            DatabaseOpenHelper.COLUMN_AMOUNT,
            DatabaseOpenHelper.COLUMN_DATE,
            DatabaseOpenHelper.COLUMN_COMMENT,
            DatabaseOpenHelper.COLUMN_PHOTO
    };

    public DataSource(Context context){
        //create an instance of DatabaseOpenHelper class
        dbHelper =new DatabaseOpenHelper(context);
    }
    public void open(){
        //create instance of writable database so that we can modify our database through this instance
        //open database connection
        database= dbHelper.getWritableDatabase();
    }

    public void close(){
        database.close();
    }

    public ExpenseEntry create(ExpenseEntry expenseEntry){
        //create instance of ContentValues which act like a map to store our key-value pair
        //I'm not putting a value in for the ID. That's because for this table, the key is an auto-incrementing integer value. It will be generated automatically
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseOpenHelper.COLUMN_PROJECT,expenseEntry.getProject());
        contentValues.put(DatabaseOpenHelper.COLUMN_CATEGORY,expenseEntry.getCategory());
        contentValues.put(DatabaseOpenHelper.COLUMN_AMOUNT,expenseEntry.getAmount());
        contentValues.put(DatabaseOpenHelper.COLUMN_DATE,expenseEntry.getDate());
        contentValues.put(DatabaseOpenHelper.COLUMN_COMMENT,expenseEntry.getComment());
        contentValues.put(DatabaseOpenHelper.COLUMN_PHOTO,expenseEntry.getPhoto());
        long insertId=database.insert(DatabaseOpenHelper.TABLE_EXPENSE,null,contentValues);
        expenseEntry.setId(insertId);
        return expenseEntry;
    }

    public List<ExpenseEntry> findAll(){
        //Cursor is a reference to the data that's returned from the query
        Cursor cursor=database.query(DatabaseOpenHelper.TABLE_EXPENSE,allColumns,null,null,null,null,null);
        List<ExpenseEntry> expenseEntryList = cursorToList(cursor);
        return expenseEntryList;
    }

    public List<ExpenseEntry> findFiltered(String selection, String orderBy){
        //Cursor is a reference to the data that's returned from the query
        Cursor cursor=database.query(DatabaseOpenHelper.TABLE_EXPENSE,allColumns,selection,null,null,null,orderBy);
        List<ExpenseEntry> expenseEntryList = cursorToList(cursor);
        return expenseEntryList;
    }

    private List<ExpenseEntry> cursorToList(Cursor cursor) {
        List<ExpenseEntry> expenseEntryList=new ArrayList<ExpenseEntry>();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                ExpenseEntry expenseEntry=new ExpenseEntry();
                expenseEntry.setId(cursor.getLong(cursor.getColumnIndex(DatabaseOpenHelper.COLUMN_ID)));
                expenseEntry.setProject(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.COLUMN_PROJECT)));
                expenseEntry.setCategory(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.COLUMN_CATEGORY)));
                expenseEntry.setAmount(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.COLUMN_AMOUNT)));
                expenseEntry.setDate(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.COLUMN_DATE)));
                expenseEntry.setComment(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.COLUMN_COMMENT)));
                expenseEntry.setPhoto(cursor.getBlob(cursor.getColumnIndex(DatabaseOpenHelper.COLUMN_PHOTO)));

                expenseEntryList.add(expenseEntry);
            }
        }
        return expenseEntryList;
    }

    public boolean removeEntry(ExpenseEntry expenseEntry){
        String where=DatabaseOpenHelper.COLUMN_ID + "=" + expenseEntry.getId();
        int result = database.delete(DatabaseOpenHelper.TABLE_EXPENSE,where,null);
        return (result==1);
    }

    public Float sumAllMeal(String date_from, String date_to){

        float columntotal = 0;
        float total_sum = 0;

        Cursor cursor1 = database.rawQuery("SELECT SUM(" + DatabaseOpenHelper.COLUMN_AMOUNT + ") FROM expense WHERE category = 'Breakfast' " + "AND " + DatabaseOpenHelper.COLUMN_DATE + " >= '" + date_from + "' AND " + DatabaseOpenHelper.COLUMN_DATE + " <= '" + date_to + "'", null);
        if(cursor1.moveToFirst()) {
            columntotal = cursor1.getFloat(0);
        }
        total_sum += columntotal;
        cursor1.close();

        Cursor cursor2 = database.rawQuery("SELECT SUM(" + DatabaseOpenHelper.COLUMN_AMOUNT + ") FROM expense WHERE category = 'Lunch' " + "AND " + DatabaseOpenHelper.COLUMN_DATE + " >= '" + date_from + "' AND " + DatabaseOpenHelper.COLUMN_DATE + " <= '" + date_to + "'", null);
        if(cursor2.moveToFirst()) {
            columntotal = cursor2.getFloat(0);
        }
        total_sum += columntotal;
        cursor2.close();

        Cursor cursor3 = database.rawQuery("SELECT SUM(" + DatabaseOpenHelper.COLUMN_AMOUNT + ") FROM expense WHERE category = 'Dinner' " + "AND " + DatabaseOpenHelper.COLUMN_DATE + " >= '" + date_from + "' AND " + DatabaseOpenHelper.COLUMN_DATE + " <= '" + date_to + "'", null);
        if(cursor3.moveToFirst()) {
            columntotal = cursor3.getFloat(0);
        }
        total_sum += columntotal;
        cursor3.close();

        Cursor cursor4 = database.rawQuery("SELECT SUM(" + DatabaseOpenHelper.COLUMN_AMOUNT + ") FROM expense WHERE category = 'Half board' " + "AND " + DatabaseOpenHelper.COLUMN_DATE + " >= '" + date_from + "' AND " + DatabaseOpenHelper.COLUMN_DATE + " <= '" + date_to + "'", null);
        if(cursor4.moveToFirst()) {
            columntotal = cursor4.getFloat(0);
        }
        total_sum += columntotal;
        cursor4.close();

        Cursor cursor5 = database.rawQuery("SELECT SUM(" + DatabaseOpenHelper.COLUMN_AMOUNT + ") FROM expense WHERE category = 'Full board' " + "AND " + DatabaseOpenHelper.COLUMN_DATE + " >= '" + date_from + "' AND " + DatabaseOpenHelper.COLUMN_DATE + " <= '" + date_to + "'", null);
        if(cursor5.moveToFirst()) {
            columntotal = cursor5.getFloat(0);
        }
        total_sum += columntotal;
        cursor5.close();

        //Float  sumtotal = (float)columntotal;

        return total_sum;
    }

    public Float sumAllTransport(String date_from, String date_to){

        float columntotal = 0;
        float total_sum = 0;

        Cursor cursor1 = database.rawQuery("SELECT SUM(" + DatabaseOpenHelper.COLUMN_AMOUNT + ") FROM expense WHERE category = 'Plane' " + "AND " + DatabaseOpenHelper.COLUMN_DATE + " >= '" + date_from + "' AND " + DatabaseOpenHelper.COLUMN_DATE + " <= '" + date_to + "'", null);
        if(cursor1.moveToFirst()) {
            columntotal = cursor1.getFloat(0);
        }
        total_sum += columntotal;
        cursor1.close();

        Cursor cursor2 = database.rawQuery("SELECT SUM(" + DatabaseOpenHelper.COLUMN_AMOUNT + ") FROM expense WHERE category = 'Train' " + "AND " + DatabaseOpenHelper.COLUMN_DATE + " >= '" + date_from + "' AND " + DatabaseOpenHelper.COLUMN_DATE + " <= '" + date_to + "'", null);
        if(cursor2.moveToFirst()) {
            columntotal = cursor2.getFloat(0);
        }
        total_sum += columntotal;
        cursor2.close();

        Cursor cursor3 = database.rawQuery("SELECT SUM(" + DatabaseOpenHelper.COLUMN_AMOUNT + ") FROM expense WHERE category = 'Bus' " + "AND " + DatabaseOpenHelper.COLUMN_DATE + " >= '" + date_from + "' AND " + DatabaseOpenHelper.COLUMN_DATE + " <= '" + date_to + "'", null);
        if(cursor3.moveToFirst()) {
            columntotal = cursor3.getFloat(0);
        }
        total_sum += columntotal;
        cursor3.close();

        Cursor cursor4 = database.rawQuery("SELECT SUM(" + DatabaseOpenHelper.COLUMN_AMOUNT + ") FROM expense WHERE category = 'Taxi' " + "AND " + DatabaseOpenHelper.COLUMN_DATE + " >= '" + date_from + "' AND " + DatabaseOpenHelper.COLUMN_DATE + " <= '" + date_to + "'", null);
        if(cursor4.moveToFirst()) {
            columntotal = cursor4.getFloat(0);
        }
        total_sum += columntotal;
        cursor4.close();

        Cursor cursor5 = database.rawQuery("SELECT SUM(" + DatabaseOpenHelper.COLUMN_AMOUNT + ") FROM expense WHERE category = 'Public Transport'", null);
        if(cursor5.moveToFirst()) {
            columntotal = cursor5.getFloat(0);
        }
        total_sum += columntotal;
        cursor5.close();

        //Float  sumtotal = (float)columntotal;

        return total_sum;
    }

    public Float sumAllCompanyCar(String date_from, String date_to){

        float columntotal = 0;
        float total_sum = 0;

        Cursor cursor1 = database.rawQuery("SELECT SUM(" + DatabaseOpenHelper.COLUMN_AMOUNT + ") FROM expense WHERE category = 'Diesel fuel' " + "AND " + DatabaseOpenHelper.COLUMN_DATE + " >= '" + date_from + "' AND " + DatabaseOpenHelper.COLUMN_DATE + " <= '" + date_to + "'", null);
        if(cursor1.moveToFirst()) {
            columntotal = cursor1.getFloat(0);
        }
        total_sum += columntotal;
        cursor1.close();

        Cursor cursor2 = database.rawQuery("SELECT SUM(" + DatabaseOpenHelper.COLUMN_AMOUNT + ") FROM expense WHERE category = 'Petrol fuel' " + "AND " + DatabaseOpenHelper.COLUMN_DATE + " >= '" + date_from + "' AND " + DatabaseOpenHelper.COLUMN_DATE + " <= '" + date_to + "'", null);
        if(cursor2.moveToFirst()) {
            columntotal = cursor2.getFloat(0);
        }
        total_sum += columntotal;
        cursor2.close();

        Cursor cursor3 = database.rawQuery("SELECT SUM(" + DatabaseOpenHelper.COLUMN_AMOUNT + ") FROM expense WHERE category = 'Vehicle maintenance' " + "AND " + DatabaseOpenHelper.COLUMN_DATE + " >= '" + date_from + "' AND " + DatabaseOpenHelper.COLUMN_DATE + " <= '" + date_to + "'", null);
        if(cursor3.moveToFirst()) {
            columntotal = cursor3.getFloat(0);
        }
        total_sum += columntotal;
        cursor3.close();

        Cursor cursor4 = database.rawQuery("SELECT SUM(" + DatabaseOpenHelper.COLUMN_AMOUNT + ") FROM expense WHERE category = 'Oil Change' " + "AND " + DatabaseOpenHelper.COLUMN_DATE + " >= '" + date_from + "' AND " + DatabaseOpenHelper.COLUMN_DATE + " <= '" + date_to + "'", null);
        if(cursor4.moveToFirst()) {
            columntotal = cursor4.getFloat(0);
        }
        total_sum += columntotal;
        cursor4.close();

        Cursor cursor5 = database.rawQuery("SELECT SUM(" + DatabaseOpenHelper.COLUMN_AMOUNT + ") FROM expense WHERE category = 'Tires' " + "AND " + DatabaseOpenHelper.COLUMN_DATE + " >= '" + date_from + "' AND " + DatabaseOpenHelper.COLUMN_DATE + " <= '" + date_to + "'", null);
        if(cursor5.moveToFirst()) {
            columntotal = cursor5.getFloat(0);
        }
        total_sum += columntotal;
        cursor5.close();

        Cursor cursor6 = database.rawQuery("SELECT SUM(" + DatabaseOpenHelper.COLUMN_AMOUNT + ") FROM expense WHERE category = 'Vehicle tune-up' " + "AND " + DatabaseOpenHelper.COLUMN_DATE + " >= '" + date_from + "' AND " + DatabaseOpenHelper.COLUMN_DATE + " <= '" + date_to + "'", null);
        if(cursor6.moveToFirst()) {
            columntotal = cursor6.getFloat(0);
        }
        total_sum += columntotal;
        cursor6.close();

        Cursor cursor7 = database.rawQuery("SELECT SUM(" + DatabaseOpenHelper.COLUMN_AMOUNT + ") FROM expense WHERE category = 'Other' " + "AND " + DatabaseOpenHelper.COLUMN_DATE + " >= '" + date_from + "' AND " + DatabaseOpenHelper.COLUMN_DATE + " <= '" + date_to + "'", null);
        if(cursor7.moveToFirst()) {
            columntotal = cursor7.getFloat(0);
        }
        total_sum += columntotal;
        cursor7.close();

        Cursor cursor8 = database.rawQuery("SELECT SUM(" + DatabaseOpenHelper.COLUMN_AMOUNT + ") FROM expense WHERE category = 'Parking' " + "AND " + DatabaseOpenHelper.COLUMN_DATE + " >= '" + date_from + "' AND " + DatabaseOpenHelper.COLUMN_DATE + " <= '" + date_to + "'", null);
        if(cursor8.moveToFirst()) {
            columntotal = cursor8.getFloat(0);
        }
        total_sum += columntotal;
        cursor7.close();

        //Float  sumtotal = (float)columntotal;

        return total_sum;
    }

    public Float sumAllOfficeMaterials(String date_from, String date_to){

        float columntotal = 0;
        float total_sum = 0;

        Cursor cursor1 = database.rawQuery("SELECT SUM(" + DatabaseOpenHelper.COLUMN_AMOUNT + ") FROM expense WHERE category = 'Furniture' " + "AND " + DatabaseOpenHelper.COLUMN_DATE + " >= '" + date_from + "' AND " + DatabaseOpenHelper.COLUMN_DATE + " <= '" + date_to + "'", null);
        if(cursor1.moveToFirst()) {
            columntotal = cursor1.getFloat(0);
        }
        total_sum += columntotal;
        cursor1.close();

        Cursor cursor2 = database.rawQuery("SELECT SUM(" + DatabaseOpenHelper.COLUMN_AMOUNT + ") FROM expense WHERE category = 'Technical books and documents' " + "AND " + DatabaseOpenHelper.COLUMN_DATE + " >= '" + date_from + "' AND " + DatabaseOpenHelper.COLUMN_DATE + " <= '" + date_to + "'", null);
        if(cursor2.moveToFirst()) {
            columntotal = cursor2.getFloat(0);
        }
        total_sum += columntotal;
        cursor2.close();

        Cursor cursor3 = database.rawQuery("SELECT SUM(" + DatabaseOpenHelper.COLUMN_AMOUNT + ") FROM expense WHERE category = 'House products' " + "AND " + DatabaseOpenHelper.COLUMN_DATE + " >= '" + date_from + "' AND " + DatabaseOpenHelper.COLUMN_DATE + " <= '" + date_to + "'", null);
        if(cursor3.moveToFirst()) {
            columntotal = cursor3.getFloat(0);
        }
        total_sum += columntotal;
        cursor3.close();

        //Float  sumtotal = (float)columntotal;

        return total_sum;
    }

    public Float sumAllRepresentationExpenses(String date_from, String date_to){

        float columntotal = 0;
        float total_sum = 0;

        Cursor cursor1 = database.rawQuery("SELECT SUM(" + DatabaseOpenHelper.COLUMN_AMOUNT + ") FROM expense WHERE category = 'Groceries' " + "AND " + DatabaseOpenHelper.COLUMN_DATE + " >= '" + date_from + "' AND " + DatabaseOpenHelper.COLUMN_DATE + " <= '" + date_to + "'", null);
        if(cursor1.moveToFirst()) {
            columntotal = cursor1.getFloat(0);
        }
        total_sum += columntotal;
        cursor1.close();

        Cursor cursor2 = database.rawQuery("SELECT SUM(" + DatabaseOpenHelper.COLUMN_AMOUNT + ") FROM expense WHERE category = 'Utilities' " + "AND " + DatabaseOpenHelper.COLUMN_DATE + " >= '" + date_from + "' AND " + DatabaseOpenHelper.COLUMN_DATE + " <= '" + date_to + "'", null);
        if(cursor2.moveToFirst()) {
            columntotal = cursor2.getFloat(0);
        }
        total_sum += columntotal;
        cursor2.close();

        Cursor cursor3 = database.rawQuery("SELECT SUM(" + DatabaseOpenHelper.COLUMN_AMOUNT + ") FROM expense WHERE category = 'Cleaning Service' " + "AND " + DatabaseOpenHelper.COLUMN_DATE + " >= '" + date_from + "' AND " + DatabaseOpenHelper.COLUMN_DATE + " <= '" + date_to + "'", null);
        if(cursor3.moveToFirst()) {
            columntotal = cursor3.getFloat(0);
        }
        total_sum += columntotal;
        cursor3.close();

        //Float  sumtotal = (float)columntotal;

        return total_sum;
    }

    public Float sumAllProject(String project_name, String date_from, String date_to){

        float columntotal = 0;

        Cursor cursor1 = database.rawQuery("SELECT SUM(" + DatabaseOpenHelper.COLUMN_AMOUNT + ") FROM expense WHERE project = '" + project_name +  "' AND " + DatabaseOpenHelper.COLUMN_DATE + " >= '" + date_from + "' AND " + DatabaseOpenHelper.COLUMN_DATE + " <= '" + date_to + "'", null);
        if(cursor1.moveToFirst()) {
            columntotal = cursor1.getFloat(0);
        }
        cursor1.close();

        return columntotal;
    }

}
