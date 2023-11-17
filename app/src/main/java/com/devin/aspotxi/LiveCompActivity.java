package com.devin.aspotxi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.Objects;

public class LiveCompActivity extends AppCompatActivity  {
    RadioButton infobtn, livebtn, teambtn, lscore, lvideo;
    RelativeLayout infolyt, livelyt, teamlyt, teamAlyt, teamBlyt;
    TextView matchno, date, time;
    LinearLayout mumbai, delhi, kolkata, ahmedabad, chennai, benguluru;
    RecyclerView teamArec, teamBrec, liveVideoRecView;
    private String teamAchild, teamBchild;
    TeamAdapter teamAdapter;
    TeamBadapter teamBadapter;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_comp);

        teamArec = findViewById(R.id.teamArecV);
        teamBrec = findViewById(R.id.teamBrecV);
        teamAlyt = findViewById(R.id.teamAlyt);
        teamBlyt = findViewById(R.id.teamblyt);

        final Bundle bundle = getIntent().getExtras();
        teamAchild = "Team/" + Objects.requireNonNull(bundle.getString("TeamA")).toUpperCase();
        teamBchild = "Team/" + Objects.requireNonNull(bundle.getString("TeamB")).toUpperCase();
        lscore = findViewById(R.id.lscore);
        lvideo = findViewById(R.id.lvideo);

        infobtn = findViewById(R.id.infobtn);
        livebtn = findViewById(R.id.livebtn);
        teambtn = findViewById(R.id.teambtn);
        infolyt = findViewById(R.id.infom);
        livelyt = findViewById(R.id.livelyt);
        teamlyt = findViewById(R.id.teamlyt);

        mumbai = findViewById(R.id.Mumbai);
        delhi = findViewById(R.id.Delhi);
        kolkata = findViewById(R.id.Kolkata);
        chennai = findViewById(R.id.Cheanni);
        ahmedabad = findViewById(R.id.Ahemdabad);
        benguluru = findViewById(R.id.Bengloru);


        matchno = findViewById(R.id.matchIntTv);
        date = findViewById(R.id.dates);
        time = findViewById(R.id.times);

        String teamA = Objects.requireNonNull(bundle.getString("TeamA")).toUpperCase();
        String teamB = getIntent().getStringExtra("TeamB");
        lscore.setText(teamA);
        lvideo.setText(teamB);
        String matchint = getIntent().getStringExtra("Matchint");
        String dates = getIntent().getStringExtra("Date");
        String times = getIntent().getStringExtra("Time");
        matchno.setText(matchint);
        date.setText(dates);
        time.setText(times);
        String places = getIntent().getStringExtra("Place");
        switch (places) {
            case "Wankhede Stadium, Mumbai":
                mumbai.setVisibility(View.VISIBLE);
                delhi.setVisibility(View.GONE);
                kolkata.setVisibility(View.GONE);
                chennai.setVisibility(View.GONE);
                ahmedabad.setVisibility(View.GONE);
                benguluru.setVisibility(View.GONE);
//                        holder.imgA.setImageResource(R.drawable.csk);
                break;
            case "MA Chidambaram Stadium, Chennai":
                mumbai.setVisibility(View.GONE);
                delhi.setVisibility(View.GONE);
                kolkata.setVisibility(View.GONE);
                chennai.setVisibility(View.VISIBLE);
                ahmedabad.setVisibility(View.GONE);
                benguluru.setVisibility(View.GONE);
                break;
            case "Narendra Modi Stadium Motera, Ahmedabad":
                mumbai.setVisibility(View.GONE);
                delhi.setVisibility(View.GONE);
                kolkata.setVisibility(View.GONE);
                chennai.setVisibility(View.GONE);
                ahmedabad.setVisibility(View.VISIBLE);
                benguluru.setVisibility(View.GONE);
                break;
            case "Arun Jaitley Stadium, Delhi":
                mumbai.setVisibility(View.GONE);
                delhi.setVisibility(View.VISIBLE);
                kolkata.setVisibility(View.GONE);
                chennai.setVisibility(View.GONE);
                ahmedabad.setVisibility(View.GONE);
                benguluru.setVisibility(View.GONE);
                break;
            case "M.Chinnaswamy Stadium, Bengaluru":
                mumbai.setVisibility(View.GONE);
                delhi.setVisibility(View.GONE);
                kolkata.setVisibility(View.GONE);
                chennai.setVisibility(View.GONE);
                ahmedabad.setVisibility(View.GONE);
                benguluru.setVisibility(View.VISIBLE);
                break;
            case "Eden Gardens, Kolkata":
                mumbai.setVisibility(View.GONE);
                delhi.setVisibility(View.GONE);
                kolkata.setVisibility(View.VISIBLE);
                chennai.setVisibility(View.GONE);
                ahmedabad.setVisibility(View.GONE);
                benguluru.setVisibility(View.GONE);
                break;

        }

