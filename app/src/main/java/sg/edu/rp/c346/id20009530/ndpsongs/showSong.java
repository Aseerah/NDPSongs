package sg.edu.rp.c346.id20009530.ndpsongs;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class showSong extends AppCompatActivity {

    ListView lv;
    Button btnFilter;

    ArrayList<Song> songList;
    ArrayAdapter adapter;

    int code = 9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_song);

        setTitle(getTitle().toString() + "~"+ "show song");

        lv = findViewById(R.id.lv);
        btnFilter = findViewById(R.id.btnFilter);
        DBHelper dbh = new DBHelper(this);
        songList = dbh.getAllSongs();
        dbh.close();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, songList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(showSong.this, ModifySong.class);
            i.putExtra("song", songList.get(position));
            startActivityForResult(i, code);
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(showSong.this);
                songList.clear();
                songList.addAll(dbh.getAllSongsByStars(5));
                adapter.notifyDataSetChanged();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == this.code && resultCode == RESULT_OK){
            DBHelper dbh = new DBHelper(this);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}