package com.david.juan.aplicativomovil;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.david.juan.aplicativomovil.Entidades.Usuario;
import com.david.juan.aplicativomovil.Utilidades.Utilidad;

import java.util.ArrayList;

public class Lista extends AppCompatActivity {

    ListView listapersonas;
    ArrayList<String> listainformacion;
    ArrayList<Usuario> listausuarios;

    ConexionSQLiteHelper con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
    con=new ConexionSQLiteHelper(getApplicationContext(),"bdusuarios",null,1);

    listapersonas=(ListView)findViewById(R.id.listausuarios);
    consultarlistapersonas();
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listainformacion);
        listapersonas.setAdapter(adapter);
        listapersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String informacion="id: "+listausuarios.get(position).getId()+"\n";
                informacion+="Nombre: "+ listausuarios.get(position).getNombre()+"\n";
                informacion+="Telefono: "+ listausuarios.get(position).getTelefono()+"\n";
                Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_LONG).show();

                Usuario user=listausuarios.get(position);
               // Intent intent=new Intent(Lista.this,Detalleusuario.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("usuario",user);

                //intent.putExtras(bundle);
                //startActivity(intent);
            }
        });
        listapersonas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "LongClicked", Toast.LENGTH_LONG).show();
            return true;
            }

        });

    }

    private void consultarlistapersonas() {

        SQLiteDatabase db=con.getReadableDatabase();

        Usuario usuario=null;
        listausuarios=new ArrayList<Usuario>();
        Cursor cursor=db.rawQuery("Select * from "+ Utilidad.Tabla_Usuario,null);

        while (cursor.moveToNext()){
            usuario=new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setTelefono(cursor.getString(2));

            listausuarios.add(usuario);

        }
        obtenerlista();
    }

    private void obtenerlista() {
    listainformacion=new ArrayList<String>();
    for (int i = 0; i<listausuarios.size();i++){
        listainformacion.add("ID " + listausuarios.get(i).getId()+ "Nombre: "+listausuarios.get(i).getNombre());
    }
    }

}
