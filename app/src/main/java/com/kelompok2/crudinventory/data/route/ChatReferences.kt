package com.kelompok2.crudinventory.data.route

import com.google.firebase.database.FirebaseDatabase

class ChatReferences {

    fun chatReferences() = FirebaseDatabase.getInstance().getReference("chat")

    fun userReferences() = FirebaseDatabase.getInstance().getReference("user")

}