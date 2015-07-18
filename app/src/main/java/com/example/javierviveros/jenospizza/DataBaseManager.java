package com.example.javierviveros.jenospizza;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Javier Viveros on 14/07/2015.
 */
public class DataBaseManager {

    public static final String TABLE_NAME = "sedes";
    public static final String S_ID = "_id";
    public static final String S_NAME = "nombre";
    public static final String S_LAT = "latitud";
    public static final String S_LON = "longitud";

    public static final String CREATE_TABLE = " create table " +TABLE_NAME+ " ("
            + S_ID + " integer primary key autoincrement,"
            + S_NAME + " text not null,"
            + S_LAT + " text not null,"
            + S_LON + " text not null);";

    DbHelper helper;
    SQLiteDatabase db;

    public DataBaseManager(Context context){
        helper = new DbHelper(context);
        db = helper.getWritableDatabase();
    }

    public ContentValues generarContentValues(String Nombre, String Lat, String Lon){
        ContentValues valores = new ContentValues();
        valores.put(S_NAME,Nombre);
        valores.put(S_LAT,Lat);
        valores.put(S_LON, Lon);
        return valores;
    }

    public void insertar(String Nombre, String Lat, String Lon){
        db.insert(TABLE_NAME, null, generarContentValues(Nombre, Lat, Lon));
    }

    public void eliminar(String nombre){
        db.delete(TABLE_NAME, S_NAME + "=?", new String[]{nombre});
    }

    public void modificar(String nombre, String nuevaLat, String nuevaLon){
        db.update(TABLE_NAME, generarContentValues(nombre, nuevaLat, nuevaLon), S_NAME + "=?", new String[]{nombre});
    }

    public Cursor buscar(String nombre){
        String[] columnas = new String[] {S_ID, S_NAME, S_LAT, S_LON};
        return db.query(TABLE_NAME, columnas, S_NAME + "=?", new String[] {nombre},null,null,null);
    }

    public Cursor cargarCursor(){
        String[] columnas= new String[] {S_ID, S_NAME, S_LAT, S_LON};
        return db.query(TABLE_NAME,columnas,null,null,null,null,null);
    }

}
