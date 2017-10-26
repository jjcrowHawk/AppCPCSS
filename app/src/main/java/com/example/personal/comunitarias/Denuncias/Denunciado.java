package com.example.personal.comunitarias.Denuncias;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.personal.comunitarias.BaseDeDatos.ciudad.Ciudad;
import com.example.personal.comunitarias.BaseDeDatos.provincia.Provincia;
import com.example.personal.comunitarias.R;

import java.util.ArrayList;


public class Denunciado extends Fragment implements AdapterView.OnItemSelectedListener{
    Spinner  genero;
    ArrayAdapter<CharSequence> adapter;

    private EditText txtNombre, txtApellido, txtCargo,txtParroqia;
    Button btn_enviar_r;
    private ViewPager viewPager;
    private View view;
    String correo;
    String contraseña;
    static String  Nombre_D;
    static String Apellido_D;
    static String Cargo_D;
    static String Genero_d;
    static String Provincia_d;
    static String CIudad_d;
    static String Institucion_d;
    static String Parroquia_d;
    static Integer idCiuDE,  idProvDE,idIndti;

    //
    ArrayList<String> lista_prov;
    ArrayList<String> lista_ciudades_provincias;
    ProgressDialog mProgressDialog;

    public Denunciado(ViewPager viewPager) {
        this.viewPager = viewPager;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.denuncia_tab3_denunciado,container,false);
        InicializarComp();

        view.findViewById(R.id.btn_anterior).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(1);
            }
        });
        return  view;
    }

    private  void InicializarComp() {
        //data denunciado
        //Spinner género
        genero = (Spinner) view.findViewById(R.id.spinner1);
        adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.genero, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genero.setAdapter(adapter);

        //Ciudades_provincias
        ArrayAdapter<String> adapterautocomplateCiudad = new ArrayAdapter<String> (getContext(),android.R.layout.select_dialog_item,lista_ciudades_provincias);
        final AutoCompleteTextView searchCiudad= (AutoCompleteTextView)view.findViewById(R.id.txt_ciudad);
        searchCiudad.setThreshold(1);
        searchCiudad.setAdapter(adapterautocomplateCiudad);
        searchCiudad.setTextColor(Color.BLACK);

        //Institucion
        ArrayAdapter<String> adapterautocomplate = new ArrayAdapter<String> (getContext(),android.R.layout.select_dialog_item,new ArrayList<String>());
        final AutoCompleteTextView search= (AutoCompleteTextView)view.findViewById(R.id.autoCompleteTextView1);
        search.setThreshold(1);
        search.setAdapter(adapterautocomplate);
        search.setTextColor(Color.BLACK);

        //data
        txtNombre = (EditText)view.findViewById(R.id.txt_NombresDen);
        txtApellido = (EditText)view.findViewById(R.id.txt_Apellidos);
        txtCargo = (EditText)view.findViewById(R.id.txt_cargo);
        txtParroqia = (EditText)view.findViewById(R.id.txtParroquia);
        correo ="prueba.envio.formulario@gmail.com";
        contraseña="espol1234";

        ValidarCampos();

        btn_enviar_r =(Button)view.findViewById(R.id.btn_enviar);
        btn_enviar_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Nombre_D = txtNombre.getText().toString();
                Apellido_D = txtApellido.getText().toString();
                Cargo_D = txtCargo.getText().toString();
                Genero_d= genero.getSelectedItem().toString();
                String [] ciudad_provincia= searchCiudad.getText().toString().split(", ");
                Provincia_d = ciudad_provincia[1];
                CIudad_d = ciudad_provincia[0];
                Institucion_d = search.getText().toString();
                Parroquia_d = txtParroqia.getText().toString();
                new Progress_cargando().execute();
            }
        });

    }


    public class Progress_cargando extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(Denunciado.this.getContext());
            mProgressDialog.setMessage("Procesando...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();
        }


        @Override
        protected Void doInBackground(Void... params) {
         //   idIndti = new Institucion().getID_WS(Institucion_d);
            idProvDE = new Provincia().getID_WS(Provincia_d);
            idCiuDE = new Ciudad().getID_WS(CIudad_d);
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            mProgressDialog.show();
        }

        @Override
        protected void onPostExecute(Void result) {
            mProgressDialog.dismiss();
            mProgressDialog.cancel();

            Log.d("ID Denunciado ","IdProvinvia"+idProvDE+"  idCiudad "+idCiuDE+"  IdINDTI "+idIndti);

            if(Nombre_D.equals("")|| Apellido_D.equals("") || Cargo_D.equals("") || Institucion_d.equals("")) {
                Toast.makeText(getContext(), "Por favor, llene todos los campos", Toast.LENGTH_LONG).show();
            }

            else if(!lista_ciudades_provincias.contains(CIudad_d +", "+Provincia_d)){
                Toast.makeText(getContext(), "Por favor, escriba una ciudad y provincia válida", Toast.LENGTH_LONG).show();
            }
            else {
                MostrarDatos.setearDatos();
                viewPager.setCurrentItem(3);
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {  }

    private void ValidarCampos(){

        //validacione de nombre
        txtNombre .addTextChangedListener(new TextValidator(txtNombre ) {
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
        txtApellido .addTextChangedListener(new TextValidator(txtApellido) {
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
        txtCargo .addTextChangedListener(new TextValidator(txtCargo) {
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

        //Validaciones de Institucion
        txtCargo .addTextChangedListener(new TextValidator(txtCargo) {
            @Override
            public void validate(EditText editText, String text) {
                if (text.length() > 70) {
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
        Denunciado.CIudad_d = CIudad_d;
    }

    public static String getInstitucion_d() {
        return Institucion_d;
    }

    public static void setInstitucion_d(String institucion_d) {
        Institucion_d = institucion_d;
    }

    public ArrayList<String> getLista_ciudades_provincias() {
        return lista_ciudades_provincias;
    }

    public void setLista_ciudades_provincias(ArrayList<String> lista_ciudades_provincias) {
        this.lista_ciudades_provincias = lista_ciudades_provincias;
    }

    public static Integer getIdCiuDE() {
        return idCiuDE;
    }

    public static void setIdCiuDE(Integer idCiuDE) {
        Denunciado.idCiuDE = idCiuDE;
    }

    public static Integer getIdProvDE() {
        return idProvDE;
    }

    public static void setIdProvDE(Integer idProvDE) {
        Denunciado.idProvDE = idProvDE;
    }

    public static Integer getIdIndti() {
        return idIndti;
    }

    public static void setIdIndti(Integer idIndti) {
        Denunciado.idIndti = idIndti;
    }

    public void setLista_prov(ArrayList<String> lista_prov) {this.lista_prov = lista_prov;  }

    public static String getParroquia_d() {
        return Parroquia_d;
    }

    public static void setParroquia_d(String parroquia_d) {
        Parroquia_d = parroquia_d;
    }


}


