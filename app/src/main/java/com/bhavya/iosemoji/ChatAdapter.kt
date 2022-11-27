package com.bhavya.iosemoji

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bhavya.iosemoji.ChatAdapter.ChatViewHolder
import com.fueled.iosemoji.R
import com.vanniktech.emoji.EmojiTextView
import com.vanniktech.emoji.emojiInformation

internal class ChatAdapter : RecyclerView.Adapter<ChatViewHolder>() {
    private val texts = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ChatViewHolder(layoutInflater.inflate(R.layout.item_adapter_chat, parent, false))
    }

    override fun onBindViewHolder(chatViewHolder: ChatViewHolder, position: Int) {
        val text = texts[position]
        val (isOnlyEmojis, emojis) = text.emojiInformation()
        val res: Int = when {
            isOnlyEmojis && emojis.size == 1 -> R.dimen.emoji_size_single_emoji
            isOnlyEmojis && emojis.size > 1 -> R.dimen.emoji_size_only_emojis
            else -> R.dimen.emoji_size_default
        }
        chatViewHolder.textView.setEmojiSizeRes(res, false)
        chatViewHolder.textView.text = text
    }

    override fun getItemCount() = texts.size

    fun add(text: String) {
        texts.add(text)
        notifyItemInserted(texts.size - 1)
    }

    internal class ChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: EmojiTextView = view.findViewById(R.id.itemAdapterChatTextView)
    }
}
