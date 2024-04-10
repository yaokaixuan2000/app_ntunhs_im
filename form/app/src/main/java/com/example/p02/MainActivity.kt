package com.example.p02

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.DatePicker
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val id = findViewById<EditText>(R.id.editID)
        val password = findViewById<EditText>(R.id.editPassword)
        val name = findViewById<EditText>(R.id.editName)
        val birthdate = findViewById<EditText>(R.id.editBirthDate)
        var gender = findViewById<RadioGroup>(R.id.gendergroup)
        val male = findViewById<RadioButton>(R.id.male)
        val female = findViewById<RadioButton>(R.id.female)
        val chk1 = findViewById<CheckBox>(R.id.listen)
        val chk2 = findViewById<CheckBox>(R.id.read)
        val chk3 = findViewById<CheckBox>(R.id.sing)
        val numberpicker = findViewById<NumberPicker>(R.id.numpicker)
        val submit = findViewById<Button>(R.id.submit)
        var list = mutableListOf<String>()

        var msg_id:String = "1"
        var msg_password:String = "1"
        var msg_name:String = "1"
        var msg_date:String = "1"
        var msg_gender:String = "1"
        var msg_hobbies:String = "1"
        var msg_spinner:String = "1"
        var msg_numpicker:String = "1"



        //夏拉式選單
        val city = findViewById<Spinner>(R.id.spinner)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.city,
            android.R.layout.simple_spinner_dropdown_item
        )
        city.adapter = adapter
        city.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val cities = resources.getStringArray(R.array.city)
                if (position > 0)
                    msg_spinner = cities[position].toString()
                    Toast.makeText(
                        this@MainActivity,
                        "你選的是" + cities[position],
                        Toast.LENGTH_LONG
                    ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        numberpicker.setMinValue(0)
        numberpicker.setMaxValue(100)
        numberpicker.setValue(50)




        gender.setOnCheckedChangeListener { _, checkedId ->
            var gender1 = gender.findViewById<RadioButton>(checkedId).text.toString()
            msg_password = password.text.toString()
            msg_name = name.text.toString()
            msg_id = id.text.toString()
            msg_gender = gender1.toString()
            msg_numpicker = numberpicker.value.toString()
            Toast.makeText(this, gender1+numberpicker.getValue(), Toast.LENGTH_LONG).show()
        }
        birthdate.setOnClickListener {
            val calendar  = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(this,{_,year,month,day ->
                run {
                    var format = "${setDateFormat(year,month,day)}"
                    birthdate.setText(format)
                    msg_date = format.toString()
                }},year,month,day).show()
        }

        submit.setOnClickListener{
            var msg=""
            if(chk1.isChecked()){
                msg = msg + chk1.getText().toString()
            }
            if(chk2.isChecked()){
                msg = msg + chk2.getText().toString()
            }
            if(chk3.isChecked()){
                msg = msg + chk3.getText().toString()
            }
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show()

            msg_hobbies = msg.toString()

            AlertDialog.Builder(this)
                .setTitle("Message")
                .setMessage("ID: $msg_id" + "\n" + "Name:$msg_name"+ "\n" + "Password:$msg_password"+ "\n" +
                        "BirthDate:$msg_date"+ "\n" + "Gender:$msg_gender"+ "\n" + "Hobbies:$msg_hobbies"+ "\n" +
                        "Spinner:$msg_spinner"+ "\n" +
                        "numpicker:$msg_numpicker"+ "\n")
                .create()
                .show()

        }



    }

    private fun setDateFormat(year: Int, month: Int, day: Int): String {
        return "$year-${month+1}-${day}"
    }
}