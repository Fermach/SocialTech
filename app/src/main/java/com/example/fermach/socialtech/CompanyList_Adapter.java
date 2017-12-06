package com.example.fermach.socialtech;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Adaptador para mi lista de Empresas
 */

public class CompanyList_Adapter extends ArrayAdapter<Company> {



    public CompanyList_Adapter(Context context, List<Company> companies) {
        super(context, 0, companies);
    }
    public View getView(int position,  View convertView,  ViewGroup parent) {
        Company c = getItem(position);


        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.company_item, parent, false);
        }

        TextView nombre = convertView.findViewById(R.id.empresaNombre);
        TextView provincia = convertView.findViewById(R.id.empresaProvincia);
        TextView email = convertView.findViewById(R.id.empresaEmail);
        TextView telefono = convertView.findViewById(R.id.empresaTelefono);


        nombre.setText("Nombre: "+c.getNombre());
        provincia.setText("Provincia: "+c.getProvincia());
        email.setText("Email: "+c.getEmail());
        telefono.setText("Telefono: "+c.getTelefono());

        return convertView;
    }

}