//        reference= FirebaseDatabase.getInstance().getReference().child("Matches");
//        reference.child(matchkeys).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        infolyt.setVisibility(View.VISIBLE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24));
//        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        assert bundle != null;
        Objects.requireNonNull(getSupportActionBar()).setTitle(Objects.requireNonNull(bundle.getString("TeamA")).toUpperCase()
                + " vs " + Objects.requireNonNull(bundle.getString("TeamB")).toUpperCase());
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(interstitialAd.isLoaded()){
//                    interstitialAd.show();
//                } else {
                LiveCompActivity.super.onBackPressed();
//                }
            }
        });
        teamArec = findViewById(R.id.teamArecV);
        teamArec.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<TeamModel> options =
                new FirebaseRecyclerOptions.Builder<TeamModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child(teamAchild), TeamModel.class)
                        .build();
        teamAdapter = new TeamAdapter(options);

        teamBrec = findViewById(R.id.teamBrecV);
        teamBrec.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<TeamBModel> option =
                new FirebaseRecyclerOptions.Builder<TeamBModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child(teamBchild), TeamBModel.class)
                        .build();
        teamBadapter = new TeamBadapter(option);

        String matchkeys = getIntent().getStringExtra("MatchKey");
        String childss=matchkeys+"/LvidUrl";

        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId=getIntent().getStringExtra("Urls");
                youTubePlayer.loadVideo(videoId, 0);
            }
        });


    }


    public void onRadioClick(View view) {
        boolean isSelected = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.infobtn:
                if (isSelected) {
                    infolyt.setVisibility(View.VISIBLE);
                    livelyt.setVisibility(View.GONE);
                    teamlyt.setVisibility(View.GONE);
                }
                break;
            case R.id.livebtn:
                if (isSelected) {
                    livelyt.setVisibility(View.VISIBLE);
                    infolyt.setVisibility(View.GONE);
                    teamlyt.setVisibility(View.GONE);
                }
                break;
            case R.id.teambtn:
                if (isSelected) {
                    livelyt.setVisibility(View.GONE);
                    infolyt.setVisibility(View.GONE);
                    teamlyt.setVisibility(View.VISIBLE);
                    teamArec.setAdapter(teamAdapter);
                    teamBrec.setAdapter(teamBadapter);
                }
                break;

        }
    }

    public void onliveRdClick(View view) {
        boolean isSelected = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.lscore:
                if (isSelected) {
                    lscore.setTextColor(Color.WHITE);
                    lvideo.setTextColor(Color.RED);
                    teamBlyt.setVisibility(View.GONE);
                    teamAlyt.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.lvideo:
                if (isSelected) {
                    lscore.setTextColor(Color.RED);
                    lvideo.setTextColor(Color.WHITE);
                    teamAlyt.setVisibility(View.GONE);
                    teamBlyt.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        teamAdapter.startListening();
        teamBadapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        teamAdapter.stopListening();
        teamBadapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        teamAdapter.startListening();
        teamBadapter.startListening();
    }
}