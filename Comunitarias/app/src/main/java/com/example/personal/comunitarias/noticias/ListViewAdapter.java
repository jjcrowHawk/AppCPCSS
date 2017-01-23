package com.example.personal.comunitarias.noticias;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.personal.comunitarias.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Palacios on 30/12/2016.
 */
public class ListViewAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<Noticia> noticias;

    public ListViewAdapter(Context context ) {
        this.context = context;
        this.noticias =  new ArrayList<>();

    }


    @Override
    public int getCount() {
        return noticias.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        TextView txtTitle;
        TextView txtFecha;
        TextView Txtdes;
        ImageView imagen_noticia;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.list_row, parent, false);
        txtTitle = (TextView) itemView.findViewById(R.id.campo_texto);
        txtFecha = (TextView) itemView.findViewById(R.id.campo_fecha);
        Txtdes = (TextView)itemView.findViewById(R.id.des_not);
        imagen_noticia =(ImageView)itemView.findViewById(R.id.img_noti);
        txtTitle.setText(noticias.get(position).getTitulo());
        txtFecha.setText(noticias.get(position).getFecha());
        Txtdes.setText(noticias.get(position).getDescripcion());
        String linke = noticias.get(position).getS_img();
        //Bitmap i_noticia = getBitmapFromURL("http://icons.iconarchive.com/icons/paomedia/small-n-flat/256/sign-check-icon.png");
        //imagen_noticia.setImageBitmap(i_noticia);
        Picasso.with(context).load(linke).into(imagen_noticia);
        //Picasso.with(context).load("https://images-na.ssl-images-amazon.com/images/G/01/aplusautomation/vendorimages/65fa961e-8f22-4fe6-a420-3c3c26dd2953.jpg._CB289161999__SL300__.jpg").into(imagen_noticia);




        return itemView;
    }

   /* public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }*/

    public void setList(ArrayList<Noticia> nuevas_noticias){
        noticias.addAll(nuevas_noticias);

    }


}