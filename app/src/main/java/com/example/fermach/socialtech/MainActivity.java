package com.example.fermach.socialtech;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, Serializable {

    private ListView listView;
    protected static ArrayList<Contact> contactos;
    private EditText ET_nombre;
    private EditText ET_apellidos;
    private EditText ET_email;
    private EditText ET_telefono;
    private Button btn_alta;
    private Button btn_cancelar;
    private Button btn_listaContactos;
    private AdapterView spinner_formacion;
    private TextView num_contactos;
    private ContactAdapter adaptador;
    private Contact contacto;
    public final static String CONTACTO="CONTACTO";
    public final static String CONTACTOS="CONTACTOS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);



        contactos = new ArrayList<>();
        listView=(ListView)findViewById(R.id.lista);
        ET_nombre= (EditText)findViewById(R.id.editText_nombre) ;
        ET_apellidos= (EditText)findViewById(R.id.editText_apellido) ;
        ET_email= (EditText)findViewById(R.id.editText_email) ;
        ET_telefono= (EditText)findViewById(R.id.editText_numero) ;
        btn_alta=(Button)findViewById(R.id.btn_alta);
        btn_cancelar=(Button)findViewById(R.id.btn_cancelar);
        btn_listaContactos=(Button)findViewById(R.id.btn_listaContacts);
        num_contactos= (TextView) findViewById(R.id.textView_num_contactos);
        spinner_formacion=(Spinner)findViewById(R.id.spinner_formacion);


        String[] valores_spinner= {"SMR","DAM","DAW",
                "ASIR","Ingenieria Informática","Grado","Otros"};

        spinner_formacion.setAdapter(new ArrayAdapter<String>
                (this,R.layout.support_simple_spinner_dropdown_item,valores_spinner));
        btn_alta.setOnClickListener(this);
        btn_cancelar.setOnClickListener(this);
        btn_listaContactos.setOnClickListener(this);
        num_contactos.setText("Contactos agregados: "+contactos.size());


        if(savedInstanceState!=null){
            contacto= (Contact) savedInstanceState.getSerializable(CONTACTO);
            contactos= (ArrayList<Contact>) savedInstanceState.getSerializable(CONTACTOS);


        }

    }


    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btn_alta:
               Log.i("info","Has pulsado alta");

                if(ET_nombre.getText().toString().trim().equalsIgnoreCase("") || ET_apellidos.getText().toString().trim().equalsIgnoreCase("")
                        || ET_email.getText().toString().trim().equalsIgnoreCase("") || ET_telefono.getText().toString().trim().equalsIgnoreCase("")){

                }else{
                    if(ET_telefono.getText().toString().matches("(\\+34|0034|34)?[ -]*(6|7)[ -]*([0-9][ -]*){8}")) {


                            String nombre = ET_nombre.getText().toString();
                            String apellido = ET_apellidos.getText().toString();
                            String email = ET_email.getText().toString();
                            String telefono = ET_telefono.getText().toString();
                            String formacion = spinner_formacion.getSelectedItem().toString();

                            contacto = new Contact(nombre, apellido, email, telefono, formacion);
                            Log.i("info", String.valueOf(contacto));
                        /*
                        for (Contact c: contactos) {
                             if(c.getNombre().equals(contacto.getNombre()) && c.getApellido().equals(contacto.getApellido())){
                                Toast.makeText(this,"Ese contacto ya esta en la lista",Toast.LENGTH_SHORT).show();
                                break;
                            }
                        }*/
                            contactos.add(contacto);
                            Log.i("info", "Lista de contactos: \n" + contactos);

                            num_contactos.setText("Contactos agregados: " + contactos.size());

                    }else{

                        Toast.makeText(this,"Telefono no valido",Toast.LENGTH_SHORT).show();

                    }
                }
                break;
            case R.id.btn_cancelar:
                Log.i("info","Has pulsado cancelar");

                ET_nombre.setText("");
                ET_apellidos.setText("");
                ET_email.setText("");
                ET_telefono.setText("");

                break;

            case R.id.btn_listaContacts:
                Intent intent =new Intent(view.getContext(),ContactsList.class);
                startActivityForResult(intent,0);
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(CONTACTO, contacto);
        outState.putSerializable(CONTACTOS,contactos);
    }
}
