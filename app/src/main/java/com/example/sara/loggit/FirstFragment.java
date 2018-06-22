package com.example.sara.loggit;

/**
 * Created by Miada on 05-Feb-18.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class FirstFragment extends Fragment {


    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_first, container, false);

        Toast.makeText(getActivity(), "booked successfully ✔", Toast.LENGTH_LONG).show();

        return view;
    }
}