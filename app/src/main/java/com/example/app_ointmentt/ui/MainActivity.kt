package app_ointmentt.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.app_ointmentt.R
import com.example.app_ointmentt.ui.doctor.HomePageDoctor
import com.example.app_ointmentt.ui.patient.HomePagePatient
import com.example.app_ointmentt.ui.patient.LoginPatient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val sharedPrefFile = "appointmentSharedPref"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val animLogo = AnimationUtils.loadAnimation(this,
            R.anim.dropanimation
        )
        val logo = logoImageView
        logo.startAnimation(animLogo)
        val sharedPreferences:SharedPreferences= this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val userID = sharedPreferences.getString("uid",null)
        val userType = sharedPreferences.getString("type",null)
        Handler().postDelayed(
            {

                if(userID != null){
                    if(userType.equals("doctor")){
                        startActivity(Intent(this, HomePageDoctor::class.java))
                        finish()
                    }
                    else if(userType.equals("patient")){
                        startActivity(Intent(this, HomePagePatient::class.java))
                        finish()
                    }
                    else{
                        Log.d("Doctor type not found", "No type specified")
                        startActivity(Intent(this, LoginPatient::class.java))
                        finish()
                    }
                    /* Old Implementation of Firestore Authentication
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
                    }*/

                }else {
                    startActivity(Intent(this, LoginPatient::class.java))
                    finish()
                }
            }, 1000)
    }
}
