package com.testproject.propertyviewer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.testproject.propertyviewer.Model.Property;

import java.util.List;

/**
 * Created by prachi on 25/12/16.
 */

public class PropertyListAdapter extends RecyclerView.Adapter<PropertyListAdapter.PropertyViewHolder> {

    private List<Property> propertyList;
    private Context context;

    public PropertyListAdapter(List<Property> propertyList, Context context) {
        this.propertyList = propertyList;
        this.context = context;
    }

    @Override
    public PropertyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.property_list_row, parent, false);

        return new PropertyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PropertyViewHolder holder, int position) {
        Property property = propertyList.get(position);
        holder.name.setText(property.getName());
        holder.area.setText(property.getArea());
        holder.price.setText("Rs. " + property.getMinPrice());

        Picasso.with(context).load(property.getImage()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return propertyList.size();
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }

    public class PropertyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, area, price;
        public ImageView image;

        public PropertyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.property_row_name_textview);
            area = (TextView) view.findViewById(R.id.property_row_area_textview);
            price = (TextView) view.findViewById(R.id.property_row_price_textview);
            image = (ImageView) view.findViewById(R.id.property_row_image_view);

        }
    }


}
