package com.example.dz_2111

import android.content.Context
import android.location.Geocoder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import java.util.Locale

class mapsFragment : Fragment() {
private lateinit var googleMap: GoogleMap
lateinit var  geocoder: Geocoder
private val markers = mutableListOf<Marker>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val noncontext: Context = context!!
        geocoder = Geocoder(noncontext, Locale.getDefault())
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            mapsFragment().apply {
                }
            }
}