package com.example.test

import com.google.firebase.database.Exclude

data class UserData(val name:String ="",val email:String=""){

    // function to update data
    @Exclude
    fun getMap():Map<String,Any?>{
        return mapOf(
            //"name" to name,
            "email" to email // only email will update
        )
    }
}
