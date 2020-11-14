package com.kelompok2.crudinventory.presentation.authentication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kelompok2.crudinventory.presentation.authentication.register.RegisterFragment
import com.kelompok2.crudinventory.R
import com.kelompok2.crudinventory.activities.MainActivity
import com.kelompok2.crudinventory.data.entity.User
import com.kelompok2.crudinventory.preferences.ChatPreferences
import com.kelompok2.crudinventory.presentation.authentication.login.LoginFragment
import com.kelompok2.crudinventory.presentation.chatroom.ChatRoomActivity
import com.kelompok2.crudinventory.replaceFragment

class AuthenticationActivity : AppCompatActivity(), AuthenticationPageListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        if (savedInstanceState == null) {
            replaceFragment(R.id.containerAuth, LoginFragment())
        }
    }

    override fun onLoginPage() = replaceFragment(R.id.containerAuth, LoginFragment())

    override fun onRegisterPage() = replaceFragment(R.id.containerAuth, RegisterFragment())

    override fun onAuthenticateSuccess(user: User) {
        ChatPreferences.initPreferences(this).userInfo = user
        startActivity(Intent(this@AuthenticationActivity, MainActivity::class.java))
        finish()
    }
}
