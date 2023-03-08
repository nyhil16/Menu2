package com.example.menu2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class borrarPartido extends AppCompatActivity {

    EditText et1;
    Button bt1, bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrar_partido_sql);

        et1 = findViewById(R.id.txtNombre);

        bt1 = findViewById(R.id.btnBorrar);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrar();
            }
        });

        bt2 = findViewById(R.id.btnVolverB);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volver();
            }
        });

    }

    public void borrar() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1); //sqlite helper

        SQLiteDatabase db = admin.getWritableDatabase(); //acceso

        try {
            String nombre = et1.getText().toString();

            int cant = db.delete("partidos", "nombrePartido='" + nombre + "'", null); //delete
            db.close();

            et1.setText("");

            if (cant >= 1) {
                Toast.makeText(this, "Se borro el partido con dicho nombre", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "No existe un partido con dicho nombre", Toast.LENGTH_LONG).show();
            }
        } catch (SQLiteException e) {
            Toast.makeText(this, "Has introducido un valor incorrecto", Toast.LENGTH_LONG).show();
        }
    }
    public void volver() {
        Intent intent = new Intent(borrarPartido.this, MainActivity.class);
        startActivity(intent);
    }

}