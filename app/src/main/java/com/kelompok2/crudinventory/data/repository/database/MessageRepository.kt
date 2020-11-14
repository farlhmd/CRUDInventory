package com.kelompok2.crudinventory.data.repository.database


import com.kelompok2.crudinventory.data.entity.Chat
import com.google.firebase.database.*
import com.kelompok2.crudinventory.data.route.ChatReferences

class MessageRepository(private val chatReferences: ChatReferences) {

    private val messageIds = mutableListOf<String>()

    fun getMessage(messageRepositoryCallback: MessageRepositoryCallback) {
        chatReferences.chatReferences()
                .addChildEventListener(object : ChildEventListener {
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onChildMoved(p0: DataSnapshot, p1: String?) {

                    }

                    override fun onChildChanged(dataSnapshot: DataSnapshot, p1: String?) {
                        val chat = dataSnapshot.getValue(Chat::class.java) as Chat
                        val position = messageIds.indexOf(chat.messageId)

                        messageRepositoryCallback.onMessageUpdate(position, chat)
                    }

                    override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                        val chat = dataSnapshot.getValue(Chat::class.java) as Chat
                        messageIds.add(chat.messageId ?: "0")

                        messageRepositoryCallback.onMessageComing(chat)
                    }

                    override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                        val chat = dataSnapshot.getValue(Chat::class.java) as Chat
                        val position = messageIds.indexOf(chat.messageId)

                        messageIds.removeAt(position)
                        messageRepositoryCallback.onMessageDeleted(position)
                    }
                })
    }

    fun sendMessage(chat: Chat) {
        val messageId = chatReferences.chatReferences().push().key
        chatReferences.chatReferences()
                .child(messageId ?: "0")
                .setValue(chat.copy(messageId = messageId))
    }
}