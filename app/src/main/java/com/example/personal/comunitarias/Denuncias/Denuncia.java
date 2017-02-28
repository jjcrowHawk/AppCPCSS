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
    static String Descripcion_Denuncia;
    static String Comparecer_d;
    static String  Hechos_d;

    public Denuncia(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view =  inflater.inflate(R.layout.denuncia_tab2_denuncia,container,false);
        InicializarComp();
        view.findViewById(R.id.btnDenunciaAnterior).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(0);
                //tabs = new TabsDenuncia();
                //tabs.DesbloquearTabs();

            }
        });
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
                    Peticionario d = new Peticionario(viewPager);

                    Descripcion_Denuncia= descripcion.getText().toString();
                    if (comparecer.getSelectedItem().equals("Si")) Comparecer_d ="1";
                    if (comparecer.getSelectedItem().equals("No")) Comparecer_d ="0";
                    if (hechos.getSelectedItem().equals("Si")) Hechos_d="1";
                    if (hechos.getSelectedItem().equals("No")) Hechos_d="0";
                    //
                    viewPager.setCurrentItem(2);
                }
            }
        });


    }
    public static String getDescripcion_Denuncia() {
        return Descripcion_Denuncia;
    }

    public static void setDescripcion_Denuncia(String descripcion_Denuncia) {
        Descripcion_Denuncia = descripcion_Denuncia;
    }

    public static String getComparecer_d() {
        return Comparecer_d;
    }

    public static void setComparecer_d(String comparecer_d) {
        Comparecer_d = comparecer_d;
    }

    public static String getHechos_d() {
        return Hechos_d;
    }

    public static void setHechos_d(String hechos_d) {
        Hechos_d = hechos_d;
    }



}
