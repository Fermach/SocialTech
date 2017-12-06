package com.example.fermach.socialtech;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by Fermach on 19/10/2017.
 */

public class PicturesList extends Fragment implements Serializable{

    private Pictures_Adapter adapter;
    private ArrayList<Picture> pictures;
    private Picture imagenClickeada;
    public final static String PICTURE = "PICTURE";
    private ListView listView;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View myView = inflater.inflate(R.layout.array_pictures_contacts, container, false);

        pictures = new ArrayList<>();

        listView = myView.findViewById(R.id.list_pictures);

        pictures.add(new Picture(R.drawable.account,"Imagen por defecto"));

        pictures.add(new Picture(R.drawable.img_atom,"Atómico"));
        pictures.add(new Picture(R.drawable.img_batman,"Batman"));
        pictures.add(new Picture(R.drawable.img_cap,"Capitán América"));
        pictures.add(new Picture(R.drawable.img_flash,"Flash"));
        pictures.add(new Picture(R.drawable.img_wolverine,"Wolverine"));
        pictures.add(new Picture(R.drawable.img_deadpool,"Deadpool"));
        pictures.add(new Picture(R.drawable.img_hulk,"Hulk"));
        pictures.add(new Picture(R.drawable.img_superm,"Super-Man"));



        adapter = new Pictures_Adapter(myView.getContext(), pictures);
        listView.setAdapter(adapter);
        /*
         * Cada vez que clickeo sobre una imagen, envio esta imagen mediante el Bundle a mi formulario
         * principal y me deplazo hasta su fragmento
         */

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                imagenClickeada= pictures.get(position);
                Bundle args = new Bundle();
                args.putSerializable(PICTURE, imagenClickeada);
                Fragment toFragment = new Contacts_form();
                toFragment.setArguments(args);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_main, toFragment, PICTURE)
                        .addToBackStack(PICTURE).commit();
            }
        });


      return myView;
    }
}