package com.example.loginpage

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

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

        val RgEditTextEmail = findViewById<EditText>(R.id.reg_email)
        emailLayout = findViewById<TextInputLayout>(R.id.reg_email_input_layout)
        val RgEditTextPassword = findViewById<EditText>(R.id.reg_password)
        passwordLayout = findViewById<TextInputLayout>(R.id.reg_password_input_layout)
        val RgEditTextConfirmPassword = findViewById<EditText>(R.id.reg_confirm_password)
        confirmPasswordLayout = findViewById<TextInputLayout>(R.id.reg_confirm_password_input_layout)
        val RgEditTextName = findViewById<EditText>(R.id.reg_name)
        nameLayout = findViewById<TextInputLayout>(R.id.reg_name_input_layout)
        val RgEditTextSurname = findViewById<EditText>(R.id.reg_surname)
        surnameLayout = findViewById<TextInputLayout>(R.id.reg_surname_input_layout)
        val RgEditTextContact = findViewById<EditText>(R.id.reg_contact)
        contactLayout = findViewById<TextInputLayout>(R.id.reg_contact_input_layout)
        val RgButton = findViewById<Button>(R.id.btn_register)
        val toolbar: Toolbar = findViewById(R.id.my_toolbar)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        val backButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
            //main screen done
        }
        RgButton.setOnClickListener {
            val email = RgEditTextEmail.text.toString()
            val password = RgEditTextPassword.text.toString()
            val confirmPassword = RgEditTextConfirmPassword.text.toString()
            val name = RgEditTextName.text.toString()
            val surname = RgEditTextSurname.text.toString()
            val contact = RgEditTextContact.text.toString()

            emailLayout.error = null
            passwordLayout.error = null
            confirmPasswordLayout.error = null
            nameLayout.error = null
            surnameLayout.error = null
            contactLayout.error = null

            if (validateInput(email, password, confirmPassword, name, surname, contact)) {
                //Registration can be done in the app
                Toast.makeText(this, "Registration Successful",
                    Toast.LENGTH_SHORT).show()
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
        val errorMessages = mutableListOf<String>()

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() ||
            password.length < 6 ||
            password != confirmPassword ||
            name.isEmpty() ||
            surname.isEmpty() ||
            contact.isEmpty() ||
            !android.util.Patterns.PHONE.matcher(contact).matches()
        ) {
            showAlertBox("Please check your input and try again.")
            return false
        }
        return true
    }
    private fun showAlertBox(errorMessages : String){
        AlertDialog.Builder(this)
            .setTitle("Alert")
            .setMessage(errorMessages)
            .setPositiveButton("OK",null)
            .show()
    }
}