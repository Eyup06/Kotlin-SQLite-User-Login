package com.eyupyilmaz.kotlinuserlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.loginPass
import kotlinx.android.synthetic.main.activity_login.login_name
import kotlinx.android.synthetic.main.activity_login.login_redirect
import kotlinx.android.synthetic.main.activity_login.user_login_btn

class LoginActivity : AppCompatActivity() {
    private lateinit var db : DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        db = DatabaseHelper(this)

        user_login_btn.setOnClickListener {
            val username = login_name.text.toString()
            val userpass = loginPass.text.toString()

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(userpass))
            {
                Toast.makeText(this, "Cannot Empty!!",Toast.LENGTH_SHORT).show()
            }
            else
            {
                val checkUser = db.checkUserPass(username,userpass)
                if (checkUser == true)
                {
                    Toast.makeText(this, "Login Successfully!!!!",Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, DashbordActivity::class.java)
                    startActivity(intent)
                }
                else
                {
                    Toast.makeText(this, "Wrong Username & Password!!",Toast.LENGTH_SHORT).show()
                }
            }
        }

        login_redirect.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}