package com.example.fermach.socialtech;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;




public class ContactsList extends Fragment implements Serializable{

    private ContactList_Adapter adapter;
    private ArrayList<Contact> contactos;
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private Contact nuevoContacto;
    private Contact clickContact;
    public final static String CONTACT = "CONTACT";
    private ListView listView;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View myView = inflater.inflate(R.layout.array_contacts, container, false);

       //atrapo los contactos de mi base de datos
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("contacts");

        contactos = new ArrayList<>();
        nuevoContacto = new Contact();

        listView = myView.findViewById(R.id.list_contacts);

        /*
         * Cada contacto detectado de mi base de datos Firebase la añado a mi lista de contactos
         * junto con la key que le asigna firebase como id
         * y actualizo mi adaptador
         *
         * */

        //cada vez que se suba un nuevo dato crear un contacto y añadirlo al array
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                nuevoContacto = dataSnapshot.getValue(Contact.class);
                nuevoContacto.setId(dataSnapshot.getKey());
                contactos.add(nuevoContacto);
                adapter = new ContactList_Adapter(myView.getContext(), contactos);
                listView.setAdapter(adapter);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.i("array de contactos", contactos.toString());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /*
         * Cada vez que clickeo sobre un contacto, inicio el fragmento de detalles sobre ese contacto y
         * le envio los datos de el contacto seleccionado mediante un Bundle
         */
        listView.setLongClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                clickContact = contactos.get(position);
                Bundle args = new Bundle();
                args.putSerializable(CONTACT, clickContact);
                Fragment toFragment = new ContactItem();
                toFragment.setArguments(args);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_main, toFragment, CONTACT)
                        .addToBackStack(CONTACT).commit();
            }
        });

        /*
         * Al hacer LongClick me muestra un dialogo para preguntar si deseo borrar el contacto, si es asi
         * borra el contacto usando la key que crea firebase de forma automatica
         */
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, final View view, final int position, long l) {
                Log.i("longclick", "funciona");

                AlertDialog.Builder myBuild = new AlertDialog.Builder(view.getContext());
                myBuild.setMessage("¿Estás seguro de que deseas borrar este contacto?");
                myBuild.setTitle("Borrar Contacto");
                myBuild.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // codigo a relizar cuando se haga long click
                        Contact contactToDelete = contactos.get(position);
                        ref.child(contactToDelete.getId()).removeValue();
                        contactos.remove(contactToDelete);
                        adapter = new ContactList_Adapter(myView.getContext(), contactos);
                        listView.setAdapter(adapter);
                        Snackbar.make(myView, "Contacto eliminado", Snackbar.LENGTH_SHORT).show();
                        dialogInterface.cancel();
                    }
                });
                myBuild.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                myBuild.show();
                return true;
            }
        });

      return myView;
    }
}