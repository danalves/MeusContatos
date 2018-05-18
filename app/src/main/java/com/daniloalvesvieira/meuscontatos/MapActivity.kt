package com.daniloalvesvieira.meuscontatos

import android.location.Address
import android.location.Geocoder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_detalhe.*

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    private lateinit var endereco: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent != null) {
            endereco = intent.getStringExtra("ENDERECO")
        }

        setContentView(R.layout.activity_map)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap

        val geocoder = Geocoder(this)
        var address: List<Address>?

        address = geocoder.getFromLocationName(endereco, 1)

        if (address.isNotEmpty()) {
            val location = address[0]
            adicionarMarcador(location.latitude, location.longitude, location.subAdminArea)
        } else {
            val alert = AlertDialog.Builder(this).create()
            alert.setTitle("Erro!")
            alert.setMessage("Endereço não encontrado!")

            alert.setCancelable(true)
            alert.setCanceledOnTouchOutside(false)

            alert.setButton(AlertDialog.BUTTON_POSITIVE, "OK", { dialogInterface, inteiro ->
                alert.dismiss()
            })

            alert.show()
        }

    }

    fun adicionarMarcador(lat: Double, lng: Double, titulo: String) {

        val latLngLocal = LatLng(lat, lng)

        mMap.addMarker(MarkerOptions()
                .position(latLngLocal)
                .title(titulo))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngLocal, 17f))
    }
}
