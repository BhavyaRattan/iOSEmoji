package com.bhavya.iosemoji

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fueled.iosemoji.R
import com.fueled.iosemoji.databinding.ActivityMainBinding
import com.vanniktech.emoji.EmojiPopup

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var emojiPopup: EmojiPopup


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        chatAdapter = ChatAdapter()

        emojiPopup = EmojiPopup(
            rootView = binding.rootView,
            editText = binding.chatEditText,
            onEmojiPopupShownListener = { binding.chatEmoji.setImageResource(R.drawable.ic_keyboard) },
            onEmojiPopupDismissListener = { binding.chatEmoji.setImageResource(R.drawable.ic_emojis) },
            keyboardAnimationStyle = com.vanniktech.emoji.ios.R.style.emoji_fade_animation_style
        )

        binding.chatSend.setColorFilter(
            ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.SRC_IN
        )
        binding.chatEmoji.setColorFilter(
            ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.SRC_IN
        )

        binding.chatEmoji.setOnClickListener { emojiPopup.toggle() }
        binding.chatSend.setOnClickListener {
            val text = binding.chatEditText.text.toString().trim { it <= ' ' }
            if (text.isNotEmpty()) {
                chatAdapter.add(text)
                binding.chatEditText.setText("")
            }
        }
        binding.recyclerView.adapter = chatAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }
}
