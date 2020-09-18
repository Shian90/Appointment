package com.example.app_ointmentt.tools.onClickListeners

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.xwray.groupie.Item
import com.xwray.groupie.OnItemClickListener

class appointmentOnClickListener(val context: Context) : OnItemClickListener{

    lateinit var dialog: AlertDialog
    lateinit var layoutInflater: LayoutInflater

    override fun onItemClick(item: Item<*>, view: View) {
        layoutInflater = LayoutInflater.from(context)
        dialog = AlertDialog.Builder(context).create()
        val dialogview = layoutInflater.inflate(/*LAYOUT RESOURCE*/)

        //dialogview.findViewById diye baakigula set korbo

        dialog.setView(dialogview)
        dialog.setCancelable(true)
        dialog.show()

        //button chaaple dialog.dismiss()
    }
}