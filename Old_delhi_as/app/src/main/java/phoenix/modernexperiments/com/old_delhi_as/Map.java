package phoenix.modernexperiments.com.old_delhi_as;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CameraPosition;

public class Map extends Fragment{

	MapView mMapView;

	private GoogleMap googleMap;

	public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflator.inflate(R.layout.map, container, false);

		mMapView = (MapView) rootView.findViewById(R.id.map1);
		mMapView.onCreate(savedInstanceState);

		mMapView.onResume(); // needed to get the map to display immediately

		try {
			MapsInitializer.initialize(getActivity().getApplicationContext());
		} catch (Exception e) {
			e.printStackTrace();
		}

		mMapView.getMapAsync(new OnMapReadyCallback() {
			@Override
			public void onMapReady(GoogleMap mMap) {
				googleMap = mMap;

				// Drop markers
				LatLng delhi = new LatLng(28.7, 77.1);
				googleMap.addMarker(new MarkerOptions().position(delhi).title("Delhi").snippet("Welcome"));

				LatLng imperial = new LatLng(28.6253203, 77.2183045);
				googleMap.addMarker(new MarkerOptions().position(imperial).title("The Imperial Hotel").snippet("Tea at Imperial"));

				LatLng hiralal = new LatLng(28.65,77.22);
				googleMap.addMarker(new MarkerOptions().position(hiralal).title("Hiralal Sweets").snippet("Chaat"));

				LatLng nathu = new LatLng(28.6297158, 77.2320575);
				googleMap.addMarker(new MarkerOptions().position(nathu).title("Nathu Sweets Corner").snippet("Chaat"));

				LatLng pandara = new LatLng(28.6059896, 77.2307423);
				googleMap.addMarker(new MarkerOptions().position(pandara).title("Pandara Road").snippet("Eateries"));

				LatLng chandnichowk = new LatLng(28.6505942, 77.2303284);
				googleMap.addMarker(new MarkerOptions().position(chandnichowk).title("Chandni Chowk").snippet("Eateries and sight seeing"));

				LatLng redfort = new LatLng(28.6561592, 77.2410203);
				googleMap.addMarker(new MarkerOptions().position(redfort).title("Red Fort").snippet("Red Fort"));

				// Zooming automatically to the location of the default marker - Delhi
				CameraPosition cameraPosition = new CameraPosition.Builder().target(delhi).zoom(12).build();
				googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
			}
		});
		return rootView;
	}

	@Override
	public void onResume() {
		super.onResume();
		mMapView.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		mMapView.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mMapView.onDestroy();
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		mMapView.onLowMemory();
	}

}

