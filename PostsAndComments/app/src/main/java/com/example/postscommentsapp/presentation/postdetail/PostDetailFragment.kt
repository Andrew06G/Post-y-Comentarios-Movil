package com.example.postscommentsapp.presentation.postdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.postscommentsapp.databinding.FragmentPostDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailFragment : Fragment() {

    private var _binding: FragmentPostDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PostDetailViewModel by viewModels()
    private val args: PostDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val postId = args.postId

        // Observar el post cargado
        viewModel.post.observe(viewLifecycleOwner) { post ->
            post?.let {
                binding.txtDetailTitle.text = it.title
                binding.txtDetailBody.text = it.body
            }
        }

        // Observar errores
        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

        // Observar estado de carga
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            // Aquí podrías mostrar/ocultar un ProgressBar si lo agregas al layout
        }

        // Cargar el post
        viewModel.loadPost(postId)

        // Configurar botón para ver comentarios
        binding.btnVerComentarios.setOnClickListener {
            val action = PostDetailFragmentDirections
                .actionPostDetailFragmentToCommentListFragment(postId)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
