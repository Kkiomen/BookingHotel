package com.example.bookinghotel.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookinghotel.data.models.Room
import com.example.bookinghotel.databinding.HomeRvAdapterBinding
import com.example.bookinghotel.domain.model.HotelSingleRoom

class HomeRoomsAdapter(
    private val roomsList: MutableList<HotelSingleRoom>
) : RecyclerView.Adapter<HomeRoomsAdapter.ViewHolder>() {

    inner class ViewHolder(binding: HomeRvAdapterBinding) : RecyclerView.ViewHolder(binding.root){
        val titleTextView : TextView = binding.title
        val descriptionTextView : TextView = binding.description
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HomeRvAdapterBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleTextView.text = roomsList[position].hotel?.name
        holder.descriptionTextView.text = roomsList[position].room?.description.toString()
    }

    override fun getItemCount(): Int = roomsList.size
}