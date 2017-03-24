package com.example.personal.comunitarias.Denuncias;

import android.app.ProgressDialog;
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
import com.example.personal.comunitarias.BaseDeDatos.provincia.Provincia;
import com.example.personal.comunitarias.BaseDeDatos.reclamo.Reclamo;
import com.example.personal.comunitarias.DatabaseHelper.DatabaseHelper;
import com.example.personal.comunitarias.R;

import java.util.ArrayList;
import java.util.List;


public class Peticionario extends Fragment implements AdapterView.OnItemSelectedListener{
    Spinner identidad, tipoIdentificacion, genero, estado_civil, nivelEducacion, nacionalidad, residencia, provincia, ciudad, ocupacion_peticionario;
    ArrayAdapter<CharSequence> adapter, adapter2, adapter3, adapter7,adapter8, adapter9;
    ArrayAdapter<String> adapter4,adapter5,adapter6,adapterOcupaPeticionario;
    private EditText txtNombre, txtApellido, txtCorreo,txtIdent , txtOcupacion;
    private EditText txtTelefono, txtDireccion, txtEdad, txtOrganizacionSocial, txtCargoPeticionario;
    Button btn_seguir;
    Reclamo rec;

    //Progress
    private ProgressDialog mProgressDialog;
    private ArrayList<String> ciudades;

    /******/
    private ViewPager viewPager;
    private View view;
    private TabsDenuncia tabs;
    static String Nombre ="";
    static String  Apellido;
    static String Email;
    static String Telefono;
    static String Direccion;
    static String Identidad;
    static String IdentidadReservada;
    static String TipoIden;
    static String Genero;
    static String Estado_civil;
    static String NivelEdu;
    static String Nacio;
    static String reside;
    static String provi;
    static String Ciuda;
    static Integer  idCiuP;
    static Integer idProvp;

    //Agregado
    static String Edad;
    static String Cargo;
    static String OcupacionPeticionario;
    static String OrganizacionSocial;



    public static Integer getIdCiuP() {
        return idCiuP;
    }

    public static void setIdCiuP(Integer idCiuP) {
        Peticionario.idCiuP = idCiuP;
    }

    public static Integer getIdProvp() {
        return idProvp;
    }

    public static void setIdProvp(Integer idProvp) {
        Peticionario.idProvp = idProvp;
    }

    public static Integer getIdocupacionP() {
        return idocupacionP;
    }

    public static void setIdocupacionP(Integer idocupacionP) {
        Peticionario.idocupacionP = idocupacionP;
    }

    public static Integer getIdNivelEduca() {
        return idNivelEduca;
    }

    public static void setIdNivelEduca(Integer idNivelEduca) {
        Peticionario.idNivelEduca = idNivelEduca;
    }

    public static Integer getIdestado() {
        return idestado;
    }

    public static void setIdestado(Integer idestado) {
        Peticionario.idestado = idestado;
    }

    public static Integer getIdNacionalidad() {
        return idNacionalidad;
    }

    public static void setIdNacionalidad(Integer idNacionalidad) {
        Peticionario.idNacionalidad = idNacionalidad;
    }

    static Integer idocupacionP;
    static Integer idNivelEduca;
    static Integer idestado;
    static Integer idNacionalidad;

    public static String getNombre() {
        return Nombre;
    }

    public static void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Peticionario(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.denuncia_tab1_peticionario,container,false);
        InicializarComp();

