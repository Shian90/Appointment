package com.example.app_ointmentt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home_page_patient.*

class HomePagePatient : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page_patient)

        logoutBtnPatientHomePage.setOnClickListener {
            logout()
        }
    }

    private fun logout(){

        FirebaseAuth.getInstance().signOut()

        val intent = Intent(this, Module::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        Toast.makeText(this, "User has been logged out", Toast.LENGTH_SHORT).show()
        finish()

    }
}
