package com.he1io.places

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.he1io.places.databinding.FragmentPlaceDetailsBinding
import kotlinx.coroutines.launch

class PlaceDetailsFragment : Fragment() {

    private val viewModel: PlaceViewModel by viewModels()

    private var _binding: FragmentPlaceDetailsBinding? = null
    private val binding get() = _binding!!

    private val navigationArgs: PlaceDetailsFragmentArgs by navArgs()

    private lateinit var placeId: String
    private lateinit var place: PlaceDetails

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

        viewModel.viewModelScope.launch {
            place = viewModel.getPlaceById(placeId)

            binding.apply {
                placeName.text = place.name
                val distanceText = "Distance from here: ${place.location.distance}"
                placeDistance.text = distanceText
                placeHereNow.text = place.hereNow.summary
                placeDescription.text = place.description

                for(category in place.categories){
                    if (category.primary){
                        placeCategory.text = category.name
                        category.icon.let {
                            placeIcon.load("${it.prefix}64${(it.suffix)}")
                        }
                    }
                }


            }
        }
    }
}