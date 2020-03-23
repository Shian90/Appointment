package com.example.app_ointmentt


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home_page_patient.*

class HomePagePatient : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page_patient)

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        supportFragmentManager.beginTransaction().replace(R.id.main,PatientHomeFragment(),PatientHomeFragment().javaClass.simpleName)
            .commit()
    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.home -> {
                val fragment = PatientHomeFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main, fragment, fragment.javaClass.simpleName)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.history -> {
                val fragment = PatientHistoryFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main, fragment, fragment.javaClass.simpleName)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.notification -> {
                val fragment = PatientNotificationFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main, fragment, fragment.javaClass.simpleName)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

}
