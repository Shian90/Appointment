package com.example.app_ointmentt

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.annotation.RequiresApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_register_doctor.*
import kotlinx.android.synthetic.main.activity_register_patient.*
import java.util.*

class RegisterDoctor : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_doctor)

        dobDoctorRegister.setOnClickListener {
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)

            val dialog = DatePickerDialog(this,
                android.R.style.Theme_DeviceDefault_Dialog_Alert,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    dobDoctorRegister.setText("$dayOfMonth/$monthOfYear/$year")
                },
                year, month, day)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.DKGRAY))
            dialog.show()
        }

        val bloodGroups = arrayOf("Choose a blood group", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-")
        bloodGroupDoctorRegister.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, bloodGroups)

        bloodGroupDoctorRegister.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                registerBtnDoctorRegister.isClickable = false
                Toast.makeText(this@RegisterDoctor, "Please choose a bloodgroup",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onItemSelected(p0: AdapterView<*>?, view: View?, p2: Int, id: Long) {
                if(bloodGroups[p2] == "Choose a blood group") {
                    registerBtnDoctorRegister.isClickable = false
               //     Toast.makeText(this@RegisterDoctor, "Please choose a valid bloodgroup", Toast.LENGTH_SHORT).show()
                }else {
                    registerBtnDoctorRegister.isClickable = true
                    Toast.makeText(this@RegisterDoctor, bloodGroups[p2] + " is selected",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        val gender = arrayOf("Choose a gender", "Male", "Female", "Other")

        genderDoctorRegister.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, gender)

        genderDoctorRegister.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                registerBtnDoctorRegister.isClickable = false
                Toast.makeText(this@RegisterDoctor, "Please choose a gender", Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(gender[p2] == "Choose a gender") {
                    registerBtnDoctorRegister.isClickable = false
                 //   Toast.makeText(this@RegisterDoctor,"Please choose a valid gender" , Toast.LENGTH_SHORT).show()
                }else {
                    registerBtnDoctorRegister.isClickable = true
                    Toast.makeText(this@RegisterDoctor, gender[p2] + " is selected",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }

        val addressDistrict = arrayOf("Choose a district",
            "Bagerhat",
            "Bandarban",
            "Barguna",
            "Barisal",
            "Bhola",
            "Bogra",
            "Brahmanbaria",
            "Chandpur",
            "Chittagong",
            "Chuadanga",
            "Comilla",
            "Cox's Bazar",
            "Dhaka",
            "Dinajpur",
            "Faridpur",
            "Feni",
            "Gaibandha",
            "Gazipur",
            "Gopalganj",
            "Habiganj",
            "Jaipurhat",
            "Jamalpur",
            "Jessore",
            "Jhalakati",
            "Jhenaidah",
            "Khagrachari",
            "Khulna",
            "Kishoreganj",
            "Kurigram",
            "Kushtia",
            "Lakshmipur",
            "Lalmonirhat",
            "Madaripur",
            "Magura",
            "Manikganj",
            "Meherpur",
            "Moulvibazar",
            "Munshiganj",
            "Mymensingh",
            "Naogaon",
            "Narail",
            "Narayanganj",
            "Narsingdi",
            "Natore",
            "Nawabganj",
            "Netrakona",
            "Nilphamari",
            "Noakhali",
            "Pabna",
            "Panchagarh",
            "Parbattya Chattagram",
            "Patuakhali",
            "Pirojpur",
            "Rajbari",
            "Rajshahi",
            "Rangpur",
            "Satkhira",
            "Shariatpur",
            "Sherpur",
            "Sirajganj",
            "Sunamganj",
            "Sylhet",
            "Tangail",
            "Thakurgaon"
        )

        addressDistrictDoctorRegister.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, addressDistrict)

        addressDistrictDoctorRegister.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                registerBtnDoctorRegister.isClickable = false
                Toast.makeText(this@RegisterDoctor, "Please choose a district", Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(addressDistrict[p2] == "Choose a district") {
                    registerBtnDoctorRegister.isClickable = false
                  //  Toast.makeText(this@RegisterDoctor,"Please choose a valid district" , Toast.LENGTH_SHORT).show()
                }else {
                    registerBtnDoctorRegister.isClickable = true
                    Toast.makeText(this@RegisterDoctor, addressDistrict[p2] + " is selected",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        val chamberDistrict = arrayOf("Choose your chamber location",
            "Bagerhat",
            "Bandarban",
            "Barguna",
            "Barisal",
            "Bhola",
            "Bogra",
            "Brahmanbaria",
            "Chandpur",
            "Chittagong",
            "Chuadanga",
            "Comilla",
            "Cox's Bazar",
            "Dhaka",
            "Dinajpur",
            "Faridpur",
            "Feni",
            "Gaibandha",
            "Gazipur",
            "Gopalganj",
            "Habiganj",
            "Jaipurhat",
            "Jamalpur",
            "Jessore",
            "Jhalakati",
            "Jhenaidah",
            "Khagrachari",
            "Khulna",
            "Kishoreganj",
            "Kurigram",
            "Kushtia",
            "Lakshmipur",
            "Lalmonirhat",
            "Madaripur",
            "Magura",
            "Manikganj",
            "Meherpur",
            "Moulvibazar",
            "Munshiganj",
            "Mymensingh",
            "Naogaon",
            "Narail",
            "Narayanganj",
            "Narsingdi",
            "Natore",
            "Nawabganj",
            "Netrakona",
            "Nilphamari",
            "Noakhali",
            "Pabna",
            "Panchagarh",
            "Parbattya Chattagram",
            "Patuakhali",
            "Pirojpur",
            "Rajbari",
            "Rajshahi",
            "Rangpur",
            "Satkhira",
            "Shariatpur",
            "Sherpur",
            "Sirajganj",
            "Sunamganj",
            "Sylhet",
            "Tangail",
            "Thakurgaon"
        )

        chamberLocationDistrictDoctorRegister.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, chamberDistrict)

        chamberLocationDistrictDoctorRegister.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                registerBtnDoctorRegister.isClickable = false
                Toast.makeText(this@RegisterDoctor, "Please choose a location", Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(chamberDistrict[p2] == "Choose your chamber location") {
                    registerBtnDoctorRegister.isClickable = false
                    //Toast.makeText(this@RegisterDoctor,"Please choose a valid location" , Toast.LENGTH_SHORT).show()
                }else {
                    registerBtnDoctorRegister.isClickable = true
                    Toast.makeText(this@RegisterDoctor, chamberDistrict[p2] + " is selected",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        val docType = arrayOf("Choose your type", "Medical", "Dental")
        doctorTypeDoctorRegister.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, docType)

        doctorTypeDoctorRegister.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                registerBtnDoctorRegister.isClickable = false
                Toast.makeText(this@RegisterDoctor, "Please choose a type",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onItemSelected(p0: AdapterView<*>?, view: View?, p2: Int, id: Long) {
                if(docType[p2] == "Choose your type") {
                    registerBtnDoctorRegister.isClickable = false
                  //  Toast.makeText(this@RegisterDoctor, "Please choose a valid type", Toast.LENGTH_SHORT).show()
                }else {
                    registerBtnDoctorRegister.isClickable = true
                    Toast.makeText(this@RegisterDoctor, docType[p2] + " is selected",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        registerBtnDoctorRegister.setOnClickListener {
            registerDoctor()
        }

    }

    private fun registerDoctor(){

        val usernameDoctor = usernameDoctorRegister.text.toString()
        val emailDoctorReg = emailDoctorRegister.text.toString().trim()
        val passwordDoctorReg = passwordDoctorRegister.text.toString()
        val dobDoctor = dobDoctorRegister.text.toString().trim()
        val contactDoctor = contactDoctorRegister.text.toString().trim()
        val certificateNumber = certificateRegistrationNumberDoctorRegister.text.toString().trim()
        val doctorType = doctorTypeDoctorRegister.selectedItem.toString().trim()
        val fieldOfSpeciality = specialityDoctorRegister.text.toString().trim()
        val chamberLocation = chamberLocationDistrictDoctorRegister.selectedItem.toString().trim()
        val bloodGroupDoctor = bloodGroupDoctorRegister.selectedItem.toString().trim()
        val genderDoctor = genderDoctorRegister.selectedItem.toString().trim()
        val addressDistrictDoctor = addressDistrictDoctorRegister.selectedItem.toString().trim()
        val addressAreaDoctor = addressAreaDoctorRegister.text.toString().trim()

        if(usernameDoctor.isEmpty() || emailDoctorReg.isEmpty() || passwordDoctorReg.isEmpty() || dobDoctor.isEmpty() || contactDoctor.isEmpty()|| certificateNumber.isEmpty()|| doctorType.isEmpty()|| fieldOfSpeciality.isEmpty()|| chamberLocation.isEmpty() || bloodGroupDoctor.isEmpty() || genderDoctor.isEmpty()|| addressDistrictDoctor.isEmpty()|| addressAreaDoctor.isEmpty()){
            Toast.makeText(this, "Please fill everything up", Toast.LENGTH_SHORT).show()
        }else{

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailDoctorReg, passwordDoctorReg)
                .addOnSuccessListener {
                    Toast.makeText(this, "User is created.", LENGTH_SHORT).show()

                    val refDoctor = FirebaseFirestore.getInstance().collection("Doctors")
                    val docid = FirebaseAuth.getInstance().uid
                    val doctorAttribute = DoctorClass(usernameDoctor, emailDoctorReg, passwordDoctorReg, dobDoctor, contactDoctor, certificateNumber, doctorType, fieldOfSpeciality, chamberLocation, bloodGroupDoctor, genderDoctor, addressDistrictDoctor, addressAreaDoctor)

                    refDoctor.document("$docid").set(doctorAttribute)
                        .addOnSuccessListener {
                            Toast.makeText(this, "User is added to database.", LENGTH_SHORT).show()

                            val loggedInDoctor = FirebaseAuth.getInstance().currentUser
                            loggedInDoctor?.sendEmailVerification()
                                ?.addOnSuccessListener {
                                    Toast.makeText(this, "Email verification sent", LENGTH_SHORT).show()
                                }
                                ?.addOnFailureListener {
                                    Toast.makeText(this, "Email verification failed. Put authentic email. Exception: ${it.message}", LENGTH_SHORT).show()
                                }
                            val intent = Intent(this, HomePageDoctor::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            finish()

                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "User is not added to database. Exception: ${it.message}", LENGTH_SHORT).show()
                        }
                }
                .addOnFailureListener {
                    Toast.makeText(this, "User is not created. Exception: ${it.message}", LENGTH_SHORT).show()
                }
        }
    }
}
