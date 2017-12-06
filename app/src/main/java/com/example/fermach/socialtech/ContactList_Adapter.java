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
 * Adaptador para mi lista de Contactos
 */

public class ContactList_Adapter extends ArrayAdapter<Contact> {


    public ContactList_Adapter( Context context, List<Contact> contacts) {
        super(context, 0, contacts);
    }
    public View getView(int position,  View convertView,  ViewGroup parent) {
        Contact c = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_item, parent, false);
        }

        TextView nombre = convertView.findViewById(R.id.personaNombre);
        TextView apellidos = convertView.findViewById(R.id.personaApellidos);
        TextView email = convertView.findViewById(R.id.personaEmail);
        TextView telefono = convertView.findViewById(R.id.personaTelefono);
        ImageView imagen=convertView.findViewById(R.id.imagen_en_lista);
        //ImageView imagen;

        nombre.setText("Nombre: "+c.getNombre());
        apellidos.setText("Apellido: "+c.getApellido());
        email.setText("Email: "+c.getEmail());
        telefono.setText("Telefono: "+c.getTelefono());
        imagen.setImageResource(c.getImagen());
        //imagen.setImageResource();

        return convertView;
    }

}



