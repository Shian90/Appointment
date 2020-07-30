package com.example.app_ointmentt.ui.doctor.fragment

import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.app_ointmentt.IHomepage
import com.example.app_ointmentt.R
import kotlinx.android.synthetic.main.fragment_doctor_homepage.*
import kotlinx.android.synthetic.main.fragment_doctor_homepage.view.*
import java.text.SimpleDateFormat
import java.util.*

class DoctorHomeFragment : Fragment() {
    private lateinit var iHomeFragment: IHomepage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iHomeFragment.setToolbarTitle("Appointment")
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_doctor_homepage, container, false)

        view.startTimeDefaultDoctorBtn.setOnClickListener {
            showCalenderAndGetTime(it,startTimeDefaultDoctor)
        }
        view.endTimeDefaultBtn.setOnClickListener{
            showCalenderAndGetTime(it,endTimeDefault)
        }
        view.switchSetTime.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                    dayDefaultDoctor.visibility =View.VISIBLE
                    dayDefaultDoctorBtn.visibility = View.VISIBLE
            } else {
                dayDefaultDoctor.visibility =View.GONE
                dayDefaultDoctorBtn.visibility = View.GONE
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        iHomeFragment = activity as IHomepage
    }

    private fun showCalenderAndGetTime(view: View?,textView: TextView) {
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            Toast.makeText(
                view!!.context,
                SimpleDateFormat("HH:mm", Locale.US).format(cal.time),
                Toast.LENGTH_LONG
            ).show()
            textView.text = SimpleDateFormat("HH:mm", Locale.US).format(cal.time)
        }
        TimePickerDialog(
            view!!.context,
            timeSetListener,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            false
        ).show()
    }
}

