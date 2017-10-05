package com.example.personal.comunitarias.Pedidos;

import android.content.Intent;
import android.net.Uri;
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

import com.example.personal.comunitarias.R;
import com.ipaulpro.afilechooser.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


public class Pedido extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final int REQUEST_CHOOSER = 123;
    ArrayAdapter<CharSequence> adapter1, adapter2,adapter3;
    Spinner compadecer,doc_exist;
    private ViewPager viewPager;
    private View view;
    private Button siguiente, anterior, cargarArchivo;
    private EditText descripcion,txtArchivo;
    static String Descripcion_Pedido;
    static String Comparecer_d;
    static File evidencia;


    public static String getDescripcion_Pedido() {
        return Descripcion_Pedido;
    }

    public static void setDescripcion_Pedido(String descripcion_Pedido) {
        Descripcion_Pedido = descripcion_Pedido;
    }

    public static String getComparecer_d() {
        return Comparecer_d;
    }

    public static void setComparecer_d(String comparecer_d) {
        Comparecer_d = comparecer_d;
    }



    public static String getDoc() {
        return Doc;
    }

    public static void setDoc(String doc) {
        Doc = doc;
    }

    static String  Hechos_d;
    static String Doc;

    public Pedido(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pedido_tab2_pedido, container, false);
        inicializarComponentes();
        view.findViewById(R.id.btnPedAnterior).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(0);

            }
        });

        return view;

    }

    private void inicializarComponentes() {
        compadecer = (Spinner) view.findViewById(R.id.spinner_comparecer);
        adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.si_no, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        compadecer.setAdapter(adapter1);
        txtArchivo= (EditText) view.findViewById(R.id.txtArchivo);

        /*hechos = (Spinner) view.findViewById(R.id.spinner_hechos);
        adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.si_no, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hechos.setAdapter(adapter2);
        */
        /*
        doc_exist = (Spinner) view.findViewById(R.id.spinner_investigados);
        adapter3 = ArrayAdapter.createFromResource(getContext(), R.array.si_no, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        doc_exist.setAdapter(adapter3);*/


        siguiente = (Button) view.findViewById(R.id.btnSiguientePedido);
        cargarArchivo= (Button) view.findViewById(R.id.buttonEvidencia);
        anterior= (Button) view.findViewById(R.id.btnPedAnterior);
        descripcion = (EditText) view.findViewById(R.id.txt_descripcion_ped);


        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Descripcion_Pedido = descripcion.getText().toString();
                if(Descripcion_Pedido.equals("")){
                    Toast.makeText(getContext(),"Por favor, ingrese su pedido",Toast.LENGTH_LONG).show();
                }else {
                    Descripcion_Pedido= descripcion.getText().toString();
                    if (compadecer.getSelectedItem().equals("SI")) Comparecer_d ="1";
                    if (compadecer.getSelectedItem().equals("NO")) Comparecer_d ="0";
                    /*
                    if (doc_exist.getSelectedItem().equals("SI")) Doc="1";
                    if (doc_exist.getSelectedItem().equals("NO")) Doc="0";*/

                    Log.d("PEDIDO", Descripcion_Pedido +" " + Comparecer_d +" "+ Hechos_d +"" +" "+ Doc);

                    viewPager.setCurrentItem(2);
                }
            }
        });

        cargarArchivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                    if (path != null && FileUtils.isLocal(path) && (path.endsWith(".jpg")|| path.endsWith(".pdf")|| path.endsWith(".doc") || path.endsWith(".xls") || path.endsWith(".mp4") || path.endsWith(".mp3")) && new File(path).length()<=2000000) {
                        evidencia= new File(path);
                        Toast.makeText(this.getContext(),"Archivo " + evidencia.toString() +" seleccionado",Toast.LENGTH_LONG).show();
                        txtArchivo.setText(evidencia.toString());
                    }
                    else if(!(path.endsWith(".jpg")|| path.endsWith(".pdf")|| path.endsWith(".doc") || path.endsWith(".xls") || path.endsWith(".mp4") || path.endsWith(".mp3"))){
                        Toast.makeText(this.getContext(),"Debe escoger un archivo con una de las extensiones especificadas",Toast.LENGTH_LONG).show();
                    }
                    else if(new File(path).length()>2000000){
                        Toast.makeText(this.getContext(),"El archivo seleccionado supera los 2MB, por favor escoga un archivo con menor tamaño",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(this.getContext(),"No se seleccionó ningun archivo",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}











