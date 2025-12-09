package com.example.postscommentsapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postscommentsapp.databinding.ItemPostBinding
import com.example.postscommentsapp.domain.model.Post

class PostAdapter(
    private val listener: (Post) -> Unit
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private var list: List<Post> = emptyList()

    fun submitList(posts: List<Post>) {
        list = posts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    override fun getItemCount() = list.size

    class PostViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post, listener: (Post) -> Unit) {
            binding.tvTitle.text = post.title
            itemView.setOnClickListener { listener(post) }
        }
    }
}
