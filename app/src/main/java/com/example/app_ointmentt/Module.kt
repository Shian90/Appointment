package com.example.app_ointmentt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_module.*

class Module : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_module)

        patientModuleBtn.setOnClickListener {
            startActivity(Intent(this, LoginPatient::class.java))
        }
        doctorModuleBtn.setOnClickListener {
            startActivity(Intent(this, LoginDoctor::class.java))
        }
    }
}
