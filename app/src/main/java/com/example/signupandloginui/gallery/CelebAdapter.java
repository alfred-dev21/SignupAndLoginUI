package com.example.signupandloginui.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.signupandloginui.R;

import java.util.ArrayList;

public class CelebAdapter extends RecyclerView.Adapter<CelebAdapter.ViewHolder> {
    private ArrayList<Celebrity> celebrities = new ArrayList<>();
    private final Context context;

    public CelebAdapter(Context context) {
        this.context = context;
    }

    public void setCelebrities(ArrayList<Celebrity> celebrities) {
        this.celebrities = celebrities;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.celebrity_list_item,
                parent,
                false
        );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = celebrities.get(position).getName();
        holder.txtName.setText(name);
        holder.txtJobTitle.setText(celebrities.get(position).getJobTitle());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, name + " selected", Toast.LENGTH_SHORT).show();
            }
        });

        int drawableId = 0;

        switch (celebrities.get(position).getImageUrl()){
            case "megan":
                drawableId = R.drawable.megan;
                break;
            case "kanye":
                drawableId = R.drawable.kanye;
                break;
            case "rihanna":
                drawableId = R.drawable.rihanna;
                break;
            case "morgan":
                drawableId = R.drawable.morgan;
                break;
            case "cardi":
                drawableId = R.drawable.cardB;
                break;
            case "quincy":
                drawableId = R.drawable.background1;
                break;
            default:
                drawableId = R.drawable.background1;
                break;
        }

        Glide.with(context)
                .load(drawableId)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .apply(new RequestOptions())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return celebrities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView txtName, txtJobTitle;
        private final CardView parent;
        private final ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.txtName = itemView.findViewById(R.id.txtName);
            this.txtJobTitle = itemView.findViewById(R.id.txtJobTitle);
            this.parent = itemView.findViewById(R.id.parent);
            this.image = itemView.findViewById(R.id.image);
        }
    }

}
