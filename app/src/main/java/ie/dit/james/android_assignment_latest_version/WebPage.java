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
        import android.webkit.WebView;
        import android.widget.Button;
        import android.widget.Toast;

//this is WebPage java class that is called when a button is pressed, it extends Activity
public class WebPage extends Activity {

    @Override
    //this is the onCreate method that sets the layout and carrys over the intents
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);

        //this gets the intents and stores it as an integer
        Bundle bundle = getIntent().getExtras();
        int count = bundle.getInt("counter");

        //this gets the intent and stores it in a string
        Intent theIntent = getIntent();
        String movie = theIntent.getStringExtra("MovieTitle");

        //this displays a message saying what movie is in the string
        Toast.makeText(getApplicationContext(),movie,
                Toast.LENGTH_SHORT).show();

        //if the intent value passed was one it will display the following webur
        if (count ==1)
        {
            //this shows the weburl of "rotten tomatoes" with the movietite stored in the table
            WebView myWebView = (WebView) findViewById(R.id.web);
            myWebView.loadUrl("http://www.rottentomatoes.com/search/?search=" + movie);
        }
        //if the intent value passed was two it will display the following webur
        if (count ==2)
        {
            //this shows the weburl of "amazon prime" with the movietite stored in the table
            WebView myWebView = (WebView) findViewById(R.id.web);
            myWebView.loadUrl("http://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Dprime-instant-video&field-keywords=" + movie);
        }
        //if the intent value passed was three it will display the following webur
        if (count ==3)
        {
            //this shows the weburl of "imdb" with the movietite stored in the table
            WebView myWebView = (WebView) findViewById(R.id.web);
            myWebView.loadUrl("http://www.imdb.com/find?ref_=nv_sr_fn&q=" + movie);
        }

        //we are using a setonClickListener instead of calling the method onclick
        //from our xml files
        Button button2=(Button) findViewById(R.id.finish);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this calls the button to switch back to mainActivity
                Intent switchScreen= new Intent(v.getContext(),MainActivity.class);
                startActivity(switchScreen);
            }
        });
    }

    //this is here because since the listview that brought you here is in MainActivity and not
    //EditMovie.java, I found it not possible to carry over the movieid in order to retrieve the
    //screen you were just on so instead of a bug, I thought I would disable the function of the back button
    //-----------Reference the Following code is from http://stackoverflow.com/questions/8631095/android-preventing-going-back-to-the-previous-activity
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Press the Back To HomeScreen button",
                Toast.LENGTH_SHORT).show();
    }
    ///----------Reference Complete


    //this resumes your activity from a paused stated
    protected void onResume() {
        super.onResume();
    }

    //this pauses your activity from a live state whenever you switch a screen or get a phonecall
    protected void onPause() {
        super.onPause();
    }

}
