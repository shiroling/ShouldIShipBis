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

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        CarbonEstimation estimation = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_carbon_estimation, parent, false);
        }

        // Lookup view for data population
        TextView textViewDate = convertView.findViewById(R.id.textViewDate);
        TextView textViewValue = convertView.findViewById(R.id.textViewValue);

        // Populate the data into the template view using the data object
        textViewDate.setText(estimation.getEstimationDate());
        textViewValue.setText(String.format(Locale.getDefault(), "%.2f", estimation.getCarbonKg()));

        // Return the completed view to render on screen
        return convertView;
    }
}
