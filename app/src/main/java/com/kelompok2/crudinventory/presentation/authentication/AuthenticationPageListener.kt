package com.kelompok2.crudinventory.presentation.authentication

import com.kelompok2.crudinventory.data.entity.User


interface AuthenticationPageListener {
    fun onLoginPage()

    fun onRegisterPage()

    fun onAuthenticateSuccess(user: User)
}
