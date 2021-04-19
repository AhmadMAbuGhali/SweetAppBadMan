package com.example.sweetapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sweetapp.Adapter_Iteam.AdapterIteamChaletList;
import com.example.sweetapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterChaletlistOwner extends RecyclerView.Adapter<AdapterChaletlistOwner.Holder> {
    ArrayList<AdapterIteamChaletList> adapterIteamChaletLists;
    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public AdapterChaletlistOwner(ArrayList<AdapterIteamChaletList> adapterIteamChaletLists) {
        this.adapterIteamChaletLists = adapterIteamChaletLists;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chaletlist_item, null, false);
        Holder holder = new Holder(v);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        AdapterIteamChaletList A_I_C=adapterIteamChaletLists.get(position);

//        holder.iv_Chalet.setImageResource(A_I_C.getImg_Chalet());
        Picasso.get().load(A_I_C.getImg_Chalet()).into( holder.iv_Chalet);

        holder.tv_name_Chalet.setText(A_I_C.getName_Chalet());

        holder.tv_Salary.setText(A_I_C.getSalary());
        holder.tv_Thenumberofhours_Chalet.setText(A_I_C.getNumOfHours());
        holder.tv_Title_Chalet.setText(A_I_C.getAddress_Chalet());

        holder.rb_Evaluation_Chalet.setRating(A_I_C.getEvaluation_Chalet());



    }

    @Override
    public int getItemCount() {
        return adapterIteamChaletLists.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView iv_Chalet;
        TextView tv_name_Chalet, tv_Salary, tv_Title_Chalet, tv_Thenumberofhours_Chalet;
        RatingBar rb_Evaluation_Chalet;

        public Holder(@NonNull View itemView) {


            super(itemView);
            iv_Chalet = itemView.findViewById(R.id.image_Chaletlist);


            tv_name_Chalet = itemView.findViewById(R.id.name_chaletlist);
            tv_Salary = itemView.findViewById(R.id.salaryChaletlist);
            tv_Title_Chalet = itemView.findViewById(R.id.Titel_ChaletList);
            tv_Thenumberofhours_Chalet = itemView.findViewById(R.id.Thenumberofhours_ChaletList);

            rb_Evaluation_Chalet = itemView.findViewById(R.id.ratingBar_Chaletlist);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            onItemClickListener.onItemClick(position);
                        }
                    }
                }
            });


        }
    }
}
