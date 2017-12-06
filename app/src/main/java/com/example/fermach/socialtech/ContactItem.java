package com.example.fermach.socialtech;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;



public class ContactItem extends Fragment implements Serializable{

    private Contact contact;
    //usando la libreria Butterknife para enlazar mis layouts

    @BindView(R.id.personaNombre_lista) TextView nombre;
    @BindView(R.id.personaEmail_lista) TextView email;
    @BindView(R.id.personaSexo_lista) TextView sexo;
    @BindView(R.id.personaEdad_lista) TextView edad;
    @BindView(R.id.personaPoblacion_lista) TextView poblacion;
    @BindView(R.id.personaFormacion_lista) TextView formacion;
    @BindView(R.id.personaTelefono_lista) TextView telefono;
    @BindView(R.id.personaApellidos_lista) TextView apellidos;
    @BindView(R.id.fab_detalle_contacto) FloatingActionButton fab;

    public ContactItem(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView= inflater.inflate(R.layout.contact_list, container, false);
        ButterKnife.bind(this, myView);
        //atrapamos los datos de el contacto clickado en mi listView

        Bundle args = getArguments();

        contact =(Contact) args
                .getSerializable("CONTACT");

        Log.i("contacto clickeado:",contact.toString());

        //seteamos los TextView con los datos de ese contacto
        nombre.setText(contact.getNombre());
        apellidos.setText(contact.getApellido());
        email.setText(contact.getEmail());
        sexo.setText(contact.getSexo());
        edad.setText(""+contact.getEdad());
        poblacion.setText(contact.getProvincia());
        formacion.setText(contact.getFormacion());
        telefono.setText(contact.getTelefono());
        fab.setImageResource(contact.getImagen());

        return myView;
    }
}
