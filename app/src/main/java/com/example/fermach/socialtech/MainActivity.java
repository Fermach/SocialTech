package com.example.fermach.socialtech;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.io.Serializable;

/**
 * Clase principal donde se gestionan los demás fragmentos
 *
 * @author Fermach
 * @version 1.0.
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {

    public final static String MAIN_FRAGMENT="MAIN_FRAGMENT";
    private Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragment= new Contacts_form();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        //carga el fragmento que había al girar la pantalla(si es que lo hay)
        if(savedInstanceState!=null) {
           fragment= getSupportFragmentManager().getFragment(savedInstanceState, "MAIN_FRAGMENT");


        }


            getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).commit();






        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //detecta el item de nuestro NavigationBar clickeado y nos permite navegar por los fragmentos
        int id = item.getItemId();
        boolean itemSeleccionado= false;


        if (id == R.id.nav_lista_contactos) {
            fragment= new ContactsList();
            itemSeleccionado=true;

        } else if (id == R.id.nav_nueva_empresa) {

            fragment= new Companies_form();
            itemSeleccionado=true;
        } else if (id == R.id.nav_lista_empresas) {

            fragment= new CompanyList();
            itemSeleccionado=true;
        }else if (id == R.id.nav_nuevo_contacto) {

            fragment= new Contacts_form();
            itemSeleccionado=true;
        }

        if(itemSeleccionado==true){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).commit();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState,MAIN_FRAGMENT,fragment);

    }
}
