package com.example.app_ointmentt.ui.doctor.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.app_ointmentt.IHomepage
import com.example.app_ointmentt.R
import com.example.app_ointmentt.ui.patient.LoginPatient
import com.example.app_ointmentt.utils.invokeBottomModalSheet
import kotlinx.android.synthetic.main.fragment_doctor_profile.view.*

class DoctorProfileFragment: Fragment() {
    private lateinit var iHomeFragment: IHomepage

    //sharedPreference File Name
    private val sharedPrefFile = "appointmentSharedPref"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iHomeFragment.setToolbarTitle("Profile")
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val view =  inflater.inflate(R.layout.fragment_doctor_profile,container,false)

        view.doctorUserNameEditBtn.setOnClickListener {
            invokeBottomModalSheet(it)
        }

        view.doctorLocationEditBtn.setOnClickListener {
            invokeBottomModalSheet(it)
        }
        view.doctorBloodGroupEditBtn.setOnClickListener {
            invokeBottomModalSheet(it)
        }

        view.doctorDOBEditBtn.setOnClickListener {
            invokeBottomModalSheet(it)
        }

        view.doctorEmailEditBtn.setOnClickListener {
            invokeBottomModalSheet(it)
        }
        view.doctorGenderEditBtn.setOnClickListener {
            invokeBottomModalSheet(it)
        }
        view.doctorTypeEditBtn.setOnClickListener {
            invokeBottomModalSheet(it)
        }
        view.doctorBMDCEditBtn.setOnClickListener {
            invokeBottomModalSheet(it)
        }
        view.doctorPhoneNumberEditBtn.setOnClickListener {
            invokeBottomModalSheet(it)
        }
        view.doctorPasswordEditBtn.setOnClickListener {
            invokeBottomModalSheet(it)
        }
        view.doctorLogoutBtn.setOnClickListener {
            val sharedPreferences: SharedPreferences = context!!.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("jwt", null)
            editor.putString("uid", null)
            editor.putString("type", null)
            editor.apply()
            val intent = Intent(activity, LoginPatient::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            activity!!.startActivity(intent)
            activity!!.finish()
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        iHomeFragment = activity as IHomepage
    }
}