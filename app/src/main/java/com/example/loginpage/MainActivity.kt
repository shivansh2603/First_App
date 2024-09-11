package com.example.loginpage

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
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

        val loginEmail = findViewById<EditText>(R.id.emailEdit)
        emailLayout = findViewById(R.id.fillEmailLayout)
        val originalEmailHint = emailLayout.hint
        loginEmail.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                emailLayout.hint = null
            } else {
                if (loginEmail.text.isNullOrEmpty()) {
                    emailLayout.hint = originalEmailHint
                }
            }
        }

        val loginPassword = findViewById<EditText>(R.id.passwordEdit)
        passwordLayout = findViewById(R.id.fillPasswordLayout)
        val originalLoginPasswordHint = passwordLayout.hint
        loginPassword.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                passwordLayout.hint = null
            } else {
                if (loginEmail.text.isNullOrEmpty()) {
                    passwordLayout.hint = originalLoginPasswordHint
                }
            }
        }

        val loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            if (validateLayout()) {
                val intent = Intent(this, AppActivity::class.java)
                startActivity(intent)
            }
        }
        /*loginButton.setOnClickListener {
            if (validateLayout()) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }*/
        
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