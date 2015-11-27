package ie.dit.james.android_assignment_latest_version;

import android.app.ListActivity;

//Android Assignment
//James Prendergast
//C13446122
//27_11_2015



        import android.app.ListActivity;
        import android.content.Intent;
        import android.graphics.Movie;
        import android.media.AudioManager;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.ListAdapter;
        import android.widget.ListView;
        import android.widget.SimpleAdapter;
        import android.widget.TableRow;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.HashMap;

import ie.dit.james.android_assignment_latest_version.DBTools;
import ie.dit.james.android_assignment_latest_version.New_Movie;
import ie.dit.james.android_assignment_latest_version.R;
import ie.dit.james.android_assignment_latest_version.RingerHelper;

//this is the first java class that is called when the app is running extends ListActivity
public class MainActivity extends ListActivity {


    TextView movieid; ///this creates the TextView Variable movieid
    TextView MovieTitle;
    int i = 0;  //this creates an integer variable and stores zero as it value

    AudioManager audioManager;

    // The object that allows me to modify my database with theDBTools class
    DBTools dbTools = new DBTools(this);

    // Called when the Activity is first called
    protected void onCreate(Bundle savedInstanceState) {

        //this gets any saved data, if the app was used before
        super.onCreate(savedInstanceState);

        //this sets the view of the first screen of the to activity_main.xml
        setContentView(R.layout.activity_main);

        //this retrieves the audio services
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);


        //this initialises the following xml id objects so they can be manipulated in MainActivity
        final TableRow contentView0 = (TableRow) findViewById(R.id.tableRow1);
        final TableRow contentView = (TableRow) findViewById(R.id.tableRow1_5);
        final TableRow contentView2 = (TableRow) findViewById(R.id.tableRow2);
        final TableRow contentView3 = (TableRow) findViewById(R.id.tableRow3);


        contentView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                RingerHelper.performToggle(audioManager);
                updateUi();
                Toast.makeText(getApplicationContext(), "Silent Setting Changed",
                        Toast.LENGTH_SHORT).show();
            }
        });


        final Button button1 = (Button) findViewById(R.id.button2);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {

                i++;

                if (i % 2 == 0)

                {
                    //-----------Refernce the Following code is from android example at http://stackoverflow.com/questions/2173936/how-to-set-background-color-of-a-view
                    button1.setBackgroundColor(0xff616161);
                    contentView0.setBackgroundColor(0xff616161);
                    contentView.setBackgroundColor(0xff616161);
                    contentView3.setBackgroundColor(0xff757575);
                    contentView2.setBackgroundColor(0xff9E9E9E);
                    // ok.setBackgroundColor(0xFFE0E0E0);

                    //----------Reference Complete
                    button1.setText("Dark Theme");
                    Toast.makeText(getApplicationContext(), "Dark Theme",
                            Toast.LENGTH_SHORT).show();
                } else if (i % 2 != 0) {
                    //-----------Reference the Following code is from android example at http://stackoverflow.com/questions/2173936/how-to-set-background-color-of-a-view
                    button1.setBackgroundColor(0xffBDBDBD);
                    contentView0.setBackgroundColor(0xffBDBDBD);
                    contentView.setBackgroundColor(0xffBDBDBD);
                    contentView3.setBackgroundColor(0xFFE0E0E0);
                    contentView2.setBackgroundColor(0xFFEEEEEE);
                    // ok.setBackgroundColor(0xFFFAFAFA);
                    //----------Reference Complete
                    button1.setText("Light Theme");
                    Toast.makeText(getApplicationContext(), "Light Theme",
                            Toast.LENGTH_SHORT).show();
                }


            }


        });


        // Gets all the data from the database and stores it
        // in an ArrayList

        ArrayList<HashMap<String, String>> contactList = dbTools.getAllContacts();

        // Check to make sure there are contacts to display

        if (contactList.size() != 0) {

            // Get the ListView and assign an event handler to it

            ListView listView = getListView();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    // When an item is clicked get the TextView
                    // with a matching checkId

                    movieid = (TextView) view.findViewById(R.id.movieid);
                    MovieTitle = (TextView) view.findViewById(R.id.MovieTitle);

                    // Convert that movieid into a String

                    String movieidValue = movieid.getText().toString();
                    String movieIdValue = MovieTitle.getText().toString();


                    // Signals an intention to do something
                    // getApplication() returns the application that owns
                    // this activity

                    Intent theIndent = new Intent(getApplication(), Edit_Movie.class);

                    // Put additional data in for EditContact to use

                    theIndent.putExtra("movieid", movieidValue);
                    theIndent.putExtra("MovieTitle", movieIdValue);

                    // Calls for EditContact

                    startActivity(theIndent);



                }
            });



            ListAdapter adapter = new SimpleAdapter(MainActivity.this, contactList, R.layout.movie_list, new String[]{"movieid", "MovieTitle","Director"}, new int[]{R.id.movieid, R.id.MovieTitle, R.id.Director});

            // setListAdapter provides the Cursor for the ListView
            // The Cursor provides access to the database data

            setListAdapter(adapter);
        }
    }

    // When showAddContact is called with a click the Activity
    // NewContact is called

    public void showAddContact(View view) {
        Intent theIntent = new Intent(getApplicationContext(), New_Movie.class);
        startActivity(theIntent);
    }




    private void updateUi() {
        //-----------Reference the Following code is from the book Android App Development for Dummie
        ImageView imageView = (ImageView) findViewById(R.id.phone_icon);


        int phoneImage = RingerHelper.isPhoneSilent(audioManager)
                ? R.drawable.ringer_on_smaller_6
                : R.drawable.ringer_off_smaller_5;

        imageView.setImageResource(phoneImage);
        //----------Reference Complete

    }


    protected void onResume() {

        super.onResume();
        updateUi();
        i++;
        Toast.makeText(getApplicationContext(), "Welcome Back",
                Toast.LENGTH_SHORT).show();
    }

    protected void onPause() {

        super.onPause();
    }


}