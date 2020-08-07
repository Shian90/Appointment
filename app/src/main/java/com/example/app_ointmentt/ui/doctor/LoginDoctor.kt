package com.example.app_ointmentt.ui.doctor

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app_ointmentt.R
import com.example.app_ointmentt.databasing.AuthDB
import com.example.app_ointmentt.models.Doctor
import com.example.app_ointmentt.ui.patient.LoginPatient
import kotlinx.android.synthetic.main.activity_login_doctor.*

class LoginDoctor : AppCompatActivity(),AuthDB.LoginDoctorSuccessListener,AuthDB.LoginDoctorFailureListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_doctor)

        loginBtnDoctorLogin.setOnClickListener {
            login()
        }

        signUpBtnDoctorLogin.setOnClickListener {
            startActivity(Intent(this, RegisterDoctor::class.java))
        }


        patientModuleBtn.setOnClickListener {
            startActivity(Intent(this, LoginPatient::class.java))
        }

    }
    private fun login(){

        val email = emailDoctorLogin.text.toString().trim()
        val password = passwordDoctorLogin.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill up everything", Toast.LENGTH_SHORT).show()
        } else {
            val authDB = AuthDB(this)
            authDB.setLoginDoctorSuccessListener(this)
            authDB.setLoginDoctorFailureListener(this)
            Log.d("logindoctor", emailDoctorLogin.text.toString()+passwordDoctorLogin.text.toString())
            authDB.loginDoctor(email,password)
        }

    }

    override fun loginDoctorSuccess(doctor: Doctor) {
        Log.d("success","success")
        Toast.makeText(this, "Logged in Successfully", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, HomePageDoctor::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    override fun loginDoctorFailure() {
        Log.d("failure","failure")
        Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show()
    }

}
