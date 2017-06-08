package id.sch.smktelkom_mlg.privateassignment.xirpl536.verjrynmovie.Sugar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl536.verjrynmovie.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    ArrayList<Place> pList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.popularecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        pList = new ArrayList<>();

        adapter = new PlaceAdapter(pList, getActivity().getApplicationContext());
        recyclerView.setAdapter(adapter);

        pList.addAll(Place.listAll(Place.class));
        adapter.notifyDataSetChanged();


        return view;
    }
}
