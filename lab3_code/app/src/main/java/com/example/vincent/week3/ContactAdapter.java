package com.example.vincent.week3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Vincent on 2016/10/11.
 */

public class ContactAdapter extends BaseAdapter {
    private List<Contact> list;
    private Context context;
    public ContactAdapter(Context context, List<Contact> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }
    @Override
    public Object getItem(int i) {
        if (list == null) {
            return null;
        }
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View convertView;
        ViewHolder viewHolder;
        if (view == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.contact_item, null);
            viewHolder = new ViewHolder();
            viewHolder.contact_btn = (Button) convertView.findViewById(R.id.contact_btn);
            viewHolder.contact_name = (TextView) convertView.findViewById(R.id.contact_name);
            convertView.setTag(viewHolder);
        } else {
            convertView = view;
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.contact_btn.setText(list.get(position).getName().charAt(0) + "");
        viewHolder.contact_name.setText(list.get(position).getName());
        return convertView;
    }

    private class ViewHolder {
        public Button contact_btn;
        public TextView contact_name;
    }
}

