package com.example.personal.comunitarias.AreaSocial;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.personal.comunitarias.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CCSFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CCSFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__pp_ccs, container, false);
    }

}
