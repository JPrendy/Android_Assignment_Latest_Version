//Android Assignment
//James Prendergast
//C13446122
//27_11_2015

//this is the name of the Package of the Assignment
package ie.dit.james.android_assignment_latest_version;


        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import java.util.ArrayList;
        import java.util.HashMap;


//this is the Database class that allows us to make database transactions
public class DatabaseManager extends SQLiteOpenHelper {

    //this is our constructor for our database that adds the name of our database and our version number
    public DatabaseManager(Context applicationContext){
        super(applicationContext, "movie.db", null, 8);
    }

    //this calls for the database tables to be created
    @Override
    public void onCreate(SQLiteDatabase database) {

        //thus creates the table movies with the following filds
        String query = "CREATE TABLE movies ( movieid INTEGER PRIMARY KEY, MovieTitle TEXT, " +
                "Director TEXT, Starring TEXT, Genre TEXT, RunTime INTEGER, Comments TEXT)";

        //this will execute the Create Table statement for our database
        database.execSQL(query);
    }

    //this is for whenever we update the database, it drops the table of the old if it exist
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS movies";
        database.execSQL(query);
        onCreate(database);
    }

    //this allows us to insert our hashmap string values into our database
    public void insertMovie(HashMap<String, String> queryValues){
        //this  calls for the database to be writable
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //this inserts the string values that were in the hashmaps into the fields for the insert table
        values.put("MovieTitle", queryValues.get("MovieTitle"));
        values.put("Director", queryValues.get("Director"));
        values.put("Starring", queryValues.get("Starring"));
        values.put("Genre", queryValues.get("Genre"));
        values.put("RunTime", queryValues.get("RunTime"));
        values.put("Comments", queryValues.get("Comments"));

        //this inserts values into the database
        database.insert("movies", null, values);
        database.close();  //this closes the database
    }

    //this is a method is called when we update our database using our hashmap values
    public int updateMovie(HashMap<String, String> queryValues){
        //this  calls for the database to be writable
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //this inserts the string values that were in the hashmaps into the fields for the update table
        values.put("MovieTitle", queryValues.get("MovieTitle"));
        values.put("Director", queryValues.get("Director"));
        values.put("Starring", queryValues.get("Starring"));
        values.put("Genre", queryValues.get("Genre"));
        values.put("RunTime", queryValues.get("RunTime"));
        values.put("Comments", queryValues.get("Comments"));

        //-----------Reference the Following code is from android example at "Android for Programmers An App-Driven Approach
        return database.update("movies", values, "movieid" + " = ?", new String[] {queryValues.get("movieid") });
        //----------Reference Complete
    }

    //this is a method is called whenever we delete a table from our database
    public void DeleteMovie(String id){
        //this  calls for the databse to be writeable
        SQLiteDatabase database = this.getWritableDatabase();
        //this deletes the movieid that matches the string
        String deleteQuery = "DELETE FROM movies WHERE movieid='" + id + "'";
        database.execSQL(deleteQuery); //this executes the query
    }


    //this gets all our contacts returned to us in a ArrayList with a HashMap storing
    //the strings
    //-----------Reference the Following code is from the book Android App Development for Dummies
    public ArrayList<HashMap<String, String>> getAllMovies(){
        ArrayList<HashMap<String, String>> movieArrayList = new ArrayList<HashMap<String, String>>();
        //this is a selectquery that returns everything from the movie table in order of the Movie Title alphabetically
        String selectQuery = "SELECT * FROM movies ORDER BY MovieTitle";
        //this  calls for the database to be writable
        SQLiteDatabase database = this.getWritableDatabase();
        //this gets the cursor of data of the selected query
        Cursor cursor = database.rawQuery(selectQuery, null);
        //----------Reference Complete

       //this moves the cursor to the first row of data
        if(cursor.moveToFirst()){
            do{
                //this displays all the information in this cursor row using a hashmap to store the values
                HashMap<String, String> movieMap = new HashMap<String, String>();
                movieMap.put("movieid", cursor.getString(0));
                movieMap.put("MovieTitle", cursor.getString(1));
                movieMap.put("Director", cursor.getString(2));
                movieMap.put("Starring", cursor.getString(3));
                movieMap.put("Genre", cursor.getString(4));
                movieMap.put("RunTime", cursor.getString(5));
                movieMap.put("Comments", cursor.getString(6));
                movieArrayList.add(movieMap);//this is added to our Arraylist

            } while(cursor.moveToNext());//this moves to the next row of data

        }

        return movieArrayList; //this returns our arraylist with our cursor rows information
    }


    //this method returns only one row of cursor information to a hashmap
    public HashMap<String, String> getMovieInfo(String id){

        HashMap<String, String> movieMap = new HashMap<String, String>();
        //this  calls for the database to be readablr
        SQLiteDatabase database = this.getReadableDatabase();
        //this is a selectquery that returns everything from a specific movie table
        String selectQuery = "SELECT * FROM movies WHERE movieid='" + id + "'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        //this moves the cursor to the first row of data
        if(cursor.moveToFirst()){
            do{
                //this displays all the information in this cursor row
                movieMap.put("movieid", cursor.getString(0));
                movieMap.put("MovieTitle", cursor.getString(1));
                movieMap.put("Director", cursor.getString(2));
                movieMap.put("Starring", cursor.getString(3));
                movieMap.put("Genre", cursor.getString(4));
                movieMap.put("RunTime", cursor.getString(5));
                movieMap.put("Comments", cursor.getString(6));


            } while(cursor.moveToNext());//this moves to the next row of data
        }
        return movieMap;//this returns the value of that table
    }
}