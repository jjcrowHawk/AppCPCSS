package com.example.personal.comunitarias.Pedidos;

import android.content.res.TypedArray;
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

import com.example.personal.comunitarias.BaseDeDatos.provincia.Provincia;
import com.example.personal.comunitarias.BaseDeDatos.reclamo.Reclamo;
import com.example.personal.comunitarias.DatabaseHelper.DatabaseHelper;
import com.example.personal.comunitarias.Denuncias.TabsDenuncia;
import com.example.personal.comunitarias.Denuncias.TextValidator;
import com.example.personal.comunitarias.R;

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

        //Spinner identidad reservada
        /*identidad = (Spinner) view.findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(getContext(), R.array.si_no, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        identidad.setAdapter(adapter);
           */


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
                android.R.layout.simple_spinner_item, new DatabaseHelper(getContext()).getAllEstadocivilNombres());
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estado_civil.setAdapter(adapter4);

        //Spinner Nivel educacion
        nivelEducacion = (Spinner) view.findViewById(R.id.spinner_pet_educa);
        adapter5 = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, new DatabaseHelper(getContext()).getAllNiveleducacionNombres());
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nivelEducacion.setAdapter(adapter5);

        //Spinner Nivel Nacionalidad
        nacionalidad = (Spinner) view.findViewById(R.id.spinner_pet_naciona);
        adapter6 = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, new DatabaseHelper(getContext()).getAllNacionalidadNombres());
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
                android.R.layout.simple_spinner_item, new DatabaseHelper(getContext()).getAllOcupacionNombres());
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
                //Ocupacion = txtOcupacion.getText().toString();
                Email = txtCorreo.getText().toString();
                Telefono = txtTelefono.getText().toString();
                Direccion = txtDireccion.getText().toString();
                Edad_Pet= txtEdadP.getText().toString();
                Orga_Pet =  txtOrganizacionSocialP.getText().toString();
                Cargo_Pet = txtCargoPeticionarioP.getText().toString();


                //IdentidadReservada = identidad.getSelectedItem().toString();
                TipoIden = tipoIdentificacion.getSelectedItem().toString();
                Genero = genero.getSelectedItem().toString();
                Estado_civil = estado_civil.getSelectedItem().toString();
                NivelEdu = nivelEducacion.getSelectedItem().toString();
                Nacio = nacionalidad.getSelectedItem().toString();
                reside = residencia.getSelectedItem().toString();
                provi = provincia.getSelectedItem().toString();
                Ciuda = ciudad.getSelectedItem().toString();

                idestado = new DatabaseHelper(getContext()).getEstadocivil_id(Estado_civil);
                idNivelEduca = new DatabaseHelper(getContext()).getNiveleducacion_id(NivelEdu);
                idNacionalidad = new DatabaseHelper(getContext()).getNacionalidad_id(Nacio);
                idProvp = new DatabaseHelper(getContext()).getProvincia(provi);
                idCiuP = new DatabaseHelper(getContext()).getCiudad_id(Ciuda);

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
            case R.id.spinner_pet_provin:

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

            case R.id.spinner_pet_ciud:

                break;
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

}











