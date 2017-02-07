package com.example.personal.comunitarias.Denuncias;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.personal.comunitarias.R;


public class Fragment2 extends Fragment {
    Spinner comparecer, hechos;
    ArrayAdapter<CharSequence> adapter, adapter2;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view =  inflater.inflate(R.layout.frag2,container,false);
        InicializarComp();
        return  view;

    }

    private  void InicializarComp() {
        //data de denuncia
        //Spinner comparecer con CPCCS
        comparecer = (Spinner) view.findViewById(R.id.spinner_comparecer);
        adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.si_no, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        comparecer.setAdapter(adapter);

        //Spinner hechos son investigados
        hechos = (Spinner) view.findViewById(R.id.spinner_hechos);
        adapter2 = ArrayAdapter.createFromResource(getContext(),
                R.array.si_no, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hechos.setAdapter(adapter2);
    }
}
