package com.example.app_ointmentt.ui.doctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.app_ointmentt.R
import kotlinx.android.synthetic.main.activity_register_doctor.*

class RegisterDoctor : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_doctor)

//        dobDoctorRegister.setOnClickListener {
//            val cal = Calendar.getInstance()
//            val year = cal.get(Calendar.YEAR)
//            val month = cal.get(Calendar.MONTH)
//            val day = cal.get(Calendar.DAY_OF_MONTH)
//
//            val dialog = DatePickerDialog(this,
//                android.R.style.Theme_DeviceDefault_Dialog_Alert,
//                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
//                    dobDoctorRegister.setText("$dayOfMonth/$monthOfYear/$year")
//                },
//                year, month, day)
//            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.DKGRAY))
//            dialog.show()
//        }

//        val bloodGroups = arrayOf("Choose a blood group", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-")
//        bloodGroupDoctorRegister.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, bloodGroups)

//        bloodGroupDoctorRegister.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                registerBtnDoctorRegister.isClickable = false
//                Toast.makeText(this@RegisterDoctor, "Please choose a bloodgroup",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }

//            override fun onItemSelected(p0: AdapterView<*>?, view: View?, p2: Int, id: Long) {
//                if(bloodGroups[p2] == "Choose a blood group") {
//                    registerBtnDoctorRegister.isClickable = false
//               //     Toast.makeText(this@RegisterDoctor, "Please choose a valid bloodgroup", Toast.LENGTH_SHORT).show()
//                }else {
//                    registerBtnDoctorRegister.isClickable = true
//                    Toast.makeText(this@RegisterDoctor, bloodGroups[p2] + " is selected",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//        }

//        val gender = arrayOf("Choose a gender", "Male", "Female", "Other")

//        genderDoctorRegister.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, gender)

//        genderDoctorRegister.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                registerBtnDoctorRegister.isClickable = false
//                Toast.makeText(this@RegisterDoctor, "Please choose a gender", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                if(gender[p2] == "Choose a gender") {
//                    registerBtnDoctorRegister.isClickable = false
//                 //   Toast.makeText(this@RegisterDoctor,"Please choose a valid gender" , Toast.LENGTH_SHORT).show()
//                }else {
//                    registerBtnDoctorRegister.isClickable = true
//                    Toast.makeText(this@RegisterDoctor, gender[p2] + " is selected",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//
//        }

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

//        addressDistrictDoctorRegister.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, addressDistrict)

//        addressDistrictDoctorRegister.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                registerBtnDoctorRegister.isClickable = false
//                Toast.makeText(this@RegisterDoctor, "Please choose a district", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                if(addressDistrict[p2] == "Choose a district") {
//                    registerBtnDoctorRegister.isClickable = false
//                  //  Toast.makeText(this@RegisterDoctor,"Please choose a valid district" , Toast.LENGTH_SHORT).show()
//                }else {
//                    registerBtnDoctorRegister.isClickable = true
//                    Toast.makeText(this@RegisterDoctor, addressDistrict[p2] + " is selected",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//        }

//        val chamberDistrict = arrayOf("Choose your chamber location",
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

//        chamberLocationDistrictDoctorRegister.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, chamberDistrict)

//        chamberLocationDistrictDoctorRegister.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                registerBtnDoctorRegister.isClickable = false
//                Toast.makeText(this@RegisterDoctor, "Please choose a location", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                if(chamberDistrict[p2] == "Choose your chamber location") {
//                    registerBtnDoctorRegister.isClickable = false
//                    //Toast.makeText(this@RegisterDoctor,"Please choose a valid location" , Toast.LENGTH_SHORT).show()
//                }else {
//                    registerBtnDoctorRegister.isClickable = true
//                    Toast.makeText(this@RegisterDoctor, chamberDistrict[p2] + " is selected",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//        }
//
//        val docType = arrayOf("Choose your type", "Medical", "Dental")
//        doctorTypeDoctorRegister.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, docType)
//
//        doctorTypeDoctorRegister.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                registerBtnDoctorRegister.isClickable = false
//                Toast.makeText(this@RegisterDoctor, "Please choose a type",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }

//            override fun onItemSelected(p0: AdapterView<*>?, view: View?, p2: Int, id: Long) {
//                if(docType[p2] == "Choose your type") {
//                    registerBtnDoctorRegister.isClickable = false
//                  //  Toast.makeText(this@RegisterDoctor, "Please choose a valid type", Toast.LENGTH_SHORT).show()
//                }else {
//                    registerBtnDoctorRegister.isClickable = true
//                    Toast.makeText(this@RegisterDoctor, docType[p2] + " is selected",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//        }

        registerBtnDoctorRegister.setOnClickListener {
            registerDoctor()
        }

    }

    private fun registerDoctor(){

        val usernameDoctor = usernameDoctorRegister.text.toString()
        val emailDoctorReg = emailDoctorRegister.text.toString().trim()
        val passwordDoctorReg = passwordDoctorRegister.text.toString()
//        val dobDoctor = dobDoctorRegister.text.toString().trim()
//        val contactDoctor = contactDoctorRegister.text.toString().trim()
        val certificateNumber = certificateRegistrationNumberDoctorRegister.text.toString().trim()
//        val doctorType = doctorTypeDoctorRegister.selectedItem.toString().trim()
//        val fieldOfSpeciality = specialityDoctorRegister.text.toString().trim()
//        val chamberLocation = chamberLocationDistrictDoctorRegister.selectedItem.toString().trim()
//        val bloodGroupDoctor = bloodGroupDoctorRegister.selectedItem.toString().trim()
//        val genderDoctor = genderDoctorRegister.selectedItem.toString().trim()
//        val addressDistrictDoctor = addressDistrictDoctorRegister.selectedItem.toString().trim()
//        val addressAreaDoctor = addressAreaDoctorRegister.text.toString().trim()

        if(usernameDoctor.isEmpty() || emailDoctorReg.isEmpty() || passwordDoctorReg.isEmpty()|| certificateNumber.isEmpty()){
            Toast.makeText(this, "Please fill everything up", Toast.LENGTH_SHORT).show()
      }else{
//
//           //Add Signup here
            val intent = Intent(this, HomePageDoctor::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }
}
