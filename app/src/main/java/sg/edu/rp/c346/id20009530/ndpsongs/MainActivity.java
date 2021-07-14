package sg.edu.rp.c346.id20009530.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSinger, etYear;
    Button btnInsert, btnShow;
    RadioGroup rgStar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        rgStar = findViewById(R.id.rgStar);
        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnShow);


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = etTitle.getText().toString().trim();
                String singers = etSinger.getText().toString().trim();
                if (title.length() == 0 || singers.length() == 0) {
                    Toast.makeText(MainActivity.this, "incomplete data", Toast.LENGTH_SHORT).show();
                    return;

                }
                String strYear = etYear.getText().toString().trim();
                int year = 0;
                try {
                    year = Integer.valueOf(strYear);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "year invalid", Toast.LENGTH_SHORT).show();
                    return;
                }


                DBHelper dbh = new DBHelper(MainActivity.this);

                int stars = getStars();
                dbh.insertSong(title, singers, year, stars);
                dbh.close();
                Toast.makeText(MainActivity.this, "inserted", Toast.LENGTH_SHORT).show();

                etTitle.setText("");
                etSinger.setText("");
                etYear.setText("");

            }

        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, showSong.class);
                startActivity(i);
            }
        });

    }

    private int getStars() {
        int stars = 1;
        switch (rgStar.getCheckedRadioButtonId()) {
            case R.id.radioButton1:
                stars = 1;
                break;
            case R.id.radioButton2:
                stars = 2;
                break;
            case R.id.radioButton3:
                stars = 3;
                break;
            case R.id.radioButton4:
                stars = 4;
                break;
            case R.id.radioButton5:
                stars = 5;
        }
        return stars;
    }


}