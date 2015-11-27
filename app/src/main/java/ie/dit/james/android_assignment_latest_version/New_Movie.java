package ie.dit.james.android_assignment_latest_version;

//Android Assignment
//James Prendergast
//C13446122
//27_11_2015

//this is the name of the Package of the Assignment


        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;

        import java.util.HashMap;


public class New_Movie extends Activity {

    // The EditText objects
    EditText MovieTitle;
    EditText Director;
    EditText Starring;
    EditText Genre;
    EditText RunTime;
    EditText Comments;

    //The database method calling the Database class
    DBTools dbTools = new DBTools(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {

        // Get saved data if there is any

        super.onCreate(savedInstanceState);

        // Designate that add_new_contact.xml is the interface used

        setContentView(R.layout.add_movie);

        // Initialize the EditText objects


        MovieTitle = (EditText) findViewById(R.id.MovieTitle);
        Director = (EditText) findViewById(R.id.Director);
        Starring = (EditText) findViewById(R.id.Starring);
        Genre = (EditText) findViewById(R.id.Genre);
        RunTime = (EditText) findViewById(R.id.RunTime);
        Comments = (EditText) findViewById(R.id.Comments);

    }
    public void addNewContact(View view) {

        // Will hold the HashMap of values

        HashMap<String, String> queryValuesMap =  new  HashMap<String, String>();

        // Get the values from the EditText boxes


        queryValuesMap.put("MovieTitle", MovieTitle.getText().toString());
        queryValuesMap.put("Director", Director.getText().toString());
        queryValuesMap.put("Starring", Starring.getText().toString());
        queryValuesMap.put("Genre", Genre.getText().toString());
        queryValuesMap.put("RunTime", RunTime.getText().toString());
        queryValuesMap.put("Comments", Comments.getText().toString());

        // Call for the HashMap to be added to the database

        dbTools.insertContact(queryValuesMap);

        // Call for MainActivity to execute

        this.callMainActivity(view);
    }
    public void callMainActivity(View view) {
        Intent theIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(theIntent);
    }
}