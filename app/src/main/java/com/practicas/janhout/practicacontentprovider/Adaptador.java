package com.practicas.janhout.practicacontentprovider;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adaptador extends CursorAdapter {

    private int recurso;
    private static LayoutInflater i;
    private Context contexto;

    public Adaptador(Context co, int recurso, Cursor cu) {
        super(co, cu, true);
        this.recurso = recurso;
        this.contexto = co;
        this.i = (LayoutInflater)co.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = i.inflate(recurso, parent, false);
        ViewHolder vh = new ViewHolder();

        vh.tvLocalidad = (TextView)view.findViewById(R.id.tvLocalidad);
        vh.tvPrecio = (TextView)view.findViewById(R.id.tvPrecio);
        vh.tvDireccion = (TextView)view.findViewById(R.id.tvDireccion);
        vh.ivTipo = (ImageView)view.findViewById(R.id.ivFoto);
        view.setTag(vh);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder vh = (ViewHolder) view.getTag();

        String localidad = cursor.getString(0);
        String tipo = cursor.getString(1);
        int precio = cursor.getInt(2);
        String calle = cursor.getString(3);
        String numero = cursor.getString(4);
        int subido = cursor.getInt(5);

        vh.tvLocalidad.setText(localidad + " ("+tipo+")");
        vh.tvPrecio.setText(precio+"");
        vh.tvDireccion.setText(calle + " " + numero);

        int imagen = conseguirImagen(tipo);
        vh.ivTipo.setImageResource(imagen);
        if(subido == 1){
            vh.ivTipo.setBackgroundResource(R.drawable.borde_subido);
        }else{
            vh.ivTipo.setBackgroundResource(R.drawable.borde_sin_subir);
        }
    }

    private int conseguirImagen(String tipo){
        int resultado;
        if(tipo.equals(contexto.getString(R.string.local_tipo))){
            resultado = R.drawable.local;
        }else if(tipo.equals(contexto.getString(R.string.adosada_tipo))){
            resultado = R.drawable.adosada;
        }else if(tipo.equals(contexto.getString(R.string.chalet_tipo))){
            resultado = R.drawable.chalet;
        }else if(tipo.equals(contexto.getString(R.string.parcela_tipo))){
            resultado = R.drawable.parcela;
        }else if(tipo.equals(contexto.getString(R.string.cortijo_tipo))){
            resultado = R.drawable.cortijo;
        }else if(tipo.equals(contexto.getString(R.string.piso_tipo))){
            resultado = R.drawable.piso;
        }else {
            resultado = R.drawable.otro;
        }
        return resultado;
    }

    public static class ViewHolder{
        public TextView tvLocalidad, tvPrecio, tvDireccion;
        public ImageView ivTipo;
    }
}
