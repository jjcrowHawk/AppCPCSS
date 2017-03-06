package com.example.personal.comunitarias.Pedidos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.example.personal.comunitarias.R;


public class Peticionario extends Fragment implements AdapterView.OnItemSelectedListener {
    

    private ViewPager viewPager;
    private View view;
    private Button siguiente;
    
    private EditText txt_Nombres, txt_Apellidos, txt_correo, txt_tipoIdentificacion, txt_ocupacion;

    public Peticionario(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pedido_tab1_peticionario, container, false);
        inicializarComponentes();
        ValidarCampos();


        return view;

    }

    private void inicializarComponentes() {
        txt_Nombres = (EditText) view.findViewById(R.id.txt_Nombres);
        txt_Apellidos = (EditText) view.findViewById(R.id.txt_Apellidos);
        txt_correo = (EditText) view.findViewById(R.id.txt_correo);
        txt_tipoIdentificacion = (EditText) view.findViewById(R.id.txt_tipoIdentificacion);
        txt_ocupacion = (EditText) view.findViewById(R.id.txt_ocupacion);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void ValidarCampos(){

        //validacione de nombre
        txt_Nombres .addTextChangedListener(new TextValidatorPedido(txt_Nombres) {
            @Override
            public void validate(EditText editText, String text) {
                for (int index = 0; index < text.length(); index++) {
                    String c = String.valueOf(text.charAt(index));
                    if (isNumeric(c) || (!text.matches("[a-zA-Zá-ú? ]*"))){
                        txt_Nombres.setError("Sólo ingrese letras");
                        text = text.substring(0, text.length() - 1);
                        txt_Nombres.setText(text);
                        txt_Nombres.setSelection(txt_Nombres.getText().length());
                    }
                }
                if (text.length() > 24) {
                    txt_Nombres.setError("Límite excedido");
                }
            }
        });


        //validacione de Apellido
        txt_Apellidos .addTextChangedListener(new TextValidatorPedido(txt_Apellidos) {
            @Override
            public void validate(EditText editText, String text) {
                for (int index = 0; index < text.length(); index++) {
                    String c = String.valueOf(text.charAt(index));
                    if (isNumeric(c) || (!text.matches("[a-zA-Zá-ú? ]*"))){
                        txt_Apellidos.setError("Sólo ingrese letras");
                        text = text.substring(0, text.length() - 1);
                        txt_Apellidos.setText(text);
                        txt_Apellidos.setSelection(txt_Apellidos.getText().length());
                    }
                }
                if (text.length() > 24) {
                    txt_Apellidos.setError("Límite excedido");
                }
            }
        });


        //validacione de identificación
        txt_tipoIdentificacion.addTextChangedListener(new TextValidatorPedido(txt_tipoIdentificacion) {
            @Override
            public void validate(EditText editText, String text) {
                //puede ser 10 u 11 por el RUC
                if (text.length()> 0 && text.length() < 10) {
                    txt_tipoIdentificacion.setError("Cantidad de dígitos incorrecta");
                }
            }
        });

        //validacione de Cargo
        txt_ocupacion.addTextChangedListener(new TextValidatorPedido(txt_ocupacion) {
            @Override
            public void validate(EditText editText, String text) {
                for (int index = 0; index < text.length(); index++) {
                    String c = String.valueOf(text.charAt(index));
                    if (isNumeric(c) || (!text.matches("[a-zA-Zá-ú? ]*"))){
                        txt_ocupacion.setError("Sólo ingrese letras");
                        text = text.substring(0, text.length() - 1);
                        txt_ocupacion.setText(text);
                        txt_ocupacion.setSelection(txt_ocupacion.getText().length());
                    }
                }
                if (text.length() > 24) {
                    txt_ocupacion.setError("Límite excedido");
                }
            }
        });
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
}











