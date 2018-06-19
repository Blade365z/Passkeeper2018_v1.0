package com.gfxnation.passkeeper

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class Splashactivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashactivity)
        val background = object : Thread()
        {
            override fun run() {
                try{
                    Thread.sleep(1000)
                    val intent =Intent(baseContext,Login::class.java)
                    startActivity(intent)
                }
                catch (e:Exception)
                {
                    e.printStackTrace()

                }
            }

        }
          background.start()
    }
}
