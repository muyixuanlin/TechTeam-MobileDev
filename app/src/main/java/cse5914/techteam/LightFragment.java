package cse5914.techteam;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

// This class implements a fragment for displaying embed ambient light sensor value
public class LightFragment extends Fragment implements SensorEventListener {
    private TextView currentX;
    private float lastX,deltaX;
    private SensorManager sensorManager;
    private Sensor light;

    public LightFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Invoke the sensor manager
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_light, container, false);
        currentX = (TextView) fragmentView.findViewById(R.id.currentX);
        return fragmentView;
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        // Calculate the delta of value
        deltaX = Math.abs(lastX - event.values[0]);
        lastX = event.values[0];
        currentX.setText(Float.toString(deltaX));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }


    public void onResume() {
        super.onResume();
        // Register sensor to listen to calls
        sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onPause() {
        super.onPause();
        // Unregister sensor to improve performance
        sensorManager.unregisterListener(this);
    }

}
