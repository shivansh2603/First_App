package com.example.loginpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

class CalendarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)

        val monthRadioGroup: RadioGroup = view.findViewById(R.id.monthRadioGroup)
        monthRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val selectedMonth = when (checkedId) {
                R.id.januaryRadioButton -> "January"
                R.id.februaryRadioButton -> "February"
                R.id.marchRadioButton -> "March"
                R.id.aprilRadioButton -> "April"
                R.id.MayRadioButton -> "May"
                R.id.JuneRadioButton -> "June"
                R.id.JulyRadioButton -> "July"
                R.id.AugustRadioButton -> "August"
                R.id.SeptemberRadioButton -> "September"
                R.id.OctoberRadioButton -> "October"
                R.id.NovemberRadioButton -> "November"
                R.id.DecemberRadioButton -> "December"
                else -> ""
            }
            Toast.makeText(context, "Selected Month: $selectedMonth", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}