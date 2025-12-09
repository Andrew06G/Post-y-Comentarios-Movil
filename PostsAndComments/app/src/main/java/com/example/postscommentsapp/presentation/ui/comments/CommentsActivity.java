package com.example.postscommentsapp.presentation.ui.comments;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.postscommentsapp.R;
import com.example.postscommentsapp.presentation.adapter.CommentsAdapter;
import com.example.postscommentsapp.presentation.viewmodel.CommentsViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CommentsActivity extends ComponentActivity {

    private CommentsViewModel viewModel;
    private CommentsAdapter adapter;
    private int postId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        postId = getIntent().getIntExtra("postId", 0);

        RecyclerView rv = findViewById(R.id.rvComments);
        EditText etComment = findViewById(R.id.etComment);
        Button btnSend = findViewById(R.id.btnSend);

        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommentsAdapter();
        rv.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(CommentsViewModel.class);
        viewModel.comments.observe(this, list -> adapter.submitList(list));

        viewModel.loadComments(postId);

        btnSend.setOnClickListener(v -> {
            String text = etComment.getText().toString();
            if (!text.isEmpty()) {
                viewModel.addComment(postId, text);
                etComment.setText("");
            }
        });
    }
}
