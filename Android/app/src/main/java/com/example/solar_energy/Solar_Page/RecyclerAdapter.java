package com.example.solar_energy.Solar_Page;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.solar_energy.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<Solar_Item> items;

    public RecyclerAdapter( List<Solar_Item> items) {
        this.items = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView company;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.txt_title);
            company = (TextView) itemView.findViewById(R.id.txt_company);
            cardview = (CardView) itemView.findViewById(R.id.cardview);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_solar, null);
        //set the view's size, margins, paddings and layout parameters

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Solar_Item item = items.get(position);

       // Drawable drawable = ContextCompat.getDrawable(context, item.getImage());
       // holder.image.setImageDrawable(drawable);
        Picasso.get().load(item.getImage()).into(holder.image);
        holder.title.setText(item.getName());
        holder.company.setText(item.getCompany());

/*
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, item.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,DetailPanelActivity.class);
                intent.putExtra("img",item.getImage());
                intent.putExtra("title",item.getTitle());
                intent.putExtra("company",item.getCompany());
                context.startActivity(intent);
            }
        });
        */
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }


}