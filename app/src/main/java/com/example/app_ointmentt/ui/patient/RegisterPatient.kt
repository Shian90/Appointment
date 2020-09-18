package com.example.app_ointmentt.ui.patient

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import com.example.app_ointmentt.R
import com.example.app_ointmentt.databasing.AuthDB
import com.example.app_ointmentt.models.Patient
import kotlinx.android.synthetic.main.activity_register_patient.*

class RegisterPatient : AppCompatActivity(), AuthDB.RegisterPatientBasicSuccessListener, AuthDB.RegisterPatientBasicFailureListener, AuthDB.LoginPatientSuccessListener, AuthDB.LoginPatientFailureListener {


    lateinit var password: String
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_patient)

        registerBtnPatientRegister.setOnClickListener {
            registerPatient()
        }
    }

    private fun registerPatient(){
        val username = usernamePatientRegister.text.toString()
        val email = emailPatientRegister.text.toString().trim()
        password = passwordPatientRegister.text.toString()

        if(username.isEmpty() || email.isEmpty() || password.isEmpty() ){
            Toast.makeText(this, "Please fill everything up", LENGTH_SHORT).show()
        }else{
            //Add Sign up Options here.
            val authDB = AuthDB(this)
            authDB.setRegisterPatientBasicSuccessListener(this)
            authDB.setRegisterPatientBasicFailureListener(this)
            authDB.registerPatientBasic(username,email,password)


        }
    }

    override fun registerPatientBasicFailure() {
        Log.d("failure","failure")
        Toast.makeText(this, "Failure", LENGTH_SHORT).show()
    }

    override fun registerPatientBasicSuccess(patient: Patient) {
        val authDB = AuthDB(this)
        authDB.setLoginPatientSuccessListener(this)
        authDB.setLoginPatientFailureListener(this)
        Log.d("success","success")
        Toast.makeText(this, "Registered Successfully", LENGTH_SHORT).show()
        authDB.loginPatient(patient.email,password)
    }

    override fun loginPatientSuccess(patient: Patient) {
        Log.d("success","success")
        Toast.makeText(this, "Logged in Successfully", LENGTH_SHORT).show()

        val intent = Intent(this, HomePagePatient::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    override fun loginPatientFailure() {
        Log.d("failure","failure")
        Toast.makeText(this, "Failure", LENGTH_SHORT).show()
    }
}
