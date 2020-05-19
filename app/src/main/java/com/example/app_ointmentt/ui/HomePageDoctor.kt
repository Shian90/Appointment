package com.example.app_ointmentt.ui


import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.app_ointmentt.R
import com.example.app_ointmentt.ui.fragment.DoctorHistoryFragment
import com.example.app_ointmentt.ui.fragment.DoctorHomeFragment
import com.example.app_ointmentt.ui.fragment.DoctorNotificationFragment
import com.example.app_ointmentt.ui.fragment.DoctorProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home_page_doctor.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*

class HomePageDoctor : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page_doctor)
        clickProfileImage()
        changeToolbarTitleAndInvisibleSearchView(toolbar = toolbar2, txt = "Appointment")
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        loadHomeFragment(applicationContext)
    }
    private fun clickProfileImage() {
        profile_image.setOnClickListener {
            var fragment = DoctorProfileFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainDoctor, fragment, fragment.javaClass.simpleName)
                .commit()
        }
    }
    private fun loadHomeFragment(context: Context){
        supportFragmentManager.beginTransaction().replace(
            R.id.mainDoctor,
            DoctorHomeFragment(),
            DoctorHomeFragment().javaClass.simpleName)
            .commit()
    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.home -> {
                changeToolbarTitleAndInvisibleSearchView(toolbar = toolbar2, txt = "Appointment")
                val fragment =
                    DoctorHomeFragment()
                supportFragmentManager.beginTransaction().replace(R.id.mainDoctor, fragment, fragment.javaClass.simpleName)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.history -> {
                changeToolbarTitleAndInvisibleSearchView(toolbar = toolbar2, txt = "History")
                val fragment =
                    DoctorHistoryFragment()
                supportFragmentManager.beginTransaction().replace(R.id.mainDoctor, fragment, fragment.javaClass.simpleName)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.notification -> {
                changeToolbarTitleAndInvisibleSearchView(toolbar = toolbar2, txt = "Notification")
                val fragment =
                    DoctorNotificationFragment()
                supportFragmentManager.beginTransaction().replace(R.id.mainDoctor, fragment, fragment.javaClass.simpleName)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
    private fun changeToolbarTitleAndInvisibleSearchView(toolbar: View?, txt: String){
        toolbar!!.toolbarTitle.setText(txt)
        toolbar!!.searchView.visibility = View.GONE
    }

}
