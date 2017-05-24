package com.example.sumit.volley_crud_operations

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    // Variable declaration
    private var editTextName: EditText? = null
    private var editTextPosition: EditText? = null
    private var editTextSalary: EditText? = null
    private var editTextExperience: EditText? = null
    private var button: Button? = null
    private var pd: ProgressDialog? = null

    //URL
    private val URL = "http://vga.ramstertech.com/freebieslearning/employee.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pd = ProgressDialog(this@MainActivity)

        editTextName = findViewById(R.id.editTextName) as EditText

        editTextPosition = findViewById(R.id.editTextPosition) as EditText

        editTextSalary = findViewById(R.id.editTextSalary) as EditText

        editTextExperience = findViewById(R.id.editTextExperience) as EditText

        button = findViewById(R.id.button) as Button

        // onClickListener

        button?.setOnClickListener(View.OnClickListener {

            loginRequest()
        })
    }
// Method for Inserting data using Volley

    private fun loginRequest() {
        pd!!.setMessage("Inserting Data . . ")

        pd!!.show()

        val queue = Volley.newRequestQueue(this@MainActivity)

        val response: String? = null

        val finalResponse = response

        val postRequest = object : StringRequest(Request.Method.POST, URL,Response.Listener<String>
        {

    // Getting Response from Server

            response ->

            pd!!.hide()

    //Toast Method

            toast(response)

    //Remove Method

            remove()

        },
          Response.ErrorListener {
               // error
               pd!!.hide()
               Log.d("ErrorResponse", finalResponse)
          }
        ) {
            override fun getParams(): Map<String, String> {
                //Creating HashMap
                val params = HashMap<String, String>()

                params.put("name", editTextName?.text.toString())
                params.put("position", editTextPosition?.text.toString())
                params.put("salary", editTextSalary?.text.toString())
                params.put("experience", editTextExperience?.text.toString())

                return params
            }
        }

        postRequest.retryPolicy = DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)

        queue.add(postRequest)


    }

    //Method for Toast

    fun Activity.toast(setToast: CharSequence) {
        Toast.makeText(this, setToast, Toast.LENGTH_SHORT).show()
    }

    //Method for clear Text After Sending Data to the server
    private fun remove(){
        editTextName?.text?.clear()
        editTextPosition?.text?.clear()
        editTextExperience?.text?.clear()
        editTextSalary?.text?.clear()

    }
}
