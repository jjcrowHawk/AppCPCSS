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
import android.widget.Toast;

import com.example.personal.comunitarias.R;


public class DatosEntidad extends Fragment implements AdapterView.OnItemSelectedListener {
    private Spinner spinner_inst_p, spinner_gen_p, spinner_prov_p, spinner_ciu_p;
    private EditText txt_Nombres_p, txt_Apellidos_p,txt_cargo_p ;
    private String Nombre, Apellido, Cargo;
    ArrayAdapter<CharSequence> adapter, adapter2, adapter3, adapter4, adapter5, adapter6, adapter7,adapter8, adapter9;
    private Button siguiente;
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
        ValidarCampos();
        view.findViewById(R.id.btnAnterior_entidad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(1);
            }
        });
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
        siguiente = (Button)view.findViewById(R.id.btnSiguiente_entidad) ;
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nombre = txt_Nombres_p.getText().toString();
                Apellido = txt_Apellidos_p.getText().toString();
                Cargo = txt_cargo_p.getText().toString();

                if(Nombre.equals("")||Apellido.equals("")||Cargo.equals("")){
                    Toast.makeText(getContext(),"Por favor, llene todos los campos",Toast.LENGTH_LONG).show();
                }else {
                    viewPager.setCurrentItem(3);
                }
            }
        });

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

    private void ValidarCampos(){

        //validacione de nombre
        txt_Nombres_p .addTextChangedListener(new TextValidatorPedido(txt_Nombres_p) {
            @Override
            public void validate(EditText editText, String text) {
                for (int index = 0; index < text.length(); index++) {
                    String c = String.valueOf(text.charAt(index));
                    if (isNumeric(c) || (!text.matches("[a-zA-Zá-ú? ]*"))){
                        txt_Nombres_p.setError("Sólo ingrese letras");
                        text = text.substring(0, text.length() - 1);
                        txt_Nombres_p.setText(text);
                        txt_Nombres_p.setSelection(txt_Nombres_p.getText().length());
                    }
                }
                if (text.length() > 24) {
                    txt_Nombres_p.setError("Límite excedido");
                }
            }
        });


        //validacione de Apellido
        txt_Apellidos_p .addTextChangedListener(new TextValidatorPedido(txt_Apellidos_p) {
            @Override
            public void validate(EditText editText, String text) {
                for (int index = 0; index < text.length(); index++) {
                    String c = String.valueOf(text.charAt(index));
                    if (isNumeric(c) || (!text.matches("[a-zA-Zá-ú? ]*"))){
                        txt_Apellidos_p.setError("Sólo ingrese letras");
                        text = text.substring(0, text.length() - 1);
                        txt_Apellidos_p.setText(text);
                        txt_Apellidos_p.setSelection(txt_Apellidos_p.getText().length());
                    }
                }
                if (text.length() > 24) {
                    txt_Apellidos_p.setError("Límite excedido");
                }
            }
        });


        //validacione de Cargo
        txt_cargo_p.addTextChangedListener(new TextValidatorPedido(txt_cargo_p) {
            @Override
            public void validate(EditText editText, String text) {
                for (int index = 0; index < text.length(); index++) {
                    String c = String.valueOf(text.charAt(index));
                    if (isNumeric(c) || (!text.matches("[a-zA-Zá-ú? ]*"))){
                        txt_cargo_p.setError("Sólo ingrese letras");
                        text = text.substring(0, text.length() - 1);
                        txt_cargo_p.setText(text);
                        txt_cargo_p.setSelection(txt_cargo_p.getText().length());
                    }
                }
                if (text.length() > 24) {
                    txt_cargo_p.setError("Límite excedido");
                }
            }
        });
    }
    //funcion para ver si es un numero
    private static boolean isNumeric(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }
}











