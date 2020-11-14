package com.kelompok2.crudinventory.data.repository.database

import com.kelompok2.crudinventory.data.entity.Chat

interface MessageRepositoryCallback {
    fun onMessageComing(chat: Chat)
    fun onMessageUpdate(position: Int, chat: Chat)
    fun onMessageDeleted(position: Int)
}