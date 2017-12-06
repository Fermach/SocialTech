package com.example.fermach.socialtech;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CompanyItem extends Fragment implements Serializable {

    private Company company;
    //usando la libreria Butterknife para enlazar mis layouts
    @BindView(R.id.empresaNombre_lista) TextView nombre;
    @BindView(R.id.empresaProvincia_lista) TextView provincia;
    @BindView(R.id.empresaLocalidad_lista) TextView localidad;
    @BindView(R.id.empresaDireccion_lista) TextView direccion;
    @BindView(R.id.empresaTelefono_lista) TextView telefono;
    @BindView(R.id.empresaEmail_lista) TextView email;
    @BindView(R.id.empresaObservaciones_lista) TextView observaciones;
    @BindView(R.id.empresaContacto_lista) TextView contacto;

    public CompanyItem(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View myView= inflater.inflate(R.layout.company_list, container, false);
        ButterKnife.bind(this,myView);
        //atrapamos los datos de la empresa clickada en mi listView
        Bundle args = getArguments();

        company =(Company) args
                .getSerializable("COMPANY");

        Log.i("compañia clickeada:",company.toString());
        //seteamos los TextView con los datos de esa empresa
        nombre.setText(company.getNombre());
        provincia.setText(company.getProvincia());
        email.setText(company.getEmail());
        localidad.setText(company.getLocalidad());
        direccion.setText(""+company.getDireccion());
        observaciones.setText(company.getObservaciones());
        contacto.setText(company.getContacto_asociado());
        telefono.setText(company.getTelefono());

        return myView;
    }



}
