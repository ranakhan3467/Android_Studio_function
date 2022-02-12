package com.example.readimagefromfirebase

import android.app.ProgressDialog
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imgView = findViewById<ImageView>(R.id.imageView)
        val search = findViewById<TextView>(R.id.search)
        val btnSearch = findViewById<Button>(R.id.button)

        btnSearch.setOnClickListener {
            val progressDialog = ProgressDialog(this)
            progressDialog.setMessage("Fetching image....")
            progressDialog.setCancelable(false)
            progressDialog.show()

            val imageName= search.text.toString()
            val storageRef=FirebaseStorage.getInstance().reference.child("images/$imageName")

            val localfile = File.createTempFile("tempImage","jpg")
            storageRef.getFile(localfile).addOnSuccessListener {

                if(progressDialog.isShowing){
                    progressDialog.dismiss()
                }
                val bitmap =BitmapFactory.decodeFile(localfile.absolutePath)
                imgView.setImageBitmap(bitmap)

            }.addOnFailureListener{
                progressDialog.dismiss()
                //it.printStackTrace()
                Toast.makeText(this,"Fail to load Image From FireBase Storage",Toast.LENGTH_SHORT).show()
            }
        }
    }
}