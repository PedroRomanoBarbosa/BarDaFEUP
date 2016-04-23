package com.bardafeup.hackforgood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class NotificationAdapter extends ArrayAdapter {

    private String[] time, quantity, notifications;

    public NotificationAdapter(Context context, String[] time, String[] quantity, String[] notifications) {
        super(context, R.layout.row_layout, time);
        this.notifications = notifications;
        this.quantity = quantity;
        this.time = time;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.row_layout, parent, false);

        TextView notificationView = (TextView) view.findViewById(R.id.notification);
        notificationView.setText(notifications[position]);

        TextView quantityView = (TextView) view.findViewById(R.id.quantity);
        quantityView.setText(quantity[position]);

        TextView timeView = (TextView) view.findViewById(R.id.time);
        timeView.setText(time[position]);

        return view;
    }
}
