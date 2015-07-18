package com.example.javierviveros.jenospizza;

import android.app.Activity;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Control extends Fragment {

    DataBaseManager manager = MainActivity.getManager();
    boolean flag=true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_control, container, false);

        final EditText eBuscar =(EditText) rootView.findViewById(R.id.eBuscar);
        final EditText eNombre =(EditText) rootView.findViewById(R.id.eNombre);
        final EditText eLatitud =(EditText) rootView.findViewById(R.id.eLatitud);
        final EditText eLongitud =(EditText) rootView.findViewById(R.id.eLongitud);

        final TextView tNombre =(TextView) rootView.findViewById(R.id.tNombre);
        final TextView tLatitud =(TextView) rootView.findViewById(R.id.tLatitud);
        final TextView tLongitud =(TextView) rootView.findViewById(R.id.tLongitud);

        final Button bAceptar =(Button) rootView.findViewById(R.id.bAceptar);
        final Button bAgregar =(Button) rootView.findViewById(R.id.bAgregar);
        final Button bEliminar =(Button) rootView.findViewById(R.id.bEliminar);
        final Button bBuscar =(Button) rootView.findViewById(R.id.bBuscar);
        final Button bCambiar =(Button) rootView.findViewById(R.id.bCambiar);


        bEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean encontrado;
                encontrado = consultar(eBuscar, tNombre, tLatitud, tLongitud);
                if(encontrado) {
                    manager.eliminar(eBuscar.getText().toString());

                    tNombre.setText("");
                    tLatitud.setText("");
                    tLongitud.setText("");
                    MainActivity.setManager(manager);
                    eLatitud.setVisibility(View.INVISIBLE);
                    eLongitud.setVisibility(View.INVISIBLE);
                    eNombre.setVisibility(View.INVISIBLE);
                    bAceptar.setVisibility(View.INVISIBLE);
                }
                else
                    Toast.makeText(getActivity(), "No encontrado", Toast.LENGTH_SHORT).show();

            }
        });

        bAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nombre = eNombre.getText().toString();
                String Latitud = eLatitud.getText().toString();
                String Longitud = eLongitud.getText().toString();
                String Buscar = eBuscar.getText().toString();

                if (flag){
                    manager.insertar(Nombre, Latitud, Longitud);
                    tNombre.setText(Nombre);}
                else{
                    manager.modificar(Buscar, Latitud, Longitud);
                    tNombre.setText(Buscar);}

                tLatitud.setText(Latitud);
                tLongitud.setText(Longitud);
                bAceptar.setVisibility(View.INVISIBLE);
                MainActivity.setManager(manager);
            }
        });

        bCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=false;
                boolean encontrado;
                encontrado = consultar(eBuscar, tNombre, tLatitud, tLongitud);
                if(encontrado) {
                    eLatitud.setVisibility(View.VISIBLE);
                    eLongitud.setVisibility(View.VISIBLE);
                    eNombre.setVisibility(View.INVISIBLE);
                    bAceptar.setVisibility(View.VISIBLE);
                    MainActivity.setManager(manager);
                }
                else
                    Toast.makeText(getActivity(), "No encontrado", Toast.LENGTH_SHORT).show();
            }
        });

        bBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean encontrado;
                encontrado = consultar(eBuscar, tNombre, tLatitud, tLongitud);
                if (encontrado) {
                    bAceptar.setVisibility(View.INVISIBLE);
                    eLatitud.setVisibility(View.INVISIBLE);
                    eLongitud.setVisibility(View.INVISIBLE);
                    eNombre.setVisibility(View.INVISIBLE);
                    MainActivity.setManager(manager);
                }
                else
                Toast.makeText(getActivity(), "No encontrado", Toast.LENGTH_SHORT).show();
            }
        });

        bAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                flag=true;
                tNombre.setText("");
                tLatitud.setText("");
                tLongitud.setText("");
                eLatitud.setVisibility(View.VISIBLE);
                eLongitud.setVisibility(View.VISIBLE);
                eNombre.setVisibility(View.VISIBLE);
                bAceptar.setVisibility(View.VISIBLE);
                MainActivity.setManager(manager);
            }
        });

        return rootView;
    }

    public boolean consultar (EditText eNombre, TextView tNombre, TextView tLatitud, TextView tLongitud) {

        Cursor cursor = manager.buscar(eNombre.getText().toString());

        cursor.moveToFirst();
        try{
            String dbnombre = cursor.getString(cursor.getColumnIndex(manager.S_NAME));
            tNombre.setText(dbnombre);
            String dbLat = cursor.getString(cursor.getColumnIndex(manager.S_LAT));
            tLatitud.setText(dbLat);
            String dbLon = cursor.getString(cursor.getColumnIndex(manager.S_LON));
            tLongitud.setText(dbLon);

            return true;
        }
        catch(CursorIndexOutOfBoundsException e){
            Toast.makeText(getActivity(), "No Existe esta sede", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


}
