package com.example.postscommentsapp.presentation.comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postscommentsapp.databinding.FragmentCommentListBinding
import com.example.postscommentsapp.presentation.adapter.CommentAdapter
import com.example.postscommentsapp.presentation.viewmodel.CommentsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentListFragment : Fragment() {

    private var _binding: FragmentCommentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CommentsViewModel by viewModels()
    private val args: CommentListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val postId = args.postId

        if (postId == -1) {
            Toast.makeText(requireContext(), "Error: postId inválido", Toast.LENGTH_SHORT).show()
            return
        }

        val adapter = CommentAdapter()
        binding.recyclerComments.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerComments.adapter = adapter

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
