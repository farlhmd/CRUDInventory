package com.kelompok2.crudinventory.presentation.authentication.login

import com.kelompok2.crudinventory.data.entity.User

interface LoginView {
    fun onEmailEmpty()
    fun onEmailInvalid()
    fun onPasswordEmpty()
    fun onLoginStart()
    fun onProgress(visibility: Int)
    fun onLoginSuccess(user: User)
    fun onLoginFailed(error: String?)
}