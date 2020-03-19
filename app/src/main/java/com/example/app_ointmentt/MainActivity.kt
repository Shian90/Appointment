package com.example.app_ointmentt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.core.Tag
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userID = FirebaseAuth.getInstance().uid

        Handler().postDelayed(
            {
                if(userID != null){

                    val refPat = FirebaseFirestore.getInstance().collection("Patients")
                    val refDoc = FirebaseFirestore.getInstance().collection("Doctors")

                    refPat.document("$userID").addSnapshotListener { documentSnapshot, _ ->

                        if(documentSnapshot?.exists()!!){
                            startActivity(Intent(this, HomePagePatient::class.java))
                            finish()
                        }
                    }

                    refDoc.document("$userID").addSnapshotListener { documentSnapshot, _ ->

                        if(documentSnapshot?.exists()!!){
                            startActivity(Intent(this, HomePageDoctor::class.java))
                            finish()
                        }
                    }
                }else {
                    startActivity(Intent(this, Module::class.java))
                    finish()
                }
            }, 1000)
    }
}
