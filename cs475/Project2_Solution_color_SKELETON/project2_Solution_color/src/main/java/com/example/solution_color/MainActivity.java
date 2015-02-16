package com.example.solution_color;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class MainActivity extends ActionBarActivity  {

    protected static final String PHOTO_TAKEN       = "photo_taken";
    protected static final int REQUEST_SAVE_PHOTO   = -1;
    protected static final int REQUEST_TAKE_PHOTO   = 1;
    protected static final String BODY_VALUE        = "BODY_VALUE";
    protected static final String SUBJECT_VALUE     = "SUBJECT_VALUE";

    protected boolean pictureWasTaken;
    protected String currentPictureFileName;
    protected String bodyTextFieldValue;
    protected String subjectFieldValue;

    protected SharedPreferences preferences;
    protected SharedPreferences.OnSharedPreferenceChangeListener preferencesListener;



    protected ImageView imageViewElement;

     @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // choose layout
        setContentView(R.layout.activity_main);

        // set references to important views
        imageViewElement = (ImageView)findViewById(R.id.imageView);

        preferences = getDefaultSharedPreferences(this);

         subjectFieldValue  = preferences.getString(SUBJECT_VALUE, "");
         bodyTextFieldValue = preferences.getString(BODY_VALUE, "");


    }

    public void cameraButtonClickHandler(View view) {

        dispatchTakePictureIntent();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            // action with ID action_refresh was selected
            case R.id.action_settings:

                Intent myIntent = new Intent(this, SettingsActivity2.class);
                startActivity(myIntent);

                break;

            default:
                break;
        }

        return true;
    }

    protected void dispatchTakePictureIntent() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Ensure that an app to handle camera activity exists
        if(takePictureIntent.resolveActivity(getPackageManager()) != null) {

            File photoFile = null;

            try {
                photoFile = createImageFile();
            } catch(IOException exception) {
                exception.printStackTrace();
            }

            // continue if a file to hold the image was successfully created
            if(photoFile != null) {

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);

            }

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // make sure picture was indeed taken and also saved, not discarded
        if(requestCode == REQUEST_TAKE_PHOTO && resultCode == REQUEST_SAVE_PHOTO) {

            // load and scale the captured image
            Bitmap scaledBitmapImage                = Camera_Helpers.loadAndScaleImage(currentPictureFileName, imageViewElement.getWidth(), imageViewElement.getHeight());
            BitmapDrawable bitmapImageAsDrawable    = new BitmapDrawable(getResources(), scaledBitmapImage);

            // set the scaled image as the imageView's background
            imageViewElement.setBackgroundDrawable(bitmapImageAsDrawable);

        }

    }

    protected File createImageFile() throws IOException {

        File storageDirectory   = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File temporaryImageFile = File.createTempFile("myCapturedImage", ".jpg", storageDirectory);

        currentPictureFileName  = temporaryImageFile.getAbsolutePath();

        return temporaryImageFile;

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if(savedInstanceState.getBoolean(MainActivity.PHOTO_TAKEN)) {
            pictureWasTaken = true;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(MainActivity.PHOTO_TAKEN, pictureWasTaken);
    }

}

