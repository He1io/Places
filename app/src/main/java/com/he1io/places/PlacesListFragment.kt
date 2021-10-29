package com.he1io.places

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.he1io.places.databinding.FragmentPlacesListBinding
import kotlinx.coroutines.launch

class PlacesListFragment : Fragment() {

    private val viewModel: PlaceViewModel by viewModels()

    private var _binding: FragmentPlacesListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: PlacesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentPlacesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PlacesListAdapter(listOf())
        { currentPlace: Place ->
            val action = PlacesListFragmentDirections.actionPlacesListFragmentToPlaceDetailsFragment(currentPlace.id)
            this.findNavController().navigate(action)
        }

        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context)
            searchButton.setOnClickListener {

                viewModel.viewModelScope.launch {
                    try {
                        val placesList = viewModel.getNearPlaces(
                            searchText.text.toString()
                        )
                        adapter.setPlacesList(placesList)
                        adapter.notifyDataSetChanged()
                    } catch (e: Exception) {
                        Toast.makeText(
                            context,
                            "Oops! No places found at that location",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }
}