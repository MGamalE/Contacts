package com.example.contacts.presentation.feature.splash;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.contacts.R;
import com.example.contacts.databinding.FragmentSplashBinding;


public class SplashFragment extends Fragment {

    private static final int TIME_OUT = 2000;

    private FragmentSplashBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        launchLoginUI();
    }

    private void launchLoginUI() {
        new Handler().postDelayed(() -> Navigation.findNavController(binding.getRoot()).navigate(R.id.action_splashFragment_to_loginFragment), TIME_OUT);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}