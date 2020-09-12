package com.example.app_ointmentt.ui.patient.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.app_ointmentt.IHomepage
import com.example.app_ointmentt.R
import com.example.app_ointmentt.adapter.DoctorTypeAdapter
import com.example.app_ointmentt.adaptersNew.RatedDoctorAdapter
import com.example.app_ointmentt.databasing.DoctorDB
import com.example.app_ointmentt.models.RatedDoctor
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class PatientHomeFragment : Fragment(), DoctorDB.GetTopDoctorsSuccessListener, DoctorDB.GetTopDoctorsFailureListener{

    private lateinit var doctorTypeAdapter: DoctorTypeAdapter
    private lateinit var iHomepage: IHomepage
    lateinit var doctorTypeRecyclerView: RecyclerView
    lateinit var mContext: Context
    lateinit var ddb: DoctorDB
    lateinit var ratedDoctorArrayOverall: ArrayList<RatedDoctor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iHomepage.setToolbarTitle("Appointment")
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_patient_homepage, container, false)

        doctorTypeRecyclerView = view.findViewById(R.id.doctorTyperRecyclerView)

        mContext = this.context!!
        ddb = DoctorDB(mContext)


        return view
    }

    override fun onStart() {
        val queryOpts = mutableMapOf<String, String>()
        queryOpts.put("specialty", "ENT")
        ddb.getTopDoctors(queryOpts)
        initRecyclerView()
        super.onStart()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        iHomepage = activity as IHomepage
    }

    private fun initRecyclerView(){
        val doctorTypeRecyclerViewAdapter = GroupAdapter<GroupieViewHolder>()
        ratedDoctorArrayOverall.forEach {
            doctorTypeRecyclerViewAdapter.add(RatedDoctorAdapter(it))
        }
        doctorTypeRecyclerView.adapter = doctorTypeRecyclerViewAdapter
    }

    override fun getTopDoctorsSuccess(ratedDoctorArray: ArrayList<RatedDoctor>) {
        ratedDoctorArray.forEach {
            ratedDoctorArrayOverall.add(it)
        }
    }

    override fun getTopDoctorsFailure(message: String) {
        TODO("Not yet implemented")
    }
}

