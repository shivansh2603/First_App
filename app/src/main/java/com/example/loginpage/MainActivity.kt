package com.example.loginpage

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

@Suppress("SameParameterValue")
class MainActivity : AppCompatActivity() {
    private lateinit var emailLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailLayout = findViewById(R.id.fillEmailLayout)
        passwordLayout = findViewById(R.id.fillPasswordLayout)
        editTextEmail = findViewById(R.id.fillEmail)
        editTextPassword = findViewById(R.id.fillPassword)

        val buttonLogin = findViewById<Button>(R.id.pressButton)
        val backButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
        val forgotPasswordButton = findViewById<TextView>(R.id.forgotPasswordButton)
        forgotPasswordButton.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
        buttonLogin.setOnClickListener {
            if (validateLayout()) {
                // Login can be done in the app
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(
                    this, "Please fill incorrect credentials", Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }

    private fun validateLayout(): Boolean {
        val email = editTextEmail.text.toString()
        val password = editTextPassword.text.toString()

        emailLayout.error = null
        passwordLayout.error = null

        mutableListOf<String>()

        if (! android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() ||
            password.length < 6
        ) {
            showAlertBox("Please check your input and try again.")
            return false
        }
        return true
        //sare validation passed
    }

    private fun showAlertBox(errorMessages: String) {
        AlertDialog.Builder(this)
            .setTitle("Alert")
            .setMessage(errorMessages)
            .setPositiveButton("OK", null)
            .show()
    }
}