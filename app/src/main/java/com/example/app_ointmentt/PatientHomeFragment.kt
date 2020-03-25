package com.example.app_ointmentt

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_type_of_doctor.*
import kotlinx.android.synthetic.main.fragment_type_of_doctor.view.*

class PatientHomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_type_of_doctor, container, false)

        view.logoutBtnPatientHomePage.setOnClickListener { view ->
            logout()
        }
        // Return the fragment view/layout
        return view
    }
    private fun logout(){

        FirebaseAuth.getInstance().signOut()
        val intent = Intent(activity, Module::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        Toast.makeText(activity, "User has been logged out", Toast.LENGTH_SHORT).show()
        activity?.finish()
    }
}

