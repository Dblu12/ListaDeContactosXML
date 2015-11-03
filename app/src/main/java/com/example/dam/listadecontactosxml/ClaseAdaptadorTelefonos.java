package com.example.dam.listadecontactosxml;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by David on 13/10/2015.
 */
public class ClaseAdaptadorTelefonos extends ArrayAdapter<Contacto> {



    private int res;
    private LayoutInflater lInflator;
    private ArrayList<String> numeros;
    private Context con;

    static class ViewHolder {

        public TextView tv;
        public ImageView iv;

    }

    public ClaseAdaptadorTelefonos(Context context, int resource, ArrayList objects) {
        super(context, resource, objects);

        this.res = resource; // LAYOUT
        this.numeros = objects; // LISTA DE VALORES
        this.con = context; // ACTIVIDAD

        lInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder gv = new ViewHolder();
        if (convertView == null) {
            convertView = lInflator.inflate(res, null);


            TextView tv = (TextView) convertView.findViewById(R.id.tvNum);
            gv.tv = tv;
            ImageView iv = (ImageView) convertView.findViewById(R.id.ivBorrar);
            gv.iv = iv;

            convertView.setTag(gv);
        } else {
            gv = (ViewHolder) convertView.getTag();
        }

        gv.tv.setText(numeros.get(position));

        gv.iv.setTag(position);
        return convertView;


    }
}
