package com.example.proyectopdm2024_gt02_grupo5_appbienesraices.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "InmuAppBd.db"
        private const val DATABASE_VERSION = 1

        // Columnas de la tabla Citas
        private const val TABLE_CITAS = "CITAS"
        private const val COLUMN_ID_CITA = "ID_CITA"
        private const val COLUMN_ID_INMUEBLE = "ID_INMUEBLE"
        private const val COLUMN_ID_ESTADO_CITA = "ID_ESTADO_CITA"
        private const val COLUMN_ID_USUARIO_COMPRADOR = "ID_USUARIO_COMPRADOR"
        private const val COLUMN_ID_USUARIO_VENDEDOR = "ID_USUARIO_VENDEDOR"
        private const val COLUMN_FECHA_CITA = "FECHA_CITA"
        private const val COLUMN_FECHA_CREACION = "FECHA_CREACION"
        private const val COLUMN_FUM = "FUM"
        private const val COLUMN_ULTIMO_USUARIO = "ULTIMO_USUARIO"
        private const val COLUMN_ESTA_CANCELADA = "ESTA_CANCELADA"
        private const val COLUMN_FECHA_CANCELACION = "FECHA_CANCELACION"
        private const val COLUMN_MOTIVO_CANCELACION = "MOTIVO_CANCELACION"
        private const val COLUMN_USUARIO_CANCELACION = "USUARIO_CANCELACION"

        // Columnas de la tabla Estado_Cita
        private const val TABLE_ESTADO_CITA = "ESTADO_CITA"
        private const val COLUMN_ID_ESTADO_CITA_ESTADO_CITA = "ID_ESTADO_CITA"
        private const val COLUMN_ESTADO = "ESTADO"

        // Columnas de la tabla Departamentos
        private const val TABLE_DEPARTAMENTOS = "DEPARTAMENTOS"
        private const val COLUMN_ID_DEPARTAMENTO = "ID_DEPARTAMENTO"
        private const val COLUMN_DEPARTAMENTO = "DEPARTAMENTO"

        // Columnas de la tabla Municipios
        private const val TABLE_MUNICIPIOS = "MUNICIPIOS"
        private const val COLUMN_ID_MUNICIPIO = "ID_MUNICIPIO"
        private const val COLUMN_MUNICIPIO = "MUNICIPIO"
        private const val COLUMN_ID_DEPARTAMENTO_MUNICIPIOS = "ID_DEPARTAMENTO"

        // Columnas de la tabla TIPO_INMUEBLE
        private const val TABLE_TIPO_INMUEBLE = "TIPO_INMUEBLE"
        private const val COLUMN_ID_TIPO_INMUEBLE = "ID_TIPO_INMUEBLE"
        private const val COLUMN_TIPO_INMUEBLE = "TIPO_INMUEBLE"

        // Creacion de la tabla Citas
        private const val CREATE_TABLE_CITAS = """
            CREATE TABLE $TABLE_CITAS (
                $COLUMN_ID_CITA INTEGER PRIMARY KEY,
                $COLUMN_ID_INMUEBLE INTEGER,
                $COLUMN_ID_ESTADO_CITA INTEGER,
                $COLUMN_ID_USUARIO_COMPRADOR INTEGER,
                $COLUMN_ID_USUARIO_VENDEDOR INTEGER,
                $COLUMN_FECHA_CITA TEXT,
                $COLUMN_FECHA_CREACION TEXT,
                $COLUMN_FUM TEXT,
                $COLUMN_ULTIMO_USUARIO INTEGER,
                $COLUMN_ESTA_CANCELADA CHAR,
                $COLUMN_FECHA_CANCELACION TEXT,
                $COLUMN_MOTIVO_CANCELACION TEXT,
                $COLUMN_USUARIO_CANCELACION INTEGER
            )
        """

        // Creacion de la tabla Estado_Cita
        private const val CREATE_TABLE_ESTADO_CITA = """
            CREATE TABLE $TABLE_ESTADO_CITA (
                $COLUMN_ID_ESTADO_CITA_ESTADO_CITA INTEGER NOT NULL,
                $COLUMN_ESTADO TEXT,
                PRIMARY KEY ($COLUMN_ID_ESTADO_CITA_ESTADO_CITA)
            )
        """

        // Creacion de la tabla Departamentos
        private const val CREATE_TABLE_DEPARTAMENTOS = """
            CREATE TABLE $TABLE_DEPARTAMENTOS (
                $COLUMN_ID_DEPARTAMENTO INTEGER NOT NULL,
                $COLUMN_DEPARTAMENTO TEXT,
                PRIMARY KEY ($COLUMN_ID_DEPARTAMENTO)
            )
        """

        // Creacion de la tabla Municipios
        private const val CREATE_TABLE_MUNICIPIOS = """
            CREATE TABLE $TABLE_MUNICIPIOS (
                $COLUMN_ID_MUNICIPIO INTEGER NOT NULL,
                $COLUMN_MUNICIPIO TEXT,
                $COLUMN_ID_DEPARTAMENTO_MUNICIPIOS INTEGER,
                PRIMARY KEY ($COLUMN_ID_MUNICIPIO),
                CONSTRAINT FK_DEPARTAMENTO FOREIGN KEY ($COLUMN_ID_DEPARTAMENTO_MUNICIPIOS) REFERENCES $TABLE_DEPARTAMENTOS ($COLUMN_ID_DEPARTAMENTO)
            )
        """

        // Sentencia SQL para crear la tabla TIPO_INMUEBLE
        private const val CREATE_TABLE_TIPO_INMUEBLE = """
            CREATE TABLE $TABLE_TIPO_INMUEBLE (
                $COLUMN_ID_TIPO_INMUEBLE INTEGER PRIMARY KEY,
                $COLUMN_TIPO_INMUEBLE TEXT NOT NULL
            )
        """
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_CITAS)
        db.execSQL(CREATE_TABLE_ESTADO_CITA)
        db.execSQL(CREATE_TABLE_DEPARTAMENTOS)
        db.execSQL(CREATE_TABLE_MUNICIPIOS)
        db.execSQL(CREATE_TABLE_TIPO_INMUEBLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CITAS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ESTADO_CITA")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_DEPARTAMENTOS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_MUNICIPIOS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_TIPO_INMUEBLE")

        onCreate(db)
    }

    // Método para insertar los valores iniciales en la tabla ESTADO_CITA
    fun insertEstadoCita(idEstadoCita: Int, estado: String): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_ID_ESTADO_CITA_ESTADO_CITA, idEstadoCita)
            put(COLUMN_ESTADO, estado)
        }
        val result = db.insert(TABLE_ESTADO_CITA, null, values)
        db.close()
        return result
    }

    // Método para insertar los valores iniciales en la tabla ESTADO_CITA
    @RequiresApi(Build.VERSION_CODES.O)
    fun insertCita(cita: Cita): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_ID_CITA, cita.idCita)
            put(COLUMN_ID_INMUEBLE, cita.idInmueble)
            put(COLUMN_ID_ESTADO_CITA, cita.idEstadoCita)
            put(COLUMN_ID_USUARIO_COMPRADOR, cita.idUsuarioComprador)
            put(COLUMN_ID_USUARIO_VENDEDOR, cita.idUsuarioVendedor)
            put(COLUMN_FECHA_CITA, cita.fechaCita?.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
            put(COLUMN_FECHA_CREACION, cita.fechaCreacion?.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
            put(COLUMN_FUM, cita.fum?.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
            put(COLUMN_ULTIMO_USUARIO, cita.ultimoUsuario)
            put(COLUMN_ESTA_CANCELADA, cita.estaCancelada?.toString())
            put(COLUMN_FECHA_CANCELACION, cita.fechaCancelacion?.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
            put(COLUMN_MOTIVO_CANCELACION, cita.motivoCancelacion)
            put(COLUMN_USUARIO_CANCELACION, cita.usuarioCancelacion)
        }
        val result = db.insert(TABLE_CITAS, null, values)
        db.close()
        return result
    }

    // Metodo para obtener todos los registros de la tabla Citas
    @RequiresApi(Build.VERSION_CODES.O)
    fun getAllCitas(): List<Cita> {
        val db = this.readableDatabase
        val cursor: Cursor = db.query(TABLE_CITAS, null, null, null, null, null, null)
        val citas = mutableListOf<Cita>()

        if (cursor.moveToFirst()) {
            do {
                val cita = Cita(
                    idCita = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_CITA)),
                    idInmueble = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_INMUEBLE)),
                    idEstadoCita = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_ESTADO_CITA)),
                    idUsuarioComprador = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_USUARIO_COMPRADOR)),
                    idUsuarioVendedor = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_USUARIO_VENDEDOR)),
                    fechaCita = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FECHA_CITA))?.let { LocalDate.parse(it, DateTimeFormatter.ofPattern("dd-MM-yyyy")) },
                    fechaCreacion = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FECHA_CREACION))?.let { LocalDate.parse(it, DateTimeFormatter.ofPattern("dd-MM-yyyy")) },
                    fum = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FUM))?.let { LocalDate.parse(it, DateTimeFormatter.ofPattern("dd-MM-yyyy")) },
                    ultimoUsuario = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ULTIMO_USUARIO)),
                    estaCancelada = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ESTA_CANCELADA))?.firstOrNull(),
                    fechaCancelacion = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FECHA_CANCELACION))?.let { LocalDate.parse(it, DateTimeFormatter.ofPattern("dd-MM-yyyy")) },
                    motivoCancelacion = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MOTIVO_CANCELACION)),
                    usuarioCancelacion = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USUARIO_CANCELACION))
                )
                citas.add(cita)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return citas
    }

    fun getAllTipoInmueble(): List<TipoInmueble> {
        val tiposInmueble = mutableListOf<TipoInmueble>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_TIPO_INMUEBLE", null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_TIPO_INMUEBLE))
                val tipo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIPO_INMUEBLE))
                tiposInmueble.add(TipoInmueble(id, tipo))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return tiposInmueble
    }

    fun insertTipoInmueble(id: Int, tipo: String) : Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_ID_TIPO_INMUEBLE, id)
            put(COLUMN_TIPO_INMUEBLE, tipo)
        }
        val result = db.insert(TABLE_TIPO_INMUEBLE, null, values)
        db.close()
        return result
    }

    // Método para insertar un nuevo departamento
    fun insertDepartamento(idDepartamento: Int, departamento: String): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_ID_DEPARTAMENTO, idDepartamento)
            put(COLUMN_DEPARTAMENTO, departamento)
        }
        val result = db.insert(TABLE_DEPARTAMENTOS, null, values)
        db.close()
        return result
    }

    // Método para insertar un nuevo municipio
    fun insertMunicipio(idMunicipio: Int, municipio: String, idDepartamento: Int): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_ID_MUNICIPIO, idMunicipio)
            put(COLUMN_MUNICIPIO, municipio)
            put(COLUMN_ID_DEPARTAMENTO_MUNICIPIOS, idDepartamento)
        }
        val result = db.insert(TABLE_MUNICIPIOS, null, values)
        db.close()
        return result
    }

    fun getAllDepartamentos(): List<Departamento> {
        val departamentos = mutableListOf<Departamento>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_DEPARTAMENTOS", null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_DEPARTAMENTO))
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DEPARTAMENTO))
                departamentos.add(Departamento(id, nombre))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return departamentos
    }

    fun getMunicipiosByDepartamento(departamentoId: Int): List<Municipio> {
        val municipios = mutableListOf<Municipio>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_MUNICIPIOS WHERE $COLUMN_ID_DEPARTAMENTO_MUNICIPIOS = ?",
            arrayOf(departamentoId.toString()))
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_MUNICIPIO))
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MUNICIPIO))
                municipios.add(Municipio(id, departamentoId, nombre))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return municipios
    }
}
