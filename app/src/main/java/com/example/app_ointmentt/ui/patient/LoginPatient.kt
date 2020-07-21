package com.example.app_ointmentt.ui.patient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.example.app_ointmentt.R
import com.example.app_ointmentt.models.PatientClass
import com.example.app_ointmentt.ui.doctor.LoginDoctor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_login_patient.*

class LoginPatient : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_patient)

        loginBtnPatientLogin.setOnClickListener {
            //login() -- modify this function
            val intent = Intent(this, HomePagePatient::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()

        }

        signUpBtnPatientLogin.setOnClickListener {
            startActivity(Intent(this, RegisterPatient::class.java))
        }

        doctorModuleBtn.setOnClickListener {
            startActivity(Intent(this, LoginDoctor::class.java))
        }
    }

    private fun login(){

        val emailPatientLog = emailPatientLogin.text.toString().trim()
        val passwordPatientLog = passwordPatientLogin.text.toString()

        if (emailPatientLog.isEmpty() || passwordPatientLog.isEmpty()) {
            Toast.makeText(this, "Please fill up everything", Toast.LENGTH_SHORT).show()
        } else {
            val ref = FirebaseFirestore.getInstance().collection("Patients")
            var emailFound:Int = 0

            ref.addSnapshotListener { snapshot, exception ->

                if(exception!= null)
                {
                    // Toast.makeText(this, "An exception has occured.", Toast.LENGTH_SHORT).show()
                    Log.d("CHECK","No data in database. Exception: ${exception.message}")
                }
                else{
                    for( info in snapshot!!) {

                        val data = info.toObject(PatientClass::class.java)

                        if(data.emailPatient.toString().trim() == emailPatientLog){
                            emailFound = 1
                            break
                        }
                    }

                    if (emailFound == 1){

                        FirebaseAuth.getInstance().signInWithEmailAndPassword(emailPatientLog, passwordPatientLog)
                            .addOnSuccessListener {
                                Toast.makeText(
                                    this,
                                    "Congratulations! User Logged in.",
                                    LENGTH_SHORT
                                ).show()
                                val intent = Intent(this, HomePagePatient::class.java)
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
