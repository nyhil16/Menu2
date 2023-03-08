package com.example.menu2;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class nuevoPartido extends Activity {

    EditText et1, et2, et3, et4, et5, et6;
    Button bt1, bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_partido_sql);

        et1 = findViewById(R.id.txtNombrePartido);
        et2 = findViewById(R.id.txtTelefono);
        et3 = findViewById(R.id.txtDireccion);
        et4 = findViewById(R.id.txtHorario);
        et5 = findViewById(R.id.txtJugador1);
        et6 = findViewById(R.id.txtJugador2);

        bt1 = findViewById(R.id.btnGuardar);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });

        bt2 = findViewById(R.id.btnVolverN);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volver();
            }
        });

    }

    public void guardar() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1); //sqlhelper
        SQLiteDatabase db =admin.getWritableDatabase();

        try {
            String nombrePtdo = et1.getText().toString();
            String telef = et2.getText().toString();
            String direc = et3.getText().toString();
            String hora = et4.getText().toString();
            String jug1 = et5.getText().toString();
            String jug2 = et6.getText().toString();

            ContentValues values = new ContentValues();
            values.put("nombrePartido", nombrePtdo);
            values.put("telefono", telef);
            values.put("direccion", direc);
            values.put("horario", hora);
            values.put("jugador1", jug1);
            values.put("jugador2", jug2);

            db.insert("partidos", null, values);
            db.close();

            et1.setText("");
            et2.setText("");
            et3.setText("");
            et4.setText("");
            et5.setText("");
            et6.setText("");

            Toast.makeText(this, "Datos guardado", Toast.LENGTH_LONG);
        } catch (Exception e) {
            Toast.makeText(this, "Ha producido un error", Toast.LENGTH_LONG);
        }

    }

    public void volver() {
        Intent intent = new Intent(nuevoPartido.this, MainActivity.class);
        startActivity(intent);
    }

}
