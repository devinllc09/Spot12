package com.devin.aspotxi;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myAdapter extends FirebaseRecyclerAdapter<Model, myAdapter.myHolder> {
//     boolean showshimer=true;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public myAdapter(@NonNull FirebaseRecyclerOptions<Model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myHolder holder, int position, @NonNull Model model) {
        holder.teamA.setText(model.getTeamA());
        holder.teamB.setText(model.getTeamB());
        holder.date.setText(model.getDate());
        holder.place.setText(model.getPlace());
        holder.gtitle.setText(model.getGtitle());
//        holder.matchint.setText(model.getMatchint());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity=(AppCompatActivity)v.getContext();

                final String teamA = model.getTeamA();
                final String teamB = model.getTeamB();
                final String mATCHiNT = model.getMatchint();
                Intent intent=new Intent(activity,TeamUpActivity.class);
                intent.putExtra("TeamA", teamA);
                intent.putExtra("TeamB", teamB);
                intent.putExtra("MatchKey", getRef(position).getKey());
                activity.startActivity(intent);
//                Toast.makeText(activity, "clicked", Toast.LENGTH_SHORT).show();
            }
        });
//        if (showshimer){
//            holder.shimmerFrameLayout.startShimmer();
//        }else {
//        holder.shimmerFrameLayout.stopShimmer();
//        holder.shimmerFrameLayout.setShimmer(null);
//        }
        String imga = model.getTeamA().toUpperCase();
        String imgb = model.getTeamB().toUpperCase();
        switch (imga) {
            case "CSK":
                holder.imgA.setImageResource(R.drawable.csk);
                break;
            case "DC":
                holder.imgA.setImageResource(R.drawable.ddc);
                break;
            case "SRH":
                holder.imgA.setImageResource(R.drawable.srh);
                break;
            case "KKR":
                holder.imgA.setImageResource(R.drawable.kkr);
                break;
            case "PK":
                holder.imgA.setImageResource(R.drawable.kip);
                break;
            case "MI":
                holder.imgA.setImageResource(R.drawable.mi);
                break;
            case "RCB":
                holder.imgA.setImageResource(R.drawable.rc);
                break;
            case "RR":
                holder.imgA.setImageResource(R.drawable.rr);
                break;
            default:
                holder.imgA.setImageResource(R.drawable.ic_launcher_background);
                break;
        }

        switch (imgb) {
            case "CSK":
                holder.imgB.setImageResource(R.drawable.csk);
                break;
            case "DC":
                holder.imgB.setImageResource(R.drawable.ddc);
                break;
            case "SRH":
                holder.imgB.setImageResource(R.drawable.srh);
                break;
            case "KKR":
                holder.imgB.setImageResource(R.drawable.kkr);
                break;
            case "PK":
                holder.imgB.setImageResource(R.drawable.kip);
                break;
            case "MI":
                holder.imgB.setImageResource(R.drawable.mi);
                break;
            case "RCB":
                holder.imgB.setImageResource(R.drawable.rc);
                break;
            case "RR":
                holder.imgB.setImageResource(R.drawable.rr);
                break;
            default:
                holder.imgB.setImageResource(R.drawable.ic_launcher_background);
                break;
        }

//        Glide.with(holder.imgA.getContext()).load(model.getImgA()).into(holder.imgA);
//        Glide.with(holder.imgB.getContext()).load(model.getImgB()).into(holder.imgB);
    }
    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cr, parent, false);
        return new myHolder(view);
    }

    class myHolder extends RecyclerView.ViewHolder {
        ImageView imgA, imgB;
        TextView teamA, teamB, date, gtitle, place;
//       ShimmerFrameLayout shimmerFrameLayout;
        public myHolder(@NonNull View itemView) {
            super(itemView);

            imgA = (ImageView) itemView.findViewById(R.id.img_a);
            imgB = (ImageView) itemView.findViewById(R.id.img_b);
            teamA = (TextView) itemView.findViewById(R.id.team_a);
            teamB = (TextView) itemView.findViewById(R.id.team_b);
            date = (TextView) itemView.findViewById(R.id.date_tv);
            place = (TextView) itemView.findViewById(R.id.location);
            gtitle = (TextView) itemView.findViewById(R.id.gtitle);
//            matchint = (TextView) itemView.findViewById(R.id.matchint);
//            shimmerFrameLayout=(ShimmerFrameLayout)itemView.findViewById(R.id.shimmerly);
        }
    }
    @Override
    public void onDataChanged() {
        super.onDataChanged();
        notifyDataSetChanged();
        notifyItemChanged(getItemCount());
        notifyItemInserted(getItemCount());
    }
}
