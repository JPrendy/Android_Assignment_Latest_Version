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

//this is Edit_Movie java class that is called when the a listview is pressed, it extends Activity
public class Edit_Movie extends Activity {

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
    public void onCreate(Bundle savedInstanceState){
        //this gets any saved data, if the app was used before
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_movie);

        //This stores the following EditText objects into their new objects
        MovieTitle = (EditText) findViewById(R.id.MovieTitle);
        Director = (EditText) findViewById(R.id.Director);
        Starring = (EditText) findViewById(R.id.Starring);
        Genre = (EditText) findViewById(R.id.Genre);
        RunTime = (EditText) findViewById(R.id.RunTime);
        Comments = (EditText) findViewById(R.id.Comments);


        //this calls an intents retrieves the string value of movieid
        Intent theIntent = getIntent();
        String movieid = theIntent.getStringExtra("movieid");


        //values of movieid are stored in a hashmap and as long as the list soze is not empty we will
        //able set the haspmap values
        HashMap<String, String> MovieList = databaseManager.getMovieInfo(movieid);
        if(MovieList.size()!=0)
        {
            MovieTitle.setText(MovieList.get("MovieTitle"));
            Director.setText(MovieList.get("Director"));
            Starring.setText(MovieList.get("Starring"));
            Genre.setText(MovieList.get("Genre"));
            RunTime.setText(MovieList.get("RunTime"));
            Comments.setText(MovieList.get("Comments"));
        }
    }

    //this calls editMovie which allows us to edit tables to our database
    public void editMovie(View view) {
        //this sets up the fields to get the hashmap values
        HashMap<String, String> queryValuesMap = new HashMap<String, String>();
        MovieTitle = (EditText) findViewById(R.id.MovieTitle);
        Director = (EditText) findViewById(R.id.Director);
        Starring = (EditText) findViewById(R.id.Starring);
        Genre = (EditText) findViewById(R.id.Genre);
        RunTime = (EditText) findViewById(R.id.RunTime);
        Comments = (EditText) findViewById(R.id.Comments);

        //this calls the intent information
        Intent theIntent = getIntent();
        String movieid = theIntent.getStringExtra("movieid");

        ///we are putting in our new information into our table and passing this
        //information into our database and then move back to our Main Activity
        queryValuesMap.put("movieid", movieid);
        queryValuesMap.put("MovieTitle", MovieTitle.getText().toString());
        queryValuesMap.put("Director", Director.getText().toString());
        queryValuesMap.put("Starring", Starring.getText().toString());
        queryValuesMap.put("Genre", Genre.getText().toString());
        queryValuesMap.put("RunTime", RunTime.getText().toString());
        queryValuesMap.put("Comments", Comments.getText().toString());
        databaseManager.updateMovie(queryValuesMap);
        this.callMainActivity(view);
    }


    //this calls the method onclick removeMovie when the "delete movie was pressed"
    public void removeMovie(View view) {
        //this passes in the movieid string to our database so we can
        //delete that table and then it switches back to MainActivity
        Intent theIntent = getIntent();
        String movieid = theIntent.getStringExtra("movieid");
        databaseManager.DeleteMovie(movieid);
        this.callMainActivity(view);
    }

    //this method brings us back to the Main Activity
    public void callMainActivity(View view) {
        Intent callIntent = new Intent(getApplication(), MainActivity.class);
        startActivity(callIntent);

    }


    //this calls the method onclick Rotten when the "Go to RottenTomatoes" button is pressed
    public void Rotten(View v4) {
        //this passes into the movietitle string name and the counter
        //value to webPage.class, which will help us load a specific
        //webpage
        Intent switchScreen = new Intent(v4.getContext(), WebPage.class);
        String movieIdValue = MovieTitle.getText().toString();
        startActivity(switchScreen);
        Intent theIntent = getIntent();
        Intent new_intent=new Intent(this,WebPage.class);
        new_intent.putExtra("counter",1);//counter has a value of 1
        new_intent.putExtra("MovieTitle", movieIdValue);
        startActivity(new_intent);//brings us over webpage.class
    }

    //this calls the method onclick Rotten when the "Go to AmazonPrime" button is pressed
    public void amazon(View v5) {
        //this passes into the movietitle string name and the counter
        //value to webPage.class, which will help us load a specific
        //webpage
        Intent switchScreen = new Intent(v5.getContext(), WebPage.class);
        startActivity(switchScreen);
        String movieIdValue = MovieTitle.getText().toString();
        Intent theIntent = getIntent();
        Intent new_intent=new Intent(this,WebPage.class);
        new_intent.putExtra("counter", 2);//counter has a value of 2
        new_intent.putExtra("MovieTitle", movieIdValue);
        startActivity(new_intent);//brings us over webpage.class
    }

    public void imdb(View v6) {
        //this passes into the movietitle string name and the counter
        //value to webPage.class, which will help us load a specific
        //webpage
        String movieIdValue = MovieTitle.getText().toString();
        Intent theIntent = getIntent();
        Intent new_intent=new Intent(this,WebPage.class);
        new_intent.putExtra("counter", 3);//counter has a value of 4
        new_intent.putExtra("MovieTitle", movieIdValue);
        startActivity(new_intent);//brings us over webpage.class
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