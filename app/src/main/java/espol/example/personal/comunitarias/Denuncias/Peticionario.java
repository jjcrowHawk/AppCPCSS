package espol.example.personal.comunitarias.Denuncias;

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

import espol.example.personal.comunitarias.BaseDeDatos.ciudad.Ciudad;
import espol.example.personal.comunitarias.BaseDeDatos.estadocivil.Estadocivil;
import espol.example.personal.comunitarias.BaseDeDatos.etnia.Etnia;
import espol.example.personal.comunitarias.BaseDeDatos.niveleducacion.Niveleducacion;
import espol.example.personal.comunitarias.BaseDeDatos.provincia.Provincia;
import com.example.personal.comunitarias.R;

import java.util.ArrayList;


public class Peticionario extends Fragment implements AdapterView.OnItemSelectedListener{
    Spinner identidad, tipoIdentificacion, genero, estado_civil, nivelEducacion,etnia;
    ArrayAdapter<CharSequence> adapter, adapter2, adapter3;
    static ArrayAdapter<String> adapter4,adapter5,adapterEtnia;
    private EditText txtNombre, txtApellido, txtCorreo,txtIdent,txtPais;
    private EditText txtTelefono, txtCelular, txtDireccion, txtEdad, txtOrganizacionSocial, txtCargoPeticionario;
    static ArrayAdapter<String> adapterautocomplate;
    Button btn_seguir;

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
    static String Celular;
    static String Direccion;
    static String Identidad;
    static String IdentidadReservada;
    static String TipoIden;
    static String Genero;
    static String Estado_civil;
    static String NivelEdu;
    static String Etnia;
    static String provi;
    static String Ciuda;
    static String Pais;
    static Integer  idCiuP;
    static Integer idProvp;

    //Agregado
    static String Edad;
    static String Cargo;
    static String OrganizacionSocial;
    static Integer idEtnia;

    //Listas spinners
    static ArrayList<String> lista_estadocivil;
    static ArrayList<String> lista_niveledu;
    static ArrayList<String> lista_prov;
    static ArrayList<String> lista_ciudad;
    static ArrayList<String> lista_etnia;


    static ArrayList<String> lista_ciudades_provincias;

    static Integer idNivelEduca;
    static Integer idestado;

    public Peticionario() {

    }

    public void asignarPager(ViewPager viewPager){
        this.viewPager=viewPager;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.denuncia_tab1_peticionario,container,false);

        //new Progress_guardando().execute();
        InicializarComp();

