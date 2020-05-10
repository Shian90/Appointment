package com.example.app_ointmentt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_patient_profile.view.*
import kotlinx.android.synthetic.main.toolbar.*

class PatientProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_patient_profile, container, false)
        view.profilePic.setImageDrawable(profile_image!!.drawable)
        return view
    }
}