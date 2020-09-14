package com.example.app_ointmentt.utils

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.app_ointmentt.ui.bottomsheet.BottomSheetFragment

fun changeFragmentFromFragment(
    fragment: Fragment,
    context: Context,
    root: Int,
    addToBackStack: Boolean = false, bundle: Bundle? = null) {

    val activity = context as AppCompatActivity
    if(bundle != null){
        fragment.arguments = bundle
    }
    val transition  = activity.supportFragmentManager.beginTransaction()

    transition.replace(
        root,
        fragment,
        fragment.javaClass.simpleName
    )
    if(addToBackStack){
        transition.addToBackStack(fragment.javaClass.simpleName)
    }
    transition.commit()
}

fun changeFragmentFromActivity(fragment: Fragment,root: Int,activity: AppCompatActivity){
    activity.supportFragmentManager.beginTransaction().replace(
        root,
        fragment,
        fragment.javaClass.simpleName)
        .commit()
}


fun invokeBottomModalSheet(view: View?,field: String){
    val bottomSheetFragment = BottomSheetFragment.newInstance(field)
    val activity = view!!.context as AppCompatActivity
    bottomSheetFragment.show(activity.supportFragmentManager, bottomSheetFragment.tag)
}

fun changeActivity(){
    //write important piece of code here...
}