package com.example.covid_19selftest.main;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.covid_19selftest.R;

public class SplashScreenFragment extends Fragment {

    public static SplashScreenFragment newInstance() {
            return new SplashScreenFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.fragment_splash_screen,
                    container, false);

        TextView headerText = root.findViewById(R.id.textSplashScreen);
        animateHeaderText(headerText);

        ImageView covidLogo = root.findViewById(R.id.covid_logo_splash);
        logoAnimator(covidLogo);

        return root;
        }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int cSPLASH_DISPLAY_LENGTH = 8000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    displayMainFragment(view);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }, cSPLASH_DISPLAY_LENGTH);
    }

    private void displayMainFragment(View view) {
        NavDirections action = SplashScreenFragmentDirections
                .actionSplashScreenToNavigation2();
        NavController cController = Navigation.findNavController(view);
                cController.navigate(action);
    }

    private void animateHeaderText(View view) {
        Animator cAnimator = AnimatorInflater.loadAnimator(getActivity(), R.animator.scale);
        cAnimator.setTarget(view);
        cAnimator.start();
    }

    private void logoAnimator(View view) {
        Animator cAnimator = AnimatorInflater.loadAnimator(getActivity(), R.animator.scale);
        cAnimator.setTarget(view);
        cAnimator.start();
    }
}