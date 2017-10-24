package com.example.fermach.socialtech;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by matinal on 17/10/2017.
 */

public class ContactAdapter extends ArrayAdapter<Contact> {

    public ContactAdapter(@NonNull Context context, @NonNull ArrayList<Contact> contacts) {
        super(context, 0, contacts);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Contact c = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_item, parent, false);
        }

        TextView nombre = convertView.findViewById(R.id.personaNombre);
        TextView apellidos = convertView.findViewById(R.id.personaApellidos);
        TextView telefono = convertView.findViewById(R.id.personaTelefono);
        TextView email = convertView.findViewById(R.id.personaEmail);
        TextView formacion = convertView.findViewById(R.id.personaFormacion);
        TextView provincia = convertView.findViewById(R.id.personaProvincia);
        TextView edad = convertView.findViewById(R.id.personaEdad);
        TextView sexo = convertView.findViewById(R.id.personaSexo);

        nombre.setText("Nombre: "+c.getNombre());
        apellidos.setText("Apellidos: "+c.getApellido());
        telefono.setText("Telefono: "+c.getTelefono());
        email.setText("Email: "+c.getEmail());
        formacion.setText("Formacion: "+c.getFormacion());
        provincia.setText("Provincia: "+c.getProvincia());
        edad.setText("Edad: "+c.getEdad());
        sexo.setText("Sexo: "+c.getSexo());

        return convertView;
    }

}
