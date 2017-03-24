package com.example.personal.comunitarias.Denuncias;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
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
import com.example.personal.comunitarias.BaseDeDatos.estadocivil.Estadocivil;
import com.example.personal.comunitarias.BaseDeDatos.institucion.Institucion;
import com.example.personal.comunitarias.BaseDeDatos.nacionalidad.Nacionalidad;
import com.example.personal.comunitarias.BaseDeDatos.niveleducacion.Niveleducacion;
import com.example.personal.comunitarias.BaseDeDatos.ocupacion.Ocupacion;
import com.example.personal.comunitarias.BaseDeDatos.predenuncia.Predenuncia;
import com.example.personal.comunitarias.BaseDeDatos.provincia.Provincia;
import com.example.personal.comunitarias.BaseDeDatos.reclamo.Reclamo;
import com.example.personal.comunitarias.DatabaseHelper.DatabaseHelper;
import com.example.personal.comunitarias.R;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class Denunciado extends Fragment implements AdapterView.OnItemSelectedListener{
    Spinner  genero,institucion, provincia, ciudad;
    ArrayAdapter<CharSequence> adapter, adapter2, adapter3, adapter4;


    private Spinner  ocupacion_denunciado;
    private ArrayAdapter<String>  adapterOcupaDenunciado;
    private EditText txtNombre, txtApellido, txtCargo, txtUnAfectada, txtPerjud;
    Button btn_enviar_r;
    private ViewPager viewPager;
    private View view;
    private TabsDenuncia tabs;
    String correo;
    String contraseña;
    Institucion i = new Institucion();
    static String  Nombre_D;
    static String Apellido_D;
    static String Cargo_D;
    static String Unafectada;
    static String Perdjudicada_d;
    static String Genero_d;
    static String Provincia_d;
    static String CIudad_d;
    static String Institucion_d;
    static Integer idCiuDE,  idProvDE,idIndti;


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

        //SearchBox
        final List<String> lista_instituciones =  new DatabaseHelper(getContext()).getAllInstitucionNombres();

        ArrayAdapter<String> adapterautocomplate = new ArrayAdapter<String> (getContext(),android.R.layout.select_dialog_item,lista_instituciones);
        final AutoCompleteTextView search= (AutoCompleteTextView)view.findViewById(R.id.autoCompleteTextView1);
        search.setThreshold(1);
        search.setAdapter(adapterautocomplate);
        search.setTextColor(Color.BLACK);



        //Spinner Ocupacion : empleado publico o privado
        ocupacion_denunciado = (Spinner) view.findViewById(R.id.spinnerOcupacionDenunciado);
        adapterOcupaDenunciado = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, new DatabaseHelper(getContext()).getAllOcupacionNombres());
        /*adapterOcupaDenunciado = ArrayAdapter.createFromResource(getContext(),
                R.array.ocupacion, android.R.layout.simple_spinner_item);*/
        adapterOcupaDenunciado.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ocupacion_denunciado.setAdapter(adapterOcupaDenunciado);

        //Spinner Provincia
        provincia = (Spinner) view.findViewById(R.id.spinner8);

        //Spinner Ciudad
        ciudad = (Spinner) view.findViewById(R.id.spinner9);

        //data
        txtNombre = (EditText)view.findViewById(R.id.txt_NombresDen);
        txtApellido = (EditText)view.findViewById(R.id.txt_Apellidos);
        txtCargo = (EditText)view.findViewById(R.id.txt_cargo);
        txtUnAfectada = (EditText)view.findViewById(R.id.txt_unidadafectada);
        txtPerjud = (EditText)view.findViewById(R.id.txt_num_perjudicados);
        correo ="prueba.envio.formulario@gmail.com";
        contraseña="espol1234";

        loadSpinnerProvincias();
        ValidarCampos();

        btn_enviar_r =(Button)view.findViewById(R.id.btn_enviar);
        btn_enviar_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Nombre_D = txtNombre.getText().toString();
                Apellido_D = txtApellido.getText().toString();
                Cargo_D = txtCargo.getText().toString();
                Unafectada = txtUnAfectada.getText().toString();
                Perdjudicada_d = txtPerjud.getText().toString();
                if (genero.getSelectedItem().equals("Masculino")){
                    Genero_d ="1";
                }else{
                    Genero_d="0";
                }
                Provincia_d = provincia.getSelectedItem().toString();
                CIudad_d = ciudad.getSelectedItem().toString();
                Institucion_d = search.getText().toString();
                //idIndti = i.getID_DB(Institucion_d);
                idIndti = new DatabaseHelper(getContext()).getInstitucion_id(Institucion_d);
                idProvDE = new DatabaseHelper(getContext()).getProvincia(Provincia_d);
                idCiuDE = new DatabaseHelper(getContext()).getCiudad_id(CIudad_d);




                Log.d("ID Denunciado ","IdProvinvia"+idProvDE+"  idCiudad "+idCiuDE+"  IdINDTI "+idIndti);



                if(Nombre_D.equals("")|| Apellido_D.equals("")|| Cargo_D.equals("") ) {
                    Toast.makeText(getContext(), "Por favor, llene todos los campos", Toast.LENGTH_LONG).show();

                // validacion de institucion valida
                }else if(! lista_instituciones.contains(Institucion_d)){
                    Toast.makeText(getContext(), "Por favor, elija una institución válida", Toast.LENGTH_LONG).show();

                }else {
                    MostrarDatos.setearDatos();
                    viewPager.setCurrentItem(3);
                }




            }
        });

    }

    private void loadSpinnerProvincias() {
        // Create an ArrayAdapter using the string array and a default spinner
        // layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, new DatabaseHelper(getContext()).getAllProvinciasNombres());
        /*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(), R.array.provincias, android.R.layout.simple_spinner_item);*/
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
            case R.id.spinner8:
                //Obtener las ciudades correspondiente a la provincia seleccionada de la base LOCAL
                List<String> ciudades=new DatabaseHelper(getContext()).getAllCiudadesNombres_prov(new DatabaseHelper(getContext()).getProvincia(provincia.getSelectedItem().toString()));

                //creando adapter para spinner
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_item, ciudades);

                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // Apply the adapter to the spinner
                this.ciudad.setAdapter(adapter);

                break;

            case R.id.spinner9:

                break;
        }
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
        //validacion de UnidadAfectada
        txtUnAfectada .addTextChangedListener(new TextValidator(txtUnAfectada) {
            @Override
            public void validate(EditText editText, String text) {
                for (int index = 0; index < text.length(); index++) {
                    String c = String.valueOf(text.charAt(index));
                    if ((!text.matches("[a-zA-Zá-ú0-9? ]*"))){
                        txtUnAfectada.setError("No ingrese caracteres especiales");
                        text = text.substring(0, text.length() - 1);
                        txtUnAfectada.setText(text);
                        txtUnAfectada.setSelection(txtUnAfectada.getText().length());
                    }
                }
                if (text.length() > 24) {
                    txtUnAfectada.setError("Límite excedido");
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

    public static String getUnafectada() {
        return Unafectada;
    }

    public static void setUnafectada(String unafectada) {
        Unafectada = unafectada;
    }

    public static String getPerdjudicada_d() {
        return Perdjudicada_d;
    }

    public static void setPerdjudicada_d(String perdjudicada_d) {
        Perdjudicada_d = perdjudicada_d;
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


}


