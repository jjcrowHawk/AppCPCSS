package com.example.personal.comunitarias.Pedidos;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.personal.comunitarias.R;

import java.util.LinkedList;
import java.util.List;


public class DatosEntidad extends Fragment implements AdapterView.OnItemSelectedListener {
    Spinner  genero,institucion, provincia, ciudad;
    ArrayAdapter<CharSequence> adapter, adapter2, adapter3, adapter4;

    private EditText txtNombre, txtApellido, txtCargo;
    private String Nombre, Apellido, Cargo;
    private Button siguiente;
    private ViewPager viewPager;
    private View view;
    static String  Nombre_D;
    static String Apellido_D;
    static String Cargo_D;
    static String Genero_d;
    static String Provincia_d;
    static String CIudad_d;
    static String Institucion_d;

    public static String getNombre_D() {
        return Nombre_D;
    }

    public static void setNombre_D(String nombre_D) {
        Nombre_D = nombre_D;
    }

    public static String getApellido_D() {
        return Apellido_D;
    }

    public static void setApellido_D(String apellido_D) {
        Apellido_D = apellido_D;
    }

    public static String getCargo_D() {
        return Cargo_D;
    }

    public static void setCargo_D(String cargo_D) {
        Cargo_D = cargo_D;
    }

    public static String getGenero_d() {
        return Genero_d;
    }

    public static void setGenero_d(String genero_d) {
        Genero_d = genero_d;
    }

    public static String getProvincia_d() {
        return Provincia_d;
    }

    public static void setProvincia_d(String provincia_d) {
        Provincia_d = provincia_d;
    }

    public static String getCIudad_d() {
        return CIudad_d;
    }

    public static void setCIudad_d(String CIudad_d) {
        DatosEntidad.CIudad_d = CIudad_d;
    }

    public static String getInstitucion_d() {
        return Institucion_d;
    }

    public static void setInstitucion_d(String institucion_d) {
        Institucion_d = institucion_d;
    }

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

        genero = (Spinner) view.findViewById(R.id.spinner_gen_p);
        provincia = (Spinner) view.findViewById(R.id.spinner_prov_p);
        ciudad = (Spinner) view.findViewById(R.id.spinner_ciu_p);
        txtNombre= (EditText) view.findViewById(R.id.txt_Nombres_p);
        txtApellido= (EditText) view.findViewById(R.id.txt_Apellidos_p);
        txtCargo = (EditText) view.findViewById(R.id.txt_cargo_p);

        loadSpinnerProvincias();

        //SearchBox
        final List<String> lista_instituciones = new LinkedList<>();
        //añado las instituciones a la lista
        for(String institucion : getResources().getStringArray(R.array.institucion)) {
            lista_instituciones.add(institucion);
        }
        ArrayAdapter<String> adapterautocomplate = new ArrayAdapter<> (getContext(),android.R.layout.select_dialog_item,lista_instituciones);
        final AutoCompleteTextView search= (AutoCompleteTextView)view.findViewById(R.id.autoCompleteTextView1);
        search.setThreshold(1);
        search.setAdapter(adapterautocomplate);
        search.setTextColor(Color.BLACK);

        //institucion = (Spinner) view.findViewById(R.id.spinner_inst_p);
        //adapter2 = ArrayAdapter.createFromResource(getContext(),
        //        R.array.institucion, android.R.layout.simple_spinner_item);
        //adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //institucion.setAdapter(adapter2);

        adapter3 = ArrayAdapter.createFromResource(getContext(),
                R.array.genero, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genero.setAdapter(adapter3);
        siguiente = (Button)view.findViewById(R.id.btnSiguiente_entidad) ;
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nombre = txtNombre.getText().toString();
                Apellido = txtApellido.getText().toString();
                Cargo = txtCargo.getText().toString();
                Institucion_d = search.getText().toString();

                if(Nombre.equals("")||Apellido.equals("")||Cargo.equals("")) {
                    Toast.makeText(getContext(), "Por favor, llene todos los campos", Toast.LENGTH_LONG).show();
                    // validacion de institucion valida
                }else if(! lista_instituciones.contains(Institucion_d)){
                    Toast.makeText(getContext(), "Por favor, elija una institución válida", Toast.LENGTH_LONG).show();

                }else {

                    Nombre_D = txtNombre.getText().toString();
                    Apellido_D = txtApellido.getText().toString();
                    Cargo_D = txtCargo.getText().toString();
                    if (genero.equals("Masculino")){
                        Genero_d ="0";
                    }else{
                        Genero_d="1";
                    }
                    Provincia_d = provincia.getSelectedItem().toString();
                    CIudad_d = ciudad.getSelectedItem().toString();
                    Institucion_d = search.getText().toString();

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
        this.provincia.setAdapter(adapter);

        // This activity implements the AdapterView.OnItemSelectedListener
        this.provincia.setOnItemSelectedListener(this);
        this.ciudad.setOnItemSelectedListener(this);
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
                this.ciudad.setAdapter(adapter);

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
        txtNombre.addTextChangedListener(new TextValidatorPedido(txtNombre) {
            @Override
            public void validate(EditText editText, String text) {
                for (int index = 0; index < text.length(); index++) {
                    String c = String.valueOf(text.charAt(index));
                    if (isNumeric(c) || (!text.matches("[a-zA-Zá-ú? ]*"))){
                        txtNombre.setError("Sólo ingrese letras");
                        text = text.substring(0, text.length() - 1);
                        txtNombre.setText(text);
                        txtNombre.setSelection(txtNombre.getText().length());
                    }
                }
                if (text.length() > 24) {
                    txtNombre.setError("Límite excedido");
                }
            }
        });


        //validacione de Apellido
        txtApellido.addTextChangedListener(new TextValidatorPedido(txtApellido) {
            @Override
            public void validate(EditText editText, String text) {
                for (int index = 0; index < text.length(); index++) {
                    String c = String.valueOf(text.charAt(index));
                    if (isNumeric(c) || (!text.matches("[a-zA-Zá-ú? ]*"))){
                        txtApellido.setError("Sólo ingrese letras");
                        text = text.substring(0, text.length() - 1);
                        txtApellido.setText(text);
                        txtApellido.setSelection(txtApellido.getText().length());
                    }
                }
                if (text.length() > 24) {
                    txtApellido.setError("Límite excedido");
                }
            }
        });


        //validacione de Cargo
        txtCargo.addTextChangedListener(new TextValidatorPedido(txtCargo) {
            @Override
            public void validate(EditText editText, String text) {
                for (int index = 0; index < text.length(); index++) {
                    String c = String.valueOf(text.charAt(index));
                    if (isNumeric(c) || (!text.matches("[a-zA-Zá-ú? ]*"))){
                        txtCargo.setError("Sólo ingrese letras");
                        text = text.substring(0, text.length() - 1);
                        txtCargo.setText(text);
                        txtCargo.setSelection(txtCargo.getText().length());
                    }
                }
                if (text.length() > 24) {
                    txtCargo.setError("Límite excedido");
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











