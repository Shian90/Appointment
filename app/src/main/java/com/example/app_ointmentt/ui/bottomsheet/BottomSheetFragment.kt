package com.example.app_ointmentt.ui.bottomsheet

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app_ointmentt.R
import com.example.app_ointmentt.databasing.DoctorDB
import com.example.app_ointmentt.databasing.PatientDB
import com.example.app_ointmentt.ui.patient.fragment.PatientProfileFragment
import com.example.app_ointmentt.utils.changeFragmentFromActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_bottom_sheet_dialog.view.*

class BottomSheetFragment : BottomSheetDialogFragment(),
    PatientDB.UpdatePatientProfileSuccessListener,
    PatientDB.UpdatePatientProfileFailureListener,
    DoctorDB.UpdateDoctorProfileFailureListener,
    DoctorDB.UpdateDoctorProfileSuccessListener{
    private val sharedPrefFile = "appointmentSharedPref"
    private lateinit var field: String
    companion object{
        private const val ARGS_FIELD_NAME = "field"
        fun newInstance(field: String): BottomSheetFragment{
            val bottomSheetFragment = BottomSheetFragment()
            val args  = Bundle()
            args.putString(BottomSheetFragment.ARGS_FIELD_NAME,field)
            bottomSheetFragment.arguments = args
            return bottomSheetFragment
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_sheet_dialog, container, false)
        if(arguments!=null){
            field = arguments!!.getString(ARGS_FIELD_NAME).toString()
        }
        view.updateProfileField.text = field.capitalize()
        view.updateProfileBtn.setOnClickListener{
            val sharedPreferences: SharedPreferences = activity!!.applicationContext!!.getSharedPreferences(sharedPrefFile,
                Context.MODE_PRIVATE)
            val userType = sharedPreferences.getString("type",null)
            if(userType.equals("doctor")){
                val doctorDB = DoctorDB(activity!!.applicationContext!!)
                doctorDB.setUpdateDoctorProfileSuccessListener(this)
                doctorDB.setUpdateDoctorProfileFailureListener(this)
                val updMap = mutableMapOf<String, String>()
                updMap[field] = view.updateProfileEditText.text.toString()
                doctorDB.updateDoctorProfile(updMap)
            }
            else if(userType.equals("patient")){
                val patientDB = PatientDB(activity!!.applicationContext!!)
                patientDB.setUpdatePatientProfileSuccessListener(this)
                patientDB.setUpdatePatientProfileFailureListener(this)
                val updMap = mutableMapOf<String, String>()
                updMap[field] = view.updateProfileEditText.text.toString()
                patientDB.updatePatientProfile(updMap)
            }

        }
        return view
    }

    override fun updatePatientProfileSuccess() {
        Toast.makeText(activity!!.applicationContext!!,"Update was successful. Please Refresh to see changes",Toast.LENGTH_SHORT).show()
        activity!!.supportFragmentManager.beginTransaction().remove(this).commit()
        changeFragmentFromActivity(fragment = PatientProfileFragment(),root =  R.id.mainPatient, activity = activity as AppCompatActivity)
    }

    override fun updatePatientProfileFailure(message: String?) {
        Toast.makeText(activity!!.applicationContext!!,"Update was failed.",Toast.LENGTH_SHORT).show()
        activity!!.supportFragmentManager.beginTransaction().remove(this).commit()
        changeFragmentFromActivity(fragment = PatientProfileFragment(),root =  R.id.mainPatient, activity = activity as AppCompatActivity)
    }

    override fun updateDoctorProfileSuccess() {
        Toast.makeText(activity!!.applicationContext!!,"Update was successful. Please Refresh to see changes",Toast.LENGTH_SHORT).show()
        activity!!.supportFragmentManager.beginTransaction().remove(this).commit()
        changeFragmentFromActivity(fragment = PatientProfileFragment(),root =  R.id.mainPatient, activity = activity as AppCompatActivity)
    }

    override fun updateDoctorProfileFailure() {
        Toast.makeText(activity!!.applicationContext!!,"Update was failed.",Toast.LENGTH_SHORT).show()
        activity!!.supportFragmentManager.beginTransaction().remove(this).commit()
        changeFragmentFromActivity(fragment = PatientProfileFragment(),root =  R.id.mainPatient, activity = activity as AppCompatActivity)
    }

}