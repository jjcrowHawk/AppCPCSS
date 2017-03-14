package com.example.personal.comunitarias.Denuncias;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.example.personal.comunitarias.BaseDeDatos.ciudad.Ciudad;
import com.example.personal.comunitarias.BaseDeDatos.institucion.Institucion;
import com.example.personal.comunitarias.BaseDeDatos.predenuncia.Predenuncia;
import com.example.personal.comunitarias.BaseDeDatos.provincia.Provincia;
import com.example.personal.comunitarias.BaseDeDatos.reclamo.Reclamo;
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
public class MostrarDatos extends Fragment implements AdapterView.OnItemSelectedListener {


    /******/
    private ViewPager viewPager;

    private View view;
    private TabsDenuncia tabs;
    Button guardar;
    EditText m_txtNombrePet, m_txtApellidoPet, txtIdent, txtCorreo, txtNombreDenunciado, txtApellidoDenunciado, txtDenuncia;
    String correo;
    String contraseña;
    Session session;
    String Nombre_P,Apellido_P,Mail_P,Identidad_P,Ocupacion_P,Estadocivil_P,provi_P,Ciudad_P,Nacio_p,Reside_p,Nivel_P,TipoIde_P,Genero_P,Reservada_p;
    String Descripciion_D,comparecer_d,hechos_d;
    String Nombre_DE, Apellido_DE,Cargo_DE,Unafectada_DE,Perdjudicada_DE,Genero_DE,Provincia_DE,CIudad_DE,Institucion_DE;

    public  MostrarDatos(){

    }

    public MostrarDatos(ViewPager viewPager) {
        this.viewPager = viewPager;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.denuncia_tab4_mostrar_dat,container,false);
        inicializar();
        deshabilarCampos();
        view.findViewById(R.id.anterior).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(2);
            }
        });


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
       m_txtNombrePet.setText(new Denunciado(viewPager).getNombre_D());
       m_txtApellidoPet.setText(Apellido_P);
       txtIdent.setText(Identidad_P);
       txtCorreo.setText(Mail_P);
       txtNombreDenunciado.setText(new Denunciado(viewPager).getNombre_D());
       txtApellidoDenunciado.setText(new Denunciado(viewPager).getApellido_D());

       guardar = (Button) view.findViewById(R.id.enviar);

       guardar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               obtenerinformacion();
               Guardar_Base();
           }
       });


    }

    public void deshabilarCampos(){
        m_txtNombrePet.setEnabled(false);
        m_txtApellidoPet.setEnabled(false);
        txtIdent.setEnabled(false);
        txtCorreo.setEnabled(false);
        txtNombreDenunciado.setEnabled(false);
        txtApellidoDenunciado.setEnabled(false);
        //txtDenuncia.setEnabled(false);
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
        reclamo.setCiudaddeldenunciadoid(1);
        reclamo.setCiudaddeldenuncianteid(1);
        reclamo.setComparecer(comparecer_d);
        reclamo.setDireccion("Samanes");
        reclamo.setDocumentores(hechos_d);
        reclamo.setEmail(Mail_P);
        reclamo.setResideextrangero("1");
        reclamo.setIdentidadreservada("1");
        reclamo.setNombresapellidosdenunciado(Nombre_DE);
        reclamo.setNombresapellidosdenunciante(Nombre_P);
        reclamo.setInstitucionimplicadaid(1);
        reclamo.setNumidenti(Identidad_P);
        reclamo.setProvinciadenunciadoid(1);
        reclamo.setProvinciadenuncianteid(1);
        reclamo.setTelefono("2164536");
        reclamo.setTipoidentificacion("Cedula");

        Predenuncia pd = new Predenuncia();
        pd.setTipodenuncia("1");
        pd.setDescripcioninvestigacion(Descripciion_D);
        pd.setFuncionariopublico("SOY UN FUNCIONARIO");
        pd.setGenerodenunciado("1");
        pd.setGenerodenunciante("1");
        pd.setNiveleducaciondenunciateid(1);
        pd.setOcupaciondenuncianteid(1);
        pd.setEstadocivildenuncianteid(1);
        pd.setInstitucionimplicadaid(1);
        pd.setNacionalidaddenuncianteid(1);



        reclamo.Guardar_Reclamo();
        boolean status_reclamo=reclamo.is_status();
        pd.guardarPredenuncia();
        boolean status_pred=pd.is_status();

        if(status_reclamo && status_pred){
            Log.d("myTag", "Si inserto");

            SendMail();

            new AlertDialog.Builder(getContext()).setMessage("Denuncia enviado con éxito")
                    .setTitle("Mensaje")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @TargetApi(11)
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
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
                        "<p>Sr(a) "+ Nombre_P + " "+Apellido_P +" su Denuncia ha sido Enviada Correctamente</p>" +
                        "<H3>Denuncia :</H3>" +
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
        Denuncia d = new Denuncia(viewPager);
        Peticionario p = new Peticionario(viewPager);
        Denunciado e = new Denunciado(viewPager);
        //Peticionario
        Nombre_P = p.getNombre();
        Apellido_P = p.getApellido();
        Mail_P= p.getEmail();
        Identidad_P=p.getIdentidad();
        Reservada_p=p.getIdentidadReservada();
        Ocupacion_P=p.getOcupacion();
        Estadocivil_P= p.getEstado_civil();
        provi_P= p.getProvi();
        Ciudad_P=p.getCiuda();
        Nacio_p=p.getNacio();
        Reside_p=p.getReside();
        Nivel_P=p.getNivelEdu();
        TipoIde_P=p.getTipoIden();
        Genero_P=p.getGenero();
        Log.d(" Clase Mostrar ",Nombre_P+"  "+Apellido_P+""+Ocupacion_P);

        //Denuncia
        comparecer_d = d.getComparecer_d();
        hechos_d = d.getHechos_d();
        Descripciion_D = d.getDescripcion_Denuncia();

       // Log.d(" Clase Mostrar ",Descripciion_D);

        //Denunciado
        Nombre_DE = e.getNombre_D();
        Apellido_DE = e.getApellido_D();
        Cargo_DE = e.getCargo_D();
        Log.d(" Clase Mostrar ",Nombre_DE+"  "+Apellido_DE+""+Cargo_DE);

    }
}
