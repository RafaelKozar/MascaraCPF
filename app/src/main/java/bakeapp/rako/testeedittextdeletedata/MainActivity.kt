package bakeapp.rako.testeedittextdeletedata

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import java.security.AccessController.getContext


class MainActivity : AppCompatActivity(), View.OnTouchListener {
    override fun onTouch(view: View?, p1: MotionEvent?): Boolean {
        Log.e("onTouch", view!!.id.toString())
        return true
    }

    /*override fun onClick(view: View?) {

        val imm = view!!.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }*/

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layoute.setOnClickListener {
            val imm = this@MainActivity.applicationContext
                    .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.getWindowToken(), 0)
        }

        campo.addTextChangedListener(MaskCpf.insert(campo))
    }



}
