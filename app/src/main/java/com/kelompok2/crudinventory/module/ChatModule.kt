package com.kelompok2.crudinventory.module

import com.kelompok2.crudinventory.presentation.authentication.login.LoginPresenter
import com.kelompok2.crudinventory.presentation.authentication.register.RegisterPresenter
import com.kelompok2.crudinventory.presentation.chatroom.ChatRoomPresenter
import com.google.firebase.auth.FirebaseAuth
import com.kelompok2.crudinventory.data.repository.authentication.AuthenticationRepository
import com.kelompok2.crudinventory.data.repository.database.MessageRepository
import com.kelompok2.crudinventory.data.route.ChatReferences
import org.koin.dsl.module

val chatModule = module {
    single { ChatReferences() }
    single { FirebaseAuth.getInstance() }

    factory { AuthenticationRepository(get(), get()) }
    factory { MessageRepository(get()) }

    factory { LoginPresenter(get()) }
    factory { RegisterPresenter(get()) }
    factory { ChatRoomPresenter(get()) }
}