package com.example.dam.listadecontactosxml;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditarContacto extends Principal {

    private EditText etNom;
    private TextView tvId, tvNums;
    private Button btAcep, btCancel;

    private Contacto con;

    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_contacto);
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
        etNom = (EditText) findViewById(R.id.etNombreEditar);
        tvId= (TextView) findViewById(R.id.tvIdEditar);
        tvNums = (TextView) findViewById(R.id.tvTelefonosEditar);
        btAcep = (Button)findViewById(R.id.btAceptarEditar);
        btCancel = (Button)findViewById(R.id.btCancelarEditar);

        pos= this.getIntent().getExtras().getInt("contacto");
        con= super.getListaContactos().get(pos);
        ArrayList<String> nums= (ArrayList<String>) super.getListaContactos().get(pos).getTelefonos();

        for(String n: nums){
            tvNums.append(n);
            tvNums.append("\n");
        }

        etNom.setText(super.getListaContactos().get(pos).getNombre());
        tvId.setText("ID"+super.getListaContactos().get(pos).getId());
    }

    public void aceptarEditar(View v){
        Log.v("dfs", etNom.getText().toString()); //etNom tiene texto dentro, lo muestra.
        String nombre="";
        nombre= etNom.getText().toString();

        if(!nombre.isEmpty()){
            /*
            super.getClaseAdaptadorContatos().cambiarNombre(nombre,pos);
            super.adaptadorCambio();
            this.finish();

            int childs=((LinearLayout)vertical).getChildCount();
            for(int i=2; i<childs; i++){
                EditText et = (EditText) ((LinearLayout) ((LinearLayout) vertical).getChildAt(i)).getChildAt(1);
                c.getNumeros().set(i-2,et.getText().toString());
            }

            */
            con.setNombre(nombre);
            this.getIntent().putExtra("contacto",con);
            setResult(RESULT_OK, this.getIntent());
            finish();
        }else{
            Toast.makeText(this, R.string.intnomb, Toast.LENGTH_LONG).show();
        }
    }

    public void cancelarEditar(View v){
        super.adaptadorCambio();

        this.finish();

    }
}
