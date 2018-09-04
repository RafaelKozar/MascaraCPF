package bakeapp.rako.testeedittextdeletedata

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*





class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtCpf.addTextChangedListener(MaskCpf.insert(edtCpf))
        edtCpf2.addTextChangedListener(MaskCpf2.insert(edtCpf2))
        edtCel.addTextChangedListener(MaskGenerica.insert("(##)#####-####", edtCel))
    }



}