        return  view;

    }

    public  void InicializarComp(){
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
        //lst_estadocivil=new ArrayList<>();
        adapter4 = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, lista_estadocivil);
        //new Estadocivil().getListaEstadoCivilNombres() //new DatabaseHelper(getContext()).getAllEstadocivilNombres()
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estado_civil.setAdapter(adapter4);

        //Spinner Nivel educacion
        nivelEducacion = (Spinner) view.findViewById(R.id.spinner5);
        adapter5 = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, lista_niveledu);

        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nivelEducacion.setAdapter(adapter5);


        //Spiner Etnia
        etnia = (Spinner) view.findViewById(R.id.spinnerEtnia);
        adapterEtnia = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,lista_etnia);
        adapterEtnia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etnia.setAdapter(adapterEtnia);

        //adapter ciudad
        System.out.println("IMPRIMIENDO CIUDADES!");
        for(String s: lista_ciudades_provincias){
            System.out.println("ciudad en este contexto: "+lista_ciudades_provincias);
        }
        adapterautocomplate = new ArrayAdapter<String> (getContext(),android.R.layout.select_dialog_item,lista_ciudades_provincias);
        final AutoCompleteTextView search= (AutoCompleteTextView)view.findViewById(R.id.txt_ciudad);
        search.setThreshold(1);
        search.setAdapter(adapterautocomplate);
        search.setTextColor(Color.BLACK);



        //data
        txtNombre = (EditText)view.findViewById(R.id.txt_Nombres);
        txtApellido = (EditText)view.findViewById(R.id.txt_Apellidos);
        txtIdent = (EditText)view.findViewById(R.id.txt_tipoIdentificacion);
        txtCorreo = (EditText)view.findViewById(R.id.txt_correo);
        txtTelefono = (EditText)view.findViewById(R.id.txt_telefono);
        txtCelular = (EditText)view.findViewById(R.id.txt_celular);
        txtDireccion = (EditText)view.findViewById(R.id.txt_direccion);
        txtEdad = (EditText)view.findViewById(R.id.txt_edad);
        txtOrganizacionSocial = (EditText)view.findViewById(R.id.txt_org_social);
        txtCargoPeticionario = (EditText)view.findViewById(R.id.txt_cargo_peticionario_d);
        txtPais = (EditText)view.findViewById(R.id.txtPais);


        //focusableEditText();
        ValidarCampos();

        btn_seguir =(Button) view.findViewById(R.id.btnInfoPeticionario);
        btn_seguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Nombre = txtNombre.getText().toString();
                Apellido = txtApellido.getText().toString();
                Identidad = txtIdent.getText().toString();
                Email = txtCorreo.getText().toString();
                Telefono = txtTelefono.getText().toString();
                Direccion = txtDireccion.getText().toString();
                Celular = txtCelular.getText().toString();
                Edad = txtEdad.getText().toString();
                Cargo=txtCargoPeticionario.getText().toString();
                OrganizacionSocial=txtOrganizacionSocial.getText().toString();

                IdentidadReservada = identidad.getSelectedItem().toString();
                TipoIden = tipoIdentificacion.getSelectedItem().toString();
                Genero = genero.getSelectedItem().toString();
                Estado_civil = estado_civil.getSelectedItem().toString();
                Etnia = etnia.getSelectedItem().toString();
                NivelEdu = nivelEducacion.getSelectedItem().toString();
                String [] ciudad_provincia=search.getText().toString().split(", ");
                provi = ciudad_provincia[1];
                Ciuda = ciudad_provincia[0];
                Pais = txtPais.getText().toString();

                Log.d("pet",Ciuda +"    "+ provi);
                new Progress_cargando().execute();
            }
        });



    }

    public class Progress_cargando extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(Peticionario.this.getContext());
            mProgressDialog.setMessage("Cargando...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();
        }


        @Override
        protected Void doInBackground(Void... params) {
            idestado =          new Estadocivil().getID_WS(Estado_civil);
            idNivelEduca =      new Niveleducacion().getID_WS(NivelEdu);
            idEtnia =           new Etnia().getID_WS(Etnia);
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

            Log.d("ID PETICIONARIO","IdProvinvia"+idProvp+"  idCiudad "+idCiuP+"  IdNivelEducacion "+idNivelEduca+"   idEstado"+idestado);

            if(Nombre.equals("")|| Apellido.equals("")||
                    Identidad.equals("") || Cargo.equals("") ||
                    Email.equals("")||Edad.equals("") ||
                    Telefono.equals("") ||Direccion.equals("")|| OrganizacionSocial.equals("") || Celular.equals("") || Pais.equals("")){
                Toast.makeText(getContext(),"Por favor, llene todos los campos",Toast.LENGTH_LONG).show();
            }
            else if(!lista_ciudades_provincias.contains(Ciuda +", "+provi)){
                Toast.makeText(getContext(), "Por favor, escriba una ciudad y provincia válida", Toast.LENGTH_LONG).show();
            }
            else {
                viewPager.setCurrentItem(1);
            }
        }
    }

    /*public void loadCiudadesProvincias() {
        lista_ciudades_provincias=new ArrayList<String>();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    for (String prov : lista_prov) {
                        ArrayList<String> lista_ciudades = new Ciudad().getListaNombresCiudad_prov(new Provincia().getID_WS(prov));
                        for (String ciudad : lista_ciudades) {
                            String ciudad_provincia = ciudad + ", " + prov;
                            lista_ciudades_provincias.add(ciudad_provincia);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner8:
                //Obtener las ciudades correspondiente a la provincia seleccionada de la base LOCAL
                //List<String> ciudades=new DatabaseHelper(getContext()).getAllCiudadesNombres_prov(new DatabaseHelper(getContext()).getProvincia(provincia.getSelectedItem().toString()));

                ciudades= new ArrayList<String>();
                new ProgressCiudades().execute();
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
            name_provincia= "";
            mProgressDialog = new ProgressDialog(Peticionario.this.getContext());
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

            // Apply the adapter to the spinne
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

        txtCelular.addTextChangedListener(new TextValidator(txtCelular) {
            @Override
            public void validate(EditText editText, String text) {
                //puede ser 10 u 11 por el RUC
                if (text.length()> 0 && text.length() < 10) {
                    txtCelular.setError("Cantidad de dígitos incorrecta");
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

        txtPais.addTextChangedListener(new TextValidator(txtPais) {
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
                if (text.length() > 50) {
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

    public static void setLista_ciudades_provincias(ArrayList<String> lista_ciudades_provincias) {
        Peticionario.lista_ciudades_provincias = lista_ciudades_provincias;
    }

    public static void setLista_estadocivil(ArrayList<String> lista_estadocivil) {  Peticionario.lista_estadocivil = lista_estadocivil; }

    public static void setLista_niveledu(ArrayList<String> lista_niveledu) { Peticionario.lista_niveledu = lista_niveledu;}

    public static void setLista_prov(ArrayList<String> lista_prov) { Peticionario.lista_prov = lista_prov;  }

    public static void setLista_ciudad(ArrayList<String> lista_ciudad) { Peticionario.lista_ciudad = lista_ciudad; }

    public static void setLista_etnia(ArrayList<String> lista_etnia) { Peticionario.lista_etnia= lista_etnia; }

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

    public static String getNombre() {
        return Nombre;
    }

    public static void setNombre(String nombre) {
        Nombre = nombre;
    }

    public static String getCelular() {
        return Celular;
    }

    public static void setCelular(String celular) {
        Celular = celular;
    }

    public static String getPais() {
        return Pais;
    }

    public static void setPais(String pais) {
        Pais = pais;
    }

    public static Integer getIdEtnia() {
        return idEtnia;
    }

    public static void setIdEtnia(Integer idEtnia) {
        Peticionario.idEtnia = idEtnia;
    }
}





