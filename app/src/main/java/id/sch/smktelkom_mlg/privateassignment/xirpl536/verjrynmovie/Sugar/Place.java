package id.sch.smktelkom_mlg.privateassignment.xirpl536.verjrynmovie.Sugar;

import com.orm.SugarRecord;

import java.io.Serializable;

public class Place extends SugarRecord implements Serializable {
    public String overview;
    public String release_date;
    public String title;
    byte[] backdrop_poster = new byte[2048];

    public Place() {

    }

    public Place(String overview, String release_date, String title, byte[] backdrop_poster) {
        this.overview = overview;
        this.release_date = release_date;
        this.title = title;
        this.backdrop_poster = backdrop_poster;

    }

}
