package com.example.personal.comunitarias.ControlSocial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.personal.comunitarias.R;

/**
 * Created by erick on 29/7/2017.
 */

public class OFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__cs_o, container, false);
    }
}
