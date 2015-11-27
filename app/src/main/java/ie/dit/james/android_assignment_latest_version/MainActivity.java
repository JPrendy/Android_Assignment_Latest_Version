//Android Assignment
//James Prendergast
//C13446122
//27_11_2015

//this is the name of the Package of the Assignment
package ie.dit.james.android_assignment_latest_version;

        import android.app.ListActivity;
        import android.content.Intent;
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


//this is the first java class that is called when the app is running extends ListActivity
public class MainActivity extends ListActivity {


    TextView movieid; ///this creates the TextView Variable movieid
    TextView MovieTitle;///this creates the TextView Variable MovieTitle
    int i = 0;  //this creates an integer variable and stores zero as it value

    AudioManager audioManager;

    // The object that allows me to modify my database with theDBTools class
    DatabaseManager databaseManager = new DatabaseManager(this);

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
        final TableRow contentView4 = (TableRow) findViewById(R.id.tableRow4);
        final Button button1 = (Button) findViewById(R.id.button2);

        //this cause this method to be called when ever tableRow1_5 is clicked on
        contentView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //this toggles the sound settings and updates ui and display a message to show the changes
                RingerHelper.performToggle(audioManager);
                updateUi();
                Toast.makeText(getApplicationContext(), "Silent Setting Changed",
                        Toast.LENGTH_SHORT).show();
            }
        });

        //this cause this method to be called when ever button2 is clicked on
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                //whenever clicked it add +1 to i which we use to show the effects of the buttons
                String Dark = "Dark Theme";
                String Light = "Light Theme";
                i++;
                //if i is divisible by 2 and has no remainders it sets the following methods
                if (i % 2 == 0) {

                    button1.setBackgroundColor(0xff616161);
                    contentView0.setBackgroundColor(0xff616161);
                    contentView.setBackgroundColor(0xff616161);
                    contentView3.setBackgroundColor(0xff757575);
                    contentView4.setBackgroundColor(0xff757575);
                    contentView2.setBackgroundColor(0xff9E9E9E);
                    button1.setText(Dark);

                    Toast.makeText(getApplicationContext(), Dark,
                            Toast.LENGTH_SHORT).show();
                }
                //if i is divisible by 2 and has remainders it sets the following methods
                else if (i % 2 != 0) {

                    button1.setBackgroundColor(0xffBDBDBD);
                    contentView0.setBackgroundColor(0xffBDBDBD);
                    contentView.setBackgroundColor(0xffBDBDBD);
                    contentView3.setBackgroundColor(0xFFE0E0E0);
                    contentView4.setBackgroundColor(0xFFE0E0E0);
                    contentView2.setBackgroundColor(0xFFEEEEEE);
                    button1.setText(Light);

                    Toast.makeText(getApplicationContext(), Light,
                            Toast.LENGTH_SHORT).show();
                }
            }


        });


     //this creates an arraylist that stores all our database information in a string using a hashmap
        ArrayList<HashMap<String, String>> MovieList = databaseManager.getAllMovies();

        // makes sure to see if there is any data to display
        if (MovieList.size() != 0) {

            // This set a ListView and add OnItemClickListener to it
            ListView listView = getListView();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                     //this gets the data stored in the textview ids for movieid and movieTitle
                    movieid = (TextView) view.findViewById(R.id.movieid);
                    MovieTitle = (TextView) view.findViewById(R.id.MovieTitle);

                    // this converts this data into a String
                    String movieidValue = movieid.getText().toString();
                    String movieTitleValue = MovieTitle.getText().toString();

                    //it's going to return data to Edit_Movie.class
                    Intent theIntent = new Intent(getApplication(), Edit_Movie.class);

                    // This carries the tables values over Edit_Movie.class
                    theIntent.putExtra("movieid", movieidValue);
                    theIntent.putExtra("MovieTitle", movieTitleValue);

                    // this calls the intent to switch to Edit_Movie.class
                    startActivity(theIntent);
                }
            });

            //this sets up the listview on the MainActivity displaying what we want to show and in what partocular order

            //-----------Reference the Following code is from android example at "Android for Programmers An App-Driven Approach
            ListAdapter adapter = new SimpleAdapter(MainActivity.this, MovieList, R.layout.movie_list, new String[]{"movieid", "MovieTitle","Director"}, new int[]{R.id.movieid, R.id.MovieTitle, R.id.Director});
            setListAdapter(adapter);
            //----------Reference Complete
        }
    }


    //this calls the method onclick showAddMovie when the "Add Movie" button is pressed
    public void showAddMovie(View view) {
        //this switches the view to New_Movie.class
        Intent theIntent = new Intent(getApplicationContext(), New_Movie.class);
        startActivity(theIntent);
    }

    //this method updates the interface images to coincide with silent on/off service
    private void updateUi() {
        //-----------Reference the Following code is from the book Android App Development for Dummie
        ImageView imageView = (ImageView) findViewById(R.id.phone_icon);
        //this is switches arround the following two images whenever the method update ui is called
        int phoneImage = RingerHelper.isPhoneSilent(audioManager)
                ? R.drawable.ringer_off_smaller_5
                : R.drawable.ringer_on_smaller_6;

        imageView.setImageResource(phoneImage);
        //----------Reference Complete
    }


    //this resumes your activity from a paused stated and calls updateui and add +1
    //to i inorder to make sure it keeps the correct background
    protected void onResume() {
        super.onResume();
        updateUi();
        i++;
        Toast.makeText(getApplicationContext(), "Welcome Back",
                Toast.LENGTH_SHORT).show();
    }

    //this pauses your activity from a live state whenever you switch a screen or get a phonecall
    protected void onPause() {
        super.onPause();
    }

}