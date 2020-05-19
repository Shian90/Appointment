package com.example.app_ointmentt.utils

import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.toolbar.view.*

fun changeFragmentFromFragment(fragment: Fragment,context: Context,root: Int) {
    val activity = context as AppCompatActivity
    activity.supportFragmentManager.beginTransaction().replace(
        root,
        fragment,
        fragment.javaClass.simpleName
    )
        .commit()
}

fun changeFragmentFromActivity(fragment: Fragment,root: Int,activity: AppCompatActivity){
    activity.supportFragmentManager.beginTransaction().replace(
        root,
        fragment,
        fragment.javaClass.simpleName)
        .commit()
}

fun changeToolbarTitle(toolbar: View?, txt: String){
    toolbar!!.toolbarTitle.text = txt
}

