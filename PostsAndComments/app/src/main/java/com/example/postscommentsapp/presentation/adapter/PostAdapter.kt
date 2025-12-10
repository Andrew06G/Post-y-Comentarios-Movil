package com.example.postscommentsapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postscommentsapp.databinding.ItemPostBinding
import com.example.postscommentsapp.domain.model.Post  // <── FALTABA

class PostAdapter(
    private val listener: (Post) -> Unit
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private var list: List<Post> = emptyList()

    fun submitList(posts: List<Post>) {
        list = posts
        notifyDataSetChanged()
    }

    inner class PostViewHolder(val binding: ItemPostBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount() = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = list[position]
        holder.binding.tvTitle.text = post.title
        holder.binding.root.setOnClickListener { listener(post) }
    }
}
