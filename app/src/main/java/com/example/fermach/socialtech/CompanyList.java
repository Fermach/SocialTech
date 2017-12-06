package com.example.fermach.socialtech;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;



public class CompanyList extends Fragment implements Serializable{

        private CompanyList_Adapter adapter;
        private ArrayList<Company> companies;
        private FirebaseDatabase database;
        private DatabaseReference ref;
        private Company nuevaEmpresa;
        private Company EmpresaSeleccionada;
        public final static String COMPANY= "COMPANY";
        private ListView listView;

    public CompanyList(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View myView= inflater.inflate(R.layout.array_companies, container, false);

        nuevaEmpresa= new Company();
        companies= new ArrayList<>();

        //atrapo las empresas de mi base de datos
        database = FirebaseDatabase.getInstance();
        ref= database.getReference("companies");

        /*
         * Cada empresa detectada de mi base de datos Firebase la añado a mi lista de empresas
         * junto con la key que le asigna firebase como id
         * y actualizo mi adaptador
         *
         * */

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                nuevaEmpresa = dataSnapshot.getValue(Company.class);
                nuevaEmpresa.setId(dataSnapshot.getKey());
                Log.i("newCompanyy",nuevaEmpresa.toString());
                companies.add(nuevaEmpresa);
                adapter =new CompanyList_Adapter(myView.getContext(), companies);
                listView.setAdapter(adapter);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.i("array de contactos", companies.toString());

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView= myView.findViewById(R.id.list_companies);


        /*
         * Cada vez que clickeo sobre una empresa contacto, inicio el fragmento de detalles sobre esa empresa y
         * le envio los datos de la empresa seleccionada mediante un Bundle
         */
        listView.setLongClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                EmpresaSeleccionada= companies.get(position);
                Bundle args = new Bundle();
                args.putSerializable(COMPANY, EmpresaSeleccionada);
                Fragment toFragment = new CompanyItem();
                toFragment.setArguments(args);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_main, toFragment, COMPANY)
                        .addToBackStack(COMPANY).commit();
            }
        });
        /*
         * Al hacer LongClick me muestra un dialogo para preguntar si deseo borrar la empresa, si es asi
         * borra la empresa usando la key que crea firebase de forma automatica
         */
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, final View view, final int position, long l) {
                Log.i("longclick","funciona");

                AlertDialog.Builder myBuild = new AlertDialog.Builder(view.getContext());
                myBuild.setMessage("¿Estás seguro de que deseas borrar esta empresa?");
                myBuild.setTitle("Borrar Empresa");
                myBuild.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // codigo a relizar cuando se haga long click
                        Company companyToDelete=companies.get(position);
                        ref.child(companyToDelete.getId()).removeValue();
                        companies.remove(companyToDelete);
                        adapter =new CompanyList_Adapter(myView.getContext(), companies);
                        listView.setAdapter(adapter);
                        Snackbar.make(myView,"Empresa eliminado", Snackbar.LENGTH_SHORT).show();
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



        Log.i("info_EMPRESAS ENLISTA",companies.toString());



      return myView;
    }

}

