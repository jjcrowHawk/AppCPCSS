package com.example.personal.comunitarias.Denuncias;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.personal.comunitarias.R;

/**
 * Created by PC-JANINA on 26/2/2017.
 */
public class MostrarDatos extends Fragment implements AdapterView.OnItemSelectedListener {


    /******/
    private ViewPager viewPager;
    private View view;
    private TabsDenuncia tabs;

    public  MostrarDatos(){

    }

    public MostrarDatos(ViewPager viewPager) {

        this.viewPager = viewPager;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.denuncia_tab4_mostrar_dat,container,false);


        return  view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
