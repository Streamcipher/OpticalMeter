package ch.uzh.michaelspring.cameraapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import ch.uzh.michaelspring.cameraapp.Camera.MainActivity;

public class ReviewPictureActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_picture);

        //Get URI to the new picture
        Intent intent = getIntent();
        String pictureURIString = intent.getStringExtra(MainActivity.PICTURE_URI);
        Log.d(Constants.TAG, "extra address is: " + MainActivity.PICTURE_URI);

        if (null == pictureURIString) {
            Log.e(Constants.TAG, "Image URI wasn't in the intent extras.");
            //TODO search for the image file?
        } else {
            Uri pictureURI = Uri.parse(pictureURIString);

            imageView = (ImageView) findViewById(R.id.image_view);
            imageView.setImageURI(pictureURI);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_review_picture, menu);
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
}
