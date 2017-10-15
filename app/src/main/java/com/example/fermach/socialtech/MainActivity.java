package com.example.fermach.socialtech;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{


     List<Contacto> contactos = new ArrayList<>();
     EditText ET_nombre= (EditText)findViewById(R.id.editText_nombre) ;
     EditText ET_apellidos= (EditText)findViewById(R.id.editText_apellido) ;
     EditText ET_email= (EditText)findViewById(R.id.editText_email) ;
     EditText ET_telefono= (EditText)findViewById(R.id.editText_numero) ;
     Button btn_alta=(Button)findViewById(R.id.btn_alta);
     Button btn_cancelar=(Button)findViewById(R.id.btn_cancelar);
     Spinner spinner_formacion= (Spinner)findViewById(R.id.spinner_formacion);
     TextView num_contactos= (TextView) findViewById(R.id.textView_num_contactos);

    //NO FUNCIONA, por que debo definir los atributos dentro del oncreate, pero
    // si lo hago no puedo acceder a ellos en los otros metodos como el onClick.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        String[] valores_spinner= {"SMR","DAM","DAW",
                "ASIR","Ingenieria Informática","Grado","Otros"};

        spinner_formacion.setAdapter(new ArrayAdapter<String>
                (this,R.layout.support_simple_spinner_dropdown_item,valores_spinner));
        btn_alta.setOnClickListener(this);
        btn_cancelar.setOnClickListener(this);

    }


    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btn_alta:
               System.out.println("Has pulsado alta");

                if(ET_nombre.getText().toString().trim().equalsIgnoreCase("") || ET_apellidos.getText().toString().trim().equalsIgnoreCase("")
                        || ET_email.getText().toString().trim().equalsIgnoreCase("") || ET_telefono.getText().toString().trim().equalsIgnoreCase("")
                        || ET_telefono.getText().toString().matches("[0-9]")){

                    Toast.makeText(this,"Datos no validos",Toast.LENGTH_SHORT).show();
                }else{
                    String nombre=ET_nombre.getText().toString();
                    String apellido=ET_apellidos.getText().toString();
                    String email=ET_email.getText().toString();
                    String telefono=ET_telefono.getText().toString();

                    Contacto contacto= new Contacto(nombre,apellido,email,telefono);

                    contactos.add(contacto);
                    System.out.print("Lista de contactos: \n"+contactos);
                    num_contactos.setText("Contactos agregados: "+contactos.size());
                }
                break;
            case R.id.btn_cancelar:
                System.out.println("Has pulsado cancelar");

                ET_nombre.setText("");
                ET_apellidos.setText("");
                ET_email.setText("");
                ET_telefono.setText("");

                break;


        }
    }
}
