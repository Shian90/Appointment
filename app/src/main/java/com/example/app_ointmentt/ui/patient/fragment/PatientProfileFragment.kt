package com.example.app_ointmentt.ui.patient.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.app_ointmentt.IHomepage
import com.example.app_ointmentt.R
import com.example.app_ointmentt.databasing.PatientDB
import com.example.app_ointmentt.models.Patient
import com.example.app_ointmentt.ui.patient.LoginPatient
import com.example.app_ointmentt.utils.invokeBottomModalSheet
import kotlinx.android.synthetic.main.fragment_patient_profile.*
import kotlinx.android.synthetic.main.fragment_patient_profile.view.*

class PatientProfileFragment:Fragment(),
    PatientDB.GetPatientByIdSuccessListener,
    PatientDB.GetPatientByIdFailureListener{
    private lateinit var iHomeFragment: IHomepage
    //sharedPreference File Name
    private val sharedPrefFile = "appointmentSharedPref"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iHomeFragment.setToolbarTitle("Profile")
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val view = inflater.inflate(R.layout.fragment_patient_profile,container,false)
        //Retrieve Information from Backend using API calls.
        val context = activity?.applicationContext
        if (context != null) {
            setPatientProfileFromAPI(view,context)
        }
        //Profile Picture From Given URL.
        Glide.with(this)
            .load("https://i.pinimg.com/originals/d0/f4/fc/d0f4fc818a35285642ba057436fc8720.jpg")
            .placeholder(R.drawable.profile)
            .into(view.profile_image);
        /** Add Update Button For All Fields **/
        view.patientUserNameEditBtn.setOnClickListener {
            invokeBottomModalSheet(it,"name")
        }

        view.patientAddressEditBtn.setOnClickListener {
            invokeBottomModalSheet(it,"address")
        }
        view.patientBloodGroupEditBtn.setOnClickListener {
            invokeBottomModalSheet(it,"blood")
        }

        view.patientDOBEditBtn.setOnClickListener {
            invokeBottomModalSheet(it,"dob")
        }
        view.patientDiseasesEditBtn.setOnClickListener {
            invokeBottomModalSheet(it,"past")
        }
        view.patientEmailEditBtn.setOnClickListener {
            invokeBottomModalSheet(it,"email")
        }
        view.patientGenderEditBtn.setOnClickListener {
            invokeBottomModalSheet(it,"gender")
        }

        view.patientLogoutBtn.setOnClickListener{
            val sharedPreferences: SharedPreferences = context!!.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("jwt", null)
            editor.putString("uid", null)
            editor.putString("type", null)
            editor.apply()
            val intent = Intent(activity, LoginPatient::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            activity!!.startActivity(intent)
            activity!!.finish()
        }
        view.patientPhoneNumberEditBtn.setOnClickListener {
            invokeBottomModalSheet(it,"phone")
        }
        return view
    }

    private fun setPatientProfileFromAPI(view: View?,context: Context) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)
        val patientDB = PatientDB(context)
        patientDB.setGetPatientByIdSuccessListener(this)
        patientDB.setGetPatientByIdFailureListener(this)
        val uid = sharedPreferences.getString("uid","")
        if (uid != null) {
            patientDB.getPatientById(uid)
        }
        else
            return

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        iHomeFragment = activity as IHomepage
    }

    override fun getPatientByIdSuccess(patient: Patient) {
        ProfileUsername.text = patient.name
        profile_email.text = patient.email
        if(patient.isDOBInitialized()){
            profile_dob.text = patient.dob
        }
        if(patient.isGenderInitialized()){
            profile_gender.text = patient.gender
        }
        if(patient.isAddressInitialized()){
            profile_address.text = patient.address

        }
        if(patient.isPhoneInitialized()){
            profile_phone_number.text = patient.phone
        }
        if(patient.isBloodInitialized()){
            profile_blood_group.text = patient.blood
        }

        if(patient.isPastInitialized()){
            profile_diseases.text = patient.past
        }
    }

    override fun getPatientByIdFailure(message: String?) {
        Log.d("getPatientByIdFailure", "getPatientByIdFailure: Failed to retrieve user info")
        Toast.makeText(context,"Failed to get User Profile. Please Try Later. ${message}",Toast.LENGTH_SHORT).show()
    }
}