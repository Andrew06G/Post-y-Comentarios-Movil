package com.example.postscommentsapp.presentation.ui.posts

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postscommentsapp.R
import com.example.postscommentsapp.databinding.FragmentPostsBinding
import com.example.postscommentsapp.presentation.adapter.PostAdapter
import com.example.postscommentsapp.presentation.viewmodel.PostsViewModel   // <── FALTABA
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : Fragment(R.layout.fragment_posts) {

    private val viewModel: PostsViewModel by viewModels()

    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentPostsBinding.bind(view)

        val adapter = PostAdapter { post ->
            Toast.makeText(requireContext(), post.title, Toast.LENGTH_SHORT).show()
        }

        binding.rvPosts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPosts.adapter = adapter

        viewModel.posts.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.loadPosts()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
