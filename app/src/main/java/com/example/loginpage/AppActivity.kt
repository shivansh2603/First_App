package com.example.loginpage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.loginpage.screen.ProfileScreen
import com.example.loginpage.screen.RecycleScreen
import com.example.loginpage.screen.SettingsScreen
import com.google.android.material.bottomnavigation.BottomNavigationView

class AppActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        bottomNavigationView = findViewById(R.id.bottom_navigation)

        // Set the initial fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, RecycleScreen())
            .commit()

        bottomNavigationView.selectedItemId = R.id.navigation_recycle

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_recycle -> {
                    replaceFragment(RecycleScreen())
                    true
                }
                R.id.navigation_profile -> {
                    replaceFragment(ProfileScreen())
                    true
                }
                R.id.navigation_settings -> {
                    replaceFragment(SettingsScreen())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}