  package com.example.firebaseimage

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import java.net.URI
import java.text.SimpleDateFormat
import java.util.*

  class MainActivity : AppCompatActivity() {
      lateinit var imageView: ImageView
      lateinit var imageUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val selectImage = findViewById<Button>(R.id.selectImg)
        val uploadImage =  findViewById<Button>(R.id.uploadImg)
        imageView = findViewById(R.id.imageView)

        selectImage.setOnClickListener{
                selectImg()
        }


        uploadImage.setOnClickListener{
                uploadImg()
        }
    }
      private fun selectImg(){
          val intent = Intent()
          intent.type="image/*"
          intent.action=Intent.ACTION_GET_CONTENT

         startActivityForResult(intent,100)
      }
      private fun uploadImg(){
            val progressDialog = ProgressDialog(this)
          progressDialog.setMessage("Uploading File....")
          progressDialog.setCancelable(false)
          progressDialog.show()
          val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
          val now = Date()
          val fileName = formatter.format(now)
          val storageReference =FirebaseStorage.getInstance().getReference("images/$fileName")

          storageReference.putFile(imageUri).addOnSuccessListener {
              imageView.setImageURI(null)
              Toast.makeText(this,"Successfully Uploaded",Toast.LENGTH_SHORT).show()
              if(progressDialog.isShowing){
                  progressDialog.dismiss()
              }
          }
      }

      override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
          super.onActivityResult(requestCode, resultCode, data)

          if(requestCode==100 && resultCode== RESULT_OK){
              imageUri= data?.data!!
              imageView.setImageURI(imageUri)

          }
      }
}