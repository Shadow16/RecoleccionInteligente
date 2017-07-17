package test.recoleccioninteligente;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(20.603433,-103.401399);
        mMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Market")
                // .icon(BitmapDescriptorFactory.fromResource(R.drawable.loc))
                .icon(BitmapDescriptorFactory.defaultMarker())
        );

        // AGREGAR DESTINO
        LatLng ch = new LatLng(20.614620,-103.396399);
        mMap.addPolyline(new PolylineOptions().add(
                sydney,
                new LatLng(20.607511,-103.400390),
                new LatLng(20.613817,-103.395627),
                ch
            )
            .width(10)
                .color(Color.YELLOW)
        );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18));
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        UiSettings set = mMap.getUiSettings();
        set.setZoomControlsEnabled(true);


        mMap.setMyLocationEnabled(true);
    }
}
