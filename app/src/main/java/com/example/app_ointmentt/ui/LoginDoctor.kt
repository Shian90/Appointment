package com.example.app_ointmentt.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app_ointmentt.R
import kotlinx.android.synthetic.main.activity_login_doctor.*

class LoginDoctor : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_doctor)

        loginBtnDoctorLogin.setOnClickListener {
            //login() -- modify this function
            val intent = Intent(this, HomePageDoctor::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        signUpBtnDoctorLogin.setOnClickListener {
            startActivity(Intent(this, RegisterDoctor::class.java))
        }


        patientModuleBtn.setOnClickListener {
            startActivity(Intent(this, LoginPatient::class.java))
        }

    }

}
