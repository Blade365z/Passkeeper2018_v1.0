package com.gfxnation.passkeeper

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.*

import com.gfxnation.passkeeper.adapter.PasswordsAdapter
import com.gfxnation.passkeeper.fragments.AboutUsFragment
import com.gfxnation.passkeeper.fragments.Const.ADD
import com.gfxnation.passkeeper.fragments.Const.WHAT
import com.gfxnation.passkeeper.fragments.MasterPassword
import com.gfxnation.passkeeper.fragments.Passgen
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

//import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var handler: DatabaseHelper
    lateinit var saveButton: Button
    lateinit var listview: ListView
    var adapter: PasswordsAdapter? = null
    lateinit var icon: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var token = getSharedPreferences("username", Context.MODE_PRIVATE)
        user.text = token.getString("loginusername", "")


        setSupportActionBar(toolbar)
        adapter = PasswordsAdapter(this@MainActivity)
        listview = findViewById(R.id.listView)
        listview.adapter = adapter
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        icon = findViewById(R.id.fab)

        saveButton = findViewById(R.id.savebutton)


        icon.setOnClickListener {
            var intent: Intent = Intent(this@MainActivity, AddOrEditPasswordActivity::class.java)
            intent.putExtra(WHAT, ADD)
            startActivity(intent)
        }
        saveButton.setOnClickListener {
            val intent: Intent = Intent(this@MainActivity, AddOrEditPasswordActivity::class.java)
            startActivity(intent)
        }
        val dbhandler = DatabaseHelperforPassEntry(this, null, null, 1)


        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        var navigationview: NavigationView = findViewById(R.id.nav_view)
        navigationview.setNavigationItemSelectedListener(this)
    }

    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return

        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please tap back twice again to exit", Toast.LENGTH_SHORT).show()
        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true






    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(baseContext, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_generator -> {
                this.supportFragmentManager
                        .beginTransaction()
                        .addToBackStack(null)
                        .add(R.id.content_frame, Passgen())
                        .commit()
            }
            R.id.nav_set -> {
                //dialog()
                var token = getSharedPreferences("username", Context.MODE_PRIVATE)
                var editor = token.edit()
                editor.putString("loginusername", " ")
                editor.commit()
                val intent = Intent(this,Login::class.java)
                startActivity(intent)


            }
            R.id.nav_about -> {
                this.supportFragmentManager
                        .beginTransaction()
                        .add(R.id.content_frame, AboutUsFragment())
                        .addToBackStack(null)
                        .commit()
            }
            R.id.nav_masterpass -> {
                this.supportFragmentManager
                        .beginTransaction()
                        .addToBackStack(null)
                        .add(R.id.content_frame, MasterPassword())
                        .commit()
            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun View.hideKeyboard(inputMethodManager: InputMethodManager) {
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)

    }


  //      fun dialog() {
//

        //}

        override fun onResume() {
            super.onResume()

            /*val dialog = AlertDialog.Builder(this)
        var promptpasstext:EditText ? = null
        promptpasstext=findViewById<EditText>(R.id.Passwordpromptdialog)
        val dialogView = layoutInflater.inflate(R.layout.promptpass,null)
        //val promptpasstext = dialogView.findViewById<EditText>(R.id.Passwordprompttextview)
        dialog.setView(dialogView)
        dialog.setCancelable(false)
      //  var int : Int ? = 0
        dialog.setPositiveButton("Done",{ dialogInterface: DialogInterface, i: Int -> })
        val customDialog = dialog.create()
             customDialog.show()
              backgroundcontent()
              customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener({
                var bool : Boolean = checkpassword(promptpasstext?.text.toString())
                if(bool) {

                Toast.makeText(this, "sucessful", Toast.LENGTH_SHORT).show()
                customDialog.dismiss()

               }
            else {



            }

            })


            //int = 0

        */


/*
        val dialog = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.promptpass,null)
        val promptpass = dialogView.findViewById <EditText>(R.id.Passwordpromptdialog)
        dialog.setView(dialogView)
        dialog.setCancelable(false)
        dialog.setPositiveButton("Done",{ dialogInterface: DialogInterface, i: Int -> })
        val customDialog = dialog.create()
        customDialog.show()
        backgroundcontent()

        customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener({
            var bool : Boolean = checkpassword(promptpass?.text.toString())
            if (bool){
                Toast.makeText(this, "sucessful", Toast.LENGTH_SHORT).show()
                customDialog.dismiss()
                        listview.visibility=View.VISIBLE
                background.visibility=View.GONE
                fab.visibility=View.VISIBLE

                //var intent = Intent(this, Login::class.java)
               // startActivity(intent)

            }


            else{


                Toast.makeText(baseContext, "Password not valid", Toast.LENGTH_SHORT).show()

            }

        })

*/
        }



        fun checkpassword(pass: String): Boolean {
            var e1 = intent.getStringExtra("e1")
            handler = DatabaseHelper(this)
            if (handler.ussrPresent(e1.toString(),pass)) {
                // customDialog.dismiss()
                return true

            } else {
                Toast.makeText(this, "Unsuccessful", Toast.LENGTH_SHORT).show()


                return false
            }


        }
/*
    override fun onStop() {
        super.onStop()
        finish()

    }*/

    override fun onDestroy() {
        super.onDestroy()
        moveTaskToBack(true)
    }





        fun backgroundcontent() {

            listView.visibility = View.GONE
            background.visibility = View.VISIBLE
            fab.visibility = View.GONE
        }


    }



