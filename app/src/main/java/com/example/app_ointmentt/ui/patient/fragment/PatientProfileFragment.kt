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
            .load("data:image/png;base64,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")
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
        ProfileUsername!!.text = patient.name
        profile_email!!.text = patient.email
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
        Toast.makeText(context,"Failed to get User Profile. Please Try Later. $message",Toast.LENGTH_SHORT).show()
    }
}