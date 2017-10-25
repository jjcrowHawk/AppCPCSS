package com.example.personal.comunitarias.Pedidos;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.Button;
import android.widget.EditText;

import com.example.personal.comunitarias.AntiCorrupcionActivity;
import com.example.personal.comunitarias.BaseDeDatos.pais.Pais;
import com.example.personal.comunitarias.BaseDeDatos.predenuncia.Predenuncia;
import com.example.personal.comunitarias.BaseDeDatos.reclamo.Reclamo;
import com.example.personal.comunitarias.BaseDeDatos.requerimiento.Requerimiento;
import com.example.personal.comunitarias.Denuncias.Denuncia;
import com.example.personal.comunitarias.R;

import java.sql.Connection;
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


/**
 * Created by PC-JANINA on 26/2/2017.
 */

public class MostrarDatosPedido extends Fragment implements AdapterView.OnItemSelectedListener {


    /******/
    private ViewPager viewPager;
    private View view;
    private TabsPedido tabs;
    Button guardar;
    static EditText m_txtNombrePet;
    static EditText m_txtApellidoPet;
    static EditText txtIdent;
    static EditText txtCorreo;
    static EditText txtNombreDenunciado;
    static EditText txtApellidoDenunciado;
    static EditText txtPedido;
    String correo;
    String contraseña;
    Session session;
    static String Nombre_P;
    static String Apellido_P;
    static String Mail_P;
    static String Identidad_P;
    String Ocupacion_P;
    String Estadocivil_P;
    String provi_P;
    String Ciudad_P;
    String Nivel_P;
    String TipoIde_P;
    String Genero_P;
    String Reservada_p;
    String Celular_P;
    String Institucion_P;
    String Pais_P;
    static String Descripciion_D;
    int comparecer_d,hechos_d,Documentos_D,reside;
    int idEtniaP;
    static String Nombre_DE;
    static String Apellido_DE;
    String Cargo_DE;
    String Unafectada_DE;
    String Perdjudicada_DE;
    String Genero_DE;
    String Provincia_DE;
    String CIudad_DE;
    String Institucion_DE;
    Integer idCiuDE, idCiuP , idProvDE, idProvp,idIndti,idocupacionP,idNivelEduca,idestado,idNacionalidad;
    String reservada = "";
    String gen = "";
    String Direccion_p,edad_p,cargo_p,telefono_p,NombreApellido_P,NombreApellido_D;

    //
    ProgressDialog mProgressDialog;
    Requerimiento req;

