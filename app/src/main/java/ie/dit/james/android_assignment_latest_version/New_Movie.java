//Android Assignment
//James Prendergast
//C13446122
//27_11_2015

//this is the name of the Package of the Assignment
package ie.dit.james.android_assignment_latest_version;


        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import java.util.HashMap;

//this is New_Movie java class that is called when the add movie button is pressed, it extends Activity
public class New_Movie extends Activity {

    // These are EditText objects that allows you edit in your information for DatabaseManager
    EditText MovieTitle;
    EditText Director;
    EditText Starring;
    EditText Genre;
    EditText RunTime;
    EditText Comments;

    //This is the database method calling the Database class
    DatabaseManager databaseManager = new DatabaseManager(this);


    @Override
    //this is the onCreate method that sets the layout
    public void onCreate(Bundle savedInstanceState) {
        //this gets any saved data, if the app was used before
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_movie); //layout set add_movie.xm;

        //This stores the following EditText objects into their new objects
        MovieTitle = (EditText) findViewById(R.id.MovieTitle);
        Director = (EditText) findViewById(R.id.Director);
        Starring = (EditText) findViewById(R.id.Starring);
        Genre = (EditText) findViewById(R.id.Genre);
        RunTime = (EditText) findViewById(R.id.RunTime);
        Comments = (EditText) findViewById(R.id.Comments);
    }

//this calls addNewMovie which allows us to add tables to our database
    public void addNewMovie(View view) {

        //values of queryValuesMap are stored in a hashmap
        HashMap<String, String> queryValuesMap =  new  HashMap<String, String>();

        // This retrieves from the EditText boxes and adds their values into the hashmap as stringd
        queryValuesMap.put("MovieTitle", MovieTitle.getText().toString());
        queryValuesMap.put("Director", Director.getText().toString());
        queryValuesMap.put("Starring", Starring.getText().toString());
        queryValuesMap.put("Genre", Genre.getText().toString());
        queryValuesMap.put("RunTime", RunTime.getText().toString());
        queryValuesMap.put("Comments", Comments.getText().toString());

        //this inserts the hashmap into our database with a Call to switch over to MainActivity
        databaseManager.insertMovie(queryValuesMap);
        this.callMainActivity(view);
    }

    //this calls the method onclick callMainActivity when the "Save Movie" button is pressed
    public void callMainActivity(View view) {
        Intent theIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(theIntent);
    }

    //this resumes your activity from a paused stated
    protected void onResume() {
        super.onResume();
    }

    //this pauses your activity from a live state whenever you switch a screen or get a phonecall
    protected void onPause() {
        super.onPause();
    }
}