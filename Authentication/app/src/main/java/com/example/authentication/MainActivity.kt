package com.example.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    //custom valid email pattern
    val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    // function to check valid email return true or false
    fun isValidString(str: String): Boolean{
        return EMAIL_ADDRESS_PATTERN.matcher(str).matches()
    }

   private val ref = FirebaseAuth.getInstance() //creating instance of fireBase Authentication

    private val database = FirebaseDatabase.getInstance() // creating instance of firebase database
    var databaseReference = database?.reference!!.child("profile") // creating database reference for specific  profile

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signUpEmail=findViewById<TextView>(R.id.signUpEmail)
        val signUpPassword=findViewById<TextView>(R.id.signUpPassword)
        val signUpButton = findViewById<Button>(R.id.signUp)
            val name = findViewById<TextView>(R.id.name)
        val logIn =findViewById<Button>(R.id.haveAnAccountButton)





        signUpButton.setOnClickListener{
            val emails = signUpEmail.text.toString().trim()
            // check for valid mail format
            if(!isValidString(emails)){

                signUpEmail.error="Invalid Email-Format"
                return@setOnClickListener
            }
            val email=signUpEmail.text.toString().trim()
            val psd = signUpPassword.text.toString().trim()
            if(email.isEmpty()){

                signUpEmail.error="Email Required"
                return@setOnClickListener
            }
            if(psd.isEmpty()){

                signUpPassword.error="Password Required"
                return@setOnClickListener
            }
            // create user with mail and password
            ref.createUserWithEmailAndPassword(
                signUpEmail.text.toString().trim(),
                signUpPassword.text.toString().trim()
            ).addOnCompleteListener {
                    if(it.isSuccessful){
                        val currentUser = ref.currentUser
                        val currentUserDb = databaseReference?.child((currentUser?.uid!!))
                        val userName=name.text.toString()
                         currentUserDb.child("name").setValue(userName)
                    }
            }.addOnSuccessListener {
                Toast.makeText(this,"successfully sign-up",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener(){
                it.printStackTrace()
            }
        }

        logIn.setOnClickListener{
            startActivity(Intent(this@MainActivity,login::class.java))
        }

    }
}