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
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import ie.dit.james.android_assignment_latest_version.DBTools;


public class Edit_Movie extends Activity {


    EditText MovieTitle;
    EditText Director;
    EditText Starring;
    EditText Genre;
    EditText RunTime;
    EditText Comments;


    DBTools dbTools = new DBTools(this);

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_movie);


        MovieTitle = (EditText) findViewById(R.id.MovieTitle);
        Director = (EditText) findViewById(R.id.Director);
        Starring = (EditText) findViewById(R.id.Starring);
        Genre = (EditText) findViewById(R.id.Genre);
        RunTime = (EditText) findViewById(R.id.RunTime);
        Comments = (EditText) findViewById(R.id.Comments);

        Intent theIntent = getIntent();



        String contactId = theIntent.getStringExtra("contactId");



        HashMap<String, String> contactList = dbTools.getContactInfo(contactId);

        if(contactList.size()!=0)

        {


            MovieTitle.setText(contactList.get("MovieTitle"));
            Director.setText(contactList.get("Director"));
            Starring.setText(contactList.get("Starring"));
            Genre.setText(contactList.get("Genre"));
            RunTime.setText(contactList.get("RunTime"));
            Comments.setText(contactList.get("Comments"));

        }


    }

    public void editContact(View view) {

        HashMap<String, String> queryValuesMap = new HashMap<String, String>();

        MovieTitle = (EditText) findViewById(R.id.MovieTitle);
        Director = (EditText) findViewById(R.id.Director);
        Starring = (EditText) findViewById(R.id.Starring);
        Genre = (EditText) findViewById(R.id.Genre);
        RunTime = (EditText) findViewById(R.id.RunTime);
        Comments = (EditText) findViewById(R.id.Comments);

        Intent theIntent = getIntent();

        String contactId = theIntent.getStringExtra("contactId");

        queryValuesMap.put("contactId", contactId);


        queryValuesMap.put("MovieTitle", MovieTitle.getText().toString());
        queryValuesMap.put("Director", Director.getText().toString());
        queryValuesMap.put("Starring", Starring.getText().toString());
        queryValuesMap.put("Genre", Genre.getText().toString());
        queryValuesMap.put("RunTime", RunTime.getText().toString());
        queryValuesMap.put("Comments", Comments.getText().toString());
        dbTools.updateContact(queryValuesMap);


        this.callMainActivity(view);

    }

    //work on this for the load url
    //this calls the method onclick removeContact when the "delete movie was pressed"
    public void removeContact(View view) {

        Intent theIntent = getIntent();

        String contactId = theIntent.getStringExtra("contactId");

        dbTools.deleteContact(contactId);

        this.callMainActivity(view);

    }

    public void callMainActivity(View view) {

        Intent objIntent = new Intent(getApplication(), MainActivity.class);

        startActivity(objIntent);

    }


    //this calls the method onclick Rotten when the "Go to RottenTomatoes" button is pressed
    public void Rotten(View v4) {

        Intent switchScreen = new Intent(v4.getContext(), WebPage.class);

        String movieIdValue = MovieTitle.getText().toString();

        startActivity(switchScreen);
        Intent theIntent = getIntent();
        String movie = theIntent.getStringExtra("MovieTitle");
        Intent new_intent=new Intent(this,WebPage.class);
        new_intent.putExtra("counter",1);
        new_intent.putExtra("MovieTitle", movieIdValue);

        startActivity(new_intent);

    }

    //this calls the method onclick Rotten when the "Go to AmazonPrime" button is pressed
    public void amazon(View v5) {

        Intent switchScreen = new Intent(v5.getContext(), WebPage.class);

        startActivity(switchScreen);


        String movieIdValue = MovieTitle.getText().toString();


        Intent theIntent = getIntent();
        String movie = theIntent.getStringExtra("MovieTitle");
        Intent new_intent=new Intent(this,WebPage.class);
        new_intent.putExtra("counter", 2);
        new_intent.putExtra("MovieTitle", movieIdValue);



        startActivity(new_intent);

    }

    public void imdb(View v6) {

        Intent switchScreen = new Intent(v6.getContext(), WebPage.class);

        startActivity(switchScreen);


        String movieIdValue = MovieTitle.getText().toString();


        Intent theIntent = getIntent();
        String movie = theIntent.getStringExtra("MovieTitle");
        Intent new_intent=new Intent(this,WebPage.class);
        new_intent.putExtra("counter", 3);
        new_intent.putExtra("MovieTitle", movieIdValue);



        startActivity(new_intent);

    }







}