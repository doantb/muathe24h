package com.example.windows10now.muathe24h.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.windows10now.muathe24h.R;
import com.example.windows10now.muathe24h.model.CardType;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

import static com.example.windows10now.muathe24h.R.drawable.bg_home_network;

/**
 * Created by Windows 10 Now on 11/14/2017.
 */

public class ChoseHomeNetWorkAdapter
        extends RecyclerView.Adapter<ChoseHomeNetWorkAdapter.CardTypeViewHolder> {
    private Context mContext;
    private ArrayList<CardType> mCardTypes = new ArrayList<>();
    private onHandleClick mOnHandleClick;

    public ChoseHomeNetWorkAdapter(Context context, ArrayList<CardType> cardTypes) {
        mContext = context;
        mCardTypes = cardTypes;
    }

    @Override
    public CardTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_card_type, parent, false);
        return new CardTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardTypeViewHolder holder, final int position) {
        CardType cardType = mCardTypes.get(position);
        Picasso.with(mContext).load(cardType.getImgLogo()).fit().centerInside().into(holder.imgCardType);
        holder.txtNameHomeNet.setText(cardType.getNameHomeNetWork());
        if (cardType.isWatch()){
            holder.llItemCardType.setBackgroundResource(bg_home_network);
        }
        holder.llItemCardType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnHandleClick.onClickCardType(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCardTypes.size();
    }

    public void setOnHandleClick(onHandleClick onHandleClick) {
        mOnHandleClick = onHandleClick;
    }

    public class CardTypeViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgCardType;
        private TextView txtNameHomeNet;
        private LinearLayout llItemCardType;

        public CardTypeViewHolder(View itemView) {
            super(itemView);
            imgCardType = itemView.findViewById(R.id.img_cart_type);
            txtNameHomeNet = itemView.findViewById(R.id.txt_home_net);
            llItemCardType = itemView.findViewById(R.id.ll_item_card);
        }
    }

    public interface onHandleClick{
        void onClickCardType(int position);
    }
}
