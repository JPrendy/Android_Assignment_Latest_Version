package ie.dit.james.android_assignment_latest_version;

//Android Assignment
//James Prendergast
//C13446122
//27_11_2015



        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import java.util.ArrayList;
        import java.util.HashMap;


//this is the Database class that deals with database transactions and its extends
//SQliteOpenHelper allows us to read and write to our database
public class DBTools extends SQLiteOpenHelper {

    //this is our constructor for DBTools that adds the name of our database and our version number
    public DBTools(Context applicationContext){

        super(applicationContext, "contactbook.db", null, 3);

    }

    //this calls for the database tables to be created
    @Override
    public void onCreate(SQLiteDatabase database) {


        String query = "CREATE TABLE contacts ( contactId INTEGER PRIMARY KEY, MovieTitle TEXT, " +
                "Director TEXT, Starring TEXT, Genre TEXT, RunTime INTEGER, Comments TEXT)";

        database.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXISTS contacts";

        database.execSQL(query);
        onCreate(database);

    }

    public void insertContact(HashMap<String, String> queryValues){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();



        values.put("MovieTitle", queryValues.get("MovieTitle"));
        values.put("Director", queryValues.get("Director"));
        values.put("Starring", queryValues.get("Starring"));
        values.put("Genre", queryValues.get("Genre"));
        values.put("RunTime", queryValues.get("RunTime"));
        values.put("Comments", queryValues.get("Comments"));

        database.insert("contacts", null, values);

        database.close();

    }

    public int updateContact(HashMap<String, String> queryValues){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put("MovieTitle", queryValues.get("MovieTitle"));
        values.put("Director", queryValues.get("Director"));
        values.put("Starring", queryValues.get("Starring"));
        values.put("Genre", queryValues.get("Genre"));
        values.put("RunTime", queryValues.get("RunTime"));
        values.put("Comments", queryValues.get("Comments"));

        return database.update("contacts", values,
                "contactId" + " = ?", new String[] {queryValues.get("contactId") });

    }

    public void deleteContact(String id){

        SQLiteDatabase database = this.getWritableDatabase();

        String deleteQuery = "DELETE FROM contacts WHERE contactId='" + id + "'";

        database.execSQL(deleteQuery);

    }

    public ArrayList<HashMap<String, String>> getAllContacts(){

        ArrayList<HashMap<String, String>> contactArrayList = new ArrayList<HashMap<String, String>>();

        String selectQuery = "SELECT * FROM contacts ORDER BY MovieTitle";

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){

            do{

                HashMap<String, String> contactMap = new HashMap<String, String>();



                contactMap.put("contactId", cursor.getString(0));
                contactMap.put("MovieTitle", cursor.getString(1));
                contactMap.put("Director", cursor.getString(2));
                contactMap.put("Starring", cursor.getString(3));
                contactMap.put("Genre", cursor.getString(4));
                contactMap.put("RunTime", cursor.getString(5));
                contactMap.put("Comments", cursor.getString(6));


                contactArrayList.add(contactMap);

            } while(cursor.moveToNext());

        }

        return contactArrayList;

    }

    public HashMap<String, String> getContactInfo(String id){

        HashMap<String, String> contactMap = new HashMap<String, String>();

        SQLiteDatabase database = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM contacts WHERE contactId='" + id + "'";

        Cursor cursor = database.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){

            do{


                contactMap.put("contactId", cursor.getString(0));
                contactMap.put("MovieTitle", cursor.getString(1));
                contactMap.put("Director", cursor.getString(2));
                contactMap.put("Starring", cursor.getString(3));
                contactMap.put("Genre", cursor.getString(4));
                contactMap.put("RunTime", cursor.getString(5));
                contactMap.put("Comments", cursor.getString(6));


            } while(cursor.moveToNext());

        }

        return contactMap;

    }

}