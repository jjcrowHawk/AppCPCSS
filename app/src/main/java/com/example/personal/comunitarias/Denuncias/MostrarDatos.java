package com.example.personal.comunitarias.Denuncias;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
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
import com.example.personal.comunitarias.BaseDeDatos.estadocivil.Estadocivil;
import com.example.personal.comunitarias.BaseDeDatos.institucion.Institucion;
import com.example.personal.comunitarias.BaseDeDatos.nacionalidad.Nacionalidad;
import com.example.personal.comunitarias.BaseDeDatos.niveleducacion.Niveleducacion;
import com.example.personal.comunitarias.BaseDeDatos.ocupacion.Ocupacion;
import com.example.personal.comunitarias.BaseDeDatos.predenuncia.Predenuncia;
import com.example.personal.comunitarias.BaseDeDatos.provincia.Provincia;
import com.example.personal.comunitarias.BaseDeDatos.reclamo.Reclamo;
import com.example.personal.comunitarias.DatabaseRemote.Conexion;
import com.example.personal.comunitarias.Menu;
import com.example.personal.comunitarias.R;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    static EditText m_txtNombrePet;
    static EditText m_txtApellidoPet;
    static EditText txtIdent;
    static EditText txtCorreo;
    static EditText txtNombreDenunciado;
    static EditText txtApellidoDenunciado;
    static EditText txtDenuncia;
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
    static String Descripciion_D;
    int comparecer_d,hechos_d;
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
    int reservada, reside;
    String gen = "";
    String Direccion_p,edad_p,cargo_p,telefono_p,NombreApellido_P,NombreApellido_D;


    ProgressDialog mProgressDialog;
    Predenuncia pd;
    Reclamo reclamo;

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
       txtDenuncia = (EditText)view.findViewById(R.id.txt_Descripcion_De);
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

    public void deshabilarCampos(){
        m_txtNombrePet.setEnabled(false);
        m_txtApellidoPet.setEnabled(false);
        txtIdent.setEnabled(false);
        txtCorreo.setEnabled(false);
        txtNombreDenunciado.setEnabled(false);
        txtApellidoDenunciado.setEnabled(false);
        txtDenuncia.setEnabled(false);
    }

    public static void setearDatos(){
        Nombre_P = Peticionario.getNombre();
        Apellido_P = Peticionario.getApellido();
        Mail_P= Peticionario.getEmail();
        Identidad_P=Peticionario.getIdentidad();

        Nombre_DE = Denunciado.getNombre_D();
        Apellido_DE = Denunciado.getApellido_D();

        Descripciion_D = Denuncia.getDescripcion_Denuncia();

        m_txtNombrePet.setText(Nombre_P);
        m_txtApellidoPet.setText(Apellido_P);
        txtIdent.setText(Identidad_P);
        txtCorreo.setText(Mail_P);
        txtNombreDenunciado.setText(Nombre_DE);
        txtApellidoDenunciado.setText(Apellido_DE);
        txtDenuncia.setText(Descripciion_D);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void Guardar_Base(){

        reclamo = new Reclamo();
        reclamo.setCargo(cargo_p);
        reclamo.setCiudaddeldenunciadoid(idCiuDE);
        reclamo.setCiudaddeldenuncianteid(idCiuP);
        reclamo.setComparecer(comparecer_d);
        reclamo.setDireccion(Direccion_p);
        reclamo.setDocumentores((Denuncia.evidencia ==null ? 0:1));
        reclamo.setEmail(Mail_P);
        reclamo.setResideextrangero(reside);
        reclamo.setIdentidadreservada(reservada);
        reclamo.setNombresapellidosdenunciado(NombreApellido_D);
        reclamo.setNombresapellidosdenunciante(NombreApellido_P);
        reclamo.setInstitucionimplicadaid(idIndti);
        reclamo.setNumidenti(Identidad_P);
        reclamo.setProvinciadenunciadoid(idProvDE);
        reclamo.setProvinciadenuncianteid(idProvp);
        reclamo.setTelefono(telefono_p);
        reclamo.setTipoidentificacion(TipoIde_P);
        reclamo.setPedidoDenuncia("Denuncia");

        pd = new Predenuncia();
        pd.setTipodenuncia("Predenuncia");
        pd.setDescripcioninvestigacion(Descripciion_D);
        pd.setFuncionariopublico(""); //no se sabe
        pd.setGenerodenunciado(Genero_DE);
        pd.setGenerodenunciante(Genero_P);
        pd.setNiveleducaciondenunciateid(idNivelEduca);
        pd.setOcupaciondenuncianteid(idocupacionP);
        pd.setEstadocivildenuncianteid(idestado);
        pd.setInstitucionimplicadaid(idIndti);
        pd.setNacionalidaddenuncianteid(idNacionalidad);
        pd.setUnidaddireccionafectada(Unafectada_DE);
        pd.setEvidencia(Denuncia.evidencia);

        new Progress_guardando().execute();

    }

    public void SendMail(){
                       /* MAIL+*/

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
                        ""+Descripciion_D+"" +
                        "<H3> Denunciado </H3>" +
                        ""+NombreApellido_D+"";
                messageBodyPart.setContent(htmlText, "text/html");
                // add it
                multipart.addBodyPart(messageBodyPart);


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
        NombreApellido_P = Nombre_P +" " + Apellido_P;
        Mail_P= p.getEmail();
        Identidad_P=p.getIdentidad();
        Reservada_p=p.getIdentidadReservada();
        Direccion_p = p.getDireccion();
        edad_p = p.getEdad();
        cargo_p = p.getCargo();

        if(Reservada_p.equals("SI")){
            reservada = 1;

        }else{
            reservada = 0;
        }
        //Ocupacion_P=p.getOcupacion();
        Estadocivil_P= p.getEstado_civil();
        provi_P= p.getProvi();
        Ciudad_P=p.getCiuda();
        Nacio_p=p.getNacio();
        Reside_p=p.getReside();
        telefono_p = p.getTelefono();

        if(Reside_p.equals("SI")){
            reside = 1;

        }else{
            reside = 0;
        }
        Nivel_P=p.getNivelEdu();
        TipoIde_P=p.getTipoIden();
        Genero_P=p.getGenero();

        //id
        idProvp = p.getIdProvp();
        idCiuP = p.getIdCiuP();
        idocupacionP = p.getIdocupacionP();
        idNivelEduca = p.getIdNivelEduca();
        idestado = p.getIdestado();
        idNacionalidad = p.getIdNacionalidad();




        Log.d("ID PETICIONARIO","IdProvinvia"+idProvp+"  idCiudad "+idCiuP+"  IdNivelEducacion "+idNivelEduca+"   idEstado"+idestado+"  idOcupacion"+idocupacionP+"  idNacinalidad"+ idNacionalidad);

        //Denuncia
        if(d.getComparecer_d().equals("SI")){
            comparecer_d = 1;
        }else{
            comparecer_d = 0;
        }

        Descripciion_D = d.getDescripcion_Denuncia();

       // Log.d(" Clase Mostrar ",Descripciion_D);

        //Denunciado
        Nombre_DE = e.getNombre_D();
        Apellido_DE = e.getApellido_D();
        NombreApellido_D = Nombre_DE +" "+ Apellido_DE;
        Cargo_DE = e.getCargo_D();
        Provincia_DE = e.getProvincia_d();
        CIudad_DE = e.getCIudad_d();
        Institucion_DE = e.getCIudad_d();
        Genero_DE = e.getGenero_d();
        idProvDE = e.getIdProvDE();
        idCiuDE = e.getIdCiuDE();
        idIndti = e.getIdIndti();
        Unafectada_DE = e.getUnafectada();

        Log.d("ID Denunciado ","Genero_D"+Genero_DE+"  Genere_P "+gen);

    }

    public class Progress_guardando extends AsyncTask<Void, Void, Void> {
        Connection conn;
        boolean status_reclamo,status_pred ;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MostrarDatos.this.getContext());
            mProgressDialog.setMessage("Guardando su denuncia...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }


        @Override
        protected Void doInBackground(Void... params) {
            reclamo.Guardar_ReclamoWS();
            status_reclamo=reclamo.is_status();

            pd.setIdpredenuncia(reclamo.getIdreclamo());
            pd.guardarPredenunciaWS();
            status_pred=pd.is_status();

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

            if(status_reclamo && status_pred){
                Log.d("myTag", "Si inserto");

                SendMail();

                new AlertDialog.Builder(MostrarDatos.this.getContext()).setMessage("Denuncia enviado con éxito")
                        .setTitle("Mensaje")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @TargetApi(11)
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                Intent i=new Intent(getContext(),Menu.class);
                                startActivity(i);

                            }
                        }).show();


            }else {
                new AlertDialog.Builder(MostrarDatos.this.getContext()).setMessage("Existe problema con la conexión.\n Por favor, Intente nuevamente")
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