        return  view;

    }

    private  void InicializarComp(){
        //data peticionario



        //Spinner identidad reservada
        identidad = (Spinner) view.findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(getContext(), R.array.si_no, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        identidad.setAdapter(adapter);

        //Spinner Tipo de identificacion
        tipoIdentificacion = (Spinner) view.findViewById(R.id.spinner2);
        adapter2 = ArrayAdapter.createFromResource(getContext(),
                R.array.tipo_identificacion, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoIdentificacion.setAdapter(adapter2);

        //Spinner género
        genero = (Spinner) view.findViewById(R.id.spinner3);
        adapter3 = ArrayAdapter.createFromResource(getContext(),
                R.array.genero, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genero.setAdapter(adapter3);

        //Spinner estado_civil
        estado_civil = (Spinner) view.findViewById(R.id.spinner4);
        //Se lee los datos de la base de datos
        adapter4 = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, new DatabaseHelper(getContext()).getAllEstadocivilNombres());

        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estado_civil.setAdapter(adapter4);

        //Spinner Nivel educacion
        nivelEducacion = (Spinner) view.findViewById(R.id.spinner5);
        adapter5 = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, new DatabaseHelper(getContext()).getAllNiveleducacionNombres());

        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nivelEducacion.setAdapter(adapter5);

        //Spinner Nivel Nacionalidad
        nacionalidad = (Spinner) view.findViewById(R.id.spinner6);
        adapter6 = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, new DatabaseHelper(getContext()).getAllNacionalidadNombres());

        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nacionalidad.setAdapter(adapter6);

        //Spinner Residencia
        residencia = (Spinner) view.findViewById(R.id.spinner7);
        adapter7 = ArrayAdapter.createFromResource(getContext(),
                R.array.si_no, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        residencia.setAdapter(adapter7);

        //Spinner Ocupacion : empleado publico o privado
        ocupacion_peticionario = (Spinner) view.findViewById(R.id.spinnerOcupacion);
        adapterOcupaPeticionario = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, new DatabaseHelper(getContext()).getAllOcupacionNombres());
        /*adapterOcupaPeticionario = ArrayAdapter.createFromResource(getContext(),
                R.array.ocupacion, android.R.layout.simple_spinner_item);*/
        adapterOcupaPeticionario.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ocupacion_peticionario.setAdapter(adapterOcupaPeticionario);

        //Spinner Provincia
        provincia = (Spinner) view.findViewById(R.id.spinner8);

        //Spinner Ciudad
        ciudad = (Spinner) view.findViewById(R.id.spinner9);

        //data
        txtNombre = (EditText)view.findViewById(R.id.txt_Nombres);
        txtApellido = (EditText)view.findViewById(R.id.txt_Apellidos);
        txtIdent = (EditText)view.findViewById(R.id.txt_tipoIdentificacion);
        //txtCargoPeticionario = (EditText)view.findViewById(R.id.txt_cargo_peticionario_d);
        txtCorreo = (EditText)view.findViewById(R.id.txt_correo);
        txtTelefono = (EditText)view.findViewById(R.id.txt_telefono);
        txtDireccion = (EditText)view.findViewById(R.id.txt_direccion);
        txtEdad = (EditText)view.findViewById(R.id.txt_edad);
        txtOrganizacionSocial = (EditText)view.findViewById(R.id.txt_org_social);
        txtCargoPeticionario = (EditText)view.findViewById(R.id.txt_cargo_peticionario_d);



        loadSpinnerProvincias();
        //focusableEditText();
        ValidarCampos();

        btn_seguir =(Button) view.findViewById(R.id.btnInfoPeticionario);
        btn_seguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Nombre = txtNombre.getText().toString();
                Apellido = txtApellido.getText().toString();
                Identidad = txtIdent.getText().toString();
                //Cargo = txtOcupacion.getText().toString();
                Email = txtCorreo.getText().toString();
                Telefono = txtTelefono.getText().toString();
                Direccion = txtDireccion.getText().toString();

                Edad = txtEdad.getText().toString();
                //int EdadN=Integer.parseInt(Edad);
                Cargo=txtCargoPeticionario.getText().toString();
                OrganizacionSocial=txtOrganizacionSocial.getText().toString();



                IdentidadReservada = identidad.getSelectedItem().toString();
                TipoIden = tipoIdentificacion.getSelectedItem().toString();
                Genero = genero.getSelectedItem().toString();
                Estado_civil = estado_civil.getSelectedItem().toString();
                NivelEdu = nivelEducacion.getSelectedItem().toString();
                Nacio = nacionalidad.getSelectedItem().toString();
                reside = residencia.getSelectedItem().toString();
                provi = provincia.getSelectedItem().toString();
                Ciuda = ciudad.getSelectedItem().toString();

                //NUEVO
                OcupacionPeticionario= ocupacion_peticionario.getSelectedItem().toString();

                Log.d("pet",Ciuda +"    "+ provi);


                idocupacionP = new DatabaseHelper(getContext()).getOcupacion_id(OcupacionPeticionario);
                idestado = new DatabaseHelper(getContext()).getEstadocivil_id(Estado_civil);
                idNivelEduca = new DatabaseHelper(getContext()).getNiveleducacion_id(NivelEdu);
                idNacionalidad = new DatabaseHelper(getContext()).getNacionalidad_id(Nacio);
                idProvp = new DatabaseHelper(getContext()).getProvincia(provi);
                idCiuP = new DatabaseHelper(getContext()).getCiudad_id(Ciuda);


                //idocupacionP = o.getID_DB(Ocupacion);


                Log.d("ID PETICIONARIO","IdProvinvia"+idProvp+"  idCiudad "+idCiuP+"  IdNivelEducacion "+idNivelEduca+"   idEstado"+idestado+"  idOcupacion"+idocupacionP+"  idNacinalidad"+ idNacionalidad);

                if(Nombre.equals("")|| Apellido.equals("")||
                        Identidad.equals("") || Cargo.equals("") ||
                        Email.equals("")||Edad.equals("") ||
                         Telefono.equals("") ||Direccion.equals("")|| OrganizacionSocial.equals("")){
                    Toast.makeText(getContext(),"Por favor, llene todos los campos",Toast.LENGTH_LONG).show();
                }else {
                    viewPager.setCurrentItem(1);
                }

            }
        });



    }




    private void loadSpinnerProvincias() {
        // Create an ArrayAdapter using the string array and a default spinner
        // layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, new DatabaseHelper(getContext()).getAllProvinciasNombres());

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

                //ciudades= new ArrayList<String>();
                //new ProgressCiudades().execute();

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


    public class ProgressCiudades extends AsyncTask<Void, Void, Void> {
        String name_provincia;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            name_provincia= provincia.getSelectedItem().toString();
            mProgressDialog = new ProgressDialog(Peticionario.this.getContext());
            mProgressDialog.setMessage("Cargando ciudades...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }


        @Override
        protected Void doInBackground(Void... params) {
            ciudades=new Ciudad().getListaNombresCiudad_prov(new Provincia().getID_DB(name_provincia));
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
        }
    }




    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

    //función que valida los campos del formulario


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


        //validacione de identificación
        txtIdent.addTextChangedListener(new TextValidator(txtIdent) {
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

        //validacione de edad
        txtEdad .addTextChangedListener(new TextValidator(txtEdad ) {
            @Override
            public void validate(EditText editText, String text) {
                for (int index = 0; index < text.length(); index++) {
                    String c = String.valueOf(text.charAt(index));
                    if (!isNumeric(c) ){
                        txtEdad.setError("Sólo ingrese números");
                        text = text.substring(0, text.length() - 1);
                        txtEdad.setText(text);
                        txtEdad.setSelection(txtEdad.getText().length());
                    }
                }

            }
        });

        //validacione de Cargo
        txtCargoPeticionario.addTextChangedListener(new TextValidator(txtCargoPeticionario) {
            @Override
            public void validate(EditText editText, String text) {
                for (int index = 0; index < text.length(); index++) {
                    String c = String.valueOf(text.charAt(index));
                    if (isNumeric(c) || (!text.matches("[a-zA-Zá-ú? ]*"))){
                        txtCargoPeticionario.setError("Sólo ingrese letras");
                        text = text.substring(0, text.length() - 1);
                        txtCargoPeticionario.setText(text);
                        txtCargoPeticionario.setSelection(txtCargoPeticionario.getText().length());
                    }
                }
                if (text.length() > 24) {
                    txtCargoPeticionario.setError("Límite excedido");
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

    public static String getCargo() {
        return Cargo;
    }

    public static void setCargo(String cargo) {
        Cargo = cargo;
    }

    public static String getOrganizacionSocial() {
        return OrganizacionSocial;
    }

    public static void setOrganizacionSocial(String orga) {
        OrganizacionSocial = orga;
    }

    public static String getIdentidadReservada() {
        return IdentidadReservada;
    }

    public static void setIdentidadReservada(String identidadReservada) {
        IdentidadReservada = identidadReservada;
    }

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
        Peticionario.reside = reside;
    }

    public static String getProvi() {
        return provi;
    }

    public static void setProvi(String provi) {
        Peticionario.provi = provi;
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

    public static void setDireccion(String direccion) {  Direccion = direccion;  }

    public static String getEdad() {  return Edad;  }

    public static void setEdad(String edad) { Edad = edad;  }

    public static String getOcupacionPeticionario() {
        return OcupacionPeticionario;
    }

    public static void setOcupacionPeticionario(String ocupacionPeticionario) {
        OcupacionPeticionario = ocupacionPeticionario;
    }


}




