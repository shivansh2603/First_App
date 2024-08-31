package com.example.loginpage

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputLayout

@Suppress("SameParameterValue")
class RegistrationActivity : AppCompatActivity() {
    private lateinit var emailLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var confirmPasswordLayout: TextInputLayout
    private lateinit var nameLayout: TextInputLayout
    private lateinit var surnameLayout: TextInputLayout
    private lateinit var contactLayout: TextInputLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val rgEditTextEmail = findViewById<EditText>(R.id.reg_email)
        emailLayout = findViewById(R.id.reg_email_input_layout)
        val rgEditTextPassword = findViewById<EditText>(R.id.reg_password)
        passwordLayout = findViewById(R.id.reg_password_input_layout)
        val rgEditTextConfirmPassword = findViewById<EditText>(R.id.reg_confirm_password)
        confirmPasswordLayout = findViewById(R.id.reg_confirm_password_input_layout)
        val rgEditTextName = findViewById<EditText>(R.id.reg_name)
        nameLayout = findViewById(R.id.reg_name_input_layout)
        val rgEditTextSurname = findViewById<EditText>(R.id.reg_surname)
        surnameLayout = findViewById(R.id.reg_surname_input_layout)
        val rgEditTextContact = findViewById<EditText>(R.id.reg_contact)
        contactLayout = findViewById(R.id.reg_contact_input_layout)
        val rgButton = findViewById<Button>(R.id.btn_register)
        val toolbar: Toolbar = findViewById(R.id.my_toolbar)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        val backButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
            //main screen done
        }
        rgButton.setOnClickListener {
            val email = rgEditTextEmail.text.toString()
            val password = rgEditTextPassword.text.toString()
            val confirmPassword = rgEditTextConfirmPassword.text.toString()
            val name = rgEditTextName.text.toString()
            val surname = rgEditTextSurname.text.toString()
            val contact = rgEditTextContact.text.toString()

            emailLayout.error = null
            passwordLayout.error = null
            confirmPasswordLayout.error = null
            nameLayout.error = null
            surnameLayout.error = null
            contactLayout.error = null

            if (validateInput(email, password, confirmPassword, name, surname, contact)) {
                //Registration can be done in the app
                Toast.makeText(
                    this, "Registration Successful",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun validateInput(
        email: String,
        password: String,
        confirmPassword: String,
        name: String,
        surname: String,
        contact: String
    ): Boolean {
        mutableListOf<String>()

        if (! android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() ||
            password.length < 6 ||
            password != confirmPassword ||
            name.isEmpty() ||
            surname.isEmpty() ||
            contact.isEmpty() ||
            ! android.util.Patterns.PHONE.matcher(contact).matches()
        ) {
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