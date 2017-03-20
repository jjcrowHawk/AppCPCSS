package com.example.personal.comunitarias.Pedidos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
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
    Spinner compadecer,doc_exist;
    private ViewPager viewPager;
    private View view;
    private Button siguiente;
    private EditText descripcion;
    static String Descripcion_Pedido;
    static String Comparecer_d;

    public static String getDescripcion_Pedido() {
        return Descripcion_Pedido;
    }

    public static void setDescripcion_Pedido(String descripcion_Pedido) {
        Descripcion_Pedido = descripcion_Pedido;
    }

    public static String getComparecer_d() {
        return Comparecer_d;
    }

    public static void setComparecer_d(String comparecer_d) {
        Comparecer_d = comparecer_d;
    }



    public static String getDoc() {
        return Doc;
    }

    public static void setDoc(String doc) {
        Doc = doc;
    }

    static String  Hechos_d;
    static String Doc;

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
        compadecer = (Spinner) view.findViewById(R.id.spinner_comparecer);
        adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.si_no, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        compadecer.setAdapter(adapter1);

        /*hechos = (Spinner) view.findViewById(R.id.spinner_hechos);
        adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.si_no, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hechos.setAdapter(adapter2);
        */

        doc_exist = (Spinner) view.findViewById(R.id.spinner_investigados);
        adapter3 = ArrayAdapter.createFromResource(getContext(), R.array.si_no, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        doc_exist.setAdapter(adapter3);


        siguiente = (Button) view.findViewById(R.id.btnSiguientePedido);
        descripcion = (EditText) view.findViewById(R.id.txt_descripcion_ped);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Descripcion_Pedido = descripcion.getText().toString();
                if(Descripcion_Pedido.equals("")){
                    Toast.makeText(getContext(),"Por favor, ingrese su pedido",Toast.LENGTH_LONG).show();
                }else {
                    Descripcion_Pedido= descripcion.getText().toString();
                    if (compadecer.getSelectedItem().equals("Si")) Comparecer_d ="1";
                    if (compadecer.getSelectedItem().equals("No")) Comparecer_d ="0";

                    if (doc_exist.getSelectedItem().equals("Si")) Doc="1";
                    if (doc_exist.getSelectedItem().equals("No")) Doc="0";

                    Log.d("PEDIDO", Descripcion_Pedido +" " + Comparecer_d +" "+ Hechos_d +"" +" "+ Doc);

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











