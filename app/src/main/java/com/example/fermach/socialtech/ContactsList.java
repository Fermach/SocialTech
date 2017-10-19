package com.example.fermach.socialtech;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Fermach on 19/10/2017.
 */

public class ContactsList extends AppCompatActivity{

        private ContactAdapter adaptador;
        private ArrayList<Contact> contactos;
        private ListView listView;
        public final static String CONTACTOS="CONTACTOS";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.array_contacts);

            listView=(ListView)findViewById(R.id.lista);
            contactos = MainActivity.contactos;

            if(savedInstanceState!=null){

                contactos= (ArrayList<Contact>) savedInstanceState.getSerializable(CONTACTOS);

            }
            adaptador =new ContactAdapter(this, contactos);

            listView.setAdapter(adaptador);
        }


        public void onPause(){
        super.onPause();
        finish();

        }
}
