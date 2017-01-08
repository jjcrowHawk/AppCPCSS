package noticias;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.personal.comunitarias.R;

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
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.list_row, parent, false);
        txtTitle = (TextView) itemView.findViewById(R.id.campo_texto);
        txtFecha = (TextView) itemView.findViewById(R.id.campo_fecha);
        txtTitle.setText(noticias.get(position).getTitulo());
        txtFecha.setText(noticias.get(position).getFecha());
        return itemView;
    }

    public void setList(ArrayList<Noticia> nuevas_noticias){
        noticias.addAll(nuevas_noticias);

    }
}