package com.example.personal.comunitarias.Pedidos;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.example.personal.comunitarias.BaseDeDatos.predenuncia.Predenuncia;
import com.example.personal.comunitarias.BaseDeDatos.reclamo.Reclamo;
import com.example.personal.comunitarias.Denuncias.*;
import com.example.personal.comunitarias.Denuncias.Peticionario;
import com.example.personal.comunitarias.Menu;
import com.example.personal.comunitarias.R;

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
    EditText txtDenuncia;
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
    String Nacio_p;
    String Reside_p;
    String Nivel_P;
    String TipoIde_P;
    String Genero_P;
    String Reservada_p;
    String Descripciion_D,comparecer_d,hechos_d,Documentos_D;
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
    String reside = "";
    String gen = "";

    public MostrarDatosPedido(ViewPager viewPager) {

        this.viewPager = viewPager;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.pedido_tab4_mostrar_dat,container,false);
        inicializar();
        /*view.findViewById(R.id.btn_anterior).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(1);

            }
        });*/

        return  view;
    }
    public void inicializar() {
        m_txtNombrePet= (EditText)view.findViewById(R.id.m_txt_Nombres);
        m_txtApellidoPet= (EditText)view.findViewById(R.id.m_txt_Apellidos);
        txtIdent  = (EditText)view.findViewById(R.id.txt_tipoIdentificacion);
        txtCorreo = (EditText)view.findViewById(R.id.txt_correo);
        txtNombreDenunciado = (EditText)view.findViewById(R.id.txt_nombreDenunciado);
        txtApellidoDenunciado = (EditText)view.findViewById(R.id.txt_ApellidosDenunciado);
        //txtDenuncia = (EditText)view.findViewById(R.id.m_txt_descripcion);
        correo ="prueba.envio.formulario@gmail.com";
        contraseña="espol1234";

        obtenerinformacion();
        /*m_txtNombrePet.setText(Nombre_P);
        m_txtApellidoPet.setText(Apellido_P);
        txtIdent.setText(Identidad_P);
        txtCorreo.setText(Mail_P);
        txtNombreDenunciado.setText(Nombre_DE);
        txtApellidoDenunciado.setText(Apellido_DE);
        */


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

// falta llenar la base
        /*Provincia IdPr = new Provincia();
        int IdObtenido = IdPr.getID_DB(provi_P);
        int IdObtenido_Denunciado = IdPr.getID_DB(Provincia_d);
        Ciudad c = new Ciudad();
        int IdCiudad = c.getID_DB(Ciudad_P);
        int IdCiudad_denucniado = c.getID_DB(CIudad_d);
        Institucion I = new Institucion();
        int Id_Institucion = I.getID_DB(Institucion_d);*/


        Reclamo reclamo = new Reclamo();
        reclamo.setCargo(Ocupacion_P);
        reclamo.setCiudaddeldenunciadoid(idCiuDE);
        reclamo.setCiudaddeldenuncianteid(idCiuP);
        reclamo.setComparecer(comparecer_d);
        reclamo.setDireccion("");
        reclamo.setDocumentores(hechos_d);
        reclamo.setEmail(Mail_P);
        reclamo.setResideextrangero(reside);
        reclamo.setIdentidadreservada(reservada);
        reclamo.setNombresapellidosdenunciado("");
        reclamo.setNombresapellidosdenunciante(Nombre_P);
        reclamo.setInstitucionimplicadaid(idIndti);
        reclamo.setNumidenti(Identidad_P);
        reclamo.setProvinciadenunciadoid(idProvDE);
        reclamo.setProvinciadenuncianteid(idProvp);
        reclamo.setTelefono("");
        reclamo.setTipoidentificacion(TipoIde_P);

        Predenuncia pd = new Predenuncia();
        pd.setTipodenuncia("1");
        pd.setDescripcioninvestigacion(Descripciion_D);
        pd.setFuncionariopublico("");
        pd.setGenerodenunciado(Genero_DE);
        pd.setGenerodenunciante(Genero_P);
        pd.setNiveleducaciondenunciateid(idNivelEduca);
        pd.setOcupaciondenuncianteid(1);
        pd.setEstadocivildenuncianteid(idestado);
        pd.setInstitucionimplicadaid(idIndti);
        pd.setNacionalidaddenuncianteid(idNacionalidad);




        reclamo.Guardar_Reclamo();
        pd.guardarPredenuncia();

        if(reclamo.is_status() && pd.is_status()){
            Log.d("myTag", "Si inserto");

            SendMail();

            new AlertDialog.Builder(getContext()).setMessage("Pedido enviado con éxito")
                    .setTitle("Mensaje")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @TargetApi(11)
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            Intent i=new Intent(getContext(),Menu.class);
                            startActivity(i);
                        }
                    }).show();
        }else{
            new AlertDialog.Builder(getContext()).setMessage("Existe problema con la conexión.\n Por favor, Intente nuevamente")
                    .setTitle("Conexión fallida")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @TargetApi(11)
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    }).show();
        }




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
        Mail_P= p.getEmail();
        Identidad_P=p.getIdentidad();
        //Reservada_p=p.getIdentidadReservada();

        if(Reservada_p=="Si"){
            reservada = "1";

        }else{
            reservada = "0";
        }
        //Ocupacion_P=p.getOcupacion();
        Estadocivil_P= p.getEstado_civil();
        provi_P= p.getProvi();
        Ciudad_P=p.getCiuda();
        Nacio_p=p.getNacio();
        Reside_p=p.getReside();

        if(Reside_p=="Si"){
            reside = "1";

        }else{
            reside = "0";
        }
        Nivel_P=p.getNivelEdu();
        TipoIde_P=p.getTipoIden();
        Genero_P=p.getGenero();
        if(Genero_P=="Masculino"){
            gen = "1";

        }else{
            gen = "0";
        }
        //id

        idProvp = p.getIdProvp();
        idCiuP = p.getIdCiuP();
        idocupacionP = p.getIdocupacionP();
        idNivelEduca = p.getIdNivelEduca();
        idestado = p.getIdestado();
        idNacionalidad = p.getIdNacionalidad();

        Log.d(" Clase Mostrar ",Nombre_P+"  "+Apellido_P+""+Ocupacion_P);

        //Denuncia
        comparecer_d = d.getComparecer_d();

        Descripciion_D = d.getDescripcion_Pedido();
        Documentos_D = d.getDoc();


        Log.d(" Clase Mostrar ",Descripciion_D);

        //Denunciado
        Nombre_DE = e.getNombre_D();
        Apellido_DE = e.getApellido_D();
        Cargo_DE = e.getCargo_D();
        Genero_DE = e.getGenero_d();

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

        Nombre_DE = DatosEntidad.getNombre_D();
        Apellido_DE = DatosEntidad.getApellido_D();

        m_txtNombrePet.setText(Nombre_P);
        m_txtApellidoPet.setText(Apellido_P);
        txtIdent.setText(Identidad_P);
        txtCorreo.setText(Mail_P);
        txtNombreDenunciado.setText(Nombre_DE);
        txtApellidoDenunciado.setText(Apellido_DE);


    }

}