package com.melec.myfragment.main;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends FragmentActivity implements ListFrag.onListSelectionListener {

    public static String[] mList;
    public static String[] mDetails;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mList = getResources().getStringArray(R.array.List);
        mDetails = getResources().getStringArray(R.array.Details);


        setContentView(R.layout.activity_main);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if(findViewById(R.id.fragment_container) != null){

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if(savedInstanceState != null){
                return;
            }

            // Create an instance of ListFrag
            ListFrag  firstFragment = new ListFrag();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,firstFragment).commit();

        }
    }


    public void onItemSelected(int pos) {

        /** Capture the details fragment from the activity layout*/
        DetailsFrag detailsFragment = (DetailsFrag) getSupportFragmentManager().findFragmentById(R.id.details_fragment);

        if (detailsFragment != null){
          /** If details frag is available, we're in two-pane layout...
           Call a method in the ArticleFragment to update its content  */
        detailsFragment.updateDetailsView(pos);

        }
        else{
            Intent intent = new Intent(getApplicationContext(),DetailsActivity.class);
            intent.putExtra("INDEX",pos);

            startActivity(intent);

        }

    }



    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
*/
}
