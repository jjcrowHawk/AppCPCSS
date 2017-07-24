package com.example.personal.comunitarias.Pedidos;

import android.app.ProgressDialog;
import android.content.res.TypedArray;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.personal.comunitarias.BaseDeDatos.ciudad.Ciudad;
import com.example.personal.comunitarias.BaseDeDatos.estadocivil.Estadocivil;
import com.example.personal.comunitarias.BaseDeDatos.nacionalidad.Nacionalidad;
import com.example.personal.comunitarias.BaseDeDatos.niveleducacion.Niveleducacion;
import com.example.personal.comunitarias.BaseDeDatos.ocupacion.Ocupacion;
import com.example.personal.comunitarias.BaseDeDatos.provincia.Provincia;
import com.example.personal.comunitarias.BaseDeDatos.reclamo.Reclamo;
import com.example.personal.comunitarias.DatabaseHelper.DatabaseHelper;
import com.example.personal.comunitarias.Denuncias.Peticionario;
import com.example.personal.comunitarias.Denuncias.TabsDenuncia;
import com.example.personal.comunitarias.Denuncias.TextValidator;
import com.example.personal.comunitarias.R;

import java.util.ArrayList;
import java.util.List;




public class Peticionario_PE extends Fragment implements AdapterView.OnItemSelectedListener {


    Spinner tipoIdentificacion, genero, estado_civil, nivelEducacion, nacionalidad, residencia, provincia, ciudad;
    ArrayAdapter<CharSequence> adapter, adapter2, adapter3, adapter7;
    ArrayAdapter<String> adapter4,adapter5,adapter6,adapterOcupaPeticionarioPedido;
    private EditText txtNombre, txtApellido, txtCorreo,txtIdent , txtOcupacion, txtTelefono, txtDireccion;
    Button btn_seguir;
    Reclamo rec;


    Spinner  ocupacion_peticionario_pedido;
    private EditText  txtEdadP, txtOrganizacionSocialP, txtCargoPeticionarioP;
    /******/
    private ViewPager viewPager;
    private View view;
    private TabsDenuncia tabs;
    static String Nombre ="";
    static String  Apellido;
    static String  Telefono;
    static String  Direccion;
    static String Email;
    static String Identidad;

    static String IdentidadReservada;
    static Integer idocupacionP;
    static Integer idNivelEduca;
    static Integer idestado;
    static Integer idNacionalidad;
    static Integer  idCiuP;
    static Integer idProvp;
    static String TipoIden;
    static String Genero;
    static String Estado_civil;
    static String NivelEdu;
    static String Nacio;
    static String reside;
    static String provi;
    static String Ciuda;
    static String Edad_Pet;
    static String Cargo_Pet;
    static String Orga_Pet;
    String ocupacion;

    //Listas spinners
    static ArrayList<String> lista_estadocivil, lista_niveledu, lista_nacionalidad,
            lista_ocup, lista_prov, lista_ciudad, lista_inst;

    //Progress
    private ProgressDialog mProgressDialog;
    private ArrayList<String> ciudades;


    public Peticionario_PE(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pedido_tab1_peticionario, container, false);

        InicializarComp();
        ValidarCamposPedidos();

