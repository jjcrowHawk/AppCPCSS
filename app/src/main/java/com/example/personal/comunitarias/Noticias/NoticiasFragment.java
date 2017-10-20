package com.example.personal.comunitarias.Noticias;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import java.util.LinkedList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NoticiasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NoticiasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoticiasFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public static LinkedList<Noticia> noticias = new LinkedList<>();
    public static CardViewAdapter adapter;
    private RecyclerView recyclerView;
    private int page=1;
    public static boolean refreshing = false;
    private LinearLayoutManager linearLayoutManager;

    public NoticiasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NoticiasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NoticiasFragment newInstance(String param1, String param2) {
        NoticiasFragment fragment = new NoticiasFragment();
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
        final View rootView= (View) inflater.inflate(R.layout.fragment_noticias, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerNoticias);
        adapter = new CardViewAdapter(recyclerView.getContext(),"noticias");
        recyclerView.setAdapter(adapter);

        linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()  {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();


                if (layoutManager.findLastCompletelyVisibleItemPosition() == layoutManager.getItemCount()-1  && !refreshing) {
                    page++;
                    refreshing = true;
                    new NoticiasReader(rootView,getContext(),adapter.getNoticias(),recyclerView).execute("noticias", String.valueOf(page));
                }
            }
        });

        return rootView;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagen;
        public TextView description;
        public TextView name,fecha;

        List<Noticia> noticias;
        public static Noticia select;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent, final List<Noticia> noticias) {
            super(inflater.inflate(R.layout.item_card, parent, false));
            this.noticias = noticias;


            imagen = (ImageView) itemView.findViewById(R.id.card_image);
            name = (TextView) itemView.findViewById(R.id.card_title);

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
}
