package com.example.app_ointmentt.ui


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.app_ointmentt.R
import com.example.app_ointmentt.ui.fragment.DoctorHistoryFragment
import com.example.app_ointmentt.ui.fragment.DoctorHomeFragment
import com.example.app_ointmentt.ui.fragment.DoctorNotificationFragment
import com.example.app_ointmentt.ui.fragment.DoctorProfileFragment
import com.example.app_ointmentt.utils.changeFragmentFromActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home_page_doctor.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*


/*This is the Entry Point to a Patient Account*/
class HomePageDoctor : AppCompatActivity() {
    //root is the meat part of the view where we inflate everything
    private val root = R.id.mainDoctor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page_doctor)

        changeToolbarTitleAndInvisibleSearchView(toolbar = toolbar, txt = "Appointment")
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        loadHomeFragment(this)

        //On Clicking the profile image on right top we go to the profile fragment
        profile_image.setOnClickListener {
            changeFragmentFromActivity(fragment = DoctorProfileFragment(), root = root,activity = this)
        }
    }

    private fun loadHomeFragment(activity: AppCompatActivity){
        changeFragmentFromActivity(fragment = DoctorHomeFragment(),root = root,activity = activity)
    }

    //Listener to the bottom Navigation View
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.home -> {
                changeToolbarTitleAndInvisibleSearchView(toolbar = toolbar, txt = "Appointment")
                changeFragmentFromActivity(fragment = DoctorHomeFragment(),root = root,activity = this)
                return@OnNavigationItemSelectedListener true
            }
            R.id.history -> {
                changeToolbarTitleAndInvisibleSearchView(toolbar = toolbar, txt = "History")
                changeFragmentFromActivity(fragment = DoctorHistoryFragment(),root = root,activity = this)
                return@OnNavigationItemSelectedListener true
            }
            R.id.notification -> {
                changeToolbarTitleAndInvisibleSearchView(toolbar = toolbar, txt = "Notification")
                changeFragmentFromActivity(fragment = DoctorNotificationFragment(),root = root,activity = this)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    private fun changeToolbarTitleAndInvisibleSearchView(toolbar: View?, txt: String){
        toolbar!!.toolbarTitle.text = txt
        toolbar.searchView.visibility = View.GONE
    }

}
