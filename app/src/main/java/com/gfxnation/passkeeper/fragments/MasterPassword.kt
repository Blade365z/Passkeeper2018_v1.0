package com.gfxnation.passkeeper.fragments

/**
 * Created by MY PC on 5/10/2018.
 */

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.gfxnation.passkeeper.DatabaseHelper
import com.gfxnation.passkeeper.Login
import com.gfxnation.passkeeper.R
import kotlinx.android.synthetic.main.fragment_masterpass.*
import kotlinx.android.synthetic.main.user_registration.*


class MasterPassword: Fragment(){

    var pass:EditText?=null
    var confirmpass: EditText?=null
    var submitButton: Button?=null
    var emailtext:EditText?=null
    var currentpass:EditText?=null
    lateinit var  handler:DatabaseHelper


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        super.onCreate(savedInstanceState)
        val view = inflater!!.inflate(R.layout.fragment_masterpass, container, false)
        activity.title=""
        pass = view.findViewById(R.id.pass)
        confirmpass = view.findViewById(R.id.con_pass)
        /*val intent:Intent
        intent= getIntent("Email")
        emailtext=intent.getStringExtra("Email")*/
        emailtext=view.findViewById(R.id.emailid)
        currentpass=view.findViewById(R.id.logpin)


        submitButton = view.findViewById(R.id.submit)

        handler = DatabaseHelper(context)

        submitButton?.setOnClickListener{

            if (isValidEmail(emailtext?.text.toString())== false){
                email.setError("Enter vaild email")
            }
            else if (emailtext?.text.toString().isNullOrBlank()) {

                Toast.makeText(context, "Please Fill up all the blanks   ", Toast.LENGTH_SHORT).show()


            }else {

                updatePin()
                showHome()


            }


        }
        return view
    }
    // Inflate the layout for this fragment
    fun showHome(){

        val intent= Intent(context, Login::class.java)
        startActivity(intent)
    }

    fun isValidEmail(target: CharSequence?): Boolean{
        return if(target == null){
            false
        }else{
            android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }


    fun updatePin() {


        if (handler.usermailPresent(emailtext?.text.toString())){
            Toast.makeText(context,"User Present",Toast.LENGTH_SHORT).show()
            if (pass?.text.toString().equals(con_pass?.text.toString())){
                handler.updateUserData(con_pass?.text.toString(), emailtext?.text.toString())
                Toast.makeText(context,"Password Updated",Toast.LENGTH_SHORT).show()


            }
        }

        else
        {
            Toast.makeText(context,"user not present",Toast.LENGTH_SHORT).show()
        }


    }






}


