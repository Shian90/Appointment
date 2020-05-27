package com.example.app_ointmentt.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.app_ointmentt.IHomepage
import com.example.app_ointmentt.R
import com.example.app_ointmentt.ui.LoginPatient
import com.example.app_ointmentt.utils.invokeBottomModalSheet
import kotlinx.android.synthetic.main.fragment_patient_profile.view.*

class PatientProfileFragment:Fragment() {
    private lateinit var iHomeFragment: IHomepage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iHomeFragment.setToolbarTitle("Profile")
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val view = inflater.inflate(R.layout.fragment_patient_profile,container,false)
        Glide.with(this)
            .load("https://i.pinimg.com/originals/d0/f4/fc/d0f4fc818a35285642ba057436fc8720.jpg")
            .placeholder(R.drawable.profile)
            .into(view.profile_image);

        view.patientUserNameEditBtn.setOnClickListener {
            invokeBottomModalSheet(it)
        }

        view.patientAddressEditBtn.setOnClickListener {
            invokeBottomModalSheet(it)
        }
        view.patientBloodGroupEditBtn.setOnClickListener {
            invokeBottomModalSheet(it)
        }

        view.patientDOBEditBtn.setOnClickListener {
            invokeBottomModalSheet(it)
        }
        view.patientDiseasesEditBtn.setOnClickListener {
            invokeBottomModalSheet(it)
        }
        view.patientEmailEditBtn.setOnClickListener {
            invokeBottomModalSheet(it)
        }
        view.patientGenderEditBtn.setOnClickListener {
            invokeBottomModalSheet(it)
        }
        view.patientPasswordEditBtn.setOnClickListener {
            invokeBottomModalSheet(it)
        }
        view.patientLogoutBtn.setOnClickListener{
            val intent = Intent(activity, LoginPatient::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            activity!!.startActivity(intent)
            activity!!.finish()
        }
        view.patientPhoneNumberEditBtn.setOnClickListener {
            invokeBottomModalSheet(it)
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        iHomeFragment = activity as IHomepage
    }
}