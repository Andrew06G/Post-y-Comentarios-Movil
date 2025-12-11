package com.example.postscommentsapp.presentation.postlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postscommentsapp.databinding.ItemPostBinding
import com.example.postscommentsapp.domain.model.Post

class PostAdapter(
    private val onClick: (Post) -> Unit
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private val posts = mutableListOf<Post>()

    fun submitList(list: List<Post>) {
        posts.clear()
        posts.addAll(list)
        notifyDataSetChanged()
    }

    inner class PostViewHolder(val binding: ItemPostBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.txtTitle.text = post.title
            binding.txtBody.text = post.body

            binding.root.setOnClickListener {
                onClick(post)
            }
        }
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
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int = posts.size
}
