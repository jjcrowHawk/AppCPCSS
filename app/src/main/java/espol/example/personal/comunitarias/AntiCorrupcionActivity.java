package espol.example.personal.comunitarias;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import espol.example.personal.comunitarias.Denuncias.IntroDenuncias;
import espol.example.personal.comunitarias.Pedidos.IntroPedidos;

import com.example.personal.comunitarias.R;

public class AntiCorrupcionActivity extends AppCompatActivity{
    Button Denuncias,Pedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anticorrupcion);

        Denuncias = (Button)findViewById(R.id.idDenuncias);
        Pedidos = (Button)findViewById(R.id.idPedidos);

        Denuncias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOnlineNet()) {
                    Intent i = new Intent(getBaseContext(), IntroDenuncias.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(AntiCorrupcionActivity.this,"No tiene conexion a internet, conéctese para poder acceder a esta opcion",Toast.LENGTH_SHORT).show();
                }
            }
        });
        Pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOnlineNet()) {
                    Intent i = new Intent(getBaseContext(), IntroPedidos.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(AntiCorrupcionActivity.this,"No tiene conexion a internet, conéctese para poder acceder a esta opcion",Toast.LENGTH_SHORT).show();
                }
            }
        });
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
    public void onBackPressed(){
        Intent i= new Intent(this.getBaseContext(),MenuPrincipal.class);
        startActivity(i);
        finish();
    }
}

