package com.example.vincent.lab9;

import java.util.ArrayList;
import java.util.List;
import android.R.integer;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Vincent on 2016/11/28.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private ArrayList<Weather> weather_list;
    private LayoutInflater mInflater;

    public interface OnItemClickListener {
        void onItemClick(View view, int position, Weather item);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public WeatherAdapter(Context context, ArrayList<Weather> item) {
        super();
        weather_list = item;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.weater_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        holder.Date = (TextView) view.findViewById(R.id.date);
        holder.Weather_description = (TextView) view.findViewById(R.id.weather_description);
        holder.temperature_ = (TextView) view.findViewById(R.id.temperature_);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.Date.setText(weather_list.get(i).getDate());
        viewHolder.Weather_description.setText(weather_list.get(i).getWeather_description());
        viewHolder.temperature_.setText(weather_list.get(i).getTemperature());
        if (mOnItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // todo
                    mOnItemClickListener.onItemClick(viewHolder.itemView, i, weather_list.get(i));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return weather_list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
        TextView Date;
        TextView Weather_description;
        TextView temperature_;
    }
}