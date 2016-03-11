package cse5914.techteam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

// This class implements a fragment for calling Google map
public class MapActivity extends AppCompatActivity   {
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        try {
            if (googleMap == null) {
                // Invoke the map manager
                googleMap = ((MapFragment) getFragmentManager().
                        findFragmentById(R.id.map)).getMap();
            }
            // Set the default map type to be Hybrud
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
