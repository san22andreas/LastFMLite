package de.mihaly.lastfmlite;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LastFMDataService {

    public static final String QUERY_TOP_ALBUMS = "https://ws.audioscrobbler.com/2.0/?method=user.getTopAlbums&user=";
    public static final String QUERY_TOP_ARTISTS = "https://ws.audioscrobbler.com/2.0/?method=user.getTopArtists&user=";
    public static final String QUERY_TOP_TRACKS = "https://ws.audioscrobbler.com/2.0/?method=user.getTopTracks&user=";
    public static final String API_KEY_FORMAT = "&api_key=35a295774111a703bb5f04b02cb02b1b&format=json";

    Context context;


    public LastFMDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(String Albums);
    }

    public interface ArtistByIDResponse {
        void onError(String message);

        void onResponse(List<ArtistsByID> topArtists_list);
    }

    public interface AlbumByIDResponse {
        void onError(String message);

        void onResponse(List<AlbumsByID> topAlbums_list);
    }

    public interface TrackByIDResponse {
        void onError(String message);

        void onResponse(List<TracksByID> topTracks_list);
    }


    public void getArtistsByID(String UserName, ArtistByIDResponse artistByIDResponse) {
        final List<ArtistsByID> topArtists_list = new ArrayList<>();

        String url = QUERY_TOP_ARTISTS + UserName + API_KEY_FORMAT;

        //get the json object
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Toast.makeText(context,response.toString(), Toast.LENGTH_SHORT).show();


                try {
                    JSONObject topartists = response.getJSONObject("topartists");
                    JSONArray artist_list = topartists.getJSONArray("artist");

                    //JSONArray test = artist_list.getJSONArray("@attr");


                    //get the first item in the array


                    for (int i = 0; i < artist_list.length(); i++) {
                        ArtistsByID one_rank_artist = new ArtistsByID();
                        JSONObject first_artist = artist_list.getJSONObject(i);

                        //Toast.makeText(context, rank.toString(), Toast.LENGTH_SHORT).show();

                        JSONObject rank_1_from_api = (JSONObject) artist_list.get(i);
                        JSONObject rank = first_artist.getJSONObject("@attr");
                        one_rank_artist.setUrl(rank_1_from_api.getString("url"));
                        one_rank_artist.setPlaycount(rank_1_from_api.getString("playcount"));
                        one_rank_artist.setName(rank_1_from_api.getString("name"));
                        String ranking = rank.getString("rank");
                        one_rank_artist.setRank(ranking);
                        topArtists_list.add(one_rank_artist);
                    }
                    artistByIDResponse.onResponse(topArtists_list);

                } catch (JSONException e) {

                    Toast.makeText(context, "You are a idiot", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error: Username doesn't exist", Toast.LENGTH_SHORT).show();


            }
        });


        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    public void getTracksByID(String UserName, TrackByIDResponse trackByIDResponse) {
        final List<TracksByID> topTracks_list = new ArrayList<>();

        String url = QUERY_TOP_TRACKS + UserName + API_KEY_FORMAT;

        //get the json object
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Toast.makeText(context,response.toString(), Toast.LENGTH_SHORT).show();


                try {
                    JSONObject toptracks = response.getJSONObject("toptracks");
                    JSONArray track_list = toptracks.getJSONArray("track");



                    //get the first item in the array


                    for (int i = 0; i < track_list.length(); i++) {
                        TracksByID one_rank_track = new TracksByID();
                        JSONObject first_track = track_list.getJSONObject(i);

                        //Toast.makeText(context, rank.toString(), Toast.LENGTH_SHORT).show();

                        JSONObject rank_1_from_api = (JSONObject) track_list.get(i);
                        JSONObject rank = first_track.getJSONObject("@attr");
                        JSONObject track_name = first_track.getJSONObject("artist");
                        one_rank_track.setUrl(rank_1_from_api.getString("url"));
                        one_rank_track.setPlaycount(rank_1_from_api.getString("playcount"));
                        one_rank_track.setName(rank_1_from_api.getString("name"));
                        String ranking = rank.getString("rank");
                        String a_name = track_name.getString("name");
                        one_rank_track.setRank(ranking);
                        one_rank_track.setName_artist(a_name);
                        topTracks_list.add(one_rank_track);
                    }
                    trackByIDResponse.onResponse(topTracks_list);

                } catch (JSONException e) {

                    Toast.makeText(context, "You are a idiot Tracks", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error: Username doesn't exist", Toast.LENGTH_SHORT).show();


            }
        });


        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    public void getAlbumsByID(String UserName, AlbumByIDResponse albumByIDResponse) {
        final List<AlbumsByID> topAlbums_list = new ArrayList<>();

        String url = QUERY_TOP_ALBUMS + UserName + API_KEY_FORMAT;

        //get the json object
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject topalbums = response.getJSONObject("topalbums");
                    JSONArray album_list = topalbums.getJSONArray("album");

                    //get the first item in the array

                    for (int i = 0; i < album_list.length(); i++) {
                        AlbumsByID one_rank_album = new AlbumsByID();
                        JSONObject first_album = album_list.getJSONObject(i);

                        //Toast.makeText(context, rank.toString(), Toast.LENGTH_SHORT).show();

                        JSONObject rank_1_from_api = (JSONObject) album_list.get(i);
                        JSONObject rank = first_album.getJSONObject("@attr");
                        JSONObject artist_name = first_album.getJSONObject("artist");
                        one_rank_album.setUrl(rank_1_from_api.getString("url"));
                        one_rank_album.setPlaycount(rank_1_from_api.getString("playcount"));
                        one_rank_album.setName(rank_1_from_api.getString("name"));
                        String ranking = rank.getString("rank");
                        String a_name = artist_name.getString("name");
                        one_rank_album.setRank(ranking);
                        one_rank_album.setName_artist(a_name);
                        topAlbums_list.add(one_rank_album);
                    }
                    albumByIDResponse.onResponse(topAlbums_list);

                } catch (JSONException e) {

                    Toast.makeText(context, "You are a idiot Album", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error: Username doesn't exist", Toast.LENGTH_SHORT).show();


            }
        });


        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}