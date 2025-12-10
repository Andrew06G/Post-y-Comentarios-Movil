package com.example.postscommentsapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postscommentsapp.databinding.ItemCommentBinding

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

private var list: List<Comment> = emptyList()

fun submitList(comments: List<Comment>) {
    list = comments
    notifyDataSetChanged()
}

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
    val binding = ItemCommentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
    )
    return CommentViewHolder(binding)
}

override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
    holder.bind(list[position])
}

override fun getItemCount(): Int = list.size

class CommentViewHolder(private val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {

    fun bind(comment: Comment) {
        binding.tvComment.text = comment.comment
    }
}
}
