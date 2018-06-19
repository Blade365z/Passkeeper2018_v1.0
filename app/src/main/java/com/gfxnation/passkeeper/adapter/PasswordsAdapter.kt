package com.gfxnation.passkeeper.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Parcelable
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.gfxnation.passkeeper.AddOrEditPasswordActivity
import com.gfxnation.passkeeper.DatabaseHelperforPassEntry
import com.gfxnation.passkeeper.R
import com.gfxnation.passkeeper.fragments.Const.CONTACT_KEY
import com.gfxnation.passkeeper.fragments.Const.UPDATE
import com.gfxnation.passkeeper.fragments.Const.WHAT
import com.gfxnation.passkeeper.fragments.Passwords

/**
 * Created by Anjali Sharma on 5/11/2018.
 */
class PasswordsAdapter() : BaseAdapter(){
    var list : ArrayList<Passwords>?= null
    var context : Context?= null

    constructor(context: Context?): this(){
        this.list = DatabaseHelperforPassEntry.getInstance(context!!).getAllPassword()
        this.context = context
    }
    fun  updateList(){
        this.list = DatabaseHelperforPassEntry.getInstance(context!!).getAllPassword()
        notifyDataSetChanged()
    }
    override fun getView(p0: Int, view: View?, p2: ViewGroup?): View {
        var convertView : View?= view
        if (convertView==null){
            convertView = View.inflate(context, R.layout.passwords_fragment,null)
        }
        var txtTitle : TextView = convertView?.findViewById<TextView>(R.id.pass_title) as TextView
        var txtPassword : TextView = convertView?.findViewById<TextView>(R.id.pass_password) as TextView
        var imgEdit : ImageView = convertView?.findViewById<ImageView>(R.id.editImageView) as ImageView
        var imgDelete : ImageView = convertView?.findViewById<ImageView>(R.id.deleteImageView) as ImageView
        var txttype:TextView=convertView?.findViewById<TextView>(R.id.categoryadapter)as TextView
        txtTitle.text = list?.get(p0)?.title
        txttype.text=list?.get(p0)?.category
        txtPassword.text = list?.get(p0)?.password



        imgEdit.setOnClickListener {
            var textfield=txtTitle.text.toString()
            var textcategory=txttype.text.toString()
            var textpassfield=txtPassword.text.toString()
               var intent = Intent(context, AddOrEditPasswordActivity::class.java)

            intent.putExtra("t1",textfield)
            intent.putExtra("t2",textcategory)
            intent.putExtra("t3",textpassfield)
            intent.putExtra(WHAT, UPDATE)
            intent.putExtra(CONTACT_KEY, (list?.get(p0) as Parcelable))
            context?.startActivity(intent)
            updateList()
           }
        imgDelete.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Are you sure? ")
            builder.setPositiveButton("YES", { dialogInterface: DialogInterface, i: Int ->
                DatabaseHelperforPassEntry.getInstance(context!!).deletepassword(list?.get(p0)?.id)
                list?.removeAt(p0)
                notifyDataSetChanged()
            })
            builder.setNegativeButton("NO",{ dialogInterface: DialogInterface, i: Int ->
            })
            builder.show()
        }
        return convertView
    }

    override fun getItem(p0: Int): Passwords? {
        return list?.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return list!!.size
    }

}