package com.example.dam.listadecontactosxml;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EditarTelefonosContacto extends Principal {

    private Context context;
    private ListView lv;
    private ClaseAdaptadorTelefonos adaptadorTelefonos;
    private Button anadir, cancelar;     //El boton añadir abrira un cuadro de dialogo, cancelar finalizará la actividad.
    private ArrayList<String> numeros;
    private Contacto contacto;
    private List<Contacto> con;
    //private EditText etNumNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anadir_numero);

        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private void init(){
        lv= (ListView) findViewById(R.id.listView2);
        anadir= (Button) findViewById(R.id.btAnadir);
        cancelar= (Button) findViewById(R.id.btCancelar);
        //etNumNew= (EditText) findViewById(R.id.etTelAnadir);


        //numeros= this.getIntent().getExtras().getStringArrayList("telefonos");
        int posicion= this.getIntent().getExtras().getInt("telefonos");
        Log.v("com", posicion+"");
        contacto= (Contacto) getIntent().getExtras().getSerializable("contacto");
        numeros= (ArrayList<String>) contacto.getTelefonos();

        Log.v("a", numeros + "");
        adaptadorTelefonos= new ClaseAdaptadorTelefonos(this, R.layout.item2, numeros);



        lv.setAdapter(adaptadorTelefonos);

        context= this.getApplicationContext();
    }

    public void borrar(View v){
        AlertDialog.Builder alert= new AlertDialog.Builder(this);
        alert.setTitle(R.string.str_borrar);
        LayoutInflater inflater= LayoutInflater.from(this);
        int res= R.layout.confirmacion_borrar;

        final View vista = inflater.inflate(R.layout.confirmacion_borrar, null);

        alert.setView(vista);

        final int position= (int) v.getTag();
        alert.setPositiveButton(R.string.str_si,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if(numeros.size()>1) {
                            numeros.remove(position);
                            contacto.setTelefonos(numeros);
                            adaptadorTelefonos.notifyDataSetChanged();
                        }else{
                            Toast.makeText(context, R.string.error2, Toast.LENGTH_LONG).show();
                        }
                    }
                });
        alert.setNegativeButton(R.string.cancelarbt, null);



        alert.show();
    }

    public void cancelar(View v){

        contacto.setTelefonos(numeros);
        this.getIntent().putExtra("contacto",contacto);
        setResult(RESULT_OK, this.getIntent());

        this.finish();
    }

    public void anadirEditarTelefonos(View v){
        final AlertDialog.Builder alert= new AlertDialog.Builder(this);
        alert.setTitle(R.string.anadirbt);
        LayoutInflater inflater= LayoutInflater.from(this);
        int res= R.layout.anadirtlf_layout;

        final EditText etNumNew= new EditText(this);
        etNumNew.setInputType(InputType.TYPE_CLASS_PHONE);
        etNumNew.setHint(R.string.telefono);
        final View vista = inflater.inflate(R.layout.anadirtlf_layout, null);

        alert.setView(vista);
        alert.setView(etNumNew);


        alert.setPositiveButton(R.string.anadirbt,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        String tel = etNumNew.getText().toString();
                        if (!tel.isEmpty()) {
                            numeros.add(tel);
                            adaptadorTelefonos.notifyDataSetChanged();
                            Toast.makeText(context, R.string.numana, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, R.string.intnum, Toast.LENGTH_LONG).show();
                        }

                    }
                });
        alert.setNegativeButton(R.string.cancelarbt, null);



        alert.show();
    }
}
