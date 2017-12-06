package com.example.fermach.socialtech;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * Adaptador para mi lista de Imagenes
 */

public class Pictures_Adapter extends ArrayAdapter<Picture> {


    public Pictures_Adapter(Context context, List<Picture> pictures) {
        super(context, 0, pictures);
    }
    public View getView(int position,  View convertView,  ViewGroup parent) {
        Picture p= getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.picture_item, parent, false);
        }

        TextView nombre = convertView.findViewById(R.id.pictureNombre);
        ImageView imageView=convertView.findViewById(R.id.mImagen);
        //ImageView imagen;

        nombre.setText(p.getNombre());
        imageView.setImageResource(p.getImagen());
        //imagen.setImageResource();

        return convertView;
    }

}



