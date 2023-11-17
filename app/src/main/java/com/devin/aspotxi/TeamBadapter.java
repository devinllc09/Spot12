package com.devin.aspotxi;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class TeamBadapter extends FirebaseRecyclerAdapter<TeamBModel, TeamBadapter.teamViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public TeamBadapter(@NonNull FirebaseRecyclerOptions<TeamBModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull teamViewHolder holder, int position, @NonNull TeamBModel model) {
        holder.name.setText(model.getName());
        holder.post.setText(model.getPost());
        holder.play.setText(model.getPlay());
        String plays = model.getPlay();
        switch (plays) {
            case "Last Played":
                holder.play.setTextColor(Color.BLUE);
                break;
            case "Not Playing":
                holder.play.setTextColor(Color.RED);
                break;
            case "Playing":
                holder.play.setTextColor(Color.GREEN);
                break;

        }
    }

    @NonNull
    @Override
    public teamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_b_item, parent, false);
        return new teamViewHolder(view);
    }

    class teamViewHolder extends RecyclerView.ViewHolder {
        TextView name, post, play;
        ImageView imageView;

        public teamViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.plyName);
            post = (TextView) itemView.findViewById(R.id.post);
            play = (TextView) itemView.findViewById(R.id.play);
        }
    }
}
