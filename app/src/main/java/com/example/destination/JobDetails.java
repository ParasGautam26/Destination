package com.example.destination;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class JobDetails extends AppCompatActivity {
    private TextView mname,mprofile,mstipend,mlink;
    private ImageView mImageView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);
        mname = findViewById(R.id.nameText);
        mprofile = findViewById(R.id.profileText);
        mstipend = findViewById(R.id.descText);
        mImageView=findViewById(R.id.display_image);
        button = findViewById(R.id.button);

        Intent intent = getIntent();
        String companyname = intent.getStringExtra("name");
        String profile = intent.getStringExtra("profile");
        String stipend = intent.getStringExtra("desc");
        final String link = intent.getStringExtra("link");
        String thumb = intent.getStringExtra("thumb");

        mname.setText(companyname);
        mprofile.setText(profile);
        mstipend.setText(stipend);
        RequestOptions requestOptions = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(this)
                .load(thumb)
                .apply(requestOptions)
                .into(mImageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(link);
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });

    }
}
