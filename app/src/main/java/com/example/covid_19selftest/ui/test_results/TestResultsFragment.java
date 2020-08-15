package com.example.covid_19selftest.ui.test_results;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.covid_19selftest.R;
import com.example.covid_19selftest.data.Result;

import java.util.List;

public class TestResultsFragment extends Fragment {

    private ResultViewModel mViewModel;
    private RecyclerView cRecyclerView;
    private ResultsAdapter cResultsAdapter;

    public static TestResultsFragment newInstance() {
        return new TestResultsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.result_fragment, container, false);
        cResultsAdapter = new ResultsAdapter(getContext());
        cRecyclerView = root.findViewById(R.id.resultsRecyclerView);
        cRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        cRecyclerView.setAdapter(cResultsAdapter);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ResultViewModel.class);
        mViewModel.loadResults().observe(getViewLifecycleOwner(), new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                cResultsAdapter.setResults(results);
            }
        });
    }
}