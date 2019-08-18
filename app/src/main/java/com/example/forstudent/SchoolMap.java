package com.example.forstudent;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SchoolMap extends SupportMapFragment implements OnMapReadyCallback{

    private GoogleMap mMap;
    private Geocoder geocoder;
    MainActivity main = (MainActivity)getActivity();
    Button testbutton;
    double latitude;
    double longitude;
    String schoolName = "";
    private String m_Text = "";

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        geocoder = new Geocoder(activity);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getMapAsync(this);
    }
    @Override
    public void onStart() {
        super.onStart();
        testbutton = (Button)getView().findViewById(R.id.testbutton);
        schoolNameDialog();






        List<Address> addressList = null;
        try {
            // editText에 입력한 텍스트(주소, 지역, 장소 등)을 지오 코딩을 이용해 변환
            addressList = geocoder.getFromLocationName(
                    schoolName, // 주소
                    1); // 최대 검색 결과 개수
            latitude = addressList.get(0).getLatitude();
            longitude = addressList.get(0).getLongitude();
        }
        catch (IOException e) {

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        LatLng SCHOOL = new LatLng(latitude, longitude);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SCHOOL);
        markerOptions.title(schoolName);
        //markerOptions.snippet("한국의 수도");
        mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(SCHOOL));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17));

    }
    public void schoolNameDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("학교 이름을 입력해주세요");

// Set up the input
        final EditText input = new EditText(getContext());
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
