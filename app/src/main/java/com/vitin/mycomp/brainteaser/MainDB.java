package com.vitin.mycomp.brainteaser;



import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;




public class MainDB extends AppCompatActivity {


    DatabaseHelper Db;

    private Button button_add;
    private Button button_delete;
    private EditText editText_Score;
    private EditText editText_id;
    private EditText editText_lastname;
    private EditText editText_name_id;
    private Button button_save;
    private Button button_viewAll;
    private TextView infoView;

    public MainDB() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_db);
        Db = new DatabaseHelper(this);


        button_add = (Button) findViewById(R.id.button_add);
        button_delete = (Button) findViewById(R.id.button_delete);
        editText_Score = (EditText) findViewById(R.id.editText_Score);
        editText_id = (EditText) findViewById(R.id.editText_id);
        editText_name_id = (EditText) findViewById(R.id.editText_name_id);
        editText_lastname = (EditText) findViewById(R.id.editText_lastname);
        button_viewAll = (Button) findViewById(R.id.button_viewAll);
        button_save = (Button) findViewById(R.id.button_save);
        infoView = (TextView) findViewById(R.id.infoView);


        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer delete = Db.deleteData(editText_id.getText().toString());

                if (delete > 0)
                    Toast.makeText(MainDB.this, "Deleted data", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainDB.this, "Data not deleted", Toast.LENGTH_LONG).show();
            }
        });


        // set the listeners for adding data
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean inserted = Db.dataInsert(editText_id.getText().toString(),
                        editText_name_id.getText().toString(),
                        editText_lastname.getText().toString(),
                        editText_Score.getText().toString());

                if (inserted == true) {
                    Toast.makeText(MainDB.this, "Data Has been Inserted", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(MainDB.this, "Data not yet Inserted", Toast.LENGTH_LONG).show();


            }
        });

        // set the listeners to view all the list
        button_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getSharedPreferences("user info", Context.MODE_PRIVATE);
                String name = sharedPref.getString("username","");
                String lastname = sharedPref.getString("lastname","");
                String score = sharedPref.getString("score", "");
                String id = sharedPref.getString("id", "");
                String msg = "Name:  " + name +  "Lastname:  " + lastname +
                        "Score:  " + score +
                        "Id:  " + id;
                infoView.setText(msg);
            }
        });

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getSharedPreferences("user info", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("username",editText_name_id.getText().toString());
                editor.putString("lastname", editText_lastname.getText().toString());
                editor.putString("score", editText_Score.getText().toString());
                editor.putString("id", editText_id.getText().toString());
                editor.apply();

                Toast.makeText(MainDB.this, "save",Toast.LENGTH_LONG).show();

            }
        });

    }
}