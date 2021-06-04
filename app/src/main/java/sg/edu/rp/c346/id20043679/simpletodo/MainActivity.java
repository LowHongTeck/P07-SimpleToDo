package sg.edu.rp.c346.id20043679.simpletodo;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etElement;
    Button btnAdd, btnClear, btnDelete;
    ListView lvTask;
    Spinner AddorDelete;


    ArrayList<String> alTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etElement = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.buttonAdditem);
        lvTask = findViewById(R.id.listViewColour);
        btnClear = findViewById(R.id.buttonClearitem);
        AddorDelete = findViewById(R.id.spinner);
        btnDelete = findViewById(R.id.buttonDeleteitem);

        alTask = new ArrayList<String>();


        ArrayAdapter aaTask = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTask);
        lvTask.setAdapter(aaTask);

        AddorDelete.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        etElement.setInputType(InputType.TYPE_CLASS_TEXT);
                        etElement.setHint("Enter new task");
                        etElement.getText().clear();
                        break;
                    case 1:
                        btnDelete.setEnabled(true);
                        btnAdd.setEnabled(false);
                        etElement.setInputType(InputType.TYPE_CLASS_NUMBER);
                        etElement.setHint("Type in the index of the task to be removed");
                        etElement.getText().clear();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etElement.getText().toString();
                alTask.add(task);
                aaTask.notifyDataSetChanged();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTask.clear();
                aaTask.notifyDataSetChanged();

                if (!(alTask.isEmpty())) {
                    alTask.clear();
                    aaTask.notifyDataSetChanged();
                } else if (alTask.isEmpty()){
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String task = " ";
                int pos = 0;

                if (!(etElement.getText().toString().isEmpty())){
                    task = etElement.getText().toString();
                    pos = Integer.parseInt(task);
                } else {
                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                }


                if (pos < alTask.size()){
                    alTask.remove(pos);
                    aaTask.notifyDataSetChanged();
                }
            }
        });
    }
}