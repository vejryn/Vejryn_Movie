package id.sch.smktelkom_mlg.privateassignment.xirpl536.verjrynmovie;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl536.verjrynmovie.Adapter.NowPlayingAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl536.verjrynmovie.Model.Result;
import id.sch.smktelkom_mlg.privateassignment.xirpl536.verjrynmovie.Model.ResultResponse;
import id.sch.smktelkom_mlg.privateassignment.xirpl536.verjrynmovie.Service.GsonGetRequest;
import id.sch.smktelkom_mlg.privateassignment.xirpl536.verjrynmovie.Service.VolleySingleton;


/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingFragment extends Fragment {

    ArrayList<Result> result_mList = new ArrayList<>();
    NowPlayingAdapter popular_mAdapter;

    public NowPlayingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_playing, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.popularecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        popular_mAdapter = new NowPlayingAdapter(this.getActivity(), result_mList);
        recyclerView.setAdapter(popular_mAdapter);

        downloadData();
    }

    private void downloadData() {
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=4fd21a6833f621d292cc138e8bccefe8";

        GsonGetRequest<ResultResponse> myRequest = new GsonGetRequest<ResultResponse>
                (url, ResultResponse.class, null, new Response.Listener<ResultResponse>() {

                    @Override
                    public void onResponse(ResultResponse response) {
                        Log.d("FLOW", "onResponse: " + (new Gson().toJson(response)));
                        if (response.page != 0) {
                            result_mList.addAll(response.results);
                            popular_mAdapter.notifyDataSetChanged();
                        }
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("FLOW", "onErrorResponse: ", error);
                    }
                });
        VolleySingleton.getInstance(this.getActivity()).addToRequestQueue(myRequest);
    }
}