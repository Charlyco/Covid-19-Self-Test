package com.example.covid_19selftest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19selftest.data.State;

import java.util.List;

public class StatisticsAdapter extends RecyclerView.Adapter<StatisticsAdapter.StatesViewHolder> {
    List<State> cStates;
    private LayoutInflater cInflater;

    public StatisticsAdapter(List<State> states, Context context) {
        cStates = states;
        cInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public StatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = cInflater.inflate(R.layout.states_list_row, parent, false);
        return new StatesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StatesViewHolder holder, int position) {
        if (cStates != null) {
            State currentState = cStates.get(position);
            holder.bind(currentState);
        }
    }

    @Override
    public int getItemCount() {
        if (cStates != null)
            return cStates.size();
        else return 0;
    }

    public static class StatesViewHolder extends RecyclerView.ViewHolder {
        private TextView state_name;
        private TextView state_confirmed;
        private TextView state_active;
        private TextView state_discharged;
        private TextView state_deaths;

        public StatesViewHolder(@NonNull View itemView) {
            super(itemView);
            state_name = itemView.findViewById(R.id.stateName);
            state_confirmed = itemView.findViewById(R.id.totalConfirmedState);
            state_active = itemView.findViewById(R.id.totalActiveState);
            state_discharged = itemView.findViewById(R.id.totalDischargedState);
            state_deaths = itemView.findViewById(R.id.totalDeathsState);
        }

        public void bind(State currentState) {
            state_name.setText(currentState.getState());

            StringBuilder confirmedCasesBuilder = new StringBuilder();
            confirmedCasesBuilder.append("Total Confirmed Cases: ");
            confirmedCasesBuilder.append(currentState.getConfirmedCases());
            state_confirmed.setText(confirmedCasesBuilder);

            StringBuilder activeCasesBuilder = new StringBuilder();
            activeCasesBuilder.append("Total Active Cases: ");
            activeCasesBuilder.append(currentState.getCasesOnAdmission());
            state_active.setText(activeCasesBuilder);

            StringBuilder dischargedBuilder = new StringBuilder();
            dischargedBuilder.append("Total Discharged: ");
            dischargedBuilder.append(currentState.getDischarged());
            state_discharged.setText(dischargedBuilder);

            StringBuilder deathsBuilder = new StringBuilder();
            deathsBuilder.append("Total Deaths: ");
            deathsBuilder.append(currentState.getDeath());
            state_deaths.setText(deathsBuilder);
        }
    }
}
