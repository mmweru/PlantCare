package com.example.plantcareai.dashboard

import android.graphics.Bitmap
import com.example.plantcareai.data.Chat

data class ChatState (
    val chatList: MutableList<Chat> = mutableListOf(),
    val prompt: String = "",
    val bitmap: Bitmap? = null
)