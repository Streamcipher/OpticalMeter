package ch.uzh.michaelspring.cameraapp;

import android.hardware.Camera;

/**
 * Created by melchior on 07.08.15.
 */
class MyFaceDetectionListener implements Camera.FaceDetectionListener {
    private final MainActivity activity;

    public MyFaceDetectionListener(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onFaceDetection(Camera.Face[] faces, Camera camera) {
        if (faces.length > 0) {
            activity.setDetectedFaces(faces);
        }
    }


}