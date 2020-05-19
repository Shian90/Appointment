package com.example.app_ointmentt.utils

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

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