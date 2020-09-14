package com.example.app_ointmentt.ui.patient.fragment

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_ointmentt.IHomepage
import com.example.app_ointmentt.R
import com.example.app_ointmentt.adaptersNew.AllDoctorAdapter
import com.example.app_ointmentt.databasing.DoctorDB
import com.example.app_ointmentt.models.Doctor
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder


class SeeAllDoctorsFragment : Fragment(), DoctorDB.GetDoctorsSuccessListener, DoctorDB.GetDoctorsFailureListener {

    private lateinit var iHomepage: IHomepage
    lateinit var doctorsRecyclerView: RecyclerView
    lateinit var mContext: Context
    lateinit var ddb: DoctorDB
    lateinit var allDoctorsArrayOverall: ArrayList<Doctor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iHomepage.setToolbarTitle("All Doctors")
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_see_all_doctors,container,false)

        doctorsRecyclerView = view.findViewById(R.id.allDoctorList)

        mContext = this.context!!

        ddb = DoctorDB(mContext)
        ddb.setGetDoctorsSuccessListener(this)
        ddb.setGetDoctorsFailureListener(this)

        allDoctorsArrayOverall = arrayListOf()

        return view


    }

    override fun onStart() {
        val sh = PreferenceManager.getDefaultSharedPreferences(mContext)
        val patientId = sh.getString("uid", "NONE FOUND").toString()
        val queryOpts = mutableMapOf<String, String>()

        if(patientId == "NONE FOUND"){
            Toast.makeText(mContext, "Failed to fetch doctors. Please login first.", Toast.LENGTH_SHORT).show()
            Log.d("AuthorizationProb", "Failed: No patiend id is found in shared preferences")
        }
        else{

            queryOpts.put("specialty", "ENT")
            ddb.getDoctors(queryOpts)
        }

        super.onStart()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        iHomepage = activity as IHomepage
    }

    private fun initRecyclerView(){
        val doctorsRecyclerViewAdapter = GroupAdapter<GroupieViewHolder>()
        allDoctorsArrayOverall.forEach {
            doctorsRecyclerViewAdapter.add(it)
        }
        doctorsRecyclerView.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL ,false)
        doctorsRecyclerView.adapter = doctorsRecyclerViewAdapter
    }




    override fun getDoctorsSuccess(allDoctorsArray: ArrayList<Doctor>) {
        allDoctorsArray.forEach {
            allDoctorsArrayOverall.add(it)
        }
        initRecyclerView()
    }

    override fun getDoctorsFailure(message: String) {
        Toast.makeText(mContext, "Failed to get doctors", Toast.LENGTH_SHORT).show()
        Log.d("getDoctorsFailure", "Failed: ${message.toString()}")
    }
}
