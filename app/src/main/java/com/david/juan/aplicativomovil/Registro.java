package com.david.juan.aplicativomovil;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.david.juan.aplicativomovil.Utilidades.Utilidad;

public class Registro extends AppCompatActivity {

    EditText campoId, camponombre, campotelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        campoId = (EditText) findViewById(R.id.idtext);
        camponombre = (EditText) findViewById(R.id.nombretext);
        campotelefono = (EditText) findViewById(R.id.telefonotext);


}
    public void onClick(View view) {
        registrarusuarios();
    }
    private void registrarusuarios() {
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(this, "bdusuarios", null, 1);
        SQLiteDatabase db = con.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id", campoId.getText().toString());
        values.put("nombre", camponombre.getText().toString());
        values.put("telefono", campotelefono.getText().toString());

        Long idResultado = db.insert(Utilidad.Tabla_Usuario, Utilidad.Campo_Id, values);

        Toast.makeText(getApplicationContext(), "id Registro : " + idResultado, Toast.LENGTH_LONG).show();

        db.close();

    }

}



