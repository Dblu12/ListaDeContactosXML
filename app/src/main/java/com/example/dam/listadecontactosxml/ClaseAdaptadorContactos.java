package com.example.dam.listadecontactosxml;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ClaseAdaptadorContactos extends ArrayAdapter<Contacto> {


    private int res;
    private LayoutInflater lInflator;
    private List<Contacto> contactos;
    private Context con;

    static class ViewHolder {

        public TextView tv, tv1;
        public ImageView iv, iv1;

    }


    public ClaseAdaptadorContactos(Context context, int resource, List<Contacto> objects) {

        super(context, resource, objects);

        this.res = resource; // LAYOUT
        this.contactos = objects; // LISTA DE VALORES
        this.con = context; // ACTIVIDAD

        lInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder gv = new ViewHolder();
        if (convertView == null) {
            convertView = lInflator.inflate(res, null);


            TextView tv = (TextView) convertView.findViewById(R.id.tvNombre);
            gv.tv = tv;
            TextView tv1 = (TextView) convertView.findViewById(R.id.tvTelefono);
            gv.tv1 = tv1;
            ImageView iv = (ImageView) convertView.findViewById(R.id.ivFoto);
            gv.iv = iv;
            ImageView iv1 = (ImageView) convertView.findViewById(R.id.ivSigno);

            gv.iv1 = iv1;
            gv.iv1.setTag(position);
            convertView.setTag(gv);
        } else {
            gv = (ViewHolder) convertView.getTag();
        }
        addlistener(gv.iv1, position, gv, contactos.get(position));
        gv.tv.setText(contactos.get(position).getNombre());
        gv.tv1.setText(contactos.get(position).getTelefonos().get(0));

        if (contactos.get(position).getTelefonos().size() > 1) {
            gv.iv.setImageResource(R.drawable.mas);

        } else {
            gv.iv.setImageResource(R.drawable.menos);


        }
        gv.iv1.setFocusable(true);
        gv.iv1.setClickable(true);

        //--

        gv.iv.setTag(position);

        return convertView;
    }

    public boolean borrar(int position) {
        try {
            contactos.remove(position);
            this.notifyDataSetChanged();
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    public void mostrarNumeros(){

    }

    public void cambiarNombre(String nom, int pos){
        contactos.get(pos).setNombre(nom);
    }

    public void anadir(String nom, ArrayList<String> tlfs) {


        this.notifyDataSetChanged();


    }

    private void addlistener(ImageView iv, int pos, ViewHolder vh, Contacto c) {
        final ViewHolder vv = vh;
        final int post = pos;
        final Contacto contacto= c;
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contacto.getTelefonos().size()>1){

                }
            }
        });
    }
}