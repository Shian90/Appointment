package com.example.app_ointmentt.ui


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.app_ointmentt.R
import com.example.app_ointmentt.ui.fragment.DoctorHistoryFragment
import com.example.app_ointmentt.ui.fragment.DoctorHomeFragment
import com.example.app_ointmentt.ui.fragment.DoctorNotificationFragment
import com.example.app_ointmentt.ui.fragment.PatientHomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home_page_doctor.*
import kotlinx.android.synthetic.main.activity_home_page_patient.bottomNavigationView
import kotlinx.android.synthetic.main.toolbar.view.*

class HomePageDoctor : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page_doctor)
        changeToolbarTitle(toolbar = toolbar2, txt = "Appointment")
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        loadHomeFragment()
    }
    private fun loadHomeFragment(){
        supportFragmentManager.beginTransaction().replace(
            R.id.mainDoctor,
            PatientHomeFragment(),
            PatientHomeFragment().javaClass.simpleName)
            .commit()
    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.home -> {
                changeToolbarTitle(toolbar = toolbar2, txt = "Appointment")
                val fragment =
                    DoctorHomeFragment()
                supportFragmentManager.beginTransaction().replace(R.id.mainDoctor, fragment, fragment.javaClass.simpleName)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.history -> {
                changeToolbarTitle(toolbar = toolbar2, txt = "History")
                val fragment =
                    DoctorHistoryFragment()
                supportFragmentManager.beginTransaction().replace(R.id.mainDoctor, fragment, fragment.javaClass.simpleName)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.notification -> {
                changeToolbarTitle(toolbar = toolbar2, txt = "Notification")
                val fragment =
                    DoctorNotificationFragment()
                supportFragmentManager.beginTransaction().replace(R.id.mainDoctor, fragment, fragment.javaClass.simpleName)
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
