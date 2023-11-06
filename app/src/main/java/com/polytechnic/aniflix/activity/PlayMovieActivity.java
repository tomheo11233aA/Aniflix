package com.polytechnic.aniflix.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.polytechnic.aniflix.MyApplication;
import com.polytechnic.aniflix.R;
import com.polytechnic.aniflix.adapter.CommentAdapter;
import com.polytechnic.aniflix.model.Comment;
import com.polytechnic.aniflix.model.Movie;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.polytechnic.aniflix.utils.RandomComment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PlayMovieActivity extends AppCompatActivity {

    private KProgressHUD progressHUD;
    private WebView webView;
    private Movie mMovie;
    private ImageButton btnSendCmt;
    private EditText edtCmt;

    private RecyclerView rcvComment;

    private ImageButton likeButton;

    private TextView likeCountTextView;

    private TextView tvNameOfVideo;


    @SuppressLint("SetJavaScriptEnabled")
    @TargetApi(Build.VERSION_CODES.ECLAIR_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Comment> commentList = new ArrayList();
        Comment comment1 = new Comment("Văn Nam Phúc", RandomComment.get(), "https://firebasestorage.googleapis.com/v0/b/aniflix-958d2.appspot.com/o/Phuc.png?alt=media&token=81222b2d-4751-41e6-a17a-6b21eccca2c2");
        Comment comment2 = new Comment("Hoàng Văn Thành", RandomComment.get(), "https://firebasestorage.googleapis.com/v0/b/aniflix-958d2.appspot.com/o/thanhcac.jpg?alt=media&token=fe6b856d-7396-417a-abd1-7ce2ef69c1f8");
        Comment comment3 = new Comment("Huỳnh Quí", RandomComment.get(), "https://firebasestorage.googleapis.com/v0/b/aniflix-958d2.appspot.com/o/quidaubuoi.jpg?alt=media&token=9f4b8515-3da7-45b7-a41a-a466516f4fb9");
        Comment comment4 = new Comment("Phi Phong", RandomComment.get(), "https://firebasestorage.googleapis.com/v0/b/aniflix-958d2.appspot.com/o/phiem.jpg?alt=media&token=ad4bb852-1d4b-4c06-952a-0144ddfdaf19");
        Comment comment5 = new Comment("Pháp", RandomComment.get(), "https://firebasestorage.googleapis.com/v0/b/aniflix-958d2.appspot.com/o/phapngu.jpg?alt=media&token=805faa1b-2398-48c7-ac59-00dce6c9855c");
        Comment comment6 = new Comment("Long", RandomComment.get(), "https://firebasestorage.googleapis.com/v0/b/aniflix-958d2.appspot.com/o/long.jpg?alt=media&token=52b85a62-b690-4ec6-a08f-939076c697ff");
        Comment comment7 = new Comment("Phúc Hoàng", RandomComment.get(), "https://firebasestorage.googleapis.com/v0/b/aniflix-958d2.appspot.com/o/cuphuc.jpg?alt=media&token=2719a56f-25aa-496b-9d2e-8dfc82a0e58f");
        Comment comment8 = new Comment("Đình Duy", RandomComment.get(), "https://firebasestorage.googleapis.com/v0/b/aniflix-958d2.appspot.com/o/anhguot.jpg?alt=media&token=67d94a76-d771-49d8-a9e9-3746cee2fa47");
        commentList.add(comment1);
        commentList.add(comment2);
        commentList.add(comment3);
        commentList.add(comment4);
        commentList.add(comment5);
        commentList.add(comment6);
        commentList.add(comment7);
        commentList.add(comment8);

        Collections.shuffle(commentList);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_play_movie);
        tvNameOfVideo = findViewById(R.id.tvNameOfVideo);
        rcvComment = findViewById(R.id.listComment);
        CommentAdapter commentAdapter = new CommentAdapter(commentList,this);
        rcvComment.setLayoutManager(new LinearLayoutManager(this));
        rcvComment.setAdapter(commentAdapter);
        getDataIntent();

        tvNameOfVideo.setText(mMovie.getTitle());

        btnSendCmt = findViewById(R.id.sendComment);
        edtCmt = findViewById(R.id.edtComment);
        likeButton = findViewById(R.id.likeButton);
        likeCountTextView = findViewById(R.id.likeCountTextView);
        likeButton.setTag("not_liked");
        int randomLikes = 3 + new Random().nextInt(50);
        likeCountTextView.setText(String.valueOf(randomLikes));

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentLikes = Integer.parseInt(likeCountTextView.getText().toString());
                if (likeButton.getTag() == null || likeButton.getTag().equals("not_liked")) {
                    likeButton.setImageResource(R.drawable.like_filled);
                    likeButton.setTag("liked");
                    currentLikes++;
                } else {
                    likeButton.setImageResource(R.drawable.like);
                    likeButton.setTag("not_liked");
                    currentLikes--;
                }
                likeCountTextView.setText(String.valueOf(currentLikes));
            }
        });

        btnSendCmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtCmt.setText("");
                InputMethodManager imm = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                }
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });

        webView = findViewById(R.id.web_view);

        progressHUD = KProgressHUD.create(PlayMovieActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait...")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setSupportZoom(false);
        webSettings.setSaveFormData(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webSettings.setUseWideViewPort(true);
        webSettings.setSavePassword(true);
        webSettings.setEnableSmoothTransition(true);

        webView.setWebChromeClient(new ChromeClient());

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                setTitle("Loading " + mMovie.getTitle());
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                setTitle(view.getTitle());
                progressHUD.dismiss();
            }
        });

        webView.loadUrl(mMovie.getUrl());
        setHistory();
    }

    private void getDataIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        mMovie = (Movie) bundle.get("object_movie");
        if (mMovie == null) {
            Log.d("MyApp", "Movie object is null");
        } else {
            Log.d("MyApp", "Received Movie object: " + mMovie.getTitle());
        }
    }

    private void setHistory() {
        if (mMovie.isHistory()) {
            return;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("history", true);
        MyApplication.get(this).getDatabaseReference()
                .child(String.valueOf(mMovie.getId())).updateChildren(map);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        webView.restoreState(savedInstanceState);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish();
                }
                return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    private class ChromeClient extends WebChromeClient {
        private View mCustomView;
        private WebChromeClient.CustomViewCallback mCustomViewCallback;
        private int mOriginalOrientation;
        private int mOriginalSystemUiVisibility;

        ChromeClient() {}

        public Bitmap getDefaultVideoPoster() {
            if (mCustomView == null) {
                return null;
            }
            return BitmapFactory.decodeResource(getApplicationContext().getResources(), 2130837573);
        }

        public void onHideCustomView() {
            ((FrameLayout) getWindow().getDecorView()).removeView(this.mCustomView);
            this.mCustomView = null;
            getWindow().getDecorView().setSystemUiVisibility(this.mOriginalSystemUiVisibility);
            setRequestedOrientation(this.mOriginalOrientation);
            this.mCustomViewCallback.onCustomViewHidden();
            this.mCustomViewCallback = null;
        }

        public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback) {
            if (this.mCustomView != null) {
                onHideCustomView();
                return;
            }
            this.mCustomView = paramView;
            this.mOriginalSystemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
            this.mOriginalOrientation = getRequestedOrientation();
            this.mCustomViewCallback = paramCustomViewCallback;
            ((FrameLayout) getWindow().getDecorView()).addView(this.mCustomView, new FrameLayout.LayoutParams(-1, -1));
            getWindow().getDecorView().setSystemUiVisibility(3846 | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }
}
