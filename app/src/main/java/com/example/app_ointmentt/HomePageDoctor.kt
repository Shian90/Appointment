package com.example.app_ointmentt


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home_page_doctor.*
import kotlinx.android.synthetic.main.activity_home_page_patient.*
import kotlinx.android.synthetic.main.activity_home_page_patient.bottomNavigationView
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*
import kotlinx.android.synthetic.main.toolbar.view.profile_image

class HomePageDoctor : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page_doctor)
        changeToolbarTitle(toolbar = toolbar2, txt = "Appointment")
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        loadHomeFragment()
    }
    private fun loadHomeFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.main,PatientHomeFragment(),PatientHomeFragment().javaClass.simpleName)
            .commit()
    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.home -> {
                changeToolbarTitle(toolbar = toolbar2, txt = "Appointment")
                val fragment = DoctorHomeFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main, fragment, fragment.javaClass.simpleName)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.history -> {
                changeToolbarTitle(toolbar = toolbar2, txt = "History")
                val fragment = DoctorHistoryFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main, fragment, fragment.javaClass.simpleName)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.notification -> {
                changeToolbarTitle(toolbar = toolbar2, txt = "Notification")
                val fragment = DoctorNotificationFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main, fragment, fragment.javaClass.simpleName)
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
