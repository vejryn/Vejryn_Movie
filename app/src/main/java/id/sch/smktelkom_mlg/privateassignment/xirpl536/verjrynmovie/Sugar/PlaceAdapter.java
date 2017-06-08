package id.sch.smktelkom_mlg.privateassignment.xirpl536.verjrynmovie.Sugar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl536.verjrynmovie.R;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {

    public static final String IMAGE_URL_BASE_PATH = "https://image.tmdb.org/t/p/w500";
    private final Context context;
    ArrayList<Place> pItem;
    IPlaceAdapter iPlaceAdapter;
    Bitmap bitmap = null;

    public PlaceAdapter(ArrayList<Place> place, Context context) {
        this.pItem = place;
        this.context = context;
    }


    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(PlaceAdapter.ViewHolder holder, final int position) {
        final Place place = pItem.get(position);
        holder.tvTitle.setText(place.title);
        holder.tvOverView.setText(place.overview);
        holder.tvRelease.setText(place.release_date);
        Bitmap bitmap = getImage(place.backdrop_poster);
        holder.imageViewPoster.setImageBitmap(bitmap);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Place place1 = pItem.get(position);
                pItem.remove(position);
                place1.delete();
                PlaceAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return pItem.size();
    }

    public interface IPlaceAdapter {
        //void doDelete(long id_sugar);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvOverView;
        public TextView tvRelease;
        public Button btnDelete;
        public ImageView imageViewPoster;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.textViewJudul);
            tvOverView = (TextView) itemView.findViewById(R.id.textViewDesc);
            tvRelease = (TextView) itemView.findViewById(R.id.textViewRelease);
            btnDelete = (Button) itemView.findViewById(R.id.buttonDelete);
            imageViewPoster = (ImageView) itemView.findViewById(R.id.imageViewPoster);

        }
    }
}
