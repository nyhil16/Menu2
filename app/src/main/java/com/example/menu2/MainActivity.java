package com.example.menu2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.txtMultiLine);
        loadDatos();
    }

    public void loadDatos() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db =admin.getWritableDatabase();

        String partidos = "";

        try {
            Cursor cursor = db.rawQuery("SELECT * FROM partidos", null);
            if(cursor.moveToNext()) {
                do {
                    String partido = "\n" + cursor.getString(0) + "\n"
                            + cursor.getString(1) + "\n"
                            + cursor.getString(2) + "\n"
                            + cursor.getString(3) + "\n"
                            + cursor.getString(4) + "\n"
                            + cursor.getString(5) + "\n";
                    partidos += partido;
                } while (cursor.moveToNext());
            }
            et1.setText(partidos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.mitNuevoPartido) {
            nuevoPartidoActivity();
        }
        if(itemId == R.id.mitBorrar) {
            borrarPartidoActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    public void nuevoPartidoActivity() {
        Intent intent = new Intent(MainActivity.this, nuevoPartido.class);
        startActivity(intent);
    }

    private void borrarPartidoActivity() {
        Intent intent = new Intent(MainActivity.this, borrarPartido.class);
        startActivity(intent);
    }

}