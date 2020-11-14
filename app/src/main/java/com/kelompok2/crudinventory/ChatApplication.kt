package com.kelompok2.crudinventory

import android.app.Application
import com.kelompok2.crudinventory.module.chatModule
import com.google.firebase.database.FirebaseDatabase
import org.koin.core.context.startKoin


class ChatApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val database = FirebaseDatabase.getInstance()
        database.setPersistenceEnabled(true)

        startKoin { modules(chatModule) }
    }
}