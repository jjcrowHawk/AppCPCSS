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


public class Peticionario extends Fragment implements AdapterView.OnItemSelectedListener {
    

    private ViewPager viewPager;
    private View view;
    private Button siguiente;
    private Spinner spinner_pet_tipIden, spinner_pet_gener, spinner_pet_estado_civil, spinner_pet_educa, spinner_pet_naciona, spinner_pet_resid, spinner_pet_provin, spinner_pet_ciud;
    private ArrayAdapter<CharSequence> adapter, adapter2, adapter3, adapter4, adapter5, adapter6, adapter7,adapter8, adapter9;
    private EditText txt_Nombres, txt_Apellidos, txt_correo, txt_tipoIdentificacion, txt_ocupacion;
    private EditText txt_Telefono, txt_Direccion;
    private String Nombre, Apellido, Identidad, Ocupacion, Email;
    private String telfono, direccion;
    public Peticionario(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pedido_tab1_peticionario, container, false);
        inicializarComponentes();
        ValidarCampos();


        return view;

    }

    private void inicializarComponentes() {


        txt_Nombres = (EditText) view.findViewById(R.id.txt_Nombres_petic);
        txt_Apellidos = (EditText) view.findViewById(R.id.txt_Apellidos_petic);
        txt_correo = (EditText) view.findViewById(R.id.txt_correo_petic);
        txt_tipoIdentificacion = (EditText) view.findViewById(R.id.txt_tipoIdent_petic);
        txt_ocupacion = (EditText) view.findViewById(R.id.txt_ocupacion_petic);
        txt_Telefono = (EditText) view.findViewById(R.id.txt_telefonop);
        txt_Direccion = (EditText) view.findViewById(R.id.txt_direccionp);

        spinner_pet_tipIden = (Spinner)view.findViewById(R.id.spinner_pet_tipIden);
        spinner_pet_gener = (Spinner)view.findViewById(R.id.spinner_pet_gener);
        spinner_pet_estado_civil = (Spinner)view.findViewById(R.id. spinner_pet_estado_civil);
        spinner_pet_educa = (Spinner)view.findViewById(R.id.spinner_pet_educa);
        spinner_pet_naciona = (Spinner)view.findViewById(R.id.spinner_pet_naciona);
        spinner_pet_resid = (Spinner)view.findViewById(R.id.spinner_pet_resid);
        spinner_pet_provin = (Spinner)view.findViewById(R.id.spinner_pet_provin);
        spinner_pet_ciud = (Spinner)view.findViewById(R.id.spinner_pet_ciud);

        //Spinner Tipo de identificacion

        adapter2 = ArrayAdapter.createFromResource(getContext(),
                R.array.tipo_identificacion, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_pet_tipIden.setAdapter(adapter2);

        //Spinner género
        adapter3 = ArrayAdapter.createFromResource(getContext(),
                R.array.genero, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_pet_gener.setAdapter(adapter3);

        //Spinner estado_civil
        adapter4 = ArrayAdapter.createFromResource(getContext(),
                R.array.estado_civil, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_pet_estado_civil.setAdapter(adapter4);

        //Spinner Nivel educacion
        adapter5 = ArrayAdapter.createFromResource(getContext(),
                R.array.nivel__educacion, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_pet_educa.setAdapter(adapter5);

        //Spinner Nivel Nacionalidad
        adapter6 = ArrayAdapter.createFromResource(getContext(),
                R.array.nacionalidad, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_pet_naciona.setAdapter(adapter6);

        //Spinner Residencia
        adapter7 = ArrayAdapter.createFromResource(getContext(),
                R.array.si_no, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_pet_resid.setAdapter(adapter7);

        siguiente = (Button) view.findViewById(R.id.btnSiguiente_petic);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Nombre = txt_Nombres.getText().toString();
                Apellido = txt_Apellidos.getText().toString();
                Identidad = txt_tipoIdentificacion.getText().toString();
                Ocupacion = txt_ocupacion.getText().toString();
                Email = txt_correo.getText().toString();
                telfono = txt_Telefono.getText().toString();
                direccion = txt_Direccion.getText().toString();

                if(Nombre.equals("")|| Apellido.equals("")||
                        Identidad.equals("") || Ocupacion.equals("") ||
                        Email.equals("")){
                    Toast.makeText(getContext(),"Por favor, llene todos los campos",Toast.LENGTH_LONG).show();
                }else {
                    viewPager.setCurrentItem(1);

                }
            }
        });

        loadSpinnerProvincias();


    }
    private void loadSpinnerProvincias() {
        // Create an ArrayAdapter using the string array and a default spinner
        // layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(), R.array.provincias, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        this.spinner_pet_provin.setAdapter(adapter);

        // This activity implements the AdapterView.OnItemSelectedListener
        this.spinner_pet_provin.setOnItemSelectedListener(this);
        this.spinner_pet_ciud.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner_pet_provin:

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
                this.spinner_pet_ciud.setAdapter(adapter);

                break;

            case R.id.spinner_pet_ciud:

                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void ValidarCampos(){

        //validacione de nombre
        txt_Nombres .addTextChangedListener(new TextValidatorPedido(txt_Nombres) {
            @Override
            public void validate(EditText editText, String text) {
                for (int index = 0; index < text.length(); index++) {
                    String c = String.valueOf(text.charAt(index));
                    if (isNumeric(c) || (!text.matches("[a-zA-Zá-ú? ]*"))){
                        txt_Nombres.setError("Sólo ingrese letras");
                        text = text.substring(0, text.length() - 1);
                        txt_Nombres.setText(text);
                        txt_Nombres.setSelection(txt_Nombres.getText().length());
                    }
                }
                if (text.length() > 24) {
                    txt_Nombres.setError("Límite excedido");
                }
            }
        });


        //validacione de Apellido
        txt_Apellidos .addTextChangedListener(new TextValidatorPedido(txt_Apellidos) {
            @Override
            public void validate(EditText editText, String text) {
                for (int index = 0; index < text.length(); index++) {
                    String c = String.valueOf(text.charAt(index));
                    if (isNumeric(c) || (!text.matches("[a-zA-Zá-ú? ]*"))){
                        txt_Apellidos.setError("Sólo ingrese letras");
                        text = text.substring(0, text.length() - 1);
                        txt_Apellidos.setText(text);
                        txt_Apellidos.setSelection(txt_Apellidos.getText().length());
                    }
                }
                if (text.length() > 24) {
                    txt_Apellidos.setError("Límite excedido");
                }
            }
        });


        //validacione de identificación
        txt_tipoIdentificacion.addTextChangedListener(new TextValidatorPedido(txt_tipoIdentificacion) {
            @Override
            public void validate(EditText editText, String text) {
                //puede ser 10 u 11 por el RUC
                if (text.length()> 0 && text.length() < 10) {
                    txt_tipoIdentificacion.setError("Cantidad de dígitos incorrecta");
                }
            }
        });

        //validacione de telefono
        txt_Telefono.addTextChangedListener(new TextValidatorPedido(txt_Telefono) {
            @Override
            public void validate(EditText editText, String text) {
                //puede ser 10 u 11 por el RUC
                if (text.length()> 0 && text.length() < 10) {
                    txt_Telefono.setError("Cantidad de dígitos incorrecta");
                }
            }
        });

        //validacione de Cargo
        txt_ocupacion.addTextChangedListener(new TextValidatorPedido(txt_ocupacion) {
            @Override
            public void validate(EditText editText, String text) {
                for (int index = 0; index < text.length(); index++) {
                    String c = String.valueOf(text.charAt(index));
                    if (isNumeric(c) || (!text.matches("[a-zA-Zá-ú? ]*"))){
                        txt_ocupacion.setError("Sólo ingrese letras");
                        text = text.substring(0, text.length() - 1);
                        txt_ocupacion.setText(text);
                        txt_ocupacion.setSelection(txt_ocupacion.getText().length());
                    }
                }
                if (text.length() > 24) {
                    txt_ocupacion.setError("Límite excedido");
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











