package com.gfxnation.passkeeper

import android.content.Context
import android.content.Intent
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.view.View
import android.view.WindowManager.LayoutParams.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.user_registration.*
import android.content.SharedPreferences



class Login : AppCompatActivity() {
    lateinit var  handler:DatabaseHelper
    var emailtext:EditText?=null
    var emailregtext:EditText?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var token = getSharedPreferences("username",Context.MODE_PRIVATE)
       // if(token.getString("loginusername","")!="")

            if(token.getString("loginusername","")!="")
        {

            var intent = Intent(this,PasswordConfirm::class.java)
            startActivity(intent)
            finish()

        }


        emailregtext = findViewById(R.id.email)
        emailtext = findViewById(R.id.email1)




        handler = DatabaseHelper(this)
        showHome()
        register.setOnClickListener({

            showRegistration()
        })

        login.setOnClickListener({

            showLogin()


        })

        save.setOnClickListener {
            getWindow().setSoftInputMode(SOFT_INPUT_STATE_ALWAYS_HIDDEN)
            if (isValidEmail(email.text.toString())== false){
                email.setError("Enter vaild email")
            }
            else if (email.text.toString().isNullOrBlank()&&pin.text.toString().isNullOrBlank()) {

                Toast.makeText(this, "Please Fill up all the blanks   ", Toast.LENGTH_SHORT).show()


            } else {

                    if(pin.text.toString().length<6)
                    {
                        Toast.makeText(this, "Password must have atleast 6 characters  ", Toast.LENGTH_SHORT).show()
                        pin.requestFocus()

                    }

                handler.insertUserData(username.text.toString(), email.text.toString(), pin.text.toString())
                showHome()

            }
        }
        loginbutton.setOnClickListener {
            if (isValidEmail(email1.text.toString())== false){
                email1.setError("Enter vaild email")
            }

            else if (email1.text.toString().isNullOrBlank()) {
                Toast.makeText(this, "Enter Email/Password Properly", Toast.LENGTH_SHORT).show()

            }
                else if(handler.ussrPresent(email1.text.toString(), pin1.text.toString())) {
                      Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()

                 //       startActivity(intent)

                val emlay=email1.text.toString()
                val pin=pin.text.toString()
                var intent = Intent(baseContext, MainActivity::class.java)
                intent.putExtra("e1",emlay)
                var editor = token.edit()
                editor.putString("loginusername",emlay)
                editor.commit()
                startActivity(intent)

                }

            else {
            Toast.makeText(this, "Unsucessful", Toast.LENGTH_SHORT).show()

        }
        }
    }

        private fun showRegistration(){
            registrationlayout.visibility=View.VISIBLE
            loginlayout.visibility=View.GONE
            home.visibility=View.GONE

        }

    private fun showLogin(){

        registrationlayout.visibility=View.GONE
        loginlayout.visibility=View.VISIBLE
        home.visibility=View.GONE
    }
    private  fun showHome(){

        registrationlayout.visibility=View.GONE
        loginlayout.visibility=View.GONE
        home.visibility=View.VISIBLE

    }

    override fun onBackPressed() {

        showHome()

    }


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)


    }

    fun isValidEmail(target: CharSequence?): Boolean{
        return if(target == null){
            false
        }else{
            android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }


}


