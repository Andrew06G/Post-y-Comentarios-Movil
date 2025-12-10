package com.example.postscommentsapp.presentation.ui.comments

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.postscommentsapp.R
import com.example.postscommentsapp.presentation.adapter.CommentAdapter
import com.example.postscommentsapp.presentation.viewmodel.CommentsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentsActivity : ComponentActivity() {

    private val viewModel: CommentsViewModel by viewModels()
    private lateinit var adapter: CommentAdapter
    private var postId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        postId = intent.getIntExtra("postId", 0)

        val rv = findViewById<RecyclerView>(R.id.rvComments)
        val etComment = findViewById<EditText>(R.id.etComment)
        val btnSend = findViewById<Button>(R.id.btnSend)

        rv.layoutManager = LinearLayoutManager(this)
        adapter = CommentAdapter()
        rv.adapter = adapter

        viewModel.comments.observe(this) { list ->
            adapter.submitList(list)
        }

        viewModel.loadComments(postId)

        btnSend.setOnClickListener {
            val text = etComment.text.toString()
            if (text.isNotEmpty()) {
                viewModel.addComment(postId, text)
                etComment.setText("")
            }
        }
    }
}
