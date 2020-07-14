package com.example.app_ointmentt.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app_ointmentt.IHomepage
import com.example.app_ointmentt.R
import com.example.app_ointmentt.ui.fragment.PatientHistoryFragment
import com.example.app_ointmentt.ui.fragment.PatientHomeFragment
import com.example.app_ointmentt.ui.fragment.PatientNotificationFragment
import com.example.app_ointmentt.ui.fragment.PatientProfileFragment
import com.example.app_ointmentt.utils.changeFragmentFromActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home_page_patient.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*

/*This is the Entry Point to a Patient Account*/
class HomePagePatient : AppCompatActivity(),IHomepage {

    //root is the meat part of the view where we inflate everything
    private val root = R.id.mainPatient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page_patient)


        //changeToolbarTitle(toolbar = toolbar,txt = "Appointment")
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        loadHomeFragment(this)

        //On Clicking the profile image on right top we go to the profile fragment
        profile_image.setOnClickListener {
            changeFragmentFromActivity(fragment = PatientProfileFragment(),root = root, activity = this)
        }
    }

    private fun loadHomeFragment(activity: AppCompatActivity){
        changeFragmentFromActivity(fragment = PatientHomeFragment(),root = root,activity = activity)
    }

    //Listener to the bottom Navigation View
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.home -> {
                //changeToolbarTitle(toolbar = toolbar,txt = "Appointment")
                changeFragmentFromActivity(fragment = PatientHomeFragment(),root = root,activity = this)
                return@OnNavigationItemSelectedListener true
            }
            R.id.history -> {
                //changeToolbarTitle(toolbar = toolbar,txt = "History")
                changeFragmentFromActivity(fragment = PatientHistoryFragment(),root = root,activity = this)
                return@OnNavigationItemSelectedListener true
            }
            R.id.notification -> {
                //changeToolbarTitle(toolbar = toolbar,txt = "Notification")
                changeFragmentFromActivity(fragment = PatientNotificationFragment(),root = root,activity = this)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun setToolbarTitle(fragmentTag: String?) {
        toolbar!!.toolbarTitle.text = fragmentTag
    }
}
