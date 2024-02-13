package com.eyupyilmaz.kotlinuserlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_up.edit_text_confirm_pass
import kotlinx.android.synthetic.main.activity_sign_up.edit_text_name
import kotlinx.android.synthetic.main.activity_sign_up.edit_text_pass
import kotlinx.android.synthetic.main.activity_sign_up.sign_up_redirect
import kotlinx.android.synthetic.main.activity_sign_up.user_sign_up_btn

class SignUpActivity : AppCompatActivity() {

    private lateinit var db : DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        db = DatabaseHelper(this)

        user_sign_up_btn.setOnClickListener {
            val nameText = edit_text_name.text.toString()
            val passText = edit_text_pass.text.toString()
            val cpText = edit_text_confirm_pass.text.toString()
            val savedData = db.insertData(nameText, passText)

            if (TextUtils.isEmpty(nameText) || TextUtils.isEmpty(passText) || TextUtils.isEmpty(cpText))
            {
                Toast.makeText(this, "Cannot Empty!!",Toast.LENGTH_SHORT).show()
            }
            else
            {
                if (passText.equals(cpText))
                {
                    if (savedData == true)
                    {
                        Toast.makeText(this, "Signup Successfully!!",Toast.LENGTH_SHORT).show()
                        val intent = Intent(applicationContext, LoginActivity::class.java)
                        startActivity(intent)
                    }
                    else
                    {
                        Toast.makeText(this, "User Exısts!!",Toast.LENGTH_SHORT).show()
                    }
                }
                else
                {
                    Toast.makeText(this, "Password Not Match!!",Toast.LENGTH_SHORT).show()
                }
            }
        }

        sign_up_redirect.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}