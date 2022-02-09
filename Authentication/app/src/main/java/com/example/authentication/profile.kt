package com.example.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        var profileNamed=findViewById<TextView>(R.id.profileName1)
        var logOut = findViewById<Button>(R.id.logOut1)

         var ref = FirebaseAuth.getInstance() //creating instance of fireBase Authentication
        val database = FirebaseDatabase.getInstance() // creating instance of firebase dataBase
        var databaseReference = database?.reference!!.child("profile")  // creating database reference
        //loadProfile(profileNamed,logOut)
        val currentUser = ref.currentUser // instance of current user
        val currentUserDb = databaseReference?.child((currentUser?.uid!!)) // database reference for current user

        // taking data snapshot to read data
        currentUserDb?.get()?.addOnSuccessListener{
            if(it.exists()){
                val userName= it.child("name").value
                profileNamed.text = userName.toString()
            }

        }?.addOnFailureListener{
            it.printStackTrace()
        }

        logOut.setOnClickListener{
            startActivity(Intent(this@profile,MainActivity::class.java))
        }

    }

}