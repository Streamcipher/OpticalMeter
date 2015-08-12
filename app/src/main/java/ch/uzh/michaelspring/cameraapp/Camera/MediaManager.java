package ch.uzh.michaelspring.cameraapp.Camera;

import android.hardware.Camera;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ch.uzh.michaelspring.cameraapp.Constants;

/**
 * Created by melchior on 11.08.15.
 */
public class MediaManager {

    private static final int MEDIA_TYPE_IMAGE = 1;
    private static final int MEDIA_TYPE_VIDEO = 2;
    private static final String MEDIA_MOUNTED = "mounted";

    /** Create a File for saving an image or video */

    /**
     * Create a File for saving an image or video.
     * Returns null if it doesn't work!
     *
     * @param type
     * @return File, or null if something failed.
     */
    public static File getOutputMediaFile(int type) {

        Log.i(Constants.TAG, "Trying to safe the picture.");

        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        String storageState = Environment.getExternalStorageState();
        if (!MEDIA_MOUNTED.equals(storageState)) {
            Log.d(Constants.TAG, "Storage not accessible. Storage has the following state: " + storageState + "It should be " + MEDIA_MOUNTED);
            return null;
        }


        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), Constants.DIRECTORYNAME);
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(Constants.TAG, "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    public static File savePictureToDisk(byte[] data) {
        File pictureFile = MediaManager.getOutputMediaFile(1);
        if (pictureFile == null) {
            Log.d(Constants.TAG, "Error creating media file, check storage permissions: ");
            return null;
        }

        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            fos.write(data);
            fos.close();

        } catch (FileNotFoundException e) {
            Log.d(Constants.TAG, "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d(Constants.TAG, "Error accessing file: " + e.getMessage());
        }

        return pictureFile;
    }
}
