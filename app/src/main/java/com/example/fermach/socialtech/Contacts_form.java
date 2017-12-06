package com.example.fermach.socialtech;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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


public class Contacts_form extends Fragment implements View.OnClickListener, Serializable, SeekBar.OnSeekBarChangeListener {

    //usando la libreria Butterknife para enlazar mis layouts
    @BindView(R.id.form_contact) View view;
    @BindView(R.id.editText_nombre) EditText ET_nombre;
    @BindView(R.id.editText_apellido) EditText ET_apellidos;
    @BindView(R.id.editText_email) EditText ET_email;
    @BindView(R.id.editText_numero) EditText ET_telefono;
    @BindView(R.id.btn_alta) Button btn_alta;
    @BindView(R.id.btn_listaContacts) Button btn_listaContactos;
    @BindView(R.id.multiAutoComplete_formacion) MultiAutoCompleteTextView multiAutoComplete_formacion;
    @BindView(R.id.spinner_provincia) AdapterView spinner_provincia;
    @BindView(R.id.textView_num_contactos) TextView num_contactos;
    @BindView(R.id.textView_edad_elegida) TextView edad_elegida;
    @BindView(R.id.seekBar)  SeekBar seekbar_edad;
    @BindView(R.id.Rgroup) RadioGroup rg;
    @BindView(R.id.radio1) RadioButton rb_hombre;
    @BindView(R.id.radio2) RadioButton rb_mujer;
    @BindView(R.id.fab_contacto)FloatingActionButton fab;
    public final static String CONTACTO="CONTACTO";
    private ContactList_Adapter adaptador;
    private Contact contacto;
    private Contact nuevoContacto;
    private ArrayList<Contact> contactos;
    private int edad;
    int contacts_in_list;
    private String sexo=null;
    private Picture imagen;
    private FirebaseDatabase database;
    private DatabaseReference ref;

    public Contacts_form(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.contact_form, container, false);
        ButterKnife.bind(this,myView);

        //atrapo los contactos de mi base de datos
        database =FirebaseDatabase.getInstance();
        ref= database.getReference("contacts");

        contacts_in_list = 0;
        nuevoContacto= new Contact();
        contactos= new ArrayList<>();
        contacto=new Contact();
        imagen=new Picture();

        Bundle args = getArguments();

        //si no le paso argumentos en el bundle se queda la imagen por defecto
        if(args != null) {

            imagen = (Picture) args
                    .getSerializable("PICTURE");
        }else{

            imagen = new Picture(R.drawable.account, "defecto");
        }

        //al clicear sobre el fab me leva a una lista con imagenes(Picture)
        //para elegir el ImageResource
        fab.setImageResource(imagen.getImagen());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment=new PicturesList();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).commit();
            }
        });



        //cada vez que detecta un contacto actualiza el textView y lo añade a mi lista
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                nuevoContacto = dataSnapshot.getValue(Contact.class);
                contacts_in_list++;
                contactos.add(nuevoContacto);
                num_contactos.setText("Contactos agregados: "+ contacts_in_list);
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


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup rg, int checkedId) {
                if (checkedId == R.id.radio1) {
                    sexo = (String) rb_hombre.getText();
                }else if(checkedId == R.id.radio2) {
                    sexo = (String) rb_mujer.getText();

                }
            }
        });

        seekbar_edad.setMax(150);
        seekbar_edad.setOnSeekBarChangeListener(this);
        edad_elegida.setText(""+seekbar_edad.getProgress()+" /"+seekbar_edad.getMax());

        String[] valores_formacion= {"SMR","DAM","DAW",
                "ASIR","Ingenieria Informática","Grado","Otros"};

        String[] valores_provincia= {"Jaen","Malaga","Sevilla",
                "Cordoba","Almeria","Huelva","Cadiz","Granada"};

        multiAutoComplete_formacion.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        multiAutoComplete_formacion.setAdapter(new ArrayAdapter<String>
                (getContext(),R.layout.support_simple_spinner_dropdown_item,valores_formacion));

        spinner_provincia.setAdapter(new ArrayAdapter<String>
                (getContext(),R.layout.support_simple_spinner_dropdown_item,valores_provincia));

        btn_alta.setOnClickListener(this);
        btn_listaContactos.setOnClickListener(this);




        if(savedInstanceState!=null){
            contacto= (Contact) savedInstanceState.getSerializable(CONTACTO);

        }

     return myView;
    }

    /**
     * Metodo para detectar los botones clickeados
     *
     * @param view
     */
     public void onClick(View view) {
        switch (view.getId()){

            case R.id.btn_alta:
                Log.i("info","Has pulsado alta");
                Log.i("valido?", String.valueOf(comprobarDatos()));

                //si los valores esenciales de los TextView son correctos
                if(comprobarDatos()){


                    String nombre = ET_nombre.getText().toString();
                    String apellido = ET_apellidos.getText().toString();
                    String email = ET_email.getText().toString();
                    String telefono = ET_telefono.getText().toString();
                    String formacion = multiAutoComplete_formacion.getText().toString();
                    String provincia = spinner_provincia.getSelectedItem().toString();
                    //cojo el atributo de la imagen del mi objeto picture que he pasado desde mi fragmento
                    // con milista de imagenes con un bundle(ya que el nombre solo o necesito para ese fragmento)
                    int myImagen= imagen.getImagen();
                    edad= seekbar_edad.getProgress();

                    //creo un contacto con los datos
                    contacto = new Contact(myImagen, nombre, apellido, telefono, email, formacion, provincia, edad, sexo);
                    Log.i("info", String.valueOf(contacto));


                    //compruebo que el numero introducido no esta en mi lista
                    if(!comprobarContactoEnLista(contacto)){
                        ref.push().setValue(contacto);

                    }else{

                       // si esta en mi lista me muestra una alerta para comprobar si de verdad
                       // quiero añadir un contacto repetido a mi lista
                        Snackbar.make(this.view,"Ese telefono ya se encuentra en su lista", Snackbar.LENGTH_SHORT).show();
                        AlertDialog.Builder myBuild = new AlertDialog.Builder(view.getContext());
                        myBuild.setMessage("¿Estás seguro de que deseas añadir de nuevo ese teléfono a tu lista de contactos?");
                        myBuild.setTitle("Contacto repetido");
                        myBuild.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ref.push().setValue(contacto);
                            }
                        });
                        myBuild.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        myBuild.show();
                    }


                }
                break;


            case R.id.btn_listaContacts:
                Fragment f=new ContactsList();

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main,f).commit();

                break;


        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(CONTACTO, contacto);

    }

    /**
     * Método para detectar el cambio en mi seekbar y actualizar el texto con el progreso
     * @param seekBar
     * @param progress
     * @param b
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        edad=progress;
        edad_elegida.setText(""+progress+" /"+seekbar_edad.getMax());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    /**
     * Metodo para setear el texto de de la progresión de la barra en la posición que se ha detenido
     * @param seekBar
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        edad_elegida.setText(""+ edad+" /"+ seekbar_edad.getMax());
    }

    /**
     * Método para comprobar los datos introducidos en algunos de los textview del formulario y
     * evitar añadir contactos sin sentido y que muestra un mensaje con los campos erroneos
     *
     * @return valido
     */
    public boolean comprobarDatos() {
        boolean valido=true;
        String msg_error="Error en los siguientes campos:  ";
        String msg_exito="Contacto añadido con éxito";

        if( ET_nombre.getText().toString().trim().equalsIgnoreCase("")
                || ET_nombre.getText().toString().trim().length() >30) {
            valido = false;
            msg_error = msg_error + " Nombre,";

        }if(ET_apellidos.getText().toString().trim().equalsIgnoreCase("")
                || ET_apellidos.getText().toString().trim().length() >30) {
            valido = false;
            msg_error = msg_error + "  Apellido,";
        }if(sexo ==null) {
            valido = false;
            msg_error = msg_error + " Sexo,";
        }if(!ET_telefono.getText().toString().trim().matches("(\\+34|0034|34)?[ -]*(6|7)[ -]*([0-9][ -]*){8}")) {
            valido = false;
            msg_error = msg_error + " Teléfono,";
        }if(ET_email.getText().toString().trim().equalsIgnoreCase("")
                || !ET_email.getText().toString().trim().matches("[A-Za-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")) {
            valido = false;
            msg_error = msg_error + " Email,";
        }if(seekbar_edad.getProgress() ==0) {
            valido=false;
            msg_error=msg_error+" Edad,";
        }

        msg_error= msg_error.substring(0,msg_error.length()-1);

        if(valido){
            Snackbar.make(this.view,msg_exito, Snackbar.LENGTH_SHORT).show();

        }else{

            Snackbar.make(this.view,msg_error, Snackbar.LENGTH_LONG).show();
        }
        return valido;
    }


    /**
     * Método para comprobar si el número de teléfono de un contacto ya esta en mi lista
     *
     * @param contact
     * @return itsInMyList
     */
    public boolean comprobarContactoEnLista(Contact contact){
        boolean itsInMyList= false;

        for(Contact c: contactos){
            if(c.getTelefono().equals(contact.getTelefono())){

                itsInMyList=true;
            }
        }

        return itsInMyList;
    }
}


