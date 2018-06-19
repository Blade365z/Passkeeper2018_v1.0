package com.gfxnation.passkeeper

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_password_confirm.*
import kotlinx.android.synthetic.main.content_password_confirm.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.user_registration.*

class PasswordConfirm : AppCompatActivity() {

    lateinit var handler: DatabaseHelper

    //confirmbutton : Button = findViewById(R.id.confirmbutton)
  //  val passwordconfirmtext:EditText=findViewById(R.id.confirmpass)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
          setContentView(R.layout.content_password_confirm)
            var passwordconfirmtext:EditText=findViewById(R.id.confirmpass)
            var email:EditText=findViewById(R.id.confirmmail)
          confirmbutton.setOnClickListener({

              handler = DatabaseHelper(this)
              if (isValidEmail(confirmmail.text.toString())== false){
                  confirmmail.setError("Enter valid email")
              }


              else if (confirmmail.text.toString().isNullOrBlank()) {
                  Toast.makeText(this, "Enter Email/Password Properly", Toast.LENGTH_SHORT).show()

              }
              else if(handler.ussrPresent(confirmmail.text.toString(), confirmpass.text.toString())) {
                  Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                  var intent = Intent(baseContext, MainActivity::class.java)
                      startActivity(intent)
                    confirmmail.setText("")
                    confirmpass.setText("")
              }

              else {
                  Toast.makeText(this, "Unsucessful", Toast.LENGTH_SHORT).show()

              }

       })


        }
    fun isValidEmail(target : CharSequence?): Boolean{
        return  if (target == null){
            false
        }else{
            android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }
   }




