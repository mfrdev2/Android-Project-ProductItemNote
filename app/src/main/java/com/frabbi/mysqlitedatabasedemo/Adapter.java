package com.frabbi.mysqlitedatabasedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Adapter extends ArrayAdapter<Product> {
    private Context context;
    private List<Product> list;

    public Adapter(@NonNull Context context, int resource, @NonNull List<Product> list) {
        super(context, resource,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.view_of_list,parent,false);
        }
        TextView textId = convertView.findViewById(R.id.Id);
        TextView textView = convertView.findViewById(R.id.textViewLId);
        final Product product = getItem(position);

        textId.setText(""+(position+1));
        textView.setText(product.getP_name());
        return convertView ;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public Product getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(@Nullable Product item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
