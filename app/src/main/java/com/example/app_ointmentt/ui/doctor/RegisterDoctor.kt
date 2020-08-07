package com.example.app_ointmentt.ui.doctor

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app_ointmentt.R
import com.example.app_ointmentt.databasing.AuthDB
import com.example.app_ointmentt.models.Doctor
import kotlinx.android.synthetic.main.activity_register_doctor.*

class RegisterDoctor : AppCompatActivity(),AuthDB.RegisterDoctorBasicSuccessListener,
    AuthDB.RegisterDoctorBasicFailureListener,
    AuthDB.LoginDoctorSuccessListener,
    AuthDB.LoginDoctorFailureListener  {
    lateinit var password: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_doctor)

     registerBtnDoctorRegister.setOnClickListener {
            registerDoctor()
        }

    }

    private fun registerDoctor(){

        val username = usernameDoctorRegister.text.toString()
        val email = emailDoctorRegister.text.toString().trim()
        password = passwordDoctorRegister.text.toString()
        val bmdcNumber = certificateRegistrationNumberDoctorRegister.text.toString().trim()

        if(username.isEmpty() || email.isEmpty() || password.isEmpty()|| bmdcNumber.isEmpty()){
            Toast.makeText(this, "Please fill everything up", Toast.LENGTH_SHORT).show()
      }else{
            val authDB = AuthDB(this)
            authDB.setRegisterDoctorBasicSuccessListener(this)
            authDB.setRegisterDoctorBasicFailureListener(this)
            authDB.registerDoctorBasic(username,email,password,bmdcNumber)
        }
    }

    override fun registerDoctorBasicSuccess(doctor: Doctor) {
        val authDB = AuthDB(this)
        authDB.setLoginDoctorSuccessListener(this)
        authDB.setLoginDoctorFailureListener(this)
        Log.d("success","success")
        Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show()
        authDB.loginDoctor(doctor.email,password)
    }

    override fun registerDoctorBasicFailure() {
        Log.d("failure","failure")
        Toast.makeText(this, "Failure registering doctor", Toast.LENGTH_SHORT).show()
    }

    override fun loginDoctorSuccess(doctor: Doctor) {
        Log.d("success","success")
        Toast.makeText(this, "Logged in Successfully", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, HomePageDoctor::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    override fun loginDoctorFailure() {
        Log.d("failure","failure")
        Toast.makeText(this, "Failure Logging in", Toast.LENGTH_SHORT).show()
    }
}
