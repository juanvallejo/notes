package juanvallejo.me.project1_paws_juan;

import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class MainActivity extends ActionBarActivity {

    private int resourceId;
    private int currentImage;
    private int totalImageCount;

    private int[] favoriteImages;

    private CheckBox imageCheckbox;
    private RelativeLayout imageHolderLayout;
    private ImageView imageElement;

    private Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resources           = this.getApplicationContext().getResources();

        // store references to elements and layout views
        imageCheckbox       = (CheckBox) findViewById(R.id.checkBox);
        imageHolderLayout   = (RelativeLayout) findViewById(R.id.image_holder);
        imageElement        = (ImageView) findViewById(R.id.imageView);

        // declare index of default image and total amount of images
        currentImage        = 0;
        totalImageCount     = 4;

        // array that will save the index number of our favorite images
        favoriteImages      = new int[totalImageCount];

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

    public void onNextButtonClick(View view) {

        // clear hearts background if it has been set
        imageHolderLayout.setBackgroundResource(0);
        imageCheckbox.setChecked(false);

        // change the current image being shown
        currentImage++;

        if(currentImage >= totalImageCount) {
            currentImage = 0;
        }

        if(favoriteImages[currentImage] == 1) {
            imageCheckbox.setChecked(true);
            imageHolderLayout.setBackgroundResource(R.drawable.hearts);
        }

        resourceId = resources.getIdentifier("pet" + currentImage, "drawable", getPackageName());
        imageElement.setImageResource(resourceId);
    }

    public void onFavoriteButtonClick(View view) {

        // change the background of the main view

        // note: the state of the checkbox is returned after its state has actually been
        // changed by the click action
        if(!imageCheckbox.isChecked()) {
            favoriteImages[currentImage] = 0;
            imageHolderLayout.setBackgroundResource(0);
            return;
        }

        favoriteImages[currentImage] = 1;
        imageHolderLayout.setBackgroundResource(R.drawable.hearts);

    }

}
