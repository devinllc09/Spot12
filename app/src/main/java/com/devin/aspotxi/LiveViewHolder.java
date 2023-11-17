package com.devin.aspotxi;

import android.app.Application;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

import org.w3c.dom.Text;

public class LiveViewHolder extends RecyclerView.ViewHolder {
    View mView;
    SimpleExoPlayerView simpleExoPlayerView;
    SimpleExoPlayer simpleExoPlayer;
    TextView titles;
    public LiveViewHolder(@NonNull View itemView) {
        super(itemView);
//        mView=itemView;
        simpleExoPlayerView=(SimpleExoPlayerView)itemView.findViewById(R.id.videoLive);
        titles=(TextView)itemView.findViewById(R.id.teaxTitle);
    }
    void prepareExoPlayer(final Application application,String title,final String url){
        try{
            titles.setText(title);
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            simpleExoPlayer =(SimpleExoPlayer) ExoPlayerFactory.newSimpleInstance(application,trackSelector);
            Uri videoURI = Uri.parse(url);

            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("LvidUrl");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null, null);

            simpleExoPlayerView.setPlayer(simpleExoPlayer);
            simpleExoPlayer.prepare(mediaSource);
            simpleExoPlayer.setPlayWhenReady(false);


        }catch (Exception e){
            Log.e("Exoplayer Crashed", e.getMessage());
            Toast.makeText(application, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}
