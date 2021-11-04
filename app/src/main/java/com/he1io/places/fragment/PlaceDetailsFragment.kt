package com.he1io.places.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.he1io.places.R
import com.he1io.places.databinding.FragmentPlaceDetailsBinding
import com.he1io.places.model.Place
import com.he1io.places.viewmodel.PlaceViewModel
import kotlinx.coroutines.launch

// Constants to Maps Api requests
private const val size = "2400x2400"

class PlaceDetailsFragment : Fragment() {

    private val viewModel: PlaceViewModel by viewModels()

    private var _binding: FragmentPlaceDetailsBinding? = null
    private val binding get() = _binding!!

    private val navigationArgs: PlaceDetailsFragmentArgs by navArgs()

    private lateinit var placeId: String
    private lateinit var place: Place

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentPlaceDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        placeId = navigationArgs.placeId
        val mapsApiKey = getString(R.string.maps_api_key)


        viewModel.viewModelScope.launch {
            place = viewModel.getPlaceById(placeId)
            val placeLocationString = "${place.location.lat},${place.location.lng}"
            binding.apply {
                map.load("https://maps.googleapis.com/maps/api/staticmap?markers=color:red%7C$placeLocationString&size=$size&key=$mapsApiKey")
                placeName.text = place.name
                for(category in place.categories){
                    if (category.primary){
                        placeCategory.text = category.name
                        category.icon.let {
                            placeIcon.load("${it.prefix}64${(it.suffix)}")
                        }
                    }
                }
                val distanceText = "Distance from here: ${place.location.distance}"
                placeDistance.text = distanceText
                placeHereNow.text = place.hereNow.summary
                placeDescripcion.text = place.description
            }
        }
    }
}