package com.example.app_ointmentt.ui.patient

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import com.example.app_ointmentt.R
import com.example.app_ointmentt.databasing.AuthDB
import com.example.app_ointmentt.models.Patient
import com.example.app_ointmentt.ui.doctor.LoginDoctor
import kotlinx.android.synthetic.main.activity_login_patient.*

class LoginPatient : AppCompatActivity(),AuthDB.LoginPatientSuccessListener,AuthDB.LoginPatientFailureListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_patient)

        loginBtnPatientLogin.setOnClickListener {
            login()
//            val authDB = AuthDB(this)
//            authDB.setLoginPatientSuccessListener(this)
//            authDB.setLoginPatientFailureListener(this)
//            Log.d("loginpatient", emailPatientLogin.text.toString()+passwordPatientLogin.text.toString())
//            authDB.loginPatient(emailPatientLogin.text.toString(),passwordPatientLogin.text.toString())
//            val intent = Intent(this, HomePagePatient::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
//            startActivity(intent)
//            finish()

        }

        signUpBtnPatientLogin.setOnClickListener {
            startActivity(Intent(this, RegisterPatient::class.java))
        }

        doctorModuleBtn.setOnClickListener {
            startActivity(Intent(this, LoginDoctor::class.java))
        }
    }

    private fun login(){

        val email = emailPatientLogin.text.toString().trim()
        val password = passwordPatientLogin.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill up everything", Toast.LENGTH_SHORT).show()
        } else {
            val authDB = AuthDB(this)
            authDB.setLoginPatientSuccessListener(this)
            authDB.setLoginPatientFailureListener(this)
            Log.d("loginpatient", emailPatientLogin.text.toString()+passwordPatientLogin.text.toString())
            authDB.loginPatient(emailPatientLogin.text.toString(),passwordPatientLogin.text.toString())
        }

    }

    override fun loginPatientSuccess(patient: Patient) {
        Log.d("success","success")
        Toast.makeText(this, "Logged in Successfully", LENGTH_SHORT).show()
        startActivity(Intent(this, HomePagePatient::class.java))
        finish()
    }

    override fun loginPatientFailure() {
        Log.d("failure","failure")
        Toast.makeText(this, "Failure", LENGTH_SHORT).show()
    }
}
