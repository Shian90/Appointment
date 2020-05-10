package com.example.app_ointmentt.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.example.app_ointmentt.R
import com.example.app_ointmentt.models.DoctorClass
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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

    private fun login(){

        val emailDoctorLog = emailDoctorLogin.text.toString().trim()
        val passwordDoctorLog = passwordDoctorLogin.text.toString()

        if (emailDoctorLog.isEmpty() || passwordDoctorLog.isEmpty()) {
            Toast.makeText(this, "Please fill up everything", Toast.LENGTH_SHORT).show()
        } else {
            val ref = FirebaseFirestore.getInstance().collection("Doctors")
            var emailFound:Int = 0

            ref.addSnapshotListener { snapshot, exception ->

                if(exception!= null)
                {
                    // Toast.makeText(this, "An exception has occured.", Toast.LENGTH_SHORT).show()
                    Log.d("CHECK","No data in database. Exception: ${exception.message}")
                }
                else{
                    for( info in snapshot!!) {

                        val data = info.toObject(DoctorClass::class.java)

                        if(data.emailDoctor.toString().trim() == emailDoctorLog){
                            emailFound = 1
                            break
                        }
                    }

                    if (emailFound == 1){

                        FirebaseAuth.getInstance().signInWithEmailAndPassword(emailDoctorLog, passwordDoctorLog)
                            .addOnSuccessListener {
                                Toast.makeText(
                                    this,
                                    "Congratulations! User Logged in.",
                                    LENGTH_SHORT
                                ).show()
                                val intent = Intent(this, HomePageDoctor::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                                finish()
                            }
                            .addOnFailureListener {
                                Toast.makeText(
                                    this,
                                    "User failed to Log in ${it.message}",
                                    LENGTH_SHORT
                                ).show()
                            }

                    } else {
                        Toast.makeText(
                            this, "User is not registered.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

    }
}
