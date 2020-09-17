package com.example.app_ointmentt.ui.bottomsheet

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app_ointmentt.R
import com.example.app_ointmentt.databasing.DoctorDB
import com.example.app_ointmentt.databasing.PatientDB
import com.example.app_ointmentt.ui.doctor.fragment.DoctorProfileFragment
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
    private lateinit var specialty: String
    private var spinnerListener: AdapterView.OnItemSelectedListener? =null
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

        val doctorTypeArray = arrayOf("ENT","Cardiology","Dermatology","Endocrinology","Gastroenterology","Nephrology")

        if(arguments!=null){
            field = arguments!!.getString(ARGS_FIELD_NAME).toString()
            Log.d("Field", field)
        }
        if(view.spinner!=null&&field=="specialty"){
            val doctorSpecialtyAdapter = ArrayAdapter(activity as AppCompatActivity, android.R.layout.simple_spinner_item,doctorTypeArray)
            view.spinner.adapter = doctorSpecialtyAdapter

            view.spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    specialty = doctorTypeArray[position]

                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }

        }



        if(field == "specialty"){
            view.spinner.visibility = View.VISIBLE
            view.updateProfileEditText.visibility = View.GONE

        }
        else{
            view.spinner.visibility = View.GONE
            view.updateProfileEditText.visibility = View.VISIBLE
        }
        view.updateProfileField.text = field.capitalize()
        val sharedPreferences: SharedPreferences = activity!!.applicationContext!!.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)
        val userType = sharedPreferences.getString("type",null)
        if(userType.equals("patient")){
            view.spinner.visibility = View.GONE
        }
        view.updateProfileBtn.setOnClickListener{
            val sharedPreferences: SharedPreferences = activity!!.applicationContext!!.getSharedPreferences(sharedPrefFile,
                Context.MODE_PRIVATE)
            val userType = sharedPreferences.getString("type",null)
            if(userType.equals("doctor")){
                val doctorDB = DoctorDB(activity!!.applicationContext!!)
                doctorDB.setUpdateDoctorProfileSuccessListener(this)
                doctorDB.setUpdateDoctorProfileFailureListener(this)
                val updMap = mutableMapOf<String, String>()
                if(field == "specialty"){
                    updMap[field] = specialty

                }
                else{
                    updMap[field] = view.updateProfileEditText.text.toString()
                }
                doctorDB.updateDoctorProfile(updMap)
                changeFragmentFromActivity(fragment = DoctorProfileFragment(),root =  R.id.mainDoctor, activity = activity as AppCompatActivity)
            }
            else if(userType.equals("patient")){

                val patientDB = PatientDB(activity!!.applicationContext!!)
                patientDB.setUpdatePatientProfileSuccessListener(this)
                patientDB.setUpdatePatientProfileFailureListener(this)
                val updMap = mutableMapOf<String, String>()
                updMap[field] = view.updateProfileEditText.text.toString()
                patientDB.updatePatientProfile(updMap)
                changeFragmentFromActivity(fragment = PatientProfileFragment(),root =  R.id.mainPatient, activity = activity as AppCompatActivity)
            }

        }
        return view
    }

    override fun updatePatientProfileSuccess() {
        Toast.makeText(activity!!.applicationContext!!,"Update was successful. Please Refresh to see changes",Toast.LENGTH_SHORT).show()
        activity!!.supportFragmentManager.beginTransaction().remove(this).commit()
    }

    override fun updatePatientProfileFailure(message: String?) {
        Toast.makeText(activity!!.applicationContext!!,"Update was failed.",Toast.LENGTH_SHORT).show()
        activity!!.supportFragmentManager.beginTransaction().remove(this).commit()

    }

    override fun updateDoctorProfileSuccess() {
        Toast.makeText(activity!!.applicationContext!!,"Update was successful. Please Refresh to see changes",Toast.LENGTH_SHORT).show()
        activity!!.supportFragmentManager.beginTransaction().remove(this).commit()

    }

    override fun updateDoctorProfileFailure() {
        Toast.makeText(activity!!.applicationContext!!,"Update was failed.",Toast.LENGTH_SHORT).show()
        activity!!.supportFragmentManager.beginTransaction().remove(this).commit()
    }

}