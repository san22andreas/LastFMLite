package de.mihaly.lastfmlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_getTopAlbums, btn_getTopTracks, btn_getTopArtists;
    EditText et_dataInput;
    ListView lv_results;
    private String AlbumNameInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assign values to each control on the layout
        btn_getTopAlbums = findViewById(R.id.btn_getTopAlbums);
        btn_getTopArtists = findViewById(R.id.btn_getTopArtists);
        btn_getTopTracks = findViewById(R.id.btn_getTopTracks);

        et_dataInput = findViewById(R.id.et_dataInput);
        lv_results = findViewById(R.id.lv_results);

        final LastFMDataService lastFMDataService = new LastFMDataService(MainActivity.this);

        // click listeners for each button
        btn_getTopAlbums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lastFMDataService.getAlbumsByID(et_dataInput.getText().toString(), new LastFMDataService.AlbumByIDResponse() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "You're such a failure", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<AlbumsByID> topAlbums_list) {
                        //put the entire list into the listview control

                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, topAlbums_list);
                        lv_results.setAdapter(arrayAdapter);


                    }
                });

                //Toast.makeText(MainActivity.this,"Hello Paralleluniverse", Toast.LENGTH_SHORT).show();
            }
        });

        btn_getTopTracks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lastFMDataService.getTracksByID(et_dataInput.getText().toString(), new LastFMDataService.TrackByIDResponse() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "You're such a failure", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<TracksByID> topTracks_list) {
                        //put the entire list into the listview control

                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, topTracks_list);
                        lv_results.setAdapter(arrayAdapter);


                    }
                });

                //Toast.makeText(MainActivity.this,"Hello Paralleluniverse", Toast.LENGTH_SHORT).show();
            }
        });

        btn_getTopArtists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lastFMDataService.getArtistsByID(et_dataInput.getText().toString(), new LastFMDataService.ArtistByIDResponse() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Youre such a failure", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<ArtistsByID> topArtists_list) {
                        //put the entire list into the listview control

                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, topArtists_list);
                        lv_results.setAdapter(arrayAdapter);


                    }
                });

                //Toast.makeText(MainActivity.this,"Hello Paralleluniverse", Toast.LENGTH_SHORT).show();
            }
        });


    }
}