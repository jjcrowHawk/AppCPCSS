package com.example.personal.comunitarias.Denuncias;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.personal.comunitarias.R;
import com.ipaulpro.afilechooser.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


public class Denuncia extends Fragment {
    private static final int REQUEST_CHOOSER =1234;
    Spinner hechos;
    ArrayAdapter<CharSequence> adapter, adapter2;
    private ViewPager viewPager;
    private View view;
    private TabsDenuncia tabs;
    private Button sgteDenuciado,busquedaButton;
    EditText descripcion,archivoText;
    static String Descripcion_Denuncia;
    static String Comparecer_d;
    static File evidencia=null;

    ArrayList<String> lista_inst;

    public Denuncia(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view =  inflater.inflate(R.layout.denuncia_tab2_denuncia,container,false);
        InicializarComp();
        view.findViewById(R.id.btnDenunciaAnterior).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(0);

            }
        });
        return  view;

    }

    private  void InicializarComp() {
        //data de denuncia
        //Spinner comparecer con CPCCS
        /*comparecer = (Spinner) view.findViewById(R.id.spinner_comparecer);
        adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.si_no, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        comparecer.setAdapter(adapter);
*/
        descripcion = (EditText) view.findViewById(R.id.txt_descripcion);
        archivoText = (EditText) view.findViewById(R.id.archivoText);
        sgteDenuciado = (Button) view.findViewById(R.id.btnDenuncia);
        busquedaButton = (Button) view.findViewById(R.id.button2);

        sgteDenuciado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(descripcion.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Por favor, describa su denuncia",Toast.LENGTH_LONG).show();
                }else {

                    Descripcion_Denuncia= descripcion.getText().toString();
                    //
                    viewPager.setCurrentItem(2);
                }
            }
        });

        busquedaButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent getContentIntent = FileUtils.createGetContentIntent();

                Intent intent = Intent.createChooser(getContentIntent, "Seleccione un archivo");
                startActivityForResult(intent, REQUEST_CHOOSER);
            }
        });
    }

     @Override
     public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CHOOSER:
                if (resultCode == RESULT_OK) {

                    final Uri uri = data.getData();
                    String path = FileUtils.getPath(this.getContext(), uri);
                    if (path != null && FileUtils.isLocal(path)) {
                        evidencia= new File(path);
                        Toast.makeText(this.getContext(),"File " + evidencia.toString() +" was selected",Toast.LENGTH_LONG).show();
                        archivoText.setText(evidencia.toString());
                    }
                    else{
                        Toast.makeText(this.getContext(),"No se seleccionó ningun archivo",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    public static String getDescripcion_Denuncia() {
        return Descripcion_Denuncia;
    }

    public static void setDescripcion_Denuncia(String descripcion_Denuncia) {
        Descripcion_Denuncia = descripcion_Denuncia;
    }

    public static String getComparecer_d() {
        return Comparecer_d;
    }

    public static void setComparecer_d(String comparecer_d) {
        Comparecer_d = comparecer_d;
    }

    public void setLista_inst(ArrayList<String> lista_inst) {
        this.lista_inst = lista_inst;
    }
}
