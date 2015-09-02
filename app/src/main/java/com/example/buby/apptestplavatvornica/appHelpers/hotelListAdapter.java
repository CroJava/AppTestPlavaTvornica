package com.example.buby.apptestplavatvornica.appHelpers;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.buby.apptestplavatvornica.R;

import java.util.List;

/**
 * Created by Reaper on 29.8.2015..
 */
public class hotelListAdapter extends BaseAdapter {

    private Activity context;
    private List<hotelListHolder> values;
    int [] imageId;

    public hotelListAdapter(Activity context, List<hotelListHolder> values,int[] prgmImages) {
        //super();
        this.context = context;
        this.values = values;
        imageId=prgmImages;
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Object getItem(int location) {
        return values.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_row, parent, false);

        // init views for populating with data
        TextView naslovText = (TextView) rowView.findViewById(R.id.naslovText);
        TextView adresaAText = (TextView) rowView.findViewById(R.id.adresaText1);
        TextView adresaBText = (TextView) rowView.findViewById(R.id.adresaText2);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.hotelImage);

        hotelListHolder n = values.get(position);

        //populating views with text
        naslovText.setText(n.getNaslov());
        adresaAText.setText(n.getAdresaA());
        adresaBText.setText(n.getAdresaB());
        imageView.setImageResource(imageId[position]);

        return rowView;
    }
}