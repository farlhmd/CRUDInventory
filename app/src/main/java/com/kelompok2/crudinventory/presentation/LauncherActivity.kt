package com.kelompok2.crudinventory.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.firebase.auth.FirebaseAuth
import com.kelompok2.crudinventory.ChatApplication
import com.kelompok2.crudinventory.R
import com.kelompok2.crudinventory.activities.MainActivity
import com.kelompok2.crudinventory.presentation.authentication.AuthenticationActivity
import com.kelompok2.crudinventory.presentation.chatroom.ChatRoomActivity

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        val auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            startActivity(Intent(this, AuthenticationActivity::class.java))
        }

        finish()
    }
}
