package com.example.app_ointmentt.tools.onClickListeners

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.app_ointmentt.R
import com.example.app_ointmentt.databasing.AppointmentDB
import com.example.app_ointmentt.databasing.SlotDB
import com.example.app_ointmentt.models.Appointment
import com.example.app_ointmentt.tools.adaptersNew.SlotAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.OnItemClickListener
import kotlinx.android.synthetic.main.layout_slot_details.view.*

class SlotOnClickListener(val context: Context, val usertype: String) : OnItemClickListener, SlotDB.deleteSlotByIdSuccessListener, SlotDB.deleteSlotByIdFailureListener, AppointmentDB.CreateAppointmentSuccessListener, AppointmentDB.CreateAppointmentFailureListener {

    lateinit var dialog: AlertDialog
    lateinit var layoutInflater: LayoutInflater
    lateinit var slotdb: SlotDB
    lateinit var appdb: AppointmentDB

    override fun onItemClick(item: Item<*>, view: View) {

        slotdb = SlotDB(context)
        slotdb.setDeleteSlotByIdSuccessListener(this)
        slotdb.setDeleteSlotByIdFailureListener(this)

        appdb = AppointmentDB(context)
        appdb.setCreateAppointmentSuccessListener(this)
        appdb.setCreateAppointmentFailureListener(this)

        item as SlotAdapter

        val slot = item.slot

        layoutInflater = LayoutInflater.from(context)
        dialog = AlertDialog.Builder(context).create()
        val dialogview = layoutInflater.inflate(R.layout.layout_slot_details, null)
        dialogview.date_slot_details.text = slot.dateOfSlot
        dialogview.start_time_slot_details.text = slot.startTime
        dialogview.end_time_slot_details.text = slot.endTime
        val button = dialogview.findViewById<Button>(R.id.generic_button)

        if ( usertype == "doctor" )
        {
            button.text = "Delete slot"
            button.setOnClickListener {
                if ( slot.status == 1 )
                {
                    Toast.makeText(context, "This slot is already booked. Please ask the patient to delete the appointment", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                else
                {
                    slotdb.deleteSlotById(slot.id)
                }
            }
        }
        else if ( usertype == "patient" )
        {
            button.text = "Book an appointment"
            button.setOnClickListener {
                appdb.createAppointment(slot.id)
            }
        }

        dialog.setView(dialogview)
        dialog.show()
        dialog.setCancelable(true)
    }

    override fun deleteSlotByIdSuccess() {
        Toast.makeText(context, "Slot deleted successfully", Toast.LENGTH_SHORT).show()
        dialog.dismiss()
    }

    override fun createAppointmentSuccess(app: Appointment) {
        Toast.makeText(context, "Appointment created successfully", Toast.LENGTH_SHORT).show()
        dialog.dismiss()
    }

    override fun createAppointmentFailure(message: String?) {
        Toast.makeText(context, "Failed to create appointment", Toast.LENGTH_SHORT).show()
        dialog.dismiss()
    }

    override fun deleteSlotByIdFailure(message: String?) {
        Toast.makeText(context, "Failed to delete slot", Toast.LENGTH_SHORT).show()
        dialog.dismiss()
    }
}