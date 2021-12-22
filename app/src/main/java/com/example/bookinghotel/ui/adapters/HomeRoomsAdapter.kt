package com.example.bookinghotel.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookinghotel.data.models.Room
import com.example.bookinghotel.databinding.HomeRvAdapterBinding

class HomeRoomsAdapter(
    private val roomsList : List<Room>
) : RecyclerView.Adapter<HomeRoomsAdapter.ViewHolder>() {

    inner class ViewHolder(binding: HomeRvAdapterBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflanter = LayoutInflater.from(parent.context)
        val binding = HomeRvAdapterBinding.inflate(inflanter)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}