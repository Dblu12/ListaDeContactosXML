package com.example.dam.listadecontactosxml;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AnadirContacto extends Principal{

    private EditText nom, tel1, tel2, tel3, tel4;
    private Button btAcep, btCancel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_contacto);
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
        nom= (EditText) findViewById(R.id.etNombreAgregar);
        tel1= (EditText) findViewById(R.id.etTel1Agregar);
        tel2= (EditText) findViewById(R.id.etTel2Agregar);
        tel3= (EditText) findViewById(R.id.etTel3Agregar);
        tel4= (EditText) findViewById(R.id.etTel4Agregar);

        btAcep= (Button) findViewById(R.id.btAceptarNuevo);
        btCancel= (Button) findViewById(R.id.btCancelarNuevo);
    }

    public void aceptarAgregar(View v){
        String nombre= nom.getText().toString();
        String tele1= tel1.getText().toString();
        String tele2= tel2.getText().toString();
        String tele3= tel3.getText().toString();
        String tele4= tel4.getText().toString();

        List<String> tlfs= new ArrayList<>();

        if(!tele1.isEmpty())
            tlfs.add(tele1);
        if(!tele2.isEmpty())
            tlfs.add(tele2);
        if(!tele3.isEmpty())
            tlfs.add(tele3);
        if(!tele4.isEmpty())
            tlfs.add(tele4);

        if(!nombre.isEmpty() && !tlfs.isEmpty()){
            Random rnd= new Random();
            Contacto a= new Contacto(rnd.nextInt(),nombre, tlfs, false);

            Toast.makeText(this, R.string.conana, Toast.LENGTH_LONG);

            this.getIntent().putExtra("contacto",a);
            setResult(2, this.getIntent());
            finish();
        }else{
            Toast.makeText(this, R.string.error1, Toast.LENGTH_LONG);
        }
    }

    public void cancelarAgregar(View v){
        finish();
    }
}
