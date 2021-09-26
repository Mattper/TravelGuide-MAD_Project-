package com.example.mad_project.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class HotelArrayAdapter extends ArrayAdapter<com.example.hotel.HotelModel> implements View.OnClickListener {
    private List<com.example.hotel.HotelModel> list;
    private com.example.hotel.HotelModel model;
    private boolean isAdmin;


    public HotelArrayAdapter(@NonNull Context context, List<com.example.hotel.HotelModel> list, boolean isAdmin) {
        super(context, R.layout.list_tile_layout, list);
        this.list = list;
        this.isAdmin = isAdmin;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        model = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.list_tile_layout, parent, false);
        TextView name = convertView.findViewById(R.id.viewplceName);
        Button see = convertView.findViewById(R.id.vtn2);
        ImageView imageView = convertView.findViewById(R.id.viewImage);
        if (model.getPhoto() != null) {
            if (!model.getPhoto().isEmpty()) {
                Picasso.get().load(model.getPhoto()).into(imageView);
            }
        }
        see.setOnClickListener(this);
        name.setText(model.getName());
        return convertView;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), com.example.hotel.ViewHotelActivity.class);
        intent.putExtra("object", model);
        intent.putExtra("isAdmin", isAdmin);
        getContext().startActivity(intent);

    }
}