    public MostrarDatosPedido(ViewPager viewPager) {

        this.viewPager = viewPager;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.pedido_tab4_mostrar_dat,container,false);
        inicializar();
        deshabilarCamposPedidos();
        view.findViewById(R.id.anteriorMostrar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(2);

            }
        });

        return  view;
    }

    public void deshabilarCamposPedidos(){
        m_txtNombrePet.setEnabled(false);
        m_txtApellidoPet.setEnabled(false);
        txtIdent.setEnabled(false);
        txtCorreo.setEnabled(false);
        txtNombreDenunciado.setEnabled(false);
        txtApellidoDenunciado.setEnabled(false);
        txtPedido.setEnabled(false);
    }

    public void inicializar() {
        m_txtNombrePet= (EditText)view.findViewById(R.id.m_txt_Nombres);
        m_txtApellidoPet= (EditText)view.findViewById(R.id.m_txt_Apellidos);
        txtIdent  = (EditText)view.findViewById(R.id.txt_tipoIdentificacion);
        txtCorreo = (EditText)view.findViewById(R.id.txt_correo);
        txtNombreDenunciado = (EditText)view.findViewById(R.id.txt_nombreDenunciado);
        txtApellidoDenunciado = (EditText)view.findViewById(R.id.txt_ApellidosDenunciado);
        txtPedido = (EditText)view.findViewById(R.id.txt_Descripcion_D);
        correo ="prueba.envio.formulario@gmail.com";
        contraseña="espol1234";

        obtenerinformacion();

        guardar = (Button) view.findViewById(R.id.enviar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerinformacion();
                Guardar_Base();
            }
        });


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void Guardar_Base(){

        req= new Requerimiento();
        req.setTipoDenuncia(1);
        req.setIdentidadReservada(false);
        req.setNombresDenunciante(NombreApellido_P);
        req.setEdadDenunciante(edad_p);
        req.setCorreoDenunciante(Mail_P);
        req.setTelefonoDenunciante(telefono_p);
        req.setCelularDenunciante(Celular_P);
        req.setDireccionDenunciante(Direccion_p);
        req.setInstitucionDenunciante(Institucion_P);
        req.setCargoDenunciante(cargo_p);
        req.setTipoIdentificacion(TipoIde_P);
        req.setIdentificacionID(Identidad_P);
        req.setPais(Pais_P);
        req.setDescripcionInvestigación(Descripciion_D);
        req.setProvinciaDenunciante(idProvp);
        req.setCiudadDenunciante(idCiuP);
        req.setGeneroDenunciante(Integer.parseInt(Genero_P));
        req.setEstadoDenunciante(idestado);
        req.setEtniaDenunciante(idEtniaP);
        req.setEducacionDenunciante(idNivelEduca);

        req.setProvinciaDenunciado(idProvDE);
        req.setCiudadDenunciado(idCiuDE);
        req.setGeneroDenunciado(Integer.parseInt(Genero_DE));
        req.setInstitucionDenunciado(Institucion_DE);
        req.setCargoDenunciado(Cargo_DE);
        req.setEvidencia(Pedido.evidencia);
        req.setParroquiaDenunciado("XXXXX");
        new Progress_guardando().execute();


    }



    public void SendMail(){
                       /* MAIL+++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

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

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(correo));
                message.setSubject("Confirmaciòn Envio De Formulario");
                message.setRecipients(Message.RecipientType.TO , InternetAddress.parse(Mail_P));


                MimeMultipart multipart = new MimeMultipart("related");

                BodyPart messageBodyPart = new MimeBodyPart();
                String htmlText = "<H1>Envio Exitoso</H1>" +
                        "<p>Sr(a) "+ Nombre_P + " "+Apellido_P +" su Peticion ha sido Enviada Correctamente</p>" +
                        "<H3>Petición :</H3>" +
                        ""+Descripciion_D+"";
                messageBodyPart.setContent(htmlText, "text/html");
                // add it
                multipart.addBodyPart(messageBodyPart);

                // second part (the image)
                       /* messageBodyPart = new MimeBodyPart();
                        Uri fileUri = Uri.parse("android.resource://com.example.personal.comunitarias/" + R.drawable.fondo2);
                        String path1 = fileUri.getPath();
                        DataSource fds = new FileDataSource(
                               path1);
                        messageBodyPart.setDataHandler(new DataHandler(fds));

                        messageBodyPart.setHeader("Content-ID", "<image>");

                        // add image to the multipart*/

                //multipart.addBodyPart(messageBodyPart);

                // put everything together
                message.setContent(multipart);
                Transport.send(message);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void obtenerinformacion(){
        Pedido d = new Pedido(viewPager);
        Peticionario_PE p = new Peticionario_PE(viewPager);
        DatosEntidad e = new DatosEntidad(viewPager);
        //Peticionario
        Nombre_P = p.getNombre();
        Apellido_P = p.getApellido();
        NombreApellido_P = Nombre_P +" "+ Apellido_P;
        Mail_P= p.getEmail();
        Identidad_P=p.getIdentidad();
        Direccion_p = p.getDireccion();
        edad_p = p.getEdad();
        cargo_p = p.getCargo_Pet();

        Estadocivil_P= p.getEstado_civil();
        provi_P= p.getProvi();
        Ciudad_P=p.getCiuda();
        telefono_p = p.getTelefono();
        Celular_P= p.getCelular();
        Institucion_P = p.getOrga_Pet();

        Nivel_P=p.getNivelEdu();
        TipoIde_P=p.getTipoIden();
        Pais_P = p.getPais();

        if(p.getGenero().equals("MASCULINO")) {
            Genero_P = "1";
        }
        else if(p.getGenero().equals("FEMENINO")){
            Genero_P = "2";
        }
        else{
            Genero_P = "3";
        }

        idProvp = p.getIdProvp();
        idCiuP = p.getIdCiuP();
        idNivelEduca = p.getIdNivelEduca();
        idestado = p.getIdestado();
        idEtniaP = p.getIdEtniaP();

        Log.d(" Clase Mostrar ",Nombre_P+"  "+Apellido_P+""+Ocupacion_P);

        //Denuncia
        if(d.getComparecer_d().equals("SI")){
            comparecer_d = 1;

        }else{
            comparecer_d = 0;
        }

        Descripciion_D = d.getDescripcion_Pedido();
        //Documentos_D = d.getDoc();


        Log.d(" Clase Mostrar ",Descripciion_D);

        //Denunciado
        Nombre_DE = e.getNombre_D();
        Apellido_DE = e.getApellido_D();
        NombreApellido_D = Nombre_DE +" "+ Apellido_DE;
        Cargo_DE = e.getCargo_D();
        Institucion_DE = e.getInstitucion_d();

        if(e.getGenero_d()!=null && e.getGenero_d().equals("MASCULINO")) {
            Genero_DE = "1";
        }
        else if(e.getGenero_d()!=null && e.getGenero_d().equals("FEMENINO")){
            Genero_DE = "2";
        }
        else{
            Genero_DE = "3";
        }

        idProvDE = e.getIdProvDE();
        idCiuDE = e.getIdCiuDE();
        idIndti = e.getIdIndti();
        Log.d(" Clase Mostrar ",Nombre_DE+"  "+Apellido_DE+""+Cargo_DE);

    }

    public static  void setearDatos(){
        Nombre_P = Peticionario_PE.getNombre();
        Apellido_P = Peticionario_PE.getApellido();
        Mail_P= Peticionario_PE.getEmail();
        Identidad_P=Peticionario_PE.getIdentidad();
        Descripciion_D = Pedido.getDescripcion_Pedido();

        Nombre_DE = DatosEntidad.getNombre_D();
        Apellido_DE = DatosEntidad.getApellido_D();

        m_txtNombrePet.setText(Nombre_P);
        m_txtApellidoPet.setText(Apellido_P);
        txtIdent.setText(Identidad_P);
        txtCorreo.setText(Mail_P);
        txtNombreDenunciado.setText(Nombre_DE);
        txtApellidoDenunciado.setText(Apellido_DE);
        txtPedido.setText(Descripciion_D);


    }

    public class Progress_guardando extends AsyncTask<Void, Void, Void> {
        Connection conn;
        boolean status_req ;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MostrarDatosPedido.this.getContext());
            mProgressDialog.setMessage("Guardando su pedido...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();
        }


        @Override
        protected Void doInBackground(Void... params) {
            req.guardarRequerimientoWS();
            status_req = req.getStatus();

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

            if(status_req){
                Log.d("myTag", "Si inserto");

                SendMail();

                new AlertDialog.Builder(MostrarDatosPedido.this.getContext()).setMessage("Pedido enviado con éxito")
                        .setTitle("Mensaje")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @TargetApi(11)
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                Intent i=new Intent(getContext(), AntiCorrupcionActivity.class);
                                startActivity(i);

                            }
                        }).show();


            }else {
                new AlertDialog.Builder(MostrarDatosPedido.this.getContext()).setMessage("Existe problema con la conexión.\n Por favor, Intente nuevamente")
                        .setTitle("Conexión fallida")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @TargetApi(11)
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();
            }
        }
    }

}