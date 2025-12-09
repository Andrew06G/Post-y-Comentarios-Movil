package com.example.postscommentsapp.presentation.ui.comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postscommentsapp.databinding.FragmentCommentsBinding
import com.example.postscommentsapp.presentation.adapter.CommentAdapter
import com.example.postscommentsapp.presentation.viewmodel.CommentsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentsFragment : Fragment() {

    private var _binding: FragmentCommentsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CommentsViewModel by viewModels()

    private var postId: Int = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            _binding = FragmentCommentsBinding.inflate(inflater, container, false)
    return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        postId = arguments?.getInt("postId") ?: -1

        if (postId == -1) {
            Toast.makeText(requireContext(), "Error: postId inválido", Toast.LENGTH_SHORT).show()
            return
        }

        val adapter = CommentAdapter()
        binding.rvComments.layoutManager = LinearLayoutManager(requireContext())
        binding.rvComments.adapter = adapter

        viewModel.comments.observe(viewLifecycleOwner) { list ->
                adapter.submitList(list)
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
                binding.progressComments.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(viewLifecycleOwner) { msg ->
            if (msg != null) Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
        }

        viewModel.loadComments(postId)

        binding.btnSendComment.setOnClickListener {
            val text = binding.etCommentInput.text.toString().trim()
            if (text.isEmpty()) {
                Toast.makeText(requireContext(), "Escribe un comentario", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addComment(postId, text)
                binding.etCommentInput.setText("")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
