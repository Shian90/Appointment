package com.example.app_ointmentt.ui


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.app_ointmentt.R
import com.example.app_ointmentt.ui.fragment.PatientHistoryFragment
import com.example.app_ointmentt.ui.fragment.PatientHomeFragment
import com.example.app_ointmentt.ui.fragment.PatientNotificationFragment
import com.example.app_ointmentt.ui.fragment.PatientProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home_page_patient.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*

class HomePagePatient : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page_patient)
        clickProfileImage()
        changeToolbarTitle(toolbar = toolbar,txt = "Appointment")
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        loadHomeFragment()
    }
    private fun clickProfileImage(){
        profile_image.setOnClickListener{
            var fragment = PatientProfileFragment()
            supportFragmentManager.beginTransaction().replace(R.id.mainPatient, fragment, fragment.javaClass.simpleName)
                .commit()
        }
    }
    private fun loadHomeFragment(){
        supportFragmentManager.beginTransaction().replace(
            R.id.mainPatient,
            PatientHomeFragment(),
            PatientHomeFragment().javaClass.simpleName)
            .commit()
    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.home -> {
                changeToolbarTitle(toolbar = toolbar,txt = "Appointment")
                val fragment =
                    PatientHomeFragment()
                supportFragmentManager.beginTransaction().replace(R.id.mainPatient, fragment, fragment.javaClass.simpleName)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.history -> {
                changeToolbarTitle(toolbar = toolbar,txt = "History")
                val fragment =
                    PatientHistoryFragment()
                supportFragmentManager.beginTransaction().replace(R.id.mainPatient, fragment, fragment.javaClass.simpleName)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.notification -> {
                changeToolbarTitle(toolbar = toolbar,txt = "Notification")
                val fragment =
                    PatientNotificationFragment()
                supportFragmentManager.beginTransaction().replace(R.id.mainPatient, fragment, fragment.javaClass.simpleName)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun changeToolbarTitle(toolbar: View?, txt: String){
        toolbar!!.toolbarTitle.setText(txt)
    }

}
