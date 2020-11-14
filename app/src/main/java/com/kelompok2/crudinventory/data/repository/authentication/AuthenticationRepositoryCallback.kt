package com.kelompok2.crudinventory.data.repository.authentication

import com.kelompok2.crudinventory.data.entity.User

interface AuthenticationRepositoryCallback {
    fun onSuccess(user: User)
    fun onFailed(error: String?)
}