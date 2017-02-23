package com.example.personal.comunitarias.Denuncias;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.personal.comunitarias.R;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import static android.R.attr.filter;


public class Denunciado extends Fragment  implements AdapterView.OnItemSelectedListener{
    Spinner  genero,institucion, provincia, ciudad;
    ArrayAdapter<CharSequence> adapter, adapter2, adapter3, adapter4;

    private EditText txtNombre, txtApellido, txtCargo, txtUnAfectada, txtPerjud;
    Button btn_enviar_r;
    String correo;
    String contraseña;
    Session session;

    /******/
    private ViewPager viewPager;
    private View view;
    private TabsDenuncia tabs;

    public Denunciado(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.frag3_denunciado,container,false);
        InicializarComp();
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

        institucion = (Spinner) view.findViewById(R.id.spinner2);
        adapter2 = ArrayAdapter.createFromResource(getContext(),
                R.array.institucion, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        institucion.setAdapter(adapter2);

        //Spinner Provincia
        provincia = (Spinner) view.findViewById(R.id.spinner8);

        //Spinner Ciudad
        ciudad = (Spinner) view.findViewById(R.id.spinner9);

        //data
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

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                Properties properties = new Properties();
                properties.put("mail.smtp.host","smtp.googlemail.com");
                properties.put("mail.smtp.socketFactory.port","465");
                properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
                properties.put("mail.smtp.auth","true");
                properties.put("mail.smtp.port","465");

                try{
                    session= Session.getDefaultInstance(properties, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(correo,contraseña);
                        }
                    });

                    if(session!=null){

                        BodyPart texto = new MimeBodyPart();
                        texto.setText("HOLA PRUEBA MULTI PART");
                        BodyPart imagen = new MimeBodyPart();
                        //imagen.setDataHandler(new DataHandler(new FileDataSource()));
                        //imagen.setFileName("Imagen de prueba");
                        imagen.setText("Se ha Enviado Su peticiòn Correctamente");

                        MimeMultipart multiparte = new MimeMultipart();
                        multiparte.addBodyPart(texto);
                        multiparte.addBodyPart(imagen);


                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(correo));
                        message.setSubject("Confirmaciòn De Envio");
                        message.setRecipients(Message.RecipientType.TO , InternetAddress.parse("prueba.recibo.formulario@gmail.com"));
                        message.setContent(multiparte);



                        Transport.send(message);

                    }

                }catch (Exception e){
                    e.printStackTrace();
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


        //validacione de identificación
        /*txtPerjud.addTextChangedListener(new TextValidator(txtPerjud) {
            @Override
            public void validate(EditText editText, String text) {
                //puede ser 10 u 11 por el RUC
                if (text.length()> 0 && text.length() < 10) {
                    txtPerjud.setError("Cantidad de dígitos incorrecta");
                }
            }
        });*/

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

    /*
    public void validateSe(){
        txtNombre = (EditText)view.findViewById(R.id.txt_Nombres);
        InputFilter filter= new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
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
    */
}


