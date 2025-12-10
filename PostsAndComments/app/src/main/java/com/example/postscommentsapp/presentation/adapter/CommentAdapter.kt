package com.example.postscommentsapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postscommentsapp.databinding.ItemCommentBinding
import com.example.postscommentsapp.domain.model.Comment   // <── ESTE FALTABA

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    private var list: List<Comment> = emptyList()

    fun submitList(comments: List<Comment>) {
        list = comments
        notifyDataSetChanged()
    }

    inner class CommentViewHolder(val binding: ItemCommentBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(
            ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = list[position]
        holder.binding.tvComment.text = comment.comment
    }
}
