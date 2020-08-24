package com.example.covid_19selftest.ui.test_results;

import android.content.Context;
import android.graphics.Color;
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
    private OnItemClickedListener cClickedListener;

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
    public void onBindViewHolder(@NonNull final ResultViewHolder holder, final int position) {
        if (cResults != null) {
            final Result currentResult = cResults.get(position);
            holder.patient_name.setText(currentResult.getName());
            holder.status.setText(currentResult.getStatus());
            if (currentResult.getStatus().equals("Most Likely Positive")) {
                holder.status.setTextColor(Color.RED);
            } else if (currentResult.getStatus().equals("Likely Positive")) {
                holder.status.setTextColor(Color.YELLOW);
            } else holder.status.setTextColor(Color.GREEN);
        }
    }

    @Override
    public int getItemCount() {
        if (cResults != null)
        return cResults.size();
        else return 0;
    }

    public void setResults(List<Result> results) {
        cResults = results;
        notifyDataSetChanged();
    }
    public void setClickedListener(OnItemClickedListener clickedListener) {
        this.cClickedListener = clickedListener;
    }
    public Result getResultAtPosition(int position){
        return cResults.get(position);
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView patient_name;
        private TextView status;


        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            patient_name = itemView.findViewById(R.id.user_name);
            status = itemView.findViewById(R.id.status);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (cClickedListener != null) {
                cClickedListener.onClick(view, getAdapterPosition());
            }
        }
    }
}
