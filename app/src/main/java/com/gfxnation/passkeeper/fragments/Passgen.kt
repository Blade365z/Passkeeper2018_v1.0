package com.gfxnation.passkeeper.fragments

/**
 * Created by MY PC on 5/10/2018.
 */

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.os.Build
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.gfxnation.passkeeper.DatabaseHelper
import com.gfxnation.passkeeper.Login
import com.gfxnation.passkeeper.R
import kotlinx.android.synthetic.main.activity_main.*


class Passgen :  Fragment(){


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val maxPasswordLength: Float = 20F //Max password lenght that my app creates
        val maxPasswordFactor: Float = 10F //Max password factor based on chars inside password
        super.onCreate(savedInstanceState)
        val view = inflater!!.inflate(R.layout.activity_password_generator, container, false)
        listView?.visibility=View.GONE
        val letters: String = "abcdefghijklmnopqrstuvwxyz"
        val uppercaseLetters: String = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val numbers: String = "0123456789"
        val special: String = "@#=+!£$%&?"
        val button: Button = view.findViewById(R.id.generatebutton)
        val textview: EditText = view.findViewById(R.id.passgen)
        val slider: SeekBar = view.findViewById(R.id.seekbar)
        var seekvalue: Int = 0
        var value: TextView = view.findViewById(R.id.seekstrength)
        slider.max = 100
        slider.setOnSeekBarChangeListener(
                object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(p0: SeekBar, p1: Int, p2: Boolean) {
                        seekvalue = p0.progress
                    }

                    override fun onStartTrackingTouch(p0: SeekBar) {
                        seekvalue = p0.progress
                    }

                    @RequiresApi(Build.VERSION_CODES.M)
                    @SuppressLint("ResourceAsColor")
                    override fun onStopTrackingTouch(p0: SeekBar) {

                        seekvalue = p0.progress
                        if (seekvalue == 0) {
                            value.setText("Please set the strength of the Password .  ")
                            button.setOnClickListener({

                                textview.setText("")
                            })


                        }
                        if (seekvalue >70) {
                            button.setOnClickListener({
                                value.setText("Very Strong")
                                textview.setText(genString())

                            })

                        }
                        if (seekvalue < 70 && seekvalue > 50) {
                            value.setText("Strong")
                            button.setOnClickListener({

                                textview.setText(genStringStrong())
                                textview.setTextColor(resources.getColor(R.color.material_blue_grey_800, null))


                            })

                        }
                        if (seekvalue < 50 && seekvalue > 10) {
                            value.setText("Weak")
                            button.setOnClickListener({

                                textview.setText(genStringWeak())
                                textview.setTextColor(R.color.colorAccent)

                            })

                        }


                    }
                })
        return view
    }



    fun genString(): String {

        val char = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890$%^&*#@"
        var password = ""
        for (i in 0..6) {
            password += char[Math.floor(Math.random() * char.length).toInt()]
        }

        return password


    }
    fun genStringStrong(): String {

        val char = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm123456789"
        var password = ""
        for (i in 0..6) {
            password += char[Math.floor(Math.random() * char.length).toInt()]
        }

        return password


    }
    fun genStringWeak(): String {

        val char = "qwertyuiopasdfghjklzxcvbnm"
        var password = ""
        for (i in 0..6) {
            password += char[Math.floor(Math.random() * char.length).toInt()]
        }

        return password


    }




}
