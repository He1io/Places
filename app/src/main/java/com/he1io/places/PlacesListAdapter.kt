package com.he1io.places

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.he1io.places.databinding.ItemPlacesListBinding

class PlacesListAdapter(
    private var placesList: List<Place>,
    private val onItemClicked: (Place) -> Unit
) : RecyclerView.Adapter<PlacesListAdapter.PlaceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val binding =
            ItemPlacesListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val place = placesList[position]
        holder.itemView.setOnClickListener {
            onItemClicked(place)
        }
        holder.bind(place)
    }

    override fun getItemCount() = placesList.size

    fun setPlacesList(newPlacesList: List<Place>) {
        placesList = newPlacesList
    }

    class PlaceViewHolder(private var binding: ItemPlacesListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(place: Place) {
            binding.apply {
                placeName.text = place.name
            }
        }
    }
}