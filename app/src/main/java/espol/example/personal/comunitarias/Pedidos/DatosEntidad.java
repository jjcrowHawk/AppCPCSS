package espol.example.personal.comunitarias.Pedidos;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
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

import espol.example.personal.comunitarias.BaseDeDatos.ciudad.Ciudad;
import espol.example.personal.comunitarias.BaseDeDatos.provincia.Provincia;
import com.example.personal.comunitarias.R;

import java.util.ArrayList;


public class DatosEntidad extends Fragment implements AdapterView.OnItemSelectedListener {
    Spinner  genero;
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
    static Integer idCiuDE;
    static Integer idProvDE;
    static Integer idIndti;

    //
    ArrayList<String> lista_prov, ciudades,lista_ciudades_provincias;
    ProgressDialog mProgressDialog;

    /*public DatosEntidad(ViewPager viewPager) {
        this.viewPager = viewPager;
    }*/

    public DatosEntidad(){}


    public void asignarPager(ViewPager viewPager){
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

    private void inicializarComponentesTab3()  {

        genero = (Spinner) view.findViewById(R.id.spinner_gen_p);
        txtNombre= (EditText) view.findViewById(R.id.txt_Nombres_p);
        txtApellido= (EditText) view.findViewById(R.id.txt_Apellidos_p);
        txtCargo = (EditText) view.findViewById(R.id.txt_cargo_p);

        //SearchBox//final List<String> lista_instituciones =  new Institucion().getListaInstitucionNombres();
        //añado las instituciones a la lista
        ArrayAdapter<String> adapterautocomplate = new ArrayAdapter<> (getContext(),android.R.layout.select_dialog_item,new ArrayList<String>());
        final AutoCompleteTextView search= (AutoCompleteTextView)view.findViewById(R.id.autoCompleteTextView1);
        search.setThreshold(1);
        search.setAdapter(adapterautocomplate);
        search.setTextColor(Color.BLACK);

        ArrayAdapter<String> adapterautocomplate2 = new ArrayAdapter<> (getContext(),android.R.layout.select_dialog_item,lista_ciudades_provincias);
        final AutoCompleteTextView search_ciudad= (AutoCompleteTextView)view.findViewById(R.id.txt_ciudad_ent);
        search_ciudad.setThreshold(1);
        search_ciudad.setAdapter(adapterautocomplate2);
        search_ciudad.setTextColor(Color.BLACK);

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
                String [] c_array= search_ciudad.getText().toString().split(",");
                Provincia_d = c_array[1].trim();
                CIudad_d = c_array[0].trim();

                if(Nombre.equals("")||Apellido.equals("")||Cargo.equals("")) {
                    Toast.makeText(getContext(), "Por favor, llene todos los campos", Toast.LENGTH_LONG).show();
                    // validacion de institucion valida
                }else if(!lista_ciudades_provincias.contains(CIudad_d +", "+Provincia_d)){
                    Toast.makeText(getContext(), "Por favor, escriba una ciudad y provincia válida", Toast.LENGTH_LONG).show();
                }
                else {

                    Nombre_D = txtNombre.getText().toString();
                    Apellido_D = txtApellido.getText().toString();
                    Cargo_D = txtCargo.getText().toString();
                    Genero_d= genero.getSelectedItem().toString();
                    Institucion_d = search.getText().toString();
                    new Progress_cargando().execute();
                }
            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {

        }
    }

    public class Progress_cargando extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(DatosEntidad.this.getContext());
            mProgressDialog.setMessage("Procesando...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();
        }


        @Override
        protected Void doInBackground(Void... params) {
            //idIndti = new Institucion().getID_WS(Institucion_d);
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

            MostrarDatosPedido.setearDatos();
            viewPager.setCurrentItem(3);
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

    public void setLista_prov(ArrayList<String> lista_prov) {
        this.lista_prov = lista_prov;
    }

    public static Integer getIdIndti() {
        return idIndti;
    }

    public static void setIdIndti(Integer idIndti) {
        DatosEntidad.idIndti = idIndti;
    }

    public static Integer getIdProvDE() {
        return idProvDE;
    }

    public static void setIdProvDE(Integer idProvDE) {
        DatosEntidad.idProvDE = idProvDE;
    }

    public static Integer getIdCiuDE() {
        return idCiuDE;
    }

    public static void setIdCiuDE(Integer idCiuDE) {
        DatosEntidad.idCiuDE = idCiuDE;
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
        DatosEntidad.CIudad_d = CIudad_d;
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

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String cargo) {
        Cargo = cargo;
    }
}











