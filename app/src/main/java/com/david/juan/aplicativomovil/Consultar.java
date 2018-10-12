package com.david.juan.aplicativomovil;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.david.juan.aplicativomovil.Utilidades.Utilidad;

public class Consultar extends AppCompatActivity {
EditText camponombre,campoid,campotelefono;
 ConexionSQLiteHelper con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        con=new ConexionSQLiteHelper(getApplicationContext(),"bdusuarios",null,1);
        campoid=(EditText)findViewById(R.id.campoId);
        camponombre=(EditText)findViewById(R.id.campoNombreConsulta);
        campotelefono=(EditText)findViewById(R.id.campoTelefonoConsulta);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnConsultar:
                consultarsql();
                break;
            case R.id.btnactualizar:
                actualizarsql();
                break;
            case R.id.btneliminar:
                eliminarsql();
                break;
        }


    }



    private void actualizarsql() {
        SQLiteDatabase db=con.getWritableDatabase();
        String[] parametro={campoid.getText().toString()};
        ContentValues values=new ContentValues();
        values.put(Utilidad.Campo_Nombre,camponombre.getText().toString());
        values.put(Utilidad.Campo_Telefono,campotelefono.getText().toString());
db.update(Utilidad.Tabla_Usuario,values,Utilidad.Campo_Id+"=?",parametro);
Toast.makeText(getApplicationContext(),"Usuario actualizado",Toast.LENGTH_SHORT).show();
db.close();
    }

    private void consultarsql() {
        SQLiteDatabase db=con.getReadableDatabase();
        String[] parametro={campoid.getText().toString()};
        String[] campos={Utilidad.Campo_Nombre,Utilidad.Campo_Telefono};

        try {
            Cursor cursor=db.query(Utilidad.Tabla_Usuario,campos,Utilidad.Campo_Id+"=?",parametro,null,null,null);
            cursor.moveToFirst();
            camponombre.setText(cursor.getString(0));
            campotelefono.setText(cursor.getString(1));
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"No existe",Toast.LENGTH_LONG).show();
            clean();

        }
    }
    private void eliminarsql() {
        SQLiteDatabase db=con.getWritableDatabase();
        String[] parametro={campoid.getText().toString()};
        db.delete(Utilidad.Tabla_Usuario,Utilidad.Campo_Id+"=?",parametro);
        campoid.setText("");
        clean();
        db.close();
    }


    private void clean() {
        camponombre.setText("");
        campotelefono.setText("");
    }
}
