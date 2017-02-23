package com.example.personal.comunitarias.Denuncias;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.personal.comunitarias.R;


public class Denuncia extends Fragment {
    Spinner comparecer, hechos;
    ArrayAdapter<CharSequence> adapter, adapter2;
    /******/
    private ViewPager viewPager;
    private View view;
    private TabsDenuncia tabs;
    private Button sgteDenuciado;
    EditText descripcion;

    public Denuncia(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view =  inflater.inflate(R.layout.frag2_denuncia,container,false);
        InicializarComp();
       /* view.findViewById(R.id.btnDenuncia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(2);
                //tabs = new TabsDenuncia();
                //tabs.DesbloquearTabs();

            }
        });*/
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

        descripcion = (EditText) view.findViewById(R.id.txt_descripcion);
        sgteDenuciado = (Button) view.findViewById(R.id.btnDenuncia);

        sgteDenuciado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(descripcion.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Por favor, describa su denuncia",Toast.LENGTH_LONG).show();
                }else {
                    viewPager.setCurrentItem(2);
                }
            }
        });


    }
}
