package com.example.shouldishipbis;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.shouldishipbis.model.apiCalls.CarbonEstimation;

import java.util.List;
import java.util.Locale;

public class CarbonEstimationAdapter extends ArrayAdapter<CarbonEstimation> {

    public CarbonEstimationAdapter(Context context, List<CarbonEstimation> estimations) {
        super(context, 0, estimations);
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CarbonEstimation estimation = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_carbon_estimation, parent, false);
        }

        TextView textEmoji = convertView.findViewById(R.id.textEmoji);
        switch (estimation.getTransport()) {
            case PLANE : { textEmoji.setText(R.string.planeEmoji);break;}
            case SHIP  : { textEmoji.setText(R.string.boatEmoji);break;}
            case TRUCK : { textEmoji.setText(R.string.truckEmoji);break;}
            case TRAIN : { textEmoji.setText(R.string.trainEmoji);break;}
        }
        TextView textName = convertView.findViewById(R.id.textName);
        textName.setText(estimation.getName());
        TextView textDate = convertView.findViewById(R.id.textDate);
        textDate.setText(estimation.getEstimationDate());
        TextView textMass = convertView.findViewById(R.id.textMass);
        textMass.setText(String.format(Locale.getDefault(), " %.2f %s", estimation.getWeight(), estimation.getWeightUnit().getSign()));
        TextView textDistance = convertView.findViewById(R.id.textDistance);
        textDistance.setText(String.format(Locale.getDefault(), " %.2f %s", estimation.getDistance(), estimation.getDistanceUnit().getSign()));
        TextView textCarbon = convertView.findViewById(R.id.textCarbon);
        textCarbon.setText(String.format(Locale.getDefault(), "%.2f kg", estimation.getCarbonKg()));

        return convertView;
    }
}
