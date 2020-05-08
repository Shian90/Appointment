package com.example.app_ointmentt


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home_page_patient.*

class HomePageDoctor : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page_doctor)

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        supportFragmentManager.beginTransaction().replace(R.id.main,DoctorHomeFragment(),DoctorHomeFragment().javaClass.simpleName)
            .commit()
    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.home -> {
                val fragment = DoctorHomeFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main, fragment, fragment.javaClass.simpleName)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.history -> {
                val fragment = DoctorHistoryFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main, fragment, fragment.javaClass.simpleName)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.notification -> {
                val fragment = DoctorNotificationFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main, fragment, fragment.javaClass.simpleName)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

}
