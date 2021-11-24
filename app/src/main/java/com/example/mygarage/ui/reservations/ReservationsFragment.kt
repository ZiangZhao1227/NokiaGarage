package com.example.mygarage.ui.reservations

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mygarage.R
import com.example.mygarage.ui.reservations.datetime.DatePickerFragment
import com.example.mygarage.ui.reservations.datetime.TimePickerFragment
import kotlinx.android.synthetic.main.fragment_article_details.*
import kotlinx.android.synthetic.main.fragment_reservations.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ReservationsFragment : Fragment() {

    private val viewModel: ReservationsViewModel by sharedViewModel()
    val args : ReservationsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reservations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val reservationImg = args.imgUrl
        val reservationName = args.name
        val reservatioColor = args.color

        reservation_layout.setBackgroundColor(Color.parseColor(reservatioColor))
        Glide.with(requireContext()).load(reservationImg).into(reservation_img)
        reservation_tv.text = reservationName
        day_pick_btn.setOnClickListener {
            showDatePickerDialog()
        }
        time_pick_btn.setOnClickListener {
            showTimePickerDialog()
        }

        viewModel.dateString.observe(viewLifecycleOwner,{
            reservation_time_tv.text = it
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    private fun showDatePickerDialog() {
        val newFragment = DatePickerFragment()
        newFragment.show(requireActivity().supportFragmentManager, "datePicker")
    }

    private fun showTimePickerDialog() {
        val newFragment = TimePickerFragment()
        newFragment.show(requireActivity().supportFragmentManager, "datePicker")
    }
}