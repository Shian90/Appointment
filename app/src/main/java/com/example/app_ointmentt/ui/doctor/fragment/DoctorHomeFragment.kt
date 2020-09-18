package com.example.app_ointmentt.ui.doctor.fragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_ointmentt.IHomepage
import com.example.app_ointmentt.R
import com.example.app_ointmentt.databasing.SlotDB
import com.example.app_ointmentt.models.Slot
import com.example.app_ointmentt.tools.adaptersNew.SlotAdapter
import com.example.app_ointmentt.tools.onClickListeners.SlotOnClickListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_doctor_homepage.*
import kotlinx.android.synthetic.main.fragment_doctor_homepage.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DoctorHomeFragment : Fragment(), SlotDB.createSlotSuccessListener, SlotDB.createSlotFailureListener, SlotDB.viewAllSlotsByDoctorSuccessListener, SlotDB.viewAllSlotsByDoctorFailureListener {

    private lateinit var iHomeFragment: IHomepage
    lateinit var startTime: String
    lateinit var endTime: String
    lateinit var numSlots: String
    lateinit var slotDialog: Dialog
    lateinit var slotdb: SlotDB
    lateinit var mContext: Context
    lateinit var slotsRecyclerView: RecyclerView
    lateinit var slotsOverall: ArrayList<Slot>
    lateinit var doctorId: String
    lateinit var doneDialog: Dialog

    //sharedPreference File Name
    private val sharedPrefFile = "appointmentSharedPref"

    var year: Int? = null
    var month: Int? = null
    var day: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iHomeFragment.setToolbarTitle("Appointment")
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_doctor_homepage, container, false)

        mContext = this.context!!

        val sharedPreferences: SharedPreferences = mContext.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)
        doctorId = sharedPreferences.getString("uid","")!!

        slotsOverall = arrayListOf()

        slotdb = SlotDB(mContext)
        slotdb.setCreateSlotSuccessListener(this)
        slotdb.setCreateSlotFailureListener(this)
        slotdb.setViewAllSlotsByDoctorSuccessListener(this)
        slotdb.setViewAllSlotsByDoctorFailureListener(this)

        slotsRecyclerView = view.findViewById(R.id.doctor_slot_recycler_view)

        view.noOfSlotsBtn.setOnClickListener {
            showDialog()
        }

        view.dayDefaultDoctorBtn.setOnClickListener {
            showCalendarView()
        }

        view.startTimeDefaultDoctorBtn.setOnClickListener {
            showCalenderAndGetTime(it,startTimeDefaultDoctor)
        }

        view.endTimeDefaultBtn.setOnClickListener{
            showCalenderAndGetTime(it,endTimeDefault)
        }

        view.saveSlotsBtn.setOnClickListener {
            Log.d("Sthhhh", "$year-$month-$day, $startTime, $endTime, ${numSlots.toInt()}")
            sendInfo("$year-$month-$day", startTime, endTime, numSlots.toInt(), 0)
        }

        view.switchSetTime.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                    dayDefaultDoctor.visibility =View.VISIBLE
                    dayDefaultDoctorBtn.visibility = View.VISIBLE
            } else {
                dayDefaultDoctor.visibility =View.GONE
                dayDefaultDoctorBtn.visibility = View.GONE

                //If the switch is clicked at least once and reverted
                getCurrentDate()
            }
        }
        return view
    }

    override fun onStart() {
        slotdb.viewAllSlotsByDoctor(doctorId)
        super.onStart()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        iHomeFragment = activity as IHomepage
    }

    private fun getCurrentDate(){
        val cal = Calendar.getInstance()
        year = cal.get(Calendar.YEAR)
        month = cal.get(Calendar.MONTH)
        day = cal.get(Calendar.DAY_OF_MONTH)
    }

    private fun showCalendarView(){
        //Initializing with current date if switch is clicked and day button is being used.
         getCurrentDate()

        val dpd = DatePickerDialog(activity as AppCompatActivity, DatePickerDialog.OnDateSetListener { view, myear, mmonth, mdayOfMonth ->
            year = myear
            month = mmonth
            day = mdayOfMonth
            dayDefaultDoctor.text = "$year - $month - $day"
        }, year!!, month!!, day!!)

        dpd.show()
    }

    private fun showDialog(){
        slotDialog = Dialog(mContext)
        slotDialog.setContentView(R.layout.cardview_doctor_numslots)
        slotDialog.show()
        numSlots = ""
        val okBtn = slotDialog.findViewById<View>(R.id.okBtn)
        val textt = slotDialog.findViewById<EditText>(R.id.numSlotsText)
        okBtn.setOnClickListener {
            numSlots = textt.text.toString()
            noOfSlots.text = textt.text
            slotDialog.dismiss()
        }
    }

    private fun showCalenderAndGetTime(view: View?,textView: TextView) {
        val cal = Calendar.getInstance()
        var selectedTime: String

        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)

            Toast.makeText(
                view!!.context,
                SimpleDateFormat("HH:mm", Locale.US).format(cal.time),
                Toast.LENGTH_LONG
            ).show()

            //If the switch is never clicked
            if(year == null && month == null && day == null){
                getCurrentDate()
            }

            textView.text = SimpleDateFormat("HH:mm", Locale.US).format(cal.time)
            selectedTime = SimpleDateFormat("HH:mm", Locale.US).format(cal.time).toString()

            if(textView.equals(startTimeDefaultDoctor) ){
                startTime = selectedTime
            }
            else if(textView.equals(endTimeDefault)){
                endTime = selectedTime
            }
        }
        TimePickerDialog(
            view!!.context,
            timeSetListener,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            false
        ).show()
    }

    private fun sendInfo(date: String, startTime: String, endTime: String, numSlots: Int, status: Int){
        slotdb.createSlot(date, startTime, endTime, numSlots, status)
    }

    override fun createSlotSuccess(slotArray: ArrayList<Slot>) {
        doneDialog = Dialog(mContext)
        doneDialog.setContentView(R.layout.fragment_appointment_confirmation)
        doneDialog.show()

        Log.d("SlotCreationSuccessful", "Successfully Created slots.")
        Toast.makeText(mContext, "You have successfully created slots for appointments.", Toast.LENGTH_SHORT).show()
    }

    override fun createSlotFailure(message: String?) {
        Log.d("SlotCreationFailed", "Failed. $message")
        Toast.makeText(mContext, "Slots creation has been failed", Toast.LENGTH_SHORT).show()
    }

    override fun viewAllSlotsByDoctorSuccessListener(slotsArray: ArrayList<Slot>) {
        slotsArray.forEach {
            slotsOverall.add(it)
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val slotsRecyclerAdapter = GroupAdapter<GroupieViewHolder>()
        slotsOverall.forEach {
            slotsRecyclerAdapter.add(SlotAdapter(it))
        }
        slotsRecyclerView.layoutManager = LinearLayoutManager(mContext)
        val listener = SlotOnClickListener(mContext, "doctor")
        slotsRecyclerAdapter.setOnItemClickListener(listener)
        slotsRecyclerView.adapter = slotsRecyclerAdapter
    }

    override fun viewAllSlotsByDoctorFailureListener(message: String?) {
        Log.d("Slot View", "No Slot Found")
    }

}

