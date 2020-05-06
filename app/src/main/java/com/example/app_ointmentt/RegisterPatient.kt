package com.example.app_ointmentt

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_register_patient.*
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class RegisterPatient : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_patient)

//        dobPatientRegister.setOnClickListener {
//            val cal = Calendar.getInstance()
//            val year = cal.get(Calendar.YEAR)
//            val month = cal.get(Calendar.MONTH)
//            val day = cal.get(Calendar.DAY_OF_MONTH)
//
//            val dialog = DatePickerDialog(this,
//                android.R.style.Theme_DeviceDefault_Dialog_Alert,
//                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
//                    dobPatientRegister.setText("$dayOfMonth/$monthOfYear/$year")
//                },
//                year, month, day)
//            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.DKGRAY))
//            dialog.show()
//        }
//
//        val bloodGroups = arrayOf("Choose a blood group", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-")
//        bloodGroupPatientRegister.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, bloodGroups)
//
//        bloodGroupPatientRegister.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                registerBtnPatientRegister.isClickable = false
//                Toast.makeText(this@RegisterPatient, "Please choose a bloodgroup", LENGTH_SHORT).show()
//            }
//
//            override fun onItemSelected(p0: AdapterView<*>?, view: View?, p2: Int, id: Long) {
//                if(bloodGroups[p2] == "Choose a blood group") {
//                    registerBtnPatientRegister.isClickable = false
//                    //Toast.makeText(this@RegisterPatient, "Please choose a valid bloodgroup", LENGTH_SHORT).show()
//                }else {
//                    registerBtnPatientRegister.isClickable = true
//                    Toast.makeText(this@RegisterPatient, bloodGroups[p2] + " is selected", LENGTH_SHORT).show()
//                }
//            }
//
//        }
//
//        val gender = arrayOf("Choose a gender", "Male", "Female", "Other")
//
//        genderPatientRegister.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, gender)
//
//        genderPatientRegister.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                registerBtnPatientRegister.isClickable = false
//                Toast.makeText(this@RegisterPatient, "Please choose a gender", LENGTH_SHORT).show()
//            }
//
//            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                if(gender[p2] == "Choose a gender") {
//                    registerBtnPatientRegister.isClickable = false
//                   // Toast.makeText(this@RegisterPatient,"Please choose a valid gender" , LENGTH_SHORT).show()
//                }else {
//                    registerBtnPatientRegister.isClickable = true
//                    Toast.makeText(this@RegisterPatient, gender[p2] + " is selected", LENGTH_SHORT).show()
//                }
//            }
//
//        }
//
//        val addressDistrict = arrayOf("Choose a district",
//            "Bagerhat",
//            "Bandarban",
//            "Barguna",
//            "Barisal",
//            "Bhola",
//            "Bogra",
//            "Brahmanbaria",
//            "Chandpur",
//            "Chittagong",
//            "Chuadanga",
//            "Comilla",
//            "Cox's Bazar",
//            "Dhaka",
//            "Dinajpur",
//            "Faridpur",
//            "Feni",
//            "Gaibandha",
//            "Gazipur",
//            "Gopalganj",
//            "Habiganj",
//            "Jaipurhat",
//            "Jamalpur",
//            "Jessore",
//            "Jhalakati",
//            "Jhenaidah",
//            "Khagrachari",
//            "Khulna",
//            "Kishoreganj",
//            "Kurigram",
//            "Kushtia",
//            "Lakshmipur",
//            "Lalmonirhat",
//            "Madaripur",
//            "Magura",
//            "Manikganj",
//            "Meherpur",
//            "Moulvibazar",
//            "Munshiganj",
//            "Mymensingh",
//            "Naogaon",
//            "Narail",
//            "Narayanganj",
//            "Narsingdi",
//            "Natore",
//            "Nawabganj",
//            "Netrakona",
//            "Nilphamari",
//            "Noakhali",
//            "Pabna",
//            "Panchagarh",
//            "Parbattya Chattagram",
//            "Patuakhali",
//            "Pirojpur",
//            "Rajbari",
//            "Rajshahi",
//            "Rangpur",
//            "Satkhira",
//            "Shariatpur",
//            "Sherpur",
//            "Sirajganj",
//            "Sunamganj",
//            "Sylhet",
//            "Tangail",
//            "Thakurgaon"
//        )
//
//        addressDistrictPatientRegister.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, addressDistrict)
//
//        addressDistrictPatientRegister.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                registerBtnPatientRegister.isClickable = false
//                Toast.makeText(this@RegisterPatient, "Please choose a district", LENGTH_SHORT).show()
//            }
//
//            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                if(addressDistrict[p2] == "Choose a district") {
//                    registerBtnPatientRegister.isClickable = false
//                   // Toast.makeText(this@RegisterPatient,"Please choose a valid district" , LENGTH_SHORT).show()
//                }else {
//                    registerBtnPatientRegister.isClickable = true
//                    Toast.makeText(this@RegisterPatient, addressDistrict[p2] + " is selected", LENGTH_SHORT).show()
//                }
//            }
//
//        }

        registerBtnPatientRegister.setOnClickListener {
            registerPatient()
        }
    }

    private fun registerPatient(){

        val usernamePatient = usernamePatientRegister.text.toString()
        val emailPatientReg = emailPatientRegister.text.toString().trim()
        val passwordPatientReg = passwordPatientRegister.text.toString()
//        val dobPatient = dobPatientRegister.text.toString().trim()
//        val contactPatient = contactPatientRegister.text.toString().trim()
//        val bloodGroupPatient = bloodGroupPatientRegister.selectedItem.toString().trim()
//        val genderPatient = genderPatientRegister.selectedItem.toString().trim()
//        val addressDistrictPatient = addressDistrictPatientRegister.selectedItem.toString().trim()
//        val addressAreaPatient = addressAreaPatientRegister.text.toString().trim()

        if(usernamePatient.isEmpty() || emailPatientReg.isEmpty() || passwordPatientReg.isEmpty() ){
            Toast.makeText(this, "Please fill everything up", LENGTH_SHORT).show()
        }else{
            //Add Sign up Options here.
            val intent = Intent(this, HomePagePatient::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }
}
