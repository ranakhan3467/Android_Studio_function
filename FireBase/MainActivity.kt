package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myRef = Firebase.database.reference // creating a database reference
        //val userId = "userid-dummy"

        // finding id from R.file
        val name = findViewById<TextView>(R.id.name)  //finding name
        val email = findViewById<TextView>(R.id.email) //finding email

        val button = findViewById<Button>(R.id.button) //finding button
        val update = findViewById<Button>(R.id.update)  // finding update
        val delete = findViewById<Button>(R.id.delete)  // finding delete button
        val view = findViewById<Button>(R.id.view)      // finding view button
        val userId = findViewById<TextView>(R.id.userId) // finding user id text view
        val userMail = findViewById<TextView>(R.id.userMail)// finding user mail text view
        //set on click listener

        button.setOnClickListener {

            //class function to variable
            val userData = UserData(name.text.toString(), email.text.toString())
            /*
             users (Root)----lv1
              |
             UserId (child)---lv2
               |
              / \
           Name Email (child)---lv3

            */
            myRef.child("users").child(name.text.toString()).setValue(userData)
                .addOnSuccessListener {

                    Toast.makeText(this, "Successfully Data added", Toast.LENGTH_SHORT).show()

                }.addOnFailureListener {

                it.printStackTrace() // Exception
            }
        }

        update.setOnClickListener {
            val userData = UserData(name.text.toString(), email.text.toString())
            myRef.child("users").child(name.text.toString()).updateChildren(userData.getMap())
                .addOnSuccessListener {

                    Toast.makeText(this, "Successfully Update Data", Toast.LENGTH_SHORT).show()

                }.addOnFailureListener {

                it.printStackTrace() // Exception
            }

        }

        delete.setOnClickListener {
            //val userData = UserData(name.text.toString(),email.text.toString())
            myRef.child("users").child(name.text.toString()).removeValue().addOnSuccessListener {

                Toast.makeText(this, "Successfully Remove Data", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {

                it.printStackTrace() // Exception
            }
        }

        view.setOnClickListener {
            //val userData = UserData(name.text.toString(),email.text.toString())
            myRef.child("users").child(name.text.toString()).get().addOnSuccessListener {

                //Toast.makeText(this,"Values:${it.value}",Toast.LENGTH_SHORT).show()
                //textView.setText(it.getValue().toString())
                if(it.exists()){
                    val userName= it.child("name").value
                    val email=it.child("email").value
                    userId.setText(userName.toString())
                    userMail.setText(email.toString())
                }
                else{
                    Toast.makeText(this, "Invalid UserId", Toast.LENGTH_SHORT).show()
                }

            }.addOnFailureListener {

                it.printStackTrace() // Exception
            }

            }
        }
    }
