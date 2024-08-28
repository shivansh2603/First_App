package com.example.loginpage

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private lateinit var emailLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var editTextEmail : EditText
    private lateinit var editTextPassword : EditText
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailLayout = findViewById<TextInputLayout>(R.id.fillEmailLayout)
        passwordLayout = findViewById<TextInputLayout>(R.id.fillPasswordLayout)
        editTextEmail = findViewById<EditText>(R.id.fillEmail)
        editTextPassword = findViewById<EditText>(R.id.fillPassword)

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
            } else {
                Toast.makeText(this,"Please fill in correct credentials"
                    ,Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateLayout():Boolean{
        val email = editTextEmail.text.toString()
        val password = editTextPassword.text.toString()

        emailLayout.error = null
        passwordLayout.error = null

        val errorMessages = mutableListOf<String>()

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() ||
            password.length < 6
            ){
            showAlertBox("Please check your input and try again.")
            return false
        }
        return true
        //sare validation passed
    }
    private fun showAlertBox(errorMessages : String){
        AlertDialog.Builder(this)
            .setTitle("Alert")
            .setMessage(errorMessages)
            .setPositiveButton("OK",null)
            .show()
    }
}