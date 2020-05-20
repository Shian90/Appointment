package com.example.app_ointmentt.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.app_ointmentt.R
import com.example.app_ointmentt.ui.LoginPatient
import com.example.app_ointmentt.ui.bottomsheet.BottomSheetFragment
import kotlinx.android.synthetic.main.fragment_patient_profile.view.*

class PatientProfileFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val view = inflater.inflate(R.layout.fragment_patient_profile,container,false)

        view.patientUserNameEditBtn.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            val activity = it.context as AppCompatActivity
            bottomSheetFragment.show(activity.supportFragmentManager, bottomSheetFragment.tag)
        }

        view.patientAddressEditBtn.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            val activity = it.context as AppCompatActivity
            bottomSheetFragment.show(activity.supportFragmentManager, bottomSheetFragment.tag)
        }
        view.patientBloodGroupEditBtn.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            val activity = it.context as AppCompatActivity
            bottomSheetFragment.show(activity.supportFragmentManager, bottomSheetFragment.tag)
        }

        view.patientDOBEditBtn.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            val activity = it.context as AppCompatActivity
            bottomSheetFragment.show(activity.supportFragmentManager, bottomSheetFragment.tag)
        }
        view.patientDiseasesEditBtn.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            val activity = it.context as AppCompatActivity
            bottomSheetFragment.show(activity.supportFragmentManager, bottomSheetFragment.tag)
        }
        view.patientEmailEditBtn.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            val activity = it.context as AppCompatActivity
            bottomSheetFragment.show(activity.supportFragmentManager, bottomSheetFragment.tag)
        }
        view.patientGenderEditBtn.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            val activity = it.context as AppCompatActivity
            bottomSheetFragment.show(activity.supportFragmentManager, bottomSheetFragment.tag)
        }
        view.patientPasswordEditBtn.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            val activity = it.context as AppCompatActivity
            bottomSheetFragment.show(activity.supportFragmentManager, bottomSheetFragment.tag)
        }
        view.patientLogoutBtn.setOnClickListener{
            val intent = Intent(activity, LoginPatient::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            activity!!.startActivity(intent)
            activity!!.finish()
        }
        view.patientPhoneNumberEditBtn.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            val activity = it.context as AppCompatActivity
            bottomSheetFragment.show(activity.supportFragmentManager, bottomSheetFragment.tag)
        }
        return view
    }
}