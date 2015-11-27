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
        import android.widget.Button;
        import android.widget.Toast;

//this is WebPage java class that is called when a button is pressed, it extends Activity
public class WebPage extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);

        Bundle bundle = getIntent().getExtras();
        int count = bundle.getInt("counter");

        Intent theIntent = getIntent();
        String movie = theIntent.getStringExtra("MovieTitle");

        Toast.makeText(getApplicationContext(),movie,
                Toast.LENGTH_SHORT).show();

        if (count ==1)
        {
            WebView myWebView = (WebView) findViewById(R.id.web);
            myWebView.loadUrl("http://www.rottentomatoes.com/search/?search=" + movie);
        }
        if (count ==2)
        {
            WebView myWebView = (WebView) findViewById(R.id.web);
            myWebView.loadUrl("http://www.imdb.com/find?ref_=nv_sr_fn&q=" + movie);
        }

        if (count ==3)
        {
            WebView myWebView = (WebView) findViewById(R.id.web);
            myWebView.loadUrl("" + movie);
        }
        //we are using a setonClickListener instead of calling the method onclick
        //from our xml files
        Button button2=(Button) findViewById(R.id.finish);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent switchScreen= new Intent(v.getContext(),MainActivity.class);
                startActivity(switchScreen);

            }


        });



    }

    //this is here because since the listview that brought you here is in MainActivity and not
    //EditMovie.java, I found it not possible to carry over the movieid in order to retrieve the
    //screen you were just on
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Press the Back To HomeScreen button",
                Toast.LENGTH_SHORT).show();
    }

}
