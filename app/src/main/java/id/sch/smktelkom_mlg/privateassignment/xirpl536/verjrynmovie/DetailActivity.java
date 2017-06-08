package id.sch.smktelkom_mlg.privateassignment.xirpl536.verjrynmovie;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import id.sch.smktelkom_mlg.privateassignment.xirpl536.verjrynmovie.Sugar.Place;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;

public class DetailActivity extends AppCompatActivity {

    public TextView tvVoteAverage, tvVoteCount, tvPopularity, tvLanguage, tvRelease, tvOverView, tvTitle;
    public ImageView ivBackDrop, ivPoster;

    public byte[] gambar = new byte[2048];
    Place place;
    boolean isPressed = true;
    boolean isNew;
    ArrayList<Place> pItem;

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle(getIntent().getStringExtra(MainActivity.RESULTTITLE));

        tvVoteAverage = (TextView) findViewById(R.id.VoteAverage);
        tvVoteCount = (TextView) findViewById(R.id.VoteCount);
        tvPopularity = (TextView) findViewById(R.id.Popularity);
        tvLanguage = (TextView) findViewById(R.id.Language);
        tvRelease = (TextView) findViewById(R.id.ReleaseDate);
        tvOverView = (TextView) findViewById(R.id.Description);
        ivBackDrop = (ImageView) findViewById(R.id.imageViewBack);
        ivPoster = (ImageView) findViewById(R.id.imageViewPoster);
        tvTitle = (TextView) findViewById(R.id.textViewJudul);

        tvVoteAverage.setText(getIntent().getStringExtra(MainActivity.RESULTVOTE));
        tvVoteCount.setText(getIntent().getStringExtra(MainActivity.RESULTVOTECOUNT));
        tvPopularity.setText(getIntent().getStringExtra(MainActivity.RESULTPOPULARITY));
        tvLanguage.setText(getIntent().getStringExtra(MainActivity.RESULTLANGUAGE));
        tvRelease.setText(getIntent().getStringExtra(MainActivity.RESULTRELEASE));
        tvOverView.setText(getIntent().getStringExtra(MainActivity.RESULTOVER));
        Glide.with(DetailActivity.this)
                .load("https://image.tmdb.org/t/p/w500" + getIntent().getStringExtra(MainActivity.RESULTBACK))
                .into(ivBackDrop);
        Glide.with(DetailActivity.this)
                .load("https://image.tmdb.org/t/p/w500" + getIntent().getStringExtra(MainActivity.RESULTPOSTER))
                .into(ivPoster);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Bitmap bitmap = Glide.
                            with(getApplicationContext()).
                            load("https://image.tmdb.org/t/p/w500" + getIntent().getStringExtra(MainActivity.RESULTBACK)).
                            asBitmap().
                            into(500, 500).get();
                    gambar = getBytes(bitmap);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        new MaterialShowcaseView.Builder(this)
                .setTarget(fab)
                .setDismissText("OK")
                .setContentText("Click the love button to add to the favorite list !")
                .setDelay(200) // optional but starting animations immediately in onCreate can make them choppy
                .singleUse("Yes") // provide a unique ID used to ensure it is only shown once
                .show();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPressed) {
                    doSimpan();
                    Snackbar.make(view, "Anda berhasil memberi rating, lihat di favorit", Snackbar.LENGTH_LONG)

                            .setAction("Action", null).show();
                } else {
                    Snackbar.make(view, "Artikel favorit anda", Snackbar.LENGTH_LONG)

                            .setAction("Action", null).show();
                }
                isPressed = !isPressed;
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void doSimpan() {
        String overview = getIntent().getStringExtra(MainActivity.RESULTOVER);
        String title = getIntent().getStringExtra(MainActivity.RESULTTITLE);
        String release_date = getIntent().getStringExtra(MainActivity.RESULTRELEASE);
        byte[] poster_path = gambar;

        place = new Place(overview, release_date, title, poster_path);
        place.save();
    }

}