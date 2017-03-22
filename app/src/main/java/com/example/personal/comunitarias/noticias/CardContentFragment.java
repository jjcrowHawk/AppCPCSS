
package com.example.personal.comunitarias.Noticias;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.personal.comunitarias.R;

import java.util.List;


public class CardContentFragment extends Fragment {

    private CardViewAdapter adapter;
    private RecyclerView recyclerView;
    private String tipo;
    private int page=1;
    static boolean refreshing = false;
    private LinearLayoutManager linearLayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        recyclerView = (RecyclerView) inflater.inflate( R.layout.recycler_view, container, false);
        tipo = this.getArguments().getString("tipo");
        adapter = new CardViewAdapter(recyclerView.getContext(),tipo);
        recyclerView.setAdapter(adapter);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()  {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();


                if (layoutManager.findLastCompletelyVisibleItemPosition() == layoutManager.getItemCount()-1  && !refreshing) {
                    page++;
                    refreshing = true;
                    new NoticiasReader(getContext(),adapter.getNoticias(),recyclerView).execute(tipo, String.valueOf(page));
                }
            }
        });


        return recyclerView;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView picture;
        public TextView name,fecha;
        public TextView description;
        List<Noticia> noticias;
        static Noticia select;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent, final List<Noticia> noticias) {
            super(inflater.inflate(R.layout.item_card, parent, false));
            this.noticias = noticias;


            picture = (ImageView) itemView.findViewById(R.id.card_image);
            name = (TextView) itemView.findViewById(R.id.card_title);
            fecha= (TextView) itemView.findViewById(R.id.fecha);
            description = (TextView) itemView.findViewById(R.id.card_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    int position = getAdapterPosition();
                    select = noticias.get(position);
                    intent.putExtra(DetailActivity.EXTRA_POSITION, getAdapterPosition());
                    context.startActivity(intent);
                }
            });

        }
    }

    public CardViewAdapter getAdapter() {
        return adapter;
    }
}
