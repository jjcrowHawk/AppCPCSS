package com.example.personal.comunitarias.Noticias;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.personal.comunitarias.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Palacios on 22/02/2017.
 */

public class CardViewAdapter extends RecyclerView.Adapter<CardContentFragment.ViewHolder> {

    private List<Noticia> noticias;
    private Context context;

    public CardViewAdapter(Context context, String tipo) {
        this.context = context;
        if(tipo.equalsIgnoreCase("noticias"))
            noticias = Intro_noticias.noticias;
        else
            noticias = Intro_noticias.boletines;

    }

    @Override
    public CardContentFragment.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CardContentFragment.ViewHolder(LayoutInflater.from(parent.getContext()), parent, noticias);


    }

    @Override
    public void onBindViewHolder(CardContentFragment.ViewHolder holder, int position) {
        Noticia noticia = noticias.get(position);
        if(noticia.getUrlImg().isEmpty()){
            holder.imagen.setImageResource(R.drawable.ic_cpccs);
        }else {
            Picasso.with(context).load(noticia.getUrlImg()).into(holder.imagen);


        }
        holder.name.setText(noticia.getTitulo());
        holder.description.setText(noticia.getDescripcion());
        holder.fecha.setText(noticia.getFecha());
    }

    @Override
    public int getItemCount() {
        return noticias.size();
    }

    public List<Noticia> getNoticias() {
        return noticias;
    }

    public void setNoticias(List<Noticia> noticias) {
        this.noticias = noticias;
    }


}