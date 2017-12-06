package com.example.fermach.socialtech;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Companies_form extends Fragment implements View.OnClickListener, Serializable{

    //usando la libreria Butterknife para enlazar mis layouts
    @BindView(R.id.form_company) View view;
    @BindView(R.id.editText_nombre_empresa) EditText ET_nombre;
    @BindView(R.id.editText_contacto_empresa) EditText ET_contacto;
    @BindView(R.id.editText_email_empresa) EditText ET_email;
    @BindView(R.id.editText_direccion_empresa) EditText ET_direccion;
    @BindView(R.id.editText_localidad_empresa) EditText ET_localidad;
    @BindView(R.id.editText_observaciones_empresa) EditText ET_observaciones;
    @BindView(R.id.editText_telefono_empresa) EditText ET_telefono;
    @BindView(R.id.spinner_provincia_empresa) AdapterView spinner_provincia;
    @BindView(R.id.textView_num_empresas) TextView num_companies;
    @BindView(R.id.btn_alta_empresa) Button btn_alta;
    @BindView(R.id.btn_listaEmpresas) Button btn_listaCompanies;
    public final static String COMPANY="COMPANY";
    private CompanyList_Adapter adaptador;
    private Company empresa;
    private Contact contacto;
    private ArrayList<Contact>lista_de_contactos;
    private ArrayList<Company>lista_de_empresas;
    private Company newCompany;
    int companies_in_list;
    private FirebaseDatabase database;
    private DatabaseReference ref_companies;
    private DatabaseReference ref_contacts;
    private Fragment fragment;
    View myView;

    public Companies_form(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView=  inflater.inflate(R.layout.company_form, container, false);
        ButterKnife.bind(this, myView);

        fragment = new Companies_form();
        newCompany= new Company();
        lista_de_contactos= new ArrayList<>();
        lista_de_empresas= new ArrayList<>();
        contacto=new Contact();
        empresa= new Company();

        //atrapo los contactos y las empresas de mi base de datos
        database =FirebaseDatabase.getInstance();
        ref_companies= database.getReference("companies");
        ref_contacts= database.getReference("contacts");


        ref_companies.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //cada vez que detecta una empresa actualiza el textView y la añade a mi lista
                newCompany = dataSnapshot.getValue(Company.class);
                companies_in_list++;
                lista_de_empresas.add(newCompany);
                num_companies.setText("Empresas agregadas: "+ companies_in_list);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {


            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ref_contacts.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //cada vez que detecta un contacto lo añade a mi lista
                contacto = dataSnapshot.getValue(Contact.class);
                lista_de_contactos.add(contacto);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        String[] valores_provincia= {"Jaen","Malaga","Sevilla",
                "Cordoba","Almeria","Huelva","Cadiz","Granada"};

        spinner_provincia.setAdapter(new ArrayAdapter<String>
                (getContext(),R.layout.support_simple_spinner_dropdown_item,valores_provincia));

        btn_alta.setOnClickListener(this);
        btn_listaCompanies.setOnClickListener(this);




        if(savedInstanceState!=null){
            empresa= (Company) savedInstanceState.getSerializable(COMPANY);

        }
        return myView;
    }

    /**
     * Método para detectar los botones clickeados
     *
     * @param view
     */
    public void onClick(View view) {
        switch (view.getId()){
            //si selecciono el boton de nueva empresa
            case R.id.btn_alta_empresa:
                Log.i("info","Has pulsado alta");
                Log.i("valido?", String.valueOf(comprobarDatos()));

                //si los valores esenciales de los TextView son correctos
                if(comprobarDatos()){

                    String nombre = ET_nombre.getText().toString();
                    String direccion = ET_direccion.getText().toString();
                    String localidad = ET_localidad.getText().toString();
                    String provincia = spinner_provincia.getSelectedItem().toString();
                    String telefono = ET_telefono.getText().toString();
                    String observaciones = ET_observaciones.getText().toString();
                    String contacto= ""+ET_contacto.getText();
                    String email=ET_email.getText().toString();

                    //creo una empresa con los datos
                    empresa = new Company(nombre, direccion, localidad, provincia, telefono, email, observaciones, contacto);
                    Log.i("info", String.valueOf(empresa));

                    /*
                     * si deja el contacto asociado en blanco lo añade como vacio
                     *
                     * si introduce datos comprueba que esta en la lista de contactos
                      */

                    if(contacto.trim()=="" ||comprobarContactoAsociado(empresa)) {
                        ref_companies.push().setValue(empresa);
                        Snackbar.make(this.view,"Empresa añadida con éxito", Snackbar.LENGTH_SHORT).show();


                    }else{

                        Snackbar.make(this.view,"Su contacto asociado no está agregado a su lista de contactos", Snackbar.LENGTH_SHORT).show();
                    }


                }
                break;

            //si seleciono mi lista de contactos
            case R.id.btn_listaEmpresas:
                Fragment f=new CompanyList();

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main,f).commit();

                break;


        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("miFragment", (Serializable) fragment);
    }

    /**
     * Método para comprobar los datos introducidos en algunos de los textview del formulario
     * para evitar añadir empresas sin sentido y que muestra un mensaje con los campos erroneos
     *
     * @return valido
     */
     public boolean comprobarDatos() {
        boolean valido=true;
        String msg_error="Error en los siguientes campos:  ";

        if( ET_nombre.getText().toString().trim().equalsIgnoreCase("")
                || ET_nombre.getText().toString().trim().length() >30) {
            valido = false;
            msg_error = msg_error + " Nombre,";

        }if(!ET_telefono.getText().toString().trim().matches("(\\+34|0034|34)?[ -]*(6|7)[ -]*([0-9][ -]*){8}")) {
            valido = false;
            msg_error = msg_error + " Teléfono,";
        }if(ET_email.getText().toString().trim().equalsIgnoreCase("")
                || !ET_email.getText().toString().trim().matches("[A-Za-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")) {
            valido = false;
            msg_error = msg_error + " Email,";
        }

        msg_error= msg_error.substring(0,msg_error.length()-1);

        if(!valido) {

            Snackbar.make(this.view, msg_error, Snackbar.LENGTH_LONG).show();
        }
        return valido;
    }

    /**
     * Método para controlar que el contacto asociado de mi empresa esté en mi lista de contactos
     * según su nombre y apellidos
     *
     * @param company
     * @return itsInMyList
     */
    public boolean comprobarContactoAsociado(Company company){
        boolean itsInMyList=false;
        for(Contact contact:lista_de_contactos){

            if(company.getContacto_asociado().trim().equals((contact.getNombre()+contact.getApellido()).trim())) {


                itsInMyList = true;

            }
        }

        return itsInMyList;
    }


}

