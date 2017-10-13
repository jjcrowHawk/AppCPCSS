package com.example.personal.comunitarias;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.personal.comunitarias.Contactenos.Contacto;
import com.example.personal.comunitarias.Facebook.FacebookActivity;
import com.example.personal.comunitarias.Facebook.IntroFacebook;
import com.example.personal.comunitarias.Noticias.Intro_noticias;
import com.example.personal.comunitarias.Noticias.PrimaryReader;
import com.example.personal.comunitarias.ParticipacionCiudadana.ParticipacionCiudadanaActivity;
import com.example.personal.comunitarias.Twitter.IntroTweets;
import com.example.personal.comunitarias.Youtube.IntroVideos;

public class MenuSecundario extends AppCompatActivity implements  SeccionFragment.OnFragmentInteractionListener,NoticiasFragment.OnFragmentInteractionListener,ContactoFragment.OnFragmentInteractionListener{

    ImageButton participacionButton,controlButton,transparenciaButton,luchaButton,noticiasButton,contactoButton,facebookButton,twitterButton,youtubeButton;
    TextView tituloTextView;
    LinearLayout container;
    FragmentTransaction transaction;
    SeccionFragment pcFragment,csFragment,tpFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_secundario);

        participacionButton = (ImageButton) findViewById(R.id.participacionButton);
        controlButton = (ImageButton) findViewById(R.id.controlSocialButton);
        transparenciaButton = (ImageButton) findViewById(R.id.transparenciaButton);
        luchaButton = (ImageButton) findViewById(R.id.denunciasButton);
        noticiasButton = (ImageButton) findViewById(R.id.noticiaButton);
        contactoButton = (ImageButton) findViewById(R.id.contactoButton);
        facebookButton = (ImageButton) findViewById(R.id.facebookButton);
        twitterButton = (ImageButton) findViewById(R.id.twitterButton);
        youtubeButton = (ImageButton) findViewById(R.id.youtubeButton);
        tituloTextView = (TextView) findViewById(R.id.tituloTextView);
        container = (LinearLayout) findViewById(R.id.containerView);
        setearAcciones();
        transaction = getSupportFragmentManager().beginTransaction();

        String seleccionInicial= getIntent().getStringExtra("WINDOW");
        Bundle seccionSolicitada;
        SeccionFragment myf;
        switch(seleccionInicial){
            case "PC":

                tituloTextView.setText("\tParticipacion Ciudadana");
                cambiarImagen("PC");
                if(isOnlineNet()){
                seccionSolicitada =new Bundle();
                seccionSolicitada.putString("seccion","PC");
                myf = new SeccionFragment();
                myf.setArguments(seccionSolicitada);
                transaction.add(R.id.containerView, myf,"");
                transaction.commit();
                }
                else{
                    Toast.makeText(MenuSecundario.this,"No tiene conexion a internet, conéctese para poder acceder a esta opcion",Toast.LENGTH_LONG).show();
                }
                break;
            case "CS":

                tituloTextView.setText("\tControl Social");
                cambiarImagen("CS");
                if(isOnlineNet()) {
                    seccionSolicitada = new Bundle();
                    seccionSolicitada.putString("seccion", "CS");
                    myf = new SeccionFragment();
                    myf.setArguments(seccionSolicitada);
                    transaction.add(R.id.containerView, myf, "");
                    transaction.commit();
                }
                else{
                    Toast.makeText(MenuSecundario.this,"No tiene conexion a internet, conéctese para poder acceder a esta opcion",Toast.LENGTH_LONG).show();
                }
                break;
            case "TP":
                tituloTextView.setText("\tTransparencia");
                cambiarImagen("TP");
                if(isOnlineNet()) {
                    seccionSolicitada = new Bundle();
                    seccionSolicitada.putString("seccion", "TP");
                    myf = new SeccionFragment();
                    myf.setArguments(seccionSolicitada);
                    transaction.add(R.id.containerView, myf, "");
                    transaction.commit();
                }
                else{
                    Toast.makeText(MenuSecundario.this,"No tiene conexion a internet, conéctese para poder acceder a esta opcion",Toast.LENGTH_LONG).show();
                }
                break;
            case "NT":
                tituloTextView.setText("\tNoticias");
                cambiarImagen("NT");
                if(isOnlineNet()) {
                    new PrimaryReader(MenuSecundario.this, NoticiasFragment.noticias, false).execute("noticias", "1");
                    NoticiasFragment nf = new NoticiasFragment();
                    transaction.add(R.id.containerView, nf);
                    transaction.commit();
                }
                else{
                    Toast.makeText(MenuSecundario.this,"No tiene conexion a internet, conéctese para poder acceder a esta opcion",Toast.LENGTH_LONG).show();
                }
                break;
            case "CON":
                tituloTextView.setText("\tContacto");
                cambiarImagen("CON");
                if(isOnlineNet()) {
                    ContactoFragment cf = new ContactoFragment();
                    transaction.add(R.id.containerView, cf);
                    transaction.commit();
                }
                else{
                    Toast.makeText(MenuSecundario.this,"No tiene conexion a internet, conéctese para poder acceder a esta opcion",Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    public void setearAcciones(){

        participacionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                tituloTextView.setText("Participación Ciudadana");
                cambiarImagen("PC");
                if(isOnlineNet()) {
                    Bundle seccionSolicitada = new Bundle();
                    seccionSolicitada.putString("seccion", "PC");
                    SeccionFragment myf = new SeccionFragment();
                    myf.setArguments(seccionSolicitada);
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.containerView, myf, "");
                    transaction.commit();
                }
                else{
                    Toast.makeText(MenuSecundario.this,"No tiene conexion a internet, conéctese para poder acceder a esta opcion",Toast.LENGTH_LONG).show();
                }
            }
        });

        controlButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                tituloTextView.setText("\tControl Social");
                cambiarImagen("CS");
                if(isOnlineNet()) {
                    Bundle seccionSolicitada = new Bundle();
                    seccionSolicitada.putString("seccion", "CS");
                    SeccionFragment myf = new SeccionFragment();
                    myf.setArguments(seccionSolicitada);
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.containerView, myf);
                    transaction.commit();
                }
                else{
                    Toast.makeText(MenuSecundario.this,"No tiene conexion a internet, conéctese para poder acceder a esta opcion",Toast.LENGTH_LONG).show();
                }
            }
        });

        transparenciaButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                tituloTextView.setText("\tTransparencia");
                cambiarImagen("TP");
                if(isOnlineNet()) {
                    Bundle seccionSolicitada = new Bundle();
                    seccionSolicitada.putString("seccion", "TP");
                    SeccionFragment myf = new SeccionFragment();
                    myf.setArguments(seccionSolicitada);
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.containerView, myf);
                    transaction.commit();
                }
                else{
                    Toast.makeText(MenuSecundario.this,"No tiene conexion a internet, conéctese para poder acceder a esta opcion",Toast.LENGTH_LONG).show();
                }
            }
        });

        luchaButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                tituloTextView.setText("\tLucha contra la Corrupción");
                cambiarImagen("LC");
                limpiarContainer();
                Intent i= new Intent(getBaseContext(),AntiCorrupcionActivity.class);
                startActivity(i);

            }
        });

        noticiasButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                tituloTextView.setText("\tNoticias");
                cambiarImagen("NT");
                if(isOnlineNet()) {
                    new PrimaryReader(MenuSecundario.this, NoticiasFragment.noticias, false).execute("noticias", "1");
                    NoticiasFragment nf = new NoticiasFragment();
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.containerView, nf);
                    transaction.commit();
                }
                else{
                    Toast.makeText(MenuSecundario.this,"No tiene conexion a internet, conéctese para poder acceder a esta opcion",Toast.LENGTH_LONG).show();
                }
            }
        });

        contactoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                tituloTextView.setText("\tContacto");
                cambiarImagen("CON");
                if(isOnlineNet()) {
                    ContactoFragment cf = new ContactoFragment();
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.containerView, cf);
                    transaction.commit();
                }
                else{
                    Toast.makeText(MenuSecundario.this,"No tiene conexion a internet, conéctese para poder acceder a esta opcion",Toast.LENGTH_LONG).show();
                }
            }
        });

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOnlineNet()) {
                    tituloTextView.setText("");
                    cambiarImagen("");
                    Intent i = new Intent(getBaseContext(), IntroFacebook.class);
                    limpiarContainer();
                    startActivity(i);
                }
                else{
                    Toast.makeText(MenuSecundario.this,"No tiene conexion a internet, conéctese para poder acceder a esta opcion",Toast.LENGTH_LONG).show();
                }
            }
        });

        twitterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOnlineNet()) {
                    tituloTextView.setText("");
                    cambiarImagen("");
                    limpiarContainer();
                    Intent i = new Intent(getBaseContext(), IntroTweets.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(MenuSecundario.this,"No tiene conexion a internet, conéctese para poder acceder a esta opcion",Toast.LENGTH_LONG).show();
                }
            }
        });

        youtubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOnlineNet()) {
                    tituloTextView.setText("");
                    cambiarImagen("");
                    limpiarContainer();
                    Intent i = new Intent(getBaseContext(), IntroVideos.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(MenuSecundario.this,"No tiene conexion a internet, conéctese para poder acceder a esta opcion",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void cambiarImagen(String boton){
        switch(boton){
            case "PC":
                participacionButton.setImageResource(R.drawable.img_asset_50hdpi);
                controlButton.setImageResource(R.drawable.img_asset_52hdpi);
                transparenciaButton.setImageResource(R.drawable.img_asset_53hdpi);
                luchaButton.setImageResource(R.drawable.img_asset_54hdpi);
                noticiasButton.getDrawable().setAlpha(255);
                contactoButton.getDrawable().setAlpha(255);
                break;
            case "CS":
                participacionButton.setImageResource(R.drawable.img_asset_51hdpi);
                controlButton.setImageResource(R.drawable.img_asset_34hdpi);
                transparenciaButton.setImageResource(R.drawable.img_asset_53hdpi);
                luchaButton.setImageResource(R.drawable.img_asset_54hdpi);
                noticiasButton.getDrawable().setAlpha(255);
                contactoButton.getDrawable().setAlpha(255);
                break;
            case "TP":
                participacionButton.setImageResource(R.drawable.img_asset_51hdpi);
                controlButton.setImageResource(R.drawable.img_asset_52hdpi);
                transparenciaButton.setImageResource(R.drawable.img_asset_35hdpi);
                luchaButton.setImageResource(R.drawable.img_asset_54hdpi);
                noticiasButton.getDrawable().setAlpha(255);
                contactoButton.getDrawable().setAlpha(255);
                break;
            case "LC":
                participacionButton.setImageResource(R.drawable.img_asset_51hdpi);
                controlButton.setImageResource(R.drawable.img_asset_52hdpi);
                transparenciaButton.setImageResource(R.drawable.img_asset_53hdpi);
                luchaButton.setImageResource(R.drawable.img_asset_36hdpi);
                noticiasButton.getDrawable().setAlpha(255);
                contactoButton.getDrawable().setAlpha(255);
                break;
            case "NT":
                participacionButton.setImageResource(R.drawable.img_asset_51hdpi);
                controlButton.setImageResource(R.drawable.img_asset_52hdpi);
                transparenciaButton.setImageResource(R.drawable.img_asset_53hdpi);
                luchaButton.setImageResource(R.drawable.img_asset_54hdpi);
                noticiasButton.getDrawable().setAlpha(85);
                contactoButton.getDrawable().setAlpha(255);
                break;
            case "CON":
                participacionButton.setImageResource(R.drawable.img_asset_51hdpi);
                controlButton.setImageResource(R.drawable.img_asset_52hdpi);
                transparenciaButton.setImageResource(R.drawable.img_asset_53hdpi);
                luchaButton.setImageResource(R.drawable.img_asset_54hdpi);
                noticiasButton.getDrawable().setAlpha(255);
                contactoButton.getDrawable().setAlpha(85);
                break;
            default:
                participacionButton.setImageResource(R.drawable.img_asset_51hdpi);
                controlButton.setImageResource(R.drawable.img_asset_52hdpi);
                transparenciaButton.setImageResource(R.drawable.img_asset_53hdpi);
                luchaButton.setImageResource(R.drawable.img_asset_54hdpi);
                noticiasButton.getDrawable().setAlpha(255);
                contactoButton.getDrawable().setAlpha(255);
                break;
        }
    }

    public void limpiarContainer(){
        for(Fragment fragment:getSupportFragmentManager().getFragments()){
                if(fragment!=null)
                    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }
    }

    public Boolean isOnlineNet() {

        ConnectivityManager cm;
        NetworkInfo ni;
        cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        ni = cm.getActiveNetworkInfo();
        boolean tipoConexion1 = false;
        boolean tipoConexion2 = false;

        if (ni != null) {
            ConnectivityManager connManager1 = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWifi = connManager1.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            ConnectivityManager connManager2 = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobile = connManager2.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if (mWifi !=null) {
                if (mWifi.isConnected()) {
                    tipoConexion1 = true;
                }
            }
            if (mMobile !=null) {
                if (mMobile.isConnected()) {
                    tipoConexion2 = true;
                }
            }

            if (tipoConexion1 == true || tipoConexion2 == true) {
               /* Estas conectado a internet usando wifi o redes moviles, puedes enviar tus datos */
                return true;
            }
        }

        return false;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //
    }
}
