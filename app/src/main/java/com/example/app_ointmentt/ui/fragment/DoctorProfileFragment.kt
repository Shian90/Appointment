package com.example.app_ointmentt.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.app_ointmentt.R
import com.example.app_ointmentt.ui.LoginDoctor
import kotlinx.android.synthetic.main.fragment_doctor_profile.view.*

class DoctorProfileFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val view =  inflater.inflate(R.layout.fragment_doctor_profile,container,false)
        view.doctorLogoutBtn.setOnClickListener {
            val intent = Intent(activity, LoginDoctor::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            activity!!.startActivity(intent)
            activity!!.finish()
        }
        return view
    }
}