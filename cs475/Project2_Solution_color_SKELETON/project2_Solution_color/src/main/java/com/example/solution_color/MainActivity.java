package com.example.solution_color;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
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
import com.library.bitmap_utilities.BitMap_Helpers;

import java.io.ByteArrayOutputStream;
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
    protected String subjectTextFieldValue;

    // Bitmap image that has been scaled, pointer to file created by camera when picture is taken
    protected Bitmap scaledBitmapImage;

    // BitmapDrawable image that has been scaled, loaded, and has sketch effect applied
    protected Bitmap sketchedAndScaledBitmapImage;
    protected Bitmap colorizedAndScaledBitmapImage;

    // store drawable, final images
    protected BitmapDrawable sketchedAndScaledBitmapImageAsDrawable;
    protected BitmapDrawable colorizedAndScaledBitmapImageAsDrawable;

    // final product to be used for sharing
    protected Bitmap finalBitmapImageProduct;

    protected SharedPreferences preferences;
    protected OnSharedPreferenceChangeListener preferencesListener;



    protected ImageView imageViewElement;

     @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // choose layout
        setContentView(R.layout.activity_main);

        // set references to important views
        imageViewElement = (ImageView)findViewById(R.id.imageView);

        preferences = getDefaultSharedPreferences(this);

        subjectTextFieldValue   = preferences.getString(SUBJECT_VALUE, "");
        bodyTextFieldValue  = preferences.getString(BODY_VALUE, "");

        // handle touch on preferences button
        preferencesListener = new SharedPreferences.OnSharedPreferenceChangeListener() {

            public void onSharedPreferenceChanged(SharedPreferences preferenceItems, String key) {

                // handle body values from preferences message
                if (key.equals(BODY_VALUE)) {
                    bodyTextFieldValue = preferenceItems.getString(BODY_VALUE, "");

                }

                if (key.equals(SUBJECT_VALUE)) {
                    subjectTextFieldValue = preferenceItems.getString(SUBJECT_VALUE, "");
                }

            }

        };

        preferences.registerOnSharedPreferenceChangeListener(preferencesListener);
        imageViewElement = (ImageView)findViewById(R.id.imageView);

    }

    public void cameraButtonClickHandler(View view) {
        dispatchTakePictureIntent();
    }

    public void sketchButtonClickHandler(View view) {

        // make sure picture to apply effect to has been taken
        if(currentPictureFileName == null || scaledBitmapImage == null) {
            return;
        }

        if(sketchedAndScaledBitmapImageAsDrawable == null) {

            // create file from resource location
            sketchedAndScaledBitmapImage = BitMap_Helpers.thresholdBmp(scaledBitmapImage, 40);
            sketchedAndScaledBitmapImageAsDrawable = new BitmapDrawable(getResources(), sketchedAndScaledBitmapImage);

        }

        finalBitmapImageProduct = sketchedAndScaledBitmapImage;

        // set the scaled image as the imageView's background
        imageViewElement.setBackgroundDrawable(sketchedAndScaledBitmapImageAsDrawable);

    }

    public void colorizeButtonClickHandler(View view) {

        // make sure a picture to apply the effect to has been taken
        if(currentPictureFileName == null || scaledBitmapImage == null) {
            return;
        }

        if(colorizedAndScaledBitmapImageAsDrawable == null) {

            // create file from resource location, determine if image has been sketched beforehand
            if(sketchedAndScaledBitmapImage == null) {
                sketchedAndScaledBitmapImage = BitMap_Helpers.thresholdBmp(scaledBitmapImage, 40);
            }

            // colorize regular image
            colorizedAndScaledBitmapImage = BitMap_Helpers.colorBmp(scaledBitmapImage, 3.7f);

            // merge colorized image and black and white image
            BitMap_Helpers.merge(colorizedAndScaledBitmapImage, sketchedAndScaledBitmapImage);

            // create a drawable image object from bitmap
            colorizedAndScaledBitmapImageAsDrawable = new BitmapDrawable(getResources(), colorizedAndScaledBitmapImage);

        }

        // store final product in an accessible location for sharing.
        finalBitmapImageProduct = colorizedAndScaledBitmapImage;

        // set the scaled image as the imageView's background
        imageViewElement.setBackgroundDrawable(colorizedAndScaledBitmapImageAsDrawable);

    }

    public void shareButtonClickHandler(View view) {

        String temporaryPictureFileName = currentPictureFileName;

        if(currentPictureFileName == null) {
           return;
        }

        Intent actionSendIntent = new Intent(Intent.ACTION_SEND);
        actionSendIntent.setType("text/plain");
        actionSendIntent.putExtra(Intent.EXTRA_SUBJECT, subjectTextFieldValue);
        actionSendIntent.putExtra(Intent.EXTRA_TEXT, bodyTextFieldValue);

        if(finalBitmapImageProduct == null) {
            File currentPicture = new File(temporaryPictureFileName);
            actionSendIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(currentPicture));
        } else {
            actionSendIntent.putExtra(Intent.EXTRA_STREAM, uriFromBitmap(finalBitmapImageProduct));
        }

        startActivity(actionSendIntent);

    }

    public Uri uriFromBitmap(Bitmap bitmapImage) {

        ByteArrayOutputStream imageByteArray = new ByteArrayOutputStream();
        bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, imageByteArray);

        String compressedImagePath = MediaStore.Images.Media.insertImage(getContentResolver(), bitmapImage, "Title", null);

        return Uri.parse(compressedImagePath);

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
            scaledBitmapImage                       = Camera_Helpers.loadAndScaleImage(currentPictureFileName, (imageViewElement.getWidth() / 2), (imageViewElement.getHeight() / 2));
            BitmapDrawable bitmapImageAsDrawable    = new BitmapDrawable(getResources(), scaledBitmapImage);

            // save pointer to image ready to be shared
            finalBitmapImageProduct = scaledBitmapImage;

            // reset drawable images
            colorizedAndScaledBitmapImageAsDrawable = null;
            sketchedAndScaledBitmapImageAsDrawable  = null;
            sketchedAndScaledBitmapImage            = null;
            colorizedAndScaledBitmapImage           = null;

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

