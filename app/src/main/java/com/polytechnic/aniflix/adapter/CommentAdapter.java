package com.polytechnic.aniflix.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.polytechnic.aniflix.R;
import com.polytechnic.aniflix.model.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{
    List<Comment> commentList = new ArrayList<>();
    private Context context;

    public CommentAdapter(List<Comment> commentList, Context context) {
        this.commentList = commentList;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new CommentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {

        Comment cmt = commentList.get(position);
        if (cmt != null) {
            holder.tvName.setText(cmt.getName());
            holder.tvComment.setText(cmt.getCommnet());
            Glide.with(context).load(cmt.getUrlImage()).error(R.drawable.ic_no_image).into(holder.imgUser);
        }
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvName;
        private final TextView tvComment;
        private final CircleImageView imgUser;
        private final TextView tvTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name);
            tvComment = itemView.findViewById(R.id.comment);
            imgUser = itemView.findViewById(R.id.imgUser);
            tvTime = itemView.findViewById(R.id.time);
            Random random = new Random();
            int randomNumber = random.nextInt(10) + 1;
            tvTime.setText(randomNumber+" tiếng trước");
        }
    }
}
