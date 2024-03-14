package com.example.form

import android.app.AlertDialog
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var message = ""
        val applyDate = findViewById<EditText>(R.id.Date1)
        applyDate.setOnClickListener{
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(this, { _, year, month, day ->
                run {
                    var format = "${setDateFormat(year, month, day)}"
                    applyDate.setText(format)
                    message += format
                }
            }, year, month, day).show()
        }
        val ID = findViewById<EditText>(R.id.ID1)
        val PWD = findViewById<EditText>(R.id.PWD1)
        val Name = findViewById<EditText>(R.id.Name1)
        message += ID.text.toString()
        message += PWD.text.toString()
        message += Name.text.toString()
        val radGrp_Gender = findViewById<RadioGroup>(R.id.radGrp_Gender)
        val radBtn_Male = findViewById<RadioButton>(R.id.radBtn_Male)
        val radBtn_Female = findViewById<RadioButton>(R.id.radBtn_Female)
        var gender = ""

        radGrp_Gender.setOnCheckedChangeListener { _, checkedId ->
            gender= radGrp_Gender.findViewById<RadioButton>(checkedId).text.toString()
            Toast.makeText(this,gender,Toast.LENGTH_LONG).show()
            message += gender
        }

        var chkbox = findViewById<CheckBox>(R.id.checkBox)
        var chkbox1 = findViewById<CheckBox>(R.id.checkBox1)
        var chkbox2 = findViewById<CheckBox>(R.id.checkBox2)
        var chkbox3 = findViewById<CheckBox>(R.id.checkBox3)

//        val btn_send = findViewById<Button>(R.id.button)

//        btn_send.setOnClickListener {
//            var msg=""
//            if (chkbox.isChecked()) {
//                msg = msg + chkbox.getText().toString()
//            }
//            if (chkbox1.isChecked()) {
//                msg = msg + "、" + chkbox1.getText().toString()
//            }
//            if (chkbox2.isChecked()) {
//                msg = msg + "、" + chkbox2.getText().toString()
//            }
//            if (chkbox3.isChecked()) {
//                msg = msg + "、" + chkbox3.getText().toString()
//            }
//            Toast.makeText(this@MainActivity, "你選的是" + msg,
//                Toast.LENGTH_LONG).show()
//        }

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            AlertDialog.Builder(this).setTitle("onclick").setMessage(message).create().show()
        }

    }
    private fun setDateFormat(year: Int, month: Int, day: Int): String {
        return "$year-${month + 1}-$day"
    }
}

