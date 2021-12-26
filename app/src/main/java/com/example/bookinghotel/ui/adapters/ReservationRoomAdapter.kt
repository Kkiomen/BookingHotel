package com.example.bookinghotel.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.bookinghotel.databinding.HomeRvAdapterBinding
import com.example.bookinghotel.databinding.ReservationRvItemBinding
import com.example.bookinghotel.domain.model.HotelSingleRoom
import com.example.bookinghotel.domain.model.UserReservedRoom
import com.example.bookinghotel.ui.dashboard.home.detailed_information.DetailedInformationActivity
import com.example.bookinghotel.ui.dashboard.reservation.detailed_reservation_information.DetailedReservationInformationActivity

class ReservationRoomAdapter(
    private var roomsList: List<UserReservedRoom>
    ) : RecyclerView.Adapter<ReservationRoomAdapter.ViewHolder>() {

        private lateinit var context: Context

        inner class ViewHolder(binding: ReservationRvItemBinding) : RecyclerView.ViewHolder(binding.root){
            val titleTextView : TextView = binding.reservationTitle
            val descriptionTextView : TextView = binding.reservationDesctiption
            val constraintLayout : ConstraintLayout = binding.constraintLayoutRV
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            context = parent.context
            val inflater = LayoutInflater.from(context)
            val binding = ReservationRvItemBinding.inflate(inflater)
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.titleTextView.text = roomsList[position].userHotel?.name
            holder.descriptionTextView.text = roomsList[position].userRoom?.description
            holder.constraintLayout.setOnClickListener {
                moveToActivity(holder)
            }
        }

        override fun getItemCount(): Int = roomsList.size

        private fun moveToActivity(holder: ViewHolder) {
            val intent = Intent(context, DetailedReservationInformationActivity::class.java).apply {
                putExtra("UserRoom", roomsList[holder.adapterPosition])
            }
            context.startActivity(intent)
        }

    fun setList(userReservedRoomList: List<UserReservedRoom>) {
        roomsList = userReservedRoomList
    }

}