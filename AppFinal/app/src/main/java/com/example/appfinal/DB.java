package com.example.appfinal;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "comidas.db";
        private static final int DATABASE_VERSION = 1;

        private static final String TABLE_COMIDAS = "comidas";
        private static final String COLUMN_ID = "_id";
        private static final String COLUMN_NOMBRE = "nombre";
        private static final String COLUMN_IMAGEN = "imagen";

        public DB(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

    public DB() {
       super(null, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
        public void onCreate(SQLiteDatabase db) {
            String createTableQuery = "CREATE TABLE " + TABLE_COMIDAS + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOMBRE + " TEXT, " +
                    COLUMN_IMAGEN + " TEXT" +
                    ")";
            db.execSQL(createTableQuery);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMIDAS);
            onCreate(db);
        }

        public long insertComida(String nombre, String imagen) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_NOMBRE, nombre);
            values.put(COLUMN_IMAGEN, imagen);
            return db.insert(TABLE_COMIDAS, null, values);
        }
    public MealCategory obtenerComidaDesdeBaseDeDatos() {
        MealCategory comida = null;
        SQLiteDatabase dbase = null;
        Cursor cursor = null;

        try {
            // Obtener una instancia de la base de datos en modo lectura

            DB db = new DB();
            dbase = db.getReadableDatabase();

            // Realizar la consulta a la base de datos para obtener la comida guardada
            String query = "SELECT * FROM comidas LIMIT 1"; // Suponiendo que solo tienes una comida guardada
            cursor = dbase.rawQuery(query, null);

            // Verificar si se obtuvo algún resultado
            if (cursor != null && cursor.moveToFirst()) {
                // Obtener los valores de las columnas de la tabla "comidas"
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                @SuppressLint("Range") String imagen = cursor.getString(cursor.getColumnIndex("imagen"));


                // Crear un objeto Comida con los valores obtenidos
                comida = new MealCategory(id, nombre, imagen);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar el cursor y la base de datos
            if (cursor != null) {
                cursor.close();
            }
            if (dbase != null) {
                dbase.close();
            }
        }

        return comida;
    }


    }


