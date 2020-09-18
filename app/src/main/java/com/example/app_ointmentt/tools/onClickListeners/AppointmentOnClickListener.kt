package com.example.app_ointmentt.tools.onClickListeners

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.example.app_ointmentt.R
import com.example.app_ointmentt.databasing.AppointmentDB
import com.example.app_ointmentt.databasing.RatingDB
import com.example.app_ointmentt.models.Appointment
import com.example.app_ointmentt.tools.adaptersNew.HistoryAdapter
import com.example.app_ointmentt.tools.adaptersNew.NotificationAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.OnItemClickListener
import kotlin.math.ceil

class AppointmentOnClickListener(val context: Context, val usertype: String, val apptype: String) : OnItemClickListener, AppointmentDB.CompleteAppointmentSuccessListener, AppointmentDB.CompleteAppointmentFailureListener, AppointmentDB.UpdatePrescriptionSuccessListener, AppointmentDB.UpdatePrescriptionFailureListener, AppointmentDB.DeleteAppointmentByIdSuccessListener, AppointmentDB.DeleteAppointmentByIdFailureListener, RatingDB.UpdateRatingByIdSuccessListener, RatingDB.UpdateRatingByIdFailureListener{

    lateinit var dialog1: AlertDialog
    lateinit var dialog2: AlertDialog
    lateinit var layoutInflater: LayoutInflater
    lateinit var appdb: AppointmentDB
    lateinit var app: Appointment
    lateinit var ratingdb: RatingDB

    override fun onItemClick(item: Item<*>, view: View) {

        if ( apptype == "history" )
        {
            item as HistoryAdapter
            app = item.app
        }
        else if ( apptype == "notif" )
        {
            item as NotificationAdapter
            app = item.app
        }

        appdb = AppointmentDB(context)
        appdb.setCompleteAppointmentSuccessListener(this)
        appdb.setCompleteAppointmentFailureListener(this)
        appdb.setUpdatePrescriptionSuccessListener(this)
        appdb.setUpdatePrescriptionFailureListener(this)
        appdb.setDeleteAppointmentByIdSuccessListener(this)
        appdb.setDeleteAppointmentByIdSuccessListener(this)

        ratingdb = RatingDB(context)
        ratingdb.setUpdateRatingByIdSuccessListener(this)
        ratingdb.setUpdateRatingByIdFailureListener(this)

        layoutInflater = LayoutInflater.from(context)
        dialog1 = AlertDialog.Builder(context).create()
        dialog2 = AlertDialog.Builder(context).create()
        val dialogview = layoutInflater.inflate(R.layout.layout_appointment_details, null)

        val button1 = dialogview.findViewById<Button>(R.id.view_appointment_layout_button1)
        val button2 = dialogview.findViewById<Button>(R.id.view_appointment_layout_button2)
        val drugTextBox = dialogview.findViewById<EditText>(R.id.prescription_appointment_drugs)
        val diagnosisTextBox = dialogview.findViewById<EditText>(R.id.prescription_appointment_diagnosis)
        dialogview.findViewById<TextView>(R.id.doctor_name_appointment_details2).text = app.slot.doctor.name
        dialogview.findViewById<TextView>(R.id.patient_name_appointment_details2).text = app.patient.name
        dialogview.findViewById<TextView>(R.id.date_appointment_details2).text = app.slot.dateOfSlot
        dialogview.findViewById<TextView>(R.id.start_time_appointment_details).text = app.slot.startTime
        dialogview.findViewById<TextView>(R.id.end_time_appointment_details).text = app.slot.endTime

        if ( usertype == "doctor" )
        {
            var drugs: String
            var diagnosis: String
            if ( app.prescription == "NONE" )
            {
                drugs = "N/A"
                diagnosis = "N/A"
            }
            else
            {
                var temp = app.prescription
                diagnosis = temp.substring(app.prescription.indexOf("Diagnosis: ").plus(11), app.prescription.indexOf("Drugs: "))
                temp = app.prescription
                drugs = temp.substring(app.prescription.indexOf("Drugs: ").plus(7), app.prescription.lastIndex+1)
            }
            drugTextBox.setText(drugs)
            diagnosisTextBox.setText(diagnosis)
            drugTextBox.isFocusable = false
            drugTextBox.isEnabled = false
            diagnosisTextBox.isFocusable = false
            diagnosisTextBox.isEnabled = false
            button2.visibility = View.GONE
            if ( apptype == "notif" )
            {
                button1.text = "Update Prescription"
                button1.setOnClickListener {
                    if ( drugTextBox.text.isEmpty() || diagnosisTextBox.text.isEmpty() )
                    {
                        Toast.makeText(context, "Please enter a valid prescription (diagnosis, drugs) before updating it", Toast.LENGTH_SHORT).show()
                    }
                    else
                    {
                        val pres = "Diagnosis: " + diagnosisTextBox.text.toString() + " Drugs: " + drugTextBox.text.toString()
                        appdb.updatePrescription(app.id, pres)
                    }
                }
            }
            else if ( apptype == "history" )
            {
                button1.visibility = View.GONE
            }
        }
        else if ( usertype == "patient" )
        {
            var drugs: String
            var diagnosis: String
            if ( app.prescription == "NONE" )
            {
                drugs = "N/A"
                diagnosis = "N/A"
            }
            else
            {
                var temp = app.prescription
                diagnosis = temp.substring(app.prescription.indexOf("Diagnosis: ").plus(11), app.prescription.indexOf("Drugs: "))
                temp = app.prescription
                drugs = temp.substring(app.prescription.indexOf("Drugs: ").plus(7), app.prescription.lastIndex+1)
            }
            drugTextBox.setText(drugs)
            diagnosisTextBox.setText(diagnosis)
            drugTextBox.isFocusable = false
            drugTextBox.isEnabled = false
            diagnosisTextBox.isFocusable = false
            diagnosisTextBox.isEnabled = false
            if ( apptype == "notif" )
            {
                button1.text = "Complete appointment"
                button1.setOnClickListener {
                    Log.d("COMPBUTTONPRESSED", "${app.id}")
                    appdb.completeAppointment(app.id)
                }
                button2.text = "Delete appointment"
                button2.setOnClickListener {
                    appdb.deleteAppointmentById(app.id)
                }
            }
            else
            {
                button1.visibility = View.GONE
                button2.visibility = View.GONE
            }
        }
        dialog1.setView(dialogview)
        dialog1.setCancelable(true)
        dialog1.show()
    }

