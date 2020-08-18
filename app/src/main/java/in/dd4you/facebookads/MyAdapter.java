package in.dd4you.facebookads;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdView;

import java.util.ArrayList;

import in.dd4you.appsconfig.DD4YouConfig;

/**
 * Created by Vinay Singh (https://www.dd4you.in) on 16/08/2020
 * Copyright (c) 2018-2020. All rights reserved.
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Object> arrayList;

    private final int ITEM_TYPE_DATA = 0;
    private final int ITEM_TYPE_AD = 1;


    public MyAdapter(Context context, ArrayList<Object> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_AD) {
            return new NativeAdViewHolder(LayoutInflater.from(context).inflate(R.layout.native_ad_layout, parent, false));
        } else {
            return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.row_item, parent, false));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            MyModel myModel = (MyModel) arrayList.get(position);
            myViewHolder.setData(myModel);
        } else {
            NativeAdViewHolder viewHolder = (NativeAdViewHolder) holder;
            NativeAd ad = (NativeAd) arrayList.get(position);

            View view = NativeAdView.render(context, ad, NativeAdView.Type.HEIGHT_300);
            viewHolder.nativeAdContainer.removeAllViews();
            viewHolder.nativeAdContainer.addView(view);
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (arrayList.get(position) instanceof NativeAd){
            return ITEM_TYPE_AD;
        }else {
            return ITEM_TYPE_DATA;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView nameLogo, nameTv, messageTv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameLogo = itemView.findViewById(R.id.nameLogo);
            nameTv = itemView.findViewById(R.id.nameTv);
            messageTv = itemView.findViewById(R.id.messageTv);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        private void setData(MyModel model) {
            DD4YouConfig dd4YouConfig = new DD4YouConfig(context);
            nameLogo.setText(dd4YouConfig.getFirstAndLastLetter(model.getName()));
            nameLogo.setBackgroundTintList(ColorStateList.valueOf(dd4YouConfig.getRandomRgbPinkishColorCode()));
            //  nameLogo.setBackgroundTintList(ContextCompat.getColorStateList(context, dd4YouConfig.getRandomRGBColorCode()));
            //  nameLogo.setBackgroundTintList(context.getResources().getColorStateList(R.color.colorAccent));
            nameTv.setText(model.getName());
            messageTv.setText(model.getMsg());
        }
    }

    //Native Ad viewholder
    class NativeAdViewHolder extends RecyclerView.ViewHolder {

        LinearLayout nativeAdContainer;

        NativeAdViewHolder(@NonNull View itemView) {
            super(itemView);
            nativeAdContainer = itemView.findViewById(R.id.adContainer);
        }
    }
}
