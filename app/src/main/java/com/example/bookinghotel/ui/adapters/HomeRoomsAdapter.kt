package com.example.bookinghotel.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.bookinghotel.data.models.Room
import com.example.bookinghotel.databinding.HomeRvAdapterBinding
import com.example.bookinghotel.domain.model.HotelSingleRoom
import com.example.bookinghotel.ui.dashboard.home.detailed_information.DetailedInformationActivity

class HomeRoomsAdapter(
    private var roomsList: MutableList<HotelSingleRoom>
) : RecyclerView.Adapter<HomeRoomsAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(binding: HomeRvAdapterBinding) : RecyclerView.ViewHolder(binding.root){
        val titleTextView : TextView = binding.title
        val descriptionTextView : TextView = binding.description
        val constraintLayout : ConstraintLayout = binding.constraintLayoutRV
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = HomeRvAdapterBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleTextView.text = roomsList[position].hotel?.name
        holder.descriptionTextView.text = roomsList[position].room?.description.toString()
        holder.constraintLayout.setOnClickListener {
            moveToActivity(holder)
        }
    }

    override fun getItemCount(): Int = roomsList.size

    private fun moveToActivity(holder: ViewHolder) {
        val intent = Intent(context, DetailedInformationActivity::class.java).apply {
            putExtra("HotelRoom", roomsList[holder.adapterPosition])
        }
        context.startActivity(intent)
    }

    fun setList(rooms: MutableList<HotelSingleRoom>){
        roomsList = rooms
    }
}