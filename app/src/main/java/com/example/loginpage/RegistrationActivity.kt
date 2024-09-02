package com.example.loginpage

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
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
    private lateinit var nameLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var mobileLayout: TextInputLayout
    private lateinit var pincodeLayout: TextInputLayout
    private lateinit var addressLayout: TextInputLayout
    private lateinit var stateAutoCompleteTextView: AutoCompleteTextView
    private lateinit var districtAutoCompleteTextView: AutoCompleteTextView

    private val states = listOf(
        "Andhra Pradesh",
        "Arunachal Pradesh",
        "Assam",
        "Bihar",
        "Chhattisgarh",
        "Goa",
        "Gujarat",
        "Haryana",
        "Himachal Pradesh",
        "Jharkhand",
        "Karnataka",
        "Kerala",
        "Madhya Pradesh",
        "Maharashtra",
        "Manipur",
        "Meghalaya",
        "Mizoram",
        "Nagaland",
        "Odisha",
        "Punjab",
        "Rajasthan",
        "Sikkim",
        "Tamil Nadu",
        "Telangana",
        "Tripura",
        "Uttar Pradesh",
        "Uttarakhand",
        "West Bengal"
    )
    private val districts = mapOf(
        "Andhra Pradesh" to listOf("Visakhapatnam", "Vijayawada", "Guntur"),
        "Arunachal Pradesh" to listOf("Itanagar", "Tawang", "Ziro"),
        "Assam" to listOf("Guwahati", "Dibrugarh", "Silchar"),
        "Bihar" to listOf("Patna", "Gaya", "Bhagalpur"),
        "Chhattisgarh" to listOf("Raipur", "Bilaspur", "Durg"),
        "Goa" to listOf("Panaji", "Margao", "Vasco da Gama"),
        "Gujarat" to listOf("Ahmedabad", "Surat", "Vadodara"),
        "Haryana" to listOf("Gurugram", "Faridabad", "Panipat"),
        "Himachal Pradesh" to listOf("Shimla", "Dharamshala", "Manali"),
        "Jharkhand" to listOf("Ranchi", "Jamshedpur", "Dhanbad"),
        "Karnataka" to listOf("Bengaluru", "Mysuru", "Mangaluru"),
        "Kerala" to listOf("Thiruvananthapuram", "Kochi", "Kozhikode"),
        "Madhya Pradesh" to listOf("Indore", "Bhopal", "Gwalior"),
        "Maharashtra" to listOf("Mumbai", "Pune", "Nagpur"),
        "Manipur" to listOf("Imphal", "Thoubal", "Churachandpur"),
        "Meghalaya" to listOf("Shillong", "Tura", "Nongstoin"),
        "Mizoram" to listOf("Aizawl", "Lunglei", "Champhai"),
        "Nagaland" to listOf("Kohima", "Dimapur", "Mokokchung"),
        "Odisha" to listOf("Bhubaneswar", "Cuttack", "Rourkela"),
        "Punjab" to listOf("Ludhiana", "Amritsar", "Jalandhar"),
        "Rajasthan" to listOf("Jaipur", "Jodhpur", "Udaipur"),
        "Sikkim" to listOf("Gangtok", "Namchi", "Gyalshing"),
        "Tamil Nadu" to listOf("Chennai", "Coimbatore", "Madurai"),
        "Telangana" to listOf("Hyderabad", "Warangal", "Nizamabad"),
        "Tripura" to listOf("Agartala", "Udaipur", "Dharmanagar"),
        "Uttar Pradesh" to listOf("Lucknow", "Kanpur", "Varanasi"),
        "Uttarakhand" to listOf("Dehradun", "Haridwar", "Nainital"),
        "West Bengal" to listOf("Kolkata", "Siliguri", "Durgapur")
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val rgEditTextName = findViewById<EditText>(R.id.reg_name)
        nameLayout = findViewById(R.id.reg_name_input_layout)
        val rgEditTextPassword = findViewById<EditText>(R.id.reg_password)
        passwordLayout = findViewById(R.id.reg_password_input_layout)
        val rgEditTextMobile = findViewById<EditText>(R.id.reg_mobile)
        mobileLayout = findViewById(R.id.reg_mobile_input_layout)
        val rgEditTextPincode = findViewById<EditText>(R.id.reg_pincode)
        pincodeLayout = findViewById(R.id.reg_pincode_input_layout)
        val rgEditTextAddress = findViewById<EditText>(R.id.reg_address)
        addressLayout = findViewById(R.id.reg_address_input_layout)

        stateAutoCompleteTextView = findViewById(R.id.reg_state)
        districtAutoCompleteTextView = findViewById(R.id.reg_district)

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
        val stateAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, states)
        stateAutoCompleteTextView.setAdapter(stateAdapter)

        val districtAdapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, emptyList<String>())
        districtAutoCompleteTextView.setAdapter(districtAdapter)

        stateAutoCompleteTextView.setOnItemClickListener { parent, _, position, _ ->
            val selectedState = parent.getItemAtPosition(position) as String
            updateDistrictDropdown(selectedState)
        }

        rgButton.setOnClickListener {
            val name = rgEditTextName.text.toString()
            val password = rgEditTextPassword.text.toString()
            val mobile = rgEditTextMobile.text.toString()
            val pincode = rgEditTextPincode.text.toString()
            val address = rgEditTextAddress.text.toString()
            val state = stateAutoCompleteTextView.text.toString() // Get selected state
            val district = districtAutoCompleteTextView.text.toString()

            nameLayout.error = null
            passwordLayout.error = null
            mobileLayout.error = null
            pincodeLayout.error = null
            addressLayout.error = null

            if (validateInput(name, password, mobile, pincode, address, state, district)) {
                // Registration can be done in the app
                Toast.makeText(
                    this, "Registration Successful",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun updateDistrictDropdown(selectedState: String) {
        Log.d("RegistrationActivity", "Updating district dropdown for state: $selectedState")
        val districtList = districts[selectedState] ?: emptyList()
        Log.d("RegistrationActivity", "District list: $districtList")
        val districtAdapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, districtList)
        districtAutoCompleteTextView.setAdapter(districtAdapter)
    }

    private fun validateInput(
        name: String,
        password: String,
        mobile: String,
        pincode: String,
        address: String,
        state: String,
        district: String
    ): Boolean {
        if (name.isEmpty() ||
            password.length < 6 ||
            mobile.isEmpty() ||
            ! android.util.Patterns.PHONE.matcher(mobile).matches() ||
            pincode.isEmpty() ||
            address.isEmpty() ||
            state.isEmpty() ||
            district.isEmpty()
        ) {
            showAlertBox("Please check your input and try again.")
            return false
        }
        if (state !in states) {
            showAlertBox("Please select a valid state.")
            return false
        }
        val validDistricts = districts[state] ?: emptyList()
        if (district !in validDistricts) {
            showAlertBox("Please select a valid district for the chosen state.")
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