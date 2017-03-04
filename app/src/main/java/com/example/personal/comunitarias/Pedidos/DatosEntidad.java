package com.example.personal.comunitarias.Pedidos;

import android.content.res.TypedArray;
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

import com.example.personal.comunitarias.R;


public class DatosEntidad extends Fragment implements AdapterView.OnItemSelectedListener {
    private Spinner spinner_inst_p, spinner_gen_p, spinner_prov_p, spinner_ciu_p;
    private EditText txt_Nombres_p, txt_Apellidos_p,txt_cargo_p ;
    ArrayAdapter<CharSequence> adapter, adapter2, adapter3, adapter4, adapter5, adapter6, adapter7,adapter8, adapter9;

    private ViewPager viewPager;
    private View view;

    public DatosEntidad(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pedido_tab3_entidad, container, false);
        inicializarComponentesTab3();
        return view;
    }

    private void inicializarComponentesTab3() {
        spinner_inst_p = (Spinner) view.findViewById(R.id.spinner_inst_p);
        spinner_gen_p = (Spinner) view.findViewById(R.id.spinner_gen_p);
        spinner_prov_p = (Spinner) view.findViewById(R.id.spinner_prov_p);
        spinner_ciu_p = (Spinner) view.findViewById(R.id.spinner_ciu_p);
        txt_Nombres_p = (EditText) view.findViewById(R.id.txt_Nombres_p);
        txt_Apellidos_p = (EditText) view.findViewById(R.id.txt_Apellidos_p);
        txt_cargo_p = (EditText) view.findViewById(R.id.txt_cargo_p);

        loadSpinnerProvincias();


        adapter2 = ArrayAdapter.createFromResource(getContext(),
                R.array.institucion, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_inst_p.setAdapter(adapter2);

        adapter3 = ArrayAdapter.createFromResource(getContext(),
                R.array.genero, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_gen_p.setAdapter(adapter3);

    }

    private void loadSpinnerProvincias() {
        // Create an ArrayAdapter using the string array and a default spinner
        // layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(), R.array.provincias, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        this.spinner_prov_p.setAdapter(adapter);

        // This activity implements the AdapterView.OnItemSelectedListener
        this.spinner_prov_p.setOnItemSelectedListener(this);
        this.spinner_ciu_p.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner_prov_p:

                // Retrieves an array
                TypedArray arrayLocalidades = getResources().obtainTypedArray(
                        R.array.array_provincia_a_localidades);

                CharSequence[] localidades = arrayLocalidades.getTextArray(position);
                arrayLocalidades.recycle();

                // Create an ArrayAdapter using the string array and a default
                // spinner layout
                ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
                        getContext(), android.R.layout.simple_spinner_item, android.R.id.text1, localidades);

                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // Apply the adapter to the spinner
                this.spinner_ciu_p.setAdapter(adapter);

                break;

            case R.id.spinner_ciu_p:

                break;
        }
    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}











