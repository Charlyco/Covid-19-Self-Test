package com.example.covid_19selftest;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.covid_19selftest.data.Result;
import com.example.covid_19selftest.ui.test_results.OnItemClickedListener;
import com.example.covid_19selftest.ui.test_results.ResultViewModel;
import com.example.covid_19selftest.ui.test_results.ResultsAdapter;

import java.util.List;

public class TestResultsFragment extends Fragment implements OnItemClickedListener {
    private ResultViewModel cResultViewModel;
    private ResultsAdapter cResultsAdapter;
    private RecyclerView cRecyclerView;
    private Result cResult;

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
        cResultsAdapter.setClickedListener(this);

        ItemTouchHelper cTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Result cResult = cResultsAdapter.getResultAtPosition(position);
                deleteResult(cResult);
            }
        });
        cTouchHelper.attachToRecyclerView(cRecyclerView);

        return root;
    }

    private void deleteResult(final Result result) {
        AlertDialog.Builder cBuilder = new AlertDialog.Builder(requireContext())
                .setMessage("Delete the selected result?")
                .setTitle("Delete Result")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cResultViewModel.deleteResult(result);
                        Toast.makeText(getContext(), "Result deleted", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog cAlertDialog = cBuilder.create();
        cAlertDialog.show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cResultViewModel = new ViewModelProvider(requireActivity()).get(ResultViewModel.class);
        cResultViewModel.loadResults().observe(getViewLifecycleOwner(), new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                cResultsAdapter.setResults(results);
            }
        });

    }
    public void launchResultDetails(View view) {
        NavDirections action = TestResultsFragmentDirections
                .actionTestResultsFragmentToResultDetails();
        Navigation.findNavController(view).navigate(action);
    }

    @Override
    public void onClick(View view, int position) {
        cResult = cResultsAdapter.getResultAtPosition(position);
        cResultViewModel.selectResult(cResult);
        launchResultDetails(view);
    }
}