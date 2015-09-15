package com.example.perkins.lab_inclass_01;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * will take 2 numbers from the 2 edit texts
     * verify that they hold valid integers
     * add the numbers to an intent
     * start the ActivityAdd to display the addition of the 2 and offer
     * a sharing option
     * @param view
     */
    public void doAdd(View view) {

    }

    /**
     * gets number 1 and 2 from  SharedPreferences
     */
    public void getPref() {

    }

    /**
     * saves number 1 and 2 to SharedPreferences
     */
    public void savePref() {

    }

}
