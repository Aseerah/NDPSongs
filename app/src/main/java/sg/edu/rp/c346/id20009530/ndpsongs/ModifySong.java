package sg.edu.rp.c346.id20009530.ndpsongs;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ModifySong extends AppCompatActivity {
    EditText etTitle, etSinger, etYear, etID;
    Button btnUpdate, btnDelete, btnCancel;
    RadioGroup rgStar;
    RadioButton rb1, rb2, rb3, rb4, rb5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_song);

        setTitle(getTitle().toString() + "~" + "modify song");
        ;

        etID = findViewById(R.id.etID);
        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        rgStar = findViewById(R.id.rgStar);
        btnCancel = findViewById(R.id.btnCancel);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        rb1 = findViewById(R.id.radioButton1);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);
        rb5 = findViewById(R.id.radioButton5);

        Intent i = getIntent();
        final Song currentSong = (Song) i.getSerializableExtra("song");

        etID.setText(currentSong.get_id() + " ");
        etTitle.setText(currentSong.getTitle() + " ");
        etYear.setText(currentSong.getYear() + " ");
        etSinger.setText(currentSong.getSingers() + " ");
        switch (currentSong.getStars()) {
            case 5:
                rb5.setChecked(true);
                break;
            case 4:
                rb4.setChecked(true);
                break;
            case 3:
                rb3.setChecked(true);
                break;
            case 2:
                rb2.setChecked(true);
                break;
            case 1:
                rb1.setChecked(true);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifySong.this);
                currentSong.setTitle((etTitle.getText().toString().trim()));
                currentSong.setSingers(etSinger.getText().toString().trim());
                int year = 0;
                try {
                    year = Integer.valueOf(etYear.getText().toString().trim());
                } catch (Exception e) {
                    Toast.makeText(ModifySong.this, "Invalid year", Toast.LENGTH_SHORT).show();
                    return;
                }
                currentSong.setYear(year);

                int selectRB = rgStar.getCheckedRadioButtonId();
                RadioButton rb = findViewById(selectRB);
                currentSong.setStars((Integer.parseInt(rb.getText().toString())));
                int outcome = dbh.updateSong(currentSong);
                if (outcome > 0) {
                    Toast.makeText(ModifySong.this, "song updated", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(ModifySong.this, "song updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifySong.this);
                int result = dbh.deleteSong(currentSong.get_id());
                if (result > 0) {
                    Toast.makeText(ModifySong.this, "Song deleted", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent();
                    setResult(RESULT_OK);
                    finish();

                } else {
                    Toast.makeText(ModifySong.this, "delete failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
