package com.example.loginpage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.graphics.drawable.GradientDrawable
import android.graphics.Color
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private lateinit var emailLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailLayout = findViewById(R.id.fillEmailLayout)
        passwordLayout = findViewById(R.id.fillPasswordLayout)

        val loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            if (validateLayout()) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
        
        val forgotPasswordButton = findViewById<TextView>(R.id.registerTextView)
        forgotPasswordButton.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateLayout(): Boolean {
        val email = emailLayout.editText?.text.toString()
        val password = passwordLayout.editText?.text.toString()

        emailLayout.error = null
        passwordLayout.error = null

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() || password.length < 6) {
            showAlertBox("Please check your input and try again.")
            return false
        }
        return true
    }

    private fun showAlertBox(errorMessages: String) {
        AlertDialog.Builder(this)
            .setTitle("Alert")
            .setMessage(errorMessages)
            .setPositiveButton("OK", null)
            .show()
    }
}