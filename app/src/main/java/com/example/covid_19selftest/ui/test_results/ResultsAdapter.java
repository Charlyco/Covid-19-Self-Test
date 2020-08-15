package com.example.covid_19selftest.ui.test_results;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19selftest.R;
import com.example.covid_19selftest.data.Result;

import java.util.List;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ResultViewHolder> {
    private LayoutInflater cInflater;
    private List<Result> cResults;

    public ResultsAdapter(Context context) {
        cInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = cInflater.inflate(R.layout.result_item_row, parent, false);
        return new ResultViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        if (cResults != null) {
            Result currentResult = cResults.get(position);
            holder.patient_name.setText(currentResult.getName());
            holder.status.setText(currentResult.getStatus());
        }
    }

    @Override
    public int getItemCount() {
        if (cResults != null)
        return cResults.size();
        else return 0;
    }

    void setResults(List<Result> results) {
        cResults = results;
        notifyDataSetChanged();
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder{
        private TextView patient_name;
        private TextView status;

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            patient_name = itemView.findViewById(R.id.user_name);
            status = itemView.findViewById(R.id.status);
        }
    }
}
