package com.example.covid_19selftest.statistics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19selftest.R;
import com.example.covid_19selftest.data.State;

import java.util.List;

public class    StatisticsAdapter extends RecyclerView.Adapter<StatisticsAdapter.StatesViewHolder> {
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
        private TextView state_confirmed, state_confirmed_value;
        private TextView state_active, state_active_value;
        private TextView state_discharged, state_discharged_value;
        private TextView state_deaths, state_deaths_value;

        public StatesViewHolder(@NonNull View itemView) {
            super(itemView);
            state_name = itemView.findViewById(R.id.state_name);
            state_confirmed = itemView.findViewById(R.id.totalConfirmedState);
            state_active = itemView.findViewById(R.id.totalActiveState);
            state_discharged = itemView.findViewById(R.id.totalDischargedState);
            state_deaths = itemView.findViewById(R.id.totalDeathsState);
            state_confirmed_value = itemView.findViewById(R.id.totalConfirmedStateValue);
            state_active_value = itemView.findViewById(R.id.totalActiveStateValue);
            state_discharged_value =  itemView.findViewById(R.id.totalDischargedStateValue);
            state_deaths_value = itemView.findViewById(R.id.totalDeathsStateValue);
        }

        public void bind(State currentState) {
            state_name.setText(currentState.getState());

            state_confirmed.setText(R.string.total_confirmed);
            state_confirmed_value.setText(currentState.getConfirmedCases().toString());

            state_active.setText(R.string.active_cases);
            state_active_value.setText(currentState.getCasesOnAdmission().toString());

            state_discharged.setText(R.string.total_discharged);
            state_discharged_value.setText(currentState.getDischarged().toString());

            state_deaths.setText(R.string.total_deaths);
            state_deaths_value.setText(currentState.getDeath().toString());
        }
    }
}
