package ch.uzh.michaelspring.cameraapp;

import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private static int displayOrientation = 90;
    private CameraPreview mCameraPreview;
    private Camera mCamera;
    private Matrix matrix;
    private FrameLayout preview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mCamera = getCameraInstance();

        //setup the camera preview view
        mCameraPreview = new CameraPreview(this, mCamera);
        preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mCameraPreview);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);


        //Create and configure new matrix to transform coordinates from the cameraframe (-1000 to 1000) to the display frame)
        //this needs to be done here, because at onCreate the width and height of views are not yet defined.
        matrix = new Matrix();
//        Camera.CameraInfo info = CameraHolder.instance().getCameraInfo()[cameraId];
        // Need mirror for front camera.
//        boolean mirror = (info.facing == CameraInfo.CAMERA_FACING_FRONT);
        matrix.setScale(false ? -1 : 1, 1);
        // This is the value for android.hardware.Camera.setDisplayOrientation.
        matrix.postRotate(displayOrientation);
        // Camera driver coordinates range from (-1000, -1000) to (1000, 1000).
        // UI coordinates range from (0, 0) to (width, height).
        Log.i(Constants.TAG, "configuration of matrix. width is " + preview.getWidth() + " and height is " + preview.getHeight());
        matrix.postScale(preview.getWidth() / 2000f, preview.getHeight() / 2000f);
        matrix.postTranslate(preview.getWidth() / 2f, preview.getHeight() / 2f);


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

    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
    }

    public static Camera getCameraInstance() {
        Camera cam = null;
        try {
            //// TODO: 07.08.15 should be done in a worker thread (can take a long time and blocks the GUI)
            cam = Camera.open();
            cam.setDisplayOrientation(displayOrientation);
        } catch (Exception e) {
            Log.e(Constants.TAG, "get Instance of camera failed: " + e.getMessage());
        }

        return cam;
    }

    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.release();        // release the camera for other applications
            mCamera = null;
        }
    }
}