        return view;

    }

    private void ValidarCamposPedidos() {
        //validacione de nombre
        txtNombre .addTextChangedListener(new TextValidatorPedido(txtNombre) {
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
        txtApellido .addTextChangedListener(new TextValidatorPedido(txtApellido) {
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


        //validacione de identificación
        txtIdent.addTextChangedListener(new TextValidatorPedido(txtIdent) {
            @Override
            public void validate(EditText editText, String text) {
                //puede ser 10 u 11 por el RUC
                if (text.length()> 0 && text.length() < 10) {
                    txtIdent.setError("Cantidad de dígitos incorrecta");
                }
            }
        });



        //validacion de telefono
        txtTelefono.addTextChangedListener(new TextValidator(txtTelefono) {
            @Override
            public void validate(EditText editText, String text) {
                //puede ser 10 u 11 por el RUC
                if (text.length()> 0 && text.length() < 7) {
                    txtTelefono.setError("Cantidad de dígitos incorrecta");
                }
            }
        });

        //validacione de Cargo

        txtCargoPeticionarioP.addTextChangedListener(new TextValidatorPedido(txtCargoPeticionarioP) {
            @Override
            public void validate(EditText editText, String text) {
                for (int index = 0; index < text.length(); index++) {
                    String c = String.valueOf(text.charAt(index));
                    if (isNumeric(c) || (!text.matches("[a-zA-Zá-ú? ]*"))){
                        txtCargoPeticionarioP.setError("Sólo ingrese letras");
                        text = text.substring(0, text.length() - 1);
                        txtCargoPeticionarioP.setText(text);
                        txtCargoPeticionarioP.setSelection(txtCargoPeticionarioP.getText().length());
                    }
                }
                if (text.length() > 24) {
                    txtCargoPeticionarioP.setError("Límite excedido");
                }
            }
        });

        //validacione de edad
        txtEdadP .addTextChangedListener(new TextValidator(txtEdadP ) {
            @Override
            public void validate(EditText editText, String text) {
                for (int index = 0; index < text.length(); index++) {
                    String c = String.valueOf(text.charAt(index));
                    if (!isNumeric(c) ){
                        txtEdadP.setError("Sólo ingrese números");
                        text = text.substring(0, text.length() - 1);
                        txtEdadP.setText(text);
                        txtEdadP.setSelection(txtEdadP.getText().length());
                    }
                }

            }
        });


    }


    private  void InicializarComp(){
        //data peticionario


        Log.e("inicializarComp","entra");


        //Spinner Tipo de identificacion
        tipoIdentificacion = (Spinner) view.findViewById(R.id.spinner_pet_tipIden);
        adapter2 = ArrayAdapter.createFromResource(getContext(),
                R.array.tipo_identificacion, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoIdentificacion.setAdapter(adapter2);

        //Spinner género
        genero = (Spinner) view.findViewById(R.id.spinner_pet_gener);
        adapter3 = ArrayAdapter.createFromResource(getContext(),
                R.array.genero, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genero.setAdapter(adapter3);

        //Spinner estado_civil
        estado_civil = (Spinner) view.findViewById(R.id.spinner_pet_estado_civil);
        //Se lee los datos de la base de datos
        adapter4 = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, lista_estadocivil);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estado_civil.setAdapter(adapter4);

        //Spinner Nivel educacion
        nivelEducacion = (Spinner) view.findViewById(R.id.spinner_pet_educa);
        adapter5 = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, lista_niveledu);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nivelEducacion.setAdapter(adapter5);

        //Spinner Nivel Nacionalidad
        nacionalidad = (Spinner) view.findViewById(R.id.spinner_pet_naciona);
        adapter6 = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, lista_nacionalidad);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nacionalidad.setAdapter(adapter6);

        //Spinner Residencia
        residencia = (Spinner) view.findViewById(R.id.spinner_pet_resid);
        adapter7 = ArrayAdapter.createFromResource(getContext(),
                R.array.si_no, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        residencia.setAdapter(adapter7);


        //Spinner Ocupacion : empleado publico o privado
        ocupacion_peticionario_pedido = (Spinner) view.findViewById(R.id.spinnerOcupacionPedido);
        adapterOcupaPeticionarioPedido = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, lista_ocup);
        /*adapterOcupaPeticionarioPedido = ArrayAdapter.createFromResource(getContext(),
                R.array.ocupacion, android.R.layout.simple_spinner_item);*/
        adapterOcupaPeticionarioPedido.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ocupacion_peticionario_pedido.setAdapter(adapterOcupaPeticionarioPedido);

        //Spinner Provincia
        provincia = (Spinner) view.findViewById(R.id.spinner_pet_provin);

        //Spinner Ciudad
        ciudad = (Spinner) view.findViewById(R.id.spinner_pet_ciud);

        //data
        txtNombre = (EditText)view.findViewById(R.id.txt_Nombres_petic);
        txtApellido = (EditText)view.findViewById(R.id.txt_Apellidos_petic);
        txtIdent = (EditText)view.findViewById(R.id.txt_tipoIdent_petic);
        //txtOcupacion = (EditText)view.findViewById(R.id.txt_ocupacion_petic);
        txtCorreo = (EditText)view.findViewById(R.id.txt_correo_petic);
        txtTelefono = (EditText)view.findViewById(R.id.txt_telefonop);
        txtDireccion = (EditText)view.findViewById(R.id.txt_direccionp);

        txtEdadP = (EditText)view.findViewById(R.id.txt_edad_P);
        txtOrganizacionSocialP = (EditText)view.findViewById(R.id.txt_org_social_P);
        txtCargoPeticionarioP = (EditText)view.findViewById(R.id.txt_cargo_peticionario_P);

        loadSpinnerProvincias();
        //focusableEditText();
        //ValidarCampos();

        btn_seguir =(Button) view.findViewById(R.id.btnSiguiente_petic);
        btn_seguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Nombre = txtNombre.getText().toString();
                Apellido = txtApellido.getText().toString();
                Identidad = txtIdent.getText().toString();
                ocupacion = ocupacion_peticionario_pedido.getSelectedItem().toString();
                Email = txtCorreo.getText().toString();
                Telefono = txtTelefono.getText().toString();
                Direccion = txtDireccion.getText().toString();
                Edad_Pet= txtEdadP.getText().toString();
                Orga_Pet =  txtOrganizacionSocialP.getText().toString();
                Cargo_Pet = txtCargoPeticionarioP.getText().toString();
                TipoIden = tipoIdentificacion.getSelectedItem().toString();
                Genero = genero.getSelectedItem().toString();
                Estado_civil = estado_civil.getSelectedItem().toString();
                NivelEdu = nivelEducacion.getSelectedItem().toString();
                Nacio = nacionalidad.getSelectedItem().toString();
                reside = residencia.getSelectedItem().toString();
                provi = provincia.getSelectedItem().toString();
                Ciuda = ciudad.getSelectedItem().toString();

                new Progress_cargando().execute();


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

    private void loadSpinnerProvincias() {
        // Create an ArrayAdapter using the string array and a default spinner
        // layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, lista_prov);
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
            case R.id.spinner_pet_provin:

                //Obtener las ciudades correspondiente a la provincia seleccionada de la base LOCAL
                //List<String> ciudades=new DatabaseHelper(getContext()).getAllCiudadesNombres_prov(new DatabaseHelper(getContext()).getProvincia(provincia.getSelectedItem().toString()));

                new ProgressCiudades().execute();
                break;

            case R.id.spinner_pet_ciud:
                break;
        }

    }

    public class Progress_cargando extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(Peticionario_PE.this.getContext());
            mProgressDialog.setMessage("Cargando...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();
        }


        @Override
        protected Void doInBackground(Void... params) {

            idocupacionP =      new Ocupacion().getID_WS(ocupacion);
            idestado =          new Estadocivil().getID_WS(Estado_civil);
            idNivelEduca =      new Niveleducacion().getID_WS(NivelEdu);
            idNacionalidad =    new Nacionalidad().getID_WS(Nacio);
            idProvp =           new Provincia().getID_WS(provi);
            idCiuP =            new Ciudad().getID_WS(Ciuda);
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

            if(Nombre.equals("")|| Apellido.equals("")||
                    Identidad.equals("")
                    ||Cargo_Pet.equals("") ||
                    Email.equals("") ||
                    Telefono.equals("") || Direccion.equals("") || Edad_Pet.equals("") || Orga_Pet.equals("") ){
                Toast.makeText(getContext(),"Por favor, llene todos los campos",Toast.LENGTH_LONG).show();
            }else {

                Log.d("Peticionario",Nombre+""+Estado_civil+""+Nacio+""+ provi+"");
                viewPager.setCurrentItem(1);
            }
        }
    }

    public class ProgressCiudades extends AsyncTask<Void, Void, Void> {
        String name_provincia;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            name_provincia= provincia.getSelectedItem().toString();
            mProgressDialog = new ProgressDialog(Peticionario_PE.this.getContext());
            mProgressDialog.setMessage("Cargando ciudades...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();
        }


        @Override
        protected Void doInBackground(Void... params) {
            ciudades=new Ciudad().getListaNombresCiudad_prov(new Provincia().getID_WS(name_provincia));
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

            //creando adapter para spinner
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                    android.R.layout.simple_spinner_item, ciudades);

            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // Apply the adapter to the spinner
            ciudad.setAdapter(adapter);
        }
    }


    public static Integer getIdocupacionP() {
        return idocupacionP;
    }

    public static void setIdocupacionP(Integer idocupacionP) {
        Peticionario_PE.idocupacionP = idocupacionP;
    }

    public static Integer getIdNivelEduca() {
        return idNivelEduca;
    }

    public static void setIdNivelEduca(Integer idNivelEduca) {
        Peticionario_PE.idNivelEduca = idNivelEduca;
    }

    public static Integer getIdestado() {
        return idestado;
    }

    public static void setIdestado(Integer idestado) {
        Peticionario_PE.idestado = idestado;
    }

    public static Integer getIdNacionalidad() {
        return idNacionalidad;
    }

    public static void setIdNacionalidad(Integer idNacionalidad) {
        Peticionario_PE.idNacionalidad = idNacionalidad;
    }

    public static Integer getIdCiuP() {
        return idCiuP;
    }

    public static void setIdCiuP(Integer idCiuP) {
        Peticionario_PE.idCiuP = idCiuP;
    }

    public static Integer getIdProvp() {
        return idProvp;
    }

    public static void setIdProvp(Integer idProvp) {
        Peticionario_PE.idProvp = idProvp;
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public static String getNombre() {
        return Nombre;
    }

    public static void setNombre(String nombre) {
        Nombre = nombre;
    }

    public static String getApellido() {
        return Apellido;
    }

    public static void setApellido(String apellido) {
        Apellido = apellido;
    }

    public static String getEmail() {
        return Email;
    }

    public static void setEmail(String email) {
        Email = email;
    }

    public static String getIdentidad() {
        return Identidad;
    }

    public static void setIdentidad(String identidad) {
        Identidad = identidad;
    }

    /*public static String getOcupacion() {
        return Ocupacion;
    }

    public static void setOcupacion(String ocupacion) {
        Ocupacion = ocupacion;
    }
    */


    /*public static String getIdentidadReservada() {
        return IdentidadReservada;
    }

    public static void setIdentidadReservada(String identidadReservada) {
        IdentidadReservada = identidadReservada;
    }*/

    public static String getTipoIden() {
        return TipoIden;
    }

    public static void setTipoIden(String tipoIden) {
        TipoIden = tipoIden;
    }

    public static String getGenero() {
        return Genero;
    }

    public static void setGenero(String genero) {
        Genero = genero;
    }

    public static String getEstado_civil() {
        return Estado_civil;
    }

    public static void setEstado_civil(String estado_civil) {
        Estado_civil = estado_civil;
    }

    public static String getNivelEdu() {
        return NivelEdu;
    }

    public static void setNivelEdu(String nivelEdu) {
        NivelEdu = nivelEdu;
    }

    public static String getNacio() {
        return Nacio;
    }

    public static void setNacio(String nacio) {
        Nacio = nacio;
    }

    public static String getReside() {
        return reside;
    }

    public static void setReside(String reside) {
        Peticionario_PE.reside = reside;
    }

    public static String getProvi() {
        return provi;
    }

    public static void setProvi(String provi) {
        Peticionario_PE.provi = provi;
    }

    public static String getCiuda() {
        return Ciuda;
    }

    public static void setCiuda(String ciuda) {
        Ciuda = ciuda;
    }

    public static String getTelefono() {
        return Telefono;
    }

    public static void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public static String getDireccion() {
        return Direccion;
    }

    public static void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public static String getEdad() {
        return Edad_Pet;
    }

    public static void setEdad(String edad) {
        Edad_Pet = edad;
    }

    public static String getIdentidadReservada() {
        return IdentidadReservada;
    }

    public static void setIdentidadReservada(String identidadReservada) {
        IdentidadReservada = identidadReservada;
    }

    public static String getEdad_Pet() {
        return Edad_Pet;
    }

    public static void setEdad_Pet(String edad_Pet) {
        Edad_Pet = edad_Pet;
    }

    public static String getCargo_Pet() {
        return Cargo_Pet;
    }

    public static void setCargo_Pet(String cargo_Pet) {
        Cargo_Pet = cargo_Pet;
    }

    public static String getOrga_Pet() {
        return Orga_Pet;
    }

    public static void setOrga_Pet(String orga_Pet) {
        Orga_Pet = orga_Pet;
    }

    public static void setLista_estadocivil(ArrayList<String> lista_estadocivil) {
        Peticionario_PE.lista_estadocivil = lista_estadocivil;
    }

    public static void setLista_niveledu(ArrayList<String> lista_niveledu) {
        Peticionario_PE.lista_niveledu = lista_niveledu;
    }

    public static void setLista_nacionalidad(ArrayList<String> lista_nacionalidad) {
        Peticionario_PE.lista_nacionalidad = lista_nacionalidad;
    }

    public static void setLista_ocup(ArrayList<String> lista_ocup) {
        Peticionario_PE.lista_ocup = lista_ocup;
    }

    public static void setLista_prov(ArrayList<String> lista_prov) {
        Peticionario_PE.lista_prov = lista_prov;
    }

    public static void setLista_ciudad(ArrayList<String> lista_ciudad) {
        Peticionario_PE.lista_ciudad = lista_ciudad;
    }

    public static void setLista_inst(ArrayList<String> lista_inst) {
        Peticionario_PE.lista_inst = lista_inst;
    }
}











