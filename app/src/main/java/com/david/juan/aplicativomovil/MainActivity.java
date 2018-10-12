package com.david.juan.aplicativomovil;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.david.juan.aplicativomovil.Entidades.Usuario;
import com.david.juan.aplicativomovil.Utilidades.Utilidad;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listapersonas;
    ArrayList<String> listainformacion;
    ArrayList<Usuario> listausuarios;
    FloatingActionButton fab;

    ConexionSQLiteHelper con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        con=new ConexionSQLiteHelper(getApplicationContext(),"bdusuarios",null,1);
        fab=(FloatingActionButton)findViewById(R.id.FabAddRegistro);
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

                showalertedit(position);
                return true;
            }

        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Registro.class);
                startActivity(intent);
            }
        });

    }

    private void showalertedit(final int posicion) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        String[] lista={"Editar"};
        builder.setTitle("Opciones");
        builder.setItems(lista, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Usuario user=listausuarios.get(posicion);
                // Intent intent=new Intent(Lista.this,Detalleusuario.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("usuario",user);

                //intent.putExtras(bundle);
                //startActivity(intent);
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
