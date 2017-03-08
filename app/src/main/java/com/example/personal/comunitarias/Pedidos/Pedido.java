package com.example.personal.comunitarias.Pedidos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.personal.comunitarias.R;


public class Pedido extends Fragment implements AdapterView.OnItemSelectedListener {

    ArrayAdapter<CharSequence> adapter1, adapter2,adapter3;
    Spinner sp1, sp2, sp3;
    private ViewPager viewPager;
    private View view;
    private Button siguiente;
    private EditText descripcion;
    private String des;

    public Pedido(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pedido_tab2_pedido, container, false);
        inicializarComponentes();
        view.findViewById(R.id.btnPedAnterior).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(0);

            }
        });

        return view;

    }

    private void inicializarComponentes() {
        sp1 = (Spinner) view.findViewById(R.id.spinner_comparecer);
        adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.si_no, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter1);

        sp2 = (Spinner) view.findViewById(R.id.spinner_hechos);
        adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.si_no, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adapter2);

        sp3 = (Spinner) view.findViewById(R.id.spinner_investigados);
        adapter3 = ArrayAdapter.createFromResource(getContext(), R.array.si_no, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp3.setAdapter(adapter3);

        siguiente = (Button) view.findViewById(R.id.btnSiguientePedido);
        descripcion = (EditText) view.findViewById(R.id.txt_descripcion_ped);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                des = descripcion.getText().toString();
                if(des.equals("")){
                    Toast.makeText(getContext(),"Por favor, ingrese su pedido",Toast.LENGTH_LONG).show();
                }else {
                    viewPager.setCurrentItem(2);
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}