    override fun completeAppointmentSuccess() {
        Toast.makeText(context, "Successfully completed appointment", Toast.LENGTH_SHORT).show()
        dialog1.dismiss()
        val newDialogView = layoutInflater.inflate(R.layout.cardview_rating_dotor, null)
        val ratingBar = newDialogView.findViewById<RatingBar>(R.id.rating_bar)
        val rateButton = newDialogView.findViewById<Button>(R.id.rating_button)
        rateButton.setOnClickListener {
            val rating = ceil(ratingBar.rating)
            val rateMap = mutableMapOf<String, String>()
            rateMap["doctorId"] = app.slot.doctorId
            rateMap["rating"] = rating.toString()
            ratingdb.updateRatingById(rateMap)
        }
        dialog2.setView(newDialogView)
        dialog2.setCancelable(true)
        dialog2.show()
    }

    override fun completeAppointmentFailure(message: String?) {
        Log.d("APPCOMPFAIL", "$message")
        Toast.makeText(context, "Failed to complete appointment. Please try again.", Toast.LENGTH_SHORT).show()
        dialog1.dismiss()
    }

    override fun deleteAppointmentByIdSuccess() {
        Toast.makeText(context, "Successfully deleted appointment", Toast.LENGTH_SHORT).show()
        dialog1.dismiss()
    }

    override fun deleteAppointmentByIdFailure(message: String?) {
        Toast.makeText(context, "Failed to delete appointment", Toast.LENGTH_SHORT).show()
        dialog1.dismiss()
    }

    override fun updatePrescriptionSuccess() {
        Toast.makeText(context, "Successfully updated prescription", Toast.LENGTH_SHORT).show()
        dialog1.dismiss()
    }

    override fun updatePrescriptionFailure(message: String?) {
        Toast.makeText(context, "Failed to update prescription. Please try again.", Toast.LENGTH_SHORT).show()
        dialog1.dismiss()
    }

    override fun updateRatingByIdSuccess() {
        Toast.makeText(context, "Successfully rated doctor", Toast.LENGTH_SHORT).show()
        dialog2.dismiss()
    }

    override fun updateRatingByIdFailure(message: String?) {
        Toast.makeText(context, "Failed to rate doctor", Toast.LENGTH_SHORT).show()
        dialog2.dismiss()
    }
}