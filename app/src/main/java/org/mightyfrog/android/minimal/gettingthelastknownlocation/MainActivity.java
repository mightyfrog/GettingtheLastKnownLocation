package org.mightyfrog.android.minimal.gettingthelastknownlocation;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * "Minimal" sample code to get the last known location.
 * https://developer.android.com/training/location/retrieve-current.html
 *
 * @author Shigehiro Soejima
 */
public class MainActivity extends Activity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildGoogleApiClient();
    }

    @Override
    protected void onStart() {
        super.onStart();

        mGoogleApiClient.connect(); // don't forget me!!
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            android.util.Log.e(TAG, "latitude=" + mLastLocation.getLatitude());
            android.util.Log.e(TAG, "longitude=" + mLastLocation.getLongitude());
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        android.util.Log.e(TAG, "connection suspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        android.util.Log.e(TAG, "connection failed");
    }

    /**
     *
     */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }
}
