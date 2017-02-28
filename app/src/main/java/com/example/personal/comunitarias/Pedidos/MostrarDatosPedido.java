package com.example.personal.comunitarias.Pedidos;

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

public class MostrarDatosPedido extends Fragment implements AdapterView.OnItemSelectedListener {


    /******/
    private ViewPager viewPager;
    private View view;
    private TabsPedido tabs;

    public MostrarDatosPedido(ViewPager viewPager) {

        this.viewPager = viewPager;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.pedido_tab4_mostrar_dat,container,false);
        //InicializarComp();
        /*view.findViewById(R.id.btn_anterior).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(1);

            }
        });*/
        return  view;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}