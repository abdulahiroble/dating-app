package com.example.gamingnews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.List;

public class PhotoAdapter extends ArrayAdapter<Cards> {
    Context mContext;


    public PhotoAdapter(@NonNull Context context, int resource, @NonNull List<Cards> objects) {
        super(context, resource, objects);
        this.mContext = context;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final Cards card_item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        TextView name = convertView.findViewById(R.id.name);
        ImageView image = convertView.findViewById(R.id.image);

        name.setText(card_item.getName() + ", " + card_item.getAge());


        name.setText(card_item.getName() + ", " + card_item.getAge());

        switch (card_item.getProfileImageUrl()) {
            case "defaultFemale":
                Glide.with(getContext()).load(R.drawable.default_woman).into(image);
                break;
            case "defaultMale":
                Glide.with(getContext()).load(R.drawable.default_man).into(image);
                break;
            default:
                Glide.with(getContext()).load(card_item.getProfileImageUrl()).into(image);
                break;
        }

        return convertView;
    }
}
