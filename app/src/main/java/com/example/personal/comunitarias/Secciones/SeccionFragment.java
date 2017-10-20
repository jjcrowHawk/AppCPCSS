package com.example.personal.comunitarias.Secciones;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.personal.comunitarias.AsynchronousTask;
import com.example.personal.comunitarias.BaseDeDatos.contenidos.Contenido;
import com.example.personal.comunitarias.Constantes;
import com.example.personal.comunitarias.R;
import com.example.personal.comunitarias.WebService.WebServiceAsynchronic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SeccionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SeccionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SeccionFragment extends Fragment implements AsynchronousTask {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    LinearLayout mainLayout;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String json_results;
    private String url_resource;
    private String section;
    private ArrayList<Contenido> contenidos;
    private OnFragmentInteractionListener mListener;
    private ArrayList<String> links;
    private ArrayList<Integer> linksIndex;
    private ArrayList<Button> videoButtons;

    public SeccionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SeccionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SeccionFragment newInstance(String param1, String param2) {
        SeccionFragment fragment = new SeccionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.contenidos= new ArrayList<>();
        this.links=new ArrayList<>();
        this.linksIndex=new ArrayList<>();
        this.videoButtons= new ArrayList<>();
        View rootView = inflater.inflate(R.layout.seccion_fragment, container, false);
        mainLayout = (LinearLayout) rootView.findViewById(R.id.mainLayout);
        this.section=getArguments().getString("seccion");
        WebServiceAsynchronic wsa= new WebServiceAsynchronic(Constantes.WS_CONTENIDO,getContext(),this,"GET");
        this.url_resource=Constantes.WS_CONTENIDO;
        wsa.execute();

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void processFinish(String result) {
        if(this.url_resource.equals(Constantes.WS_CONTENIDO)){
            System.out.println("Actual json result: "+this.json_results);
            try {
                JSONObject jsonG = new JSONObject(result);
                int registros = 0;
                registros = Integer.parseInt(jsonG.getString("count"));
                int paginas = registros / 10;
                paginas = registros % 10 > 0 ? paginas + 1 : paginas;
                for (int i = 0; i < paginas; i++) {
                    //WebServiceResolver wsr = new WebServiceResolver(Constantes.WS_CONTENIDO+ "?offset=" + i * 10, null);
                    WebServiceAsynchronic wsr = new WebServiceAsynchronic(Constantes.WS_CONTENIDO + "?offset=" + i * 10, getContext(), this, "GET");
                    //String p = wsr.makeGetPetition();
                    this.url_resource= Constantes.WS_CONTENIDO + "?offset=" + i * 10;
                    wsr.execute();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                JSONObject json = new JSONObject(result);
                JSONArray datos = json.getJSONArray("results");
                for (int j = 0; j < datos.length(); j++) {
                    JSONObject item = datos.getJSONObject(j);
                    String[] splitTitulo = item.getString("descripcion").split("-");
                    if (splitTitulo[1].equals(this.section)) {
                        Contenido c = new Contenido(splitTitulo[0], item.getString("contenido"), item.getString("url_video"));
                        if(!this.contenidos.contains(c))
                            this.contenidos.add(c);
                    }

                }
            }
            catch (JSONException e){
                e.printStackTrace();
            }
            synchronized (this){
                for(Contenido c:this.contenidos){
                    Button b=new Button(getActivity());
                    b.setText(c.getTitulo());
                    boolean isInLayout= false;
                    for(int i=0;i<mainLayout.getChildCount();i++){

                        if(mainLayout.getChildAt(i) instanceof Button && b.getText().equals(((Button)mainLayout.getChildAt(i)).getText())){
                            isInLayout= true;
                        }
                    }
                    if(!isInLayout) {
                        LinearLayout l= new LinearLayout(getActivity());
                        l.setOrientation(LinearLayout.VERTICAL);
                        l.setPadding(0,0,0,20);
                        //l.setBackgroundColor(Color.rgb(232,232,232));
                        l.setBackgroundResource(R.drawable.shape);
                        //l.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT,.70f));
                        TextView t = new TextView(getActivity());
                        t.setText(c.getDescripcion());
                        t.setTextSize(18);
                        t.setTextColor(Color.BLACK);
                        t.setPadding(0,0,0,20);
                        l.addView(t);
                        if(!c.getUrlVideo().equals("ninguno")){
                            Button videoButton= new Button(getActivity());
                            videoButton.setText("Toque para ver el video");
                            videoButton.setBackgroundColor(Color.rgb(109, 132, 180));
                            links.add(c.getUrlVideo());
                            videoButtons.add(videoButton);
                            videoButton.setOnClickListener(videoButtonListener);
                            l.addView(videoButton);
                        }
                        else{
                            linksIndex.add(-1);
                        }
                        b.setOnClickListener(buttonListener);
                        b.setBackgroundResource(R.drawable.item_bar_2x);
                        b.setTextColor(Color.WHITE);

                        l.setVisibility(View.GONE);
                        mainLayout.addView(b);
                        mainLayout.addView(l);
                    }
                }
            }
        }
    }

    public View.OnClickListener videoButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            for(int i =0;i<videoButtons.size();i++){
                if(videoButtons.get(i)== (Button) view) {
                    Intent intent = new Intent(getActivity(), SeccionVideoActivity.class);
                    intent.putExtra("LINK", links.get(i));
                    startActivity(intent);
                }
            }
        }
    };

    /*
     ** Esta clase interna se utiliza para Generar las acciones de los botones, en este caso
     *  Se encarga de poner como visible al panel contiguo al botón que ejecuto la acción, ademas
     *  de cambiarle el color a los otros botones
     */
    public View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            for(int i=0;i<mainLayout.getChildCount();i++){

                if(mainLayout.getChildAt(i) instanceof Button){
                   if(((Button)mainLayout.getChildAt(i)) == (Button) view)
                    {
                        if(((LinearLayout) mainLayout.getChildAt(i+1)).getVisibility()!=View.VISIBLE) {
                            ((LinearLayout) mainLayout.getChildAt(i + 1)).setVisibility(View.VISIBLE);
                            for(int j=0;j<mainLayout.getChildCount();j+=2){
                                if(((Button)mainLayout.getChildAt(j)) != (Button) view)
                                    ((Button)mainLayout.getChildAt(j)).setBackgroundResource(R.drawable.asset_25_2x);
                                else
                                    ((Button)mainLayout.getChildAt(j)).setBackgroundResource(R.drawable.item_bar_2x);
                            }
                        }
                        else{
                            ((LinearLayout) mainLayout.getChildAt(i+1)).setVisibility(View.GONE);
                            for(int j=0;j<mainLayout.getChildCount();j+=2)
                                ((Button)mainLayout.getChildAt(j)).setBackgroundResource(R.drawable.item_bar_2x);
                        }
                    }
                    else{
                       ((LinearLayout) mainLayout.getChildAt(i+1)).setVisibility(View.GONE);
                    }
                }
            }
        }
    };

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public ArrayList<Contenido> getContenidoSeccion(String seccion) {
        ArrayList<Contenido> lista = new ArrayList<>();
        try {
            WebServiceAsynchronic wsa= new WebServiceAsynchronic(Constantes.WS_CONTENIDO,getContext(),this,"GET");
            // WebServiceResolver ws = new WebServiceResolver(Constantes.WS_CONTENIDO, null);
            //String result = ws.makeGetPetition();
            wsa.execute().get();
            System.out.println("Actual json result: "+this.json_results);
            JSONObject jsonG = new JSONObject(this.json_results);
            int registros = Integer.parseInt(jsonG.getString("count"));
            int paginas = registros / 10;
            paginas = registros % 10 > 0 ? paginas + 1 : paginas;
            for (int i = 0; i < paginas; i++) {
                //WebServiceResolver wsr = new WebServiceResolver(Constantes.WS_CONTENIDO+ "?offset=" + i * 10, null);
                WebServiceAsynchronic wsr= new WebServiceAsynchronic(Constantes.WS_CONTENIDO + "?offset=" + i * 10,getContext(),this,"GET");
                //String p = wsr.makeGetPetition();
                wsr.execute().get();
                JSONObject json = new JSONObject(this.json_results);
                JSONArray datos = json.getJSONArray("results");
                for (int j = 0; j < datos.length(); j++) {
                    JSONObject item = datos.getJSONObject(j);
                    String [] splitTitulo= item.getString("descripcion").split("-");
                    if (splitTitulo[1].equals(seccion)) {
                        Contenido c = new Contenido(splitTitulo[0],item.getString("contenido"),item.getString("url_video"));
                        lista.add(c);
                    }

                }
            }
        } catch (JSONException /*| MalformedURLException*/ e) {
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return lista;
    }

}
