package cse5914.techteam;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

// This class implements a fragment for displaying embed accelerometer sensor value
public class AccelerometerFragment extends Fragment implements SensorEventListener {
    private TextView currentX, currentY, currentZ;
    private float lastX,lastY,lastZ,deltaX,deltaY,deltaZ;
    private SensorManager sensorManager;
    private Sensor accelerometer;

    public AccelerometerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Invoke the sensor manager
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_accelerometer, container, false);
        currentX = (TextView) fragmentView.findViewById(R.id.currentX);
        currentY = (TextView) fragmentView.findViewById(R.id.currentY);
        currentZ = (TextView) fragmentView.findViewById(R.id.currentZ);
        return fragmentView;
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        // Calculate the delta of value
        deltaX = Math.abs(lastX - event.values[0]);
        deltaY = Math.abs(lastY - event.values[1]);
        deltaZ = Math.abs(lastZ - event.values[2]);
        lastX = event.values[0];
        lastY = event.values[1];
        lastZ = event.values[2];
        currentX.setText(Float.toString(deltaX));
        currentY.setText(Float.toString(deltaY));
        currentZ.setText(Float.toString(deltaZ));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }


    public void onResume() {
        super.onResume();
        // Register sensor to listen to calls
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onPause() {
        super.onPause();
        // Unregister sensor to improve performance
        sensorManager.unregisterListener(this);
    }

}
