package com.example.personal.comunitarias.Denuncias;

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

import com.example.personal.comunitarias.BaseDeDatos.reclamo.Reclamo;
import com.example.personal.comunitarias.R;


public class Peticionario extends Fragment implements AdapterView.OnItemSelectedListener{
    Spinner identidad, tipoIdentificacion, genero, estado_civil, nivelEducacion, nacionalidad, residencia, provincia, ciudad;
    ArrayAdapter<CharSequence> adapter, adapter2, adapter3, adapter4, adapter5, adapter6, adapter7,adapter8, adapter9;
    private EditText txtNombre, txtApellido, txtCorreo,txtIdent , txtOcupacion;
    Button btn_seguir;
    Reclamo rec;
    /******/
    private ViewPager viewPager;
    private View view;
    private TabsDenuncia tabs;
    static String Nombre ="";
    static String  Apellido;
    static String Email;
    static String Identidad;
    static String Ocupacion;
    static String IdentidadReservada;
    static String TipoIden;
    static String Genero;
    static String Estado_civil;
    static String NivelEdu;
    static String Nacio;
    static String reside;
    static String provi;
    static String Ciuda;

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
        view =  inflater.inflate(R.layout.frag1_peticionario,container,false);
        InicializarComp();
       /* view.findViewById(R.id.btnInfoPeticionario).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(1);
                //tabs.DesbloquearPrimTb();
            }
        });*/
        return  view;

    }

    private  void InicializarComp(){
        //data peticionario


        Log.e("inicializarComp","entra");

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
        adapter4 = ArrayAdapter.createFromResource(getContext(),
                R.array.estado_civil, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estado_civil.setAdapter(adapter4);

        //Spinner Nivel educacion
        nivelEducacion = (Spinner) view.findViewById(R.id.spinner5);
        adapter5 = ArrayAdapter.createFromResource(getContext(),
                R.array.nivel__educacion, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nivelEducacion.setAdapter(adapter5);

        //Spinner Nivel Nacionalidad
        nacionalidad = (Spinner) view.findViewById(R.id.spinner6);
        adapter6 = ArrayAdapter.createFromResource(getContext(),
                R.array.nacionalidad, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nacionalidad.setAdapter(adapter6);

        //Spinner Residencia
        residencia = (Spinner) view.findViewById(R.id.spinner7);
        adapter7 = ArrayAdapter.createFromResource(getContext(),
                R.array.si_no, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        residencia.setAdapter(adapter7);

        //Spinner Provincia
        provincia = (Spinner) view.findViewById(R.id.spinner8);

        //Spinner Ciudad
        ciudad = (Spinner) view.findViewById(R.id.spinner9);

        //data
        txtNombre = (EditText)view.findViewById(R.id.txt_Nombres);
        txtApellido = (EditText)view.findViewById(R.id.txt_Apellidos);
        txtIdent = (EditText)view.findViewById(R.id.txt_tipoIdentificacion);
        txtOcupacion = (EditText)view.findViewById(R.id.txt_ocupacion);
        txtCorreo = (EditText)view.findViewById(R.id.txt_correo);

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
                Ocupacion = txtOcupacion.getText().toString();
                Email = txtCorreo.getText().toString();

                IdentidadReservada = identidad.getSelectedItem().toString();
                TipoIden = tipoIdentificacion.getSelectedItem().toString();
                Genero = genero.getSelectedItem().toString();
                Estado_civil = estado_civil.getSelectedItem().toString();
                NivelEdu = nivelEducacion.getSelectedItem().toString();
                Nacio = nacionalidad.getSelectedItem().toString();
                reside = residencia.getSelectedItem().toString();
                provi = provincia.getSelectedItem().toString();
                Ciuda = ciudad.getSelectedItem().toString();

                if(Nombre.equals("")|| Apellido.equals("")||
                        Identidad.equals("") || Ocupacion.equals("") ||
                        Email.equals("")){
                    Toast.makeText(getContext(),"Por favor, llene todos los campos",Toast.LENGTH_LONG).show();
                }else {
                    viewPager.setCurrentItem(1);
                }



               /* rec = new Reclamo();

                rec.setNombresapellidosdenunciante(Nombre + " "+ Apellido);
                rec.setTipoidentificacion(TipoIden);
                rec.setNumidenti(Identidad);*/
                //rec.setDireccion(); //Será capaz con lo de geolocalización
                //rec.setEmail(Email);
                //rec.setNombresapellidosdenunciado();
                //rec.setTelefono(); //no hay ese campo en el layout
                //rec.setCargo(Ocupacion);
                //rec.setComparecer(); //está en el TAB Denuncia
                //rec.setDocumentores(); //está en el TAB Denuncia, asumo que el campo de si está siendo investigado

               /* if (IdentidadReservada.equals("Si")) rec.setIdentidadreservada("1");
                if (IdentidadReservada.equals("No")) rec.setIdentidadreservada("0");

                if (Nacio.equals("Extranjero")) rec.setResideextrangero("1");
                if (!Nacio.equals("Extranjero")) rec.setResideextrangero("0");*/

                //rec.setCiudaddeldenuncianteid(); hay que colocar ID, que se necesita una función que me devuelva ese ID
                //rec.setCiudaddeldenunciadoid();
                //rec.setInstitucionimplicadaid();
                //rec.getProvinciadenuncianteid();
                //rec.getProvinciadenunciadoid();

              /*  rec.Guardar_Reclamo();
                Log.d("myTag",rec.get_mensagem());

                if (rec.is_status())
                    Log.e("status","si inserta");*/




            }
        });



    }










    public void Ir_Segundo_Fragment(){

        //Spinner identidad, tipoIdentificacion, genero, estado_civil, nivelEducacion, nacionalidad, residencia, provincia, ciudad;







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
            case R.id.spinner8:

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

            case R.id.spinner9:

                break;
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

        //validacione de Cargo
        txtOcupacion.addTextChangedListener(new TextValidator(txtOcupacion) {
            @Override
            public void validate(EditText editText, String text) {
                for (int index = 0; index < text.length(); index++) {
                    String c = String.valueOf(text.charAt(index));
                    if (isNumeric(c) || (!text.matches("[a-zA-Zá-ú? ]*"))){
                        txtOcupacion.setError("Sólo ingrese letras");
                        text = text.substring(0, text.length() - 1);
                        txtOcupacion.setText(text);
                        txtOcupacion.setSelection(txtOcupacion.getText().length());
                    }
                }
                if (text.length() > 24) {
                    txtOcupacion.setError("Límite excedido");
                }
            }
        });
    }


    //función para validar (segunda opción)
    /*
    public void validarInputs(){

        InputFilter filter= new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                System.out.println("source = "+source);
                if (source.length()>5){
                    //validarLimite();
                    txtNombre.setError("Límite excedido");
                }
                for (int i = start; i < end; i++) {
                    String checkMe = String.valueOf(source.charAt(i));
                    //Pattern pattern = Pattern.compile("[ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789_]*");
                    Pattern pattern = Pattern.compile("[ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzáéíóú ]*");
                    Matcher matcher = pattern.matcher(checkMe);
                    boolean valid = matcher.matches();
                    if(!valid){
                        Log.d("", "invalid");
                        txtNombre.setError("Sólo letras");
                        return "";
                    }
                }
                return null;
            }
        };

        txtNombre.setFilters(new InputFilter[]{filter});

    }



    public void validarLimite(){
        txtNombre .addTextChangedListener(new TextValidator(txtNombre ) {
            @Override
            public void validate(EditText editText, String text) {
                if (text.length() > 24) {
                    txtNombre.setError("Límite excedido");
                }
            }
        });
    }
     */

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

    public static String getOcupacion() {
        return Ocupacion;
    }

    public static void setOcupacion(String ocupacion) {
        Ocupacion = ocupacion;
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


}




