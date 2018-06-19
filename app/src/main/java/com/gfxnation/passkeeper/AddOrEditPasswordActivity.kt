package com.gfxnation.passkeeper

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.MenuItem
import com.gfxnation.passkeeper.fragments.Const
import com.gfxnation.passkeeper.fragments.Const.CONTACT_KEY
import com.gfxnation.passkeeper.fragments.Passwords
import kotlinx.android.synthetic.main.password_entry.*
import android.support.v4.widget.SearchViewCompat.setInputType
import android.widget.*


class AddOrEditPasswordActivity : AppCompatActivity() {
    var passwords:EditText?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_entry)
        passwords = findViewById<EditText>(R.id.password)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)

        checkBox.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener(){
            compoundButton: CompoundButton, b: Boolean ->
            if (b){
                passwords?.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            }else{
                passwords?.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        })
        if(intent.getStringExtra(Const.WHAT).equals(Const.ADD)){
            savebutton.text = "SAVE"
        }else{

           var titleedit:EditText=findViewById(R.id.titles)
            var typeedit:EditText=findViewById(R.id.type)
            var passwordedit:EditText=findViewById(R.id.password)
            var t1 = intent.getStringExtra("t1")
            var t2 = intent.getStringExtra("t2")
            var t3 = intent.getStringExtra("t3")
            titleedit.setText(t1)
            typeedit.setText(t2)
            passwordedit.setText(t3)


            var con : Passwords = intent.getParcelableExtra(CONTACT_KEY)
            savebutton.setText(con.title)
            savebutton.setText(con.category)
           savebutton.setText(con.password)
            savebutton.text = "UPDATE"
        }

        savebutton.setOnClickListener {
            if (intent.getStringExtra(Const.WHAT).equals(Const.ADD)){
                addpassword()
                finish()
            }else{
                updatepassword(intent.getParcelableExtra(CONTACT_KEY))
                finish()
            }
        }
    }
    fun logMsg(msg : String){
        Log.d("TAG",msg)
    }

   /* override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null){
            when(item.itemId){
            }
        }
    }*/

    fun validateInput(): Boolean{
        if (titles?.text.toString().trim().equals("")|| password?.text.toString().trim().equals("")|| type?.text.toString().trim().equals("")){
            Toast.makeText(this,"Please fill the data", Toast.LENGTH_SHORT).show()
            return false
        }else{
            return true
        }
    }
    //fun update(){
    //    DatabaseHelperforPassEntry.getInstance(this).getAllPassword()
    //}
    fun addpassword() {
        if (validateInput()) {
            DatabaseHelperforPassEntry.getInstance(this).addpassword(titles?.text.toString(),type?.text.toString(), password?.text.toString())
        }
    }
    fun updatepassword(passwords: Passwords?) {
        if (validateInput()) {
            DatabaseHelperforPassEntry.getInstance(this).updatepassword(passwords?.id, titles?.text.toString(),type?.text.toString(),password?.text.toString())
        }
    }

}
