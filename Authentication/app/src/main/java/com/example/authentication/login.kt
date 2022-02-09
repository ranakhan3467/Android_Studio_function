package com.example.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class login : AppCompatActivity() {

    val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
    fun isValidString(str: String): Boolean{
        return EMAIL_ADDRESS_PATTERN.matcher(str).matches()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val ref = FirebaseAuth.getInstance()

        val loginEmail=findViewById<TextView>(R.id.loginEmail)
        val loginPassword=findViewById<TextView>(R.id.loginPassword)
        val loginButton = findViewById<Button>(R.id.login)
        //val logIn =findViewById<Button>(R.id.haveAnAccountButton)

        loginButton.setOnClickListener{
            val emails = loginEmail.text.toString()
            if(!isValidString(emails)){

                loginEmail.error="Invalid Email-Format"
                return@setOnClickListener
            }
            val email=loginEmail.text.toString().trim()
            val psd = loginPassword.text.toString().trim()
            if(email.isEmpty()){

                loginEmail.error="Email Required"
                return@setOnClickListener
            }
            if(psd.isEmpty()){

                loginPassword.error="Password Required"
                return@setOnClickListener
            }
            ref.signInWithEmailAndPassword(email,psd).addOnSuccessListener {
                //Toast.makeText(this,"successfully sign-up", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@login,profile::class.java))
            }.addOnFailureListener{
                it.printStackTrace()
            }


        }
    }
}