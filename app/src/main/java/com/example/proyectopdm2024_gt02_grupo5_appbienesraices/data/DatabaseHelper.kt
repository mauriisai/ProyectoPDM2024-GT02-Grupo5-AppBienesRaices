package com.example.proyectopdm2024_gt02_grupo5_appbienesraices.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "InmuAppBd.db"
        private const val DATABASE_VERSION = 2

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

        // Columnas de la tabla de usuarios
        private const val TABLE_USUARIOS = "USUARIOS"
        private const val COLUMN_ID_USUARIO = "ID_USUARIO"
        private const val COLUMN_USUARIO = "USUARIO"
        private const val COLUMN_CLAVE = "CLAVE"
        private const val COLUMN_NOMBRE = "NOMBRE"
        private const val COLUMN_DIRECCION = "DIRECCION"
        private const val COLUMN_TELEFONO1 = "TELEFONO1"
        private const val COLUMN_TELEFONO2 = "TELEFONO2"
        private const val COLUMN_CORREO = "CORREO"
        private const val COLUMN_ID_ROL = "ID_ROL"
        private const val COLUMN_ESTADO_USUARIO = "ESTADO_USUARIO"
        private const val COLUMN_FECHA_DE_CREACION = "FECHA_DE_CREACION"
        private const val COLUMN_FUM_USUARIO = "FUM"

        //Columnas de la tabla roles
        private const val TABLE_ROLES = "ROLES"
        private const val COLUMN_ID_ROLES = "ID_ROL"
        private const val COLUMN_ROL = "ROL"

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

        // Columnas de la tabla INMUEBLE
        private const val TABLE_INMUEBLES = "INMUEBLES"
        private const val COLUMN_INMUEBLE_ID = "ID_INMUEBLE"
        private const val COLUMN_TITULO = "TITULO"
        private const val COLUMN_ID_DEPARTAMENTO_INMUEBLE = "ID_DEPARTAMENTO"
        private const val COLUMN_ID_MUNICIPIO_INMUEBLE = "ID_MUNICIPIO"
        private const val COLUMN_DESCRIPCION = "DESCRIPCION"
        private const val COLUMN_PRECIO = "PRECIO"
        private const val COLUMN_ESTADO_INMUEBLE = "ID_ESTADO"
        private const val COLUMN_UBICACION = "UBICACION"
        private const val COLUMN_TAMANIO = "TAMANIO"
        private const val COLUMN_VISITAS = "VISITAS"
        private const val COLUMN_TIPO_INMUEBLE_ID = "ID_TIPO_INMUEBLE"
        private const val COLUMN_ID_USUARIO_VENDEDOR_INMUEBLE = "ID_USUARIO_VENDEDOR"
        private const val COLUMN_FECHA_VENTA = "FECHA_VENTA"
        private const val COLUMN_FECHA_CREACION_INMUEBLE = "FECHA_CREACION"
        private const val COLUMN_FUM_INMUEBLE = "FUM"
        private const val COLUMN_ULTIMO_USUARIO_INMUEBLE = "ULTIMO_USUARIO"
        private const val COLUMN_USUARIO_CREACION_INMUEBLE = "USUARIO_CREACION"
        private val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

        private const val TABLE_IMG_INMUEBLES = "IMG_INMUEBLES"
        private const val COLUMN_ID_IMG = "ID_IMG"
        private const val COLUMN_IMAGEN = "IMAGEN"
        private const val COLUMN_CORRELATIVO = "CORRELATIVO"

        // Creacion de la tabla Citas
        private const val CREATE_TABLE_CITAS = """
            CREATE TABLE $TABLE_CITAS (
                $COLUMN_ID_CITA INTEGER PRIMARY KEY,
                $COLUMN_ID_INMUEBLE INTEGER,
                $COLUMN_ID_ESTADO_CITA INTEGER,
                $COLUMN_ID_USUARIO_COMPRADOR INTEGER,
                $COLUMN_ID_USUARIO_VENDEDOR INTEGER,
                $COLUMN_FECHA_CITA TEXT,
                $COLUMN_FECHA_DE_CREACION TEXT,
                $COLUMN_FUM TEXT,
                $COLUMN_ULTIMO_USUARIO INTEGER,
                $COLUMN_ESTA_CANCELADA CHAR,
                $COLUMN_FECHA_CANCELACION TEXT,
                $COLUMN_MOTIVO_CANCELACION TEXT,
                $COLUMN_USUARIO_CANCELACION INTEGER
            )
        """

        // Creacion tabla ROLES
        private const val CREATE_TABLE_ROLES = """
            CREATE TABLE $TABLE_ROLES (
                $COLUMN_ID_ROLES INTEGER PRIMARY KEY,
                $COLUMN_ROL TEXT
            )
        """

        // Creacion de la tabla Usuarios
        private const val CREATE_TABLE_USUARIOS = """
            CREATE TABLE $TABLE_USUARIOS (
                $COLUMN_ID_USUARIO INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USUARIO TEXT,
                $COLUMN_CLAVE TEXT,
                $COLUMN_NOMBRE TEXT,
                $COLUMN_DIRECCION TEXT,
                $COLUMN_TELEFONO1 INTEGER,
                $COLUMN_TELEFONO2 INTEGER,
                $COLUMN_CORREO TEXT,
                $COLUMN_ID_ROL INTEGER,
                $COLUMN_ESTADO_USUARIO TEXT,
                $COLUMN_FECHA_DE_CREACION TEXT,
                $COLUMN_FUM_USUARIO TEXT,
                FOREIGN KEY ($COLUMN_ID_ROL) REFERENCES $TABLE_ROLES($COLUMN_ID_ROLES)
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

        // Sentencia SQL para crear la tabla INMUEBLES
        private const val CREATE_TABLE_INMUEBLES = """
            CREATE TABLE $TABLE_INMUEBLES (
                $COLUMN_INMUEBLE_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_TITULO TEXT NOT NULL,
                $COLUMN_ID_DEPARTAMENTO_INMUEBLE INTEGER NOT NULL,
                $COLUMN_ID_MUNICIPIO_INMUEBLE INTEGER,
                $COLUMN_DESCRIPCION TEXT NOT NULL,
                $COLUMN_PRECIO REAL NOT NULL,
                $COLUMN_ESTADO_INMUEBLE INTEGER NOT NULL,
                $COLUMN_UBICACION TEXT,
                $COLUMN_TAMANIO REAL,
                $COLUMN_VISITAS INTEGER,
                $COLUMN_TIPO_INMUEBLE_ID INTEGER,
                $COLUMN_ID_USUARIO_VENDEDOR_INMUEBLE INTEGER,
                $COLUMN_FECHA_VENTA TEXT,
                $COLUMN_FECHA_CREACION_INMUEBLE TEXT,
                $COLUMN_FUM_INMUEBLE TEXT,
                $COLUMN_ULTIMO_USUARIO_INMUEBLE INTEGER,
                $COLUMN_USUARIO_CREACION_INMUEBLE INTEGER,
                FOREIGN KEY ($COLUMN_ID_DEPARTAMENTO_INMUEBLE) REFERENCES DEPARTAMENTOS(ID_DEPARTAMENTO),
                FOREIGN KEY ($COLUMN_ID_MUNICIPIO_INMUEBLE) REFERENCES MUNICIPIOS(ID_MUNICIPIO),
                FOREIGN KEY ($COLUMN_ESTADO_INMUEBLE) REFERENCES ESTADO_INMUEBLE(ID_ESTADO),
                FOREIGN KEY ($COLUMN_TIPO_INMUEBLE_ID) REFERENCES TIPO_INMUEBLE(ID_TIPO_INMUEBLE),
                FOREIGN KEY ($COLUMN_ID_USUARIO_VENDEDOR_INMUEBLE) REFERENCES USUARIOS(ID_USUARIO)
            )
        """

        private const val CREATE_TABLE_IMG_INMUEBLES = """
            CREATE TABLE $TABLE_IMG_INMUEBLES (
                $COLUMN_ID_IMG INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_ID_INMUEBLE INTEGER,
                $COLUMN_IMAGEN TEXT,
                $COLUMN_CORRELATIVO INTEGER,
                FOREIGN KEY ($COLUMN_ID_INMUEBLE) REFERENCES $TABLE_INMUEBLES($COLUMN_INMUEBLE_ID)
            )
        """

        //  Columnas de la tabla estado_INMUEBLE
        private const val TABLE_ESTADO_INMUEBLE = "ESTADO_INMUEBLE"
        private const val COLUMN_ID_ESTADO_INMUEBLE = "ID_ESTADO"
        private const val COLUMN_NOMBRE_ESTADO = "NOMBRE_ESTADO"

        // Crear tabla Estado_Inmueble
        val CREATE_TABLE_ESTADO_INMUEBLES = """
            CREATE TABLE $TABLE_ESTADO_INMUEBLE (
                $COLUMN_ID_ESTADO_INMUEBLE INTEGER PRIMARY KEY ,
                $COLUMN_NOMBRE_ESTADO TEXT NOT NULL
            )
        """

    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_CITAS)
        db.execSQL(CREATE_TABLE_USUARIOS)
        db.execSQL(CREATE_TABLE_ROLES)
        db.execSQL(CREATE_TABLE_ESTADO_CITA)
        db.execSQL(CREATE_TABLE_DEPARTAMENTOS)
        db.execSQL(CREATE_TABLE_MUNICIPIOS)
        db.execSQL(CREATE_TABLE_TIPO_INMUEBLE)
        db.execSQL(CREATE_TABLE_ESTADO_INMUEBLES)
        db.execSQL(CREATE_TABLE_INMUEBLES)
        db.execSQL(CREATE_TABLE_IMG_INMUEBLES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CITAS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USUARIOS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ROLES")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ESTADO_CITA")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_DEPARTAMENTOS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_MUNICIPIOS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_TIPO_INMUEBLE")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_IMG_INMUEBLES")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_INMUEBLES")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ESTADO_INMUEBLE")

        onCreate(db)
    }

    fun getInmueblesConImagenCorrelativoUno(): List<InmuebleConImagen> {
        val query = """
        SELECT i.TITULO, i.PRECIO, i.TAMANIO, i.UBICACION, img.IMAGEN
        FROM INMUEBLES i
        INNER JOIN IMG_INMUEBLES img ON i.ID_INMUEBLE = img.ID_INMUEBLE
        WHERE img.CORRELATIVO = 1
    """.trimIndent()

        val db = this.readableDatabase
        val inmuebles = mutableListOf<InmuebleConImagen>()

        val cursor = db.rawQuery(query, null)
        cursor.use { cursor ->
            while (cursor.moveToNext()) {
                val titulo = cursor.getString(cursor.getColumnIndexOrThrow("TITULO"))
                val precio = cursor.getDouble(cursor.getColumnIndexOrThrow("PRECIO"))
                val tamanio = cursor.getDouble(cursor.getColumnIndexOrThrow("TAMANIO"))
                val ubicacion = cursor.getString(cursor.getColumnIndexOrThrow("UBICACION"))
                val imagen = cursor.getString(cursor.getColumnIndexOrThrow("IMAGEN"))

                val inmueble = InmuebleConImagen(titulo, precio, tamanio, ubicacion, imagen)
                inmuebles.add(inmueble)
            }
        }

        db.close()

        return inmuebles
    }

    // Método para insertar los valores iniciales en la tabla ESTADO_INMUEBLE
    fun insertEstadoInmueble(idEstadoInmueble: Int, nombreEstado: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_ID_ESTADO_INMUEBLE, idEstadoInmueble)
            put(COLUMN_NOMBRE_ESTADO, nombreEstado)
        }
        val result = db.insert(TABLE_ESTADO_INMUEBLE, null, contentValues)
        db.close()
        return result
    }

    // Método para insertar los registros IMG_INMUEBLE
    fun insertImage(inmuebleId: Int, imagePath: String) {
        val db = this.writableDatabase

        // Obtener el máximo correlativo para el inmuebleId
        val query = "SELECT MAX($COLUMN_CORRELATIVO) FROM $TABLE_IMG_INMUEBLES WHERE $COLUMN_ID_INMUEBLE = ?"
        val cursor = db.rawQuery(query, arrayOf(inmuebleId.toString()))

        var correlativo = 0 // Valor predeterminado si no hay registros existentes para el inmueble

        if (cursor != null && cursor.moveToFirst()) {
            correlativo = cursor.getInt(0) + 1 // Incrementar el correlativo en uno
        }

        cursor?.close()

        // Insertar la nueva imagen con el correlativo actualizado
        val values = ContentValues().apply {
            put(COLUMN_ID_INMUEBLE, inmuebleId)
            put(COLUMN_IMAGEN, imagePath)
            put(COLUMN_CORRELATIVO, correlativo)
        }

        db.insert(TABLE_IMG_INMUEBLES, null, values)
        db.close()
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

    // Método para insertar los valores iniciales en la tabla ROLES
    fun insertRol(idRol: Int, rol: String): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_ID_ROLES, idRol)
            put(COLUMN_ROL, rol)
        }
        val result = db.insert(TABLE_ROLES, null, values)
        db.close()
        return result
    }

    // Método para insertar un registro en la tabla USUARIOS
    fun insertUsuario(usuario: Usuario): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USUARIO, usuario.usuario)
            put(COLUMN_CLAVE, usuario.clave)
            put(COLUMN_NOMBRE, usuario.nombre)
            put(COLUMN_DIRECCION, usuario.direccion)
            put(COLUMN_TELEFONO1, usuario.telefono1)
            put(COLUMN_TELEFONO2, usuario.telefono2)
            put(COLUMN_CORREO, usuario.correo)
            put(COLUMN_ID_ROL, usuario.idRol)
            put(COLUMN_ESTADO_USUARIO, usuario.estadoUsuario)
            put(COLUMN_FECHA_DE_CREACION, usuario.fechaCreacion?.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
            put(COLUMN_FUM, usuario.fumUsuario?.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
        }
        val result = db.insert(TABLE_USUARIOS, null, values)
        db.close()
        return result
    }

    // Método para insertar un registro en la tabla INMUEBLES
    fun insertInmueble(inmueble: Inmueble): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_TITULO, inmueble.titulo)
            put(COLUMN_ID_DEPARTAMENTO_INMUEBLE, inmueble.idDepartamento)
            put(COLUMN_ID_MUNICIPIO_INMUEBLE, inmueble.idMunicipio)
            put(COLUMN_DESCRIPCION, inmueble.descripcion)
            put(COLUMN_PRECIO, inmueble.precio)
            put(COLUMN_ESTADO_INMUEBLE, inmueble.idEstado)
            put(COLUMN_UBICACION, inmueble.ubicacion)
            put(COLUMN_TAMANIO, inmueble.tamanio)
            put(COLUMN_VISITAS, inmueble.visitas)
            put(COLUMN_TIPO_INMUEBLE_ID, inmueble.idTipoInmueble)
            put(COLUMN_ID_USUARIO_VENDEDOR_INMUEBLE, inmueble.idUsuarioVendedor)
            put(COLUMN_FECHA_VENTA, inmueble.fechaVenta?.format(dateFormatter))
            put(COLUMN_FECHA_CREACION_INMUEBLE, inmueble.fechaCreacion?.format(dateFormatter))
            put(COLUMN_FUM_INMUEBLE, inmueble.fum?.format(dateFormatter))
            put(COLUMN_ULTIMO_USUARIO_INMUEBLE, inmueble.ultimoUsuario)
            put(COLUMN_USUARIO_CREACION_INMUEBLE, inmueble.usuarioCreacion)
        }
        val result = db.insert(TABLE_INMUEBLES, null, contentValues)
        db.close()
        return result
    }

    // Método para insertar los valores iniciales en la tabla ESTADO_CITA

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

    fun getInmuebleDetails(idInmueble: Int): InmuebleDetails? {
        val db = this.readableDatabase
        val query = """
            SELECT i.TITULO, m.MUNICIPIO, i.PRECIO, i.TAMANIO, i.DESCRIPCION, i.ID_USUARIO_VENDEDOR, d.DEPARTAMENTO 
            FROM INMUEBLES i
            INNER JOIN DEPARTAMENTOS d ON i.ID_DEPARTAMENTO = d.ID_DEPARTAMENTO
            INNER JOIN MUNICIPIOS m ON i.ID_MUNICIPIO = m.ID_MUNICIPIO 
            WHERE i.ID_INMUEBLE = ?
        """
        val cursor = db.rawQuery(query, arrayOf(idInmueble.toString()))

        if (cursor.moveToFirst()) {
            val titulo = cursor.getString(cursor.getColumnIndexOrThrow("TITULO"))
            val municipio = cursor.getString(cursor.getColumnIndexOrThrow("MUNICIPIO"))
            val precio = cursor.getDouble(cursor.getColumnIndexOrThrow("PRECIO"))
            val tamanio = cursor.getString(cursor.getColumnIndexOrThrow("TAMANIO"))
            val descripcion = cursor.getString(cursor.getColumnIndexOrThrow("DESCRIPCION"))
            val departamento = cursor.getString(cursor.getColumnIndexOrThrow("DEPARTAMENTO"))
            cursor.close()
            return InmuebleDetails(titulo, municipio, precio, tamanio, descripcion, departamento)
        }
        cursor.close()
        return null
    }

    // Metodo para obtener todos los registros de la tabla Citas
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

    fun getUser(usuario: String, clave: String): Usuario? {
        val db = this.readableDatabase
        val cursor: Cursor = db.query(
            TABLE_USUARIOS,
            null,
            "$COLUMN_USUARIO = ? AND $COLUMN_CLAVE = ?",
            arrayOf(usuario, clave),
            null,
            null,
            null
        )
        return if (cursor.moveToFirst()) {
            val idUsuario = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_USUARIO))
            val usuario = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USUARIO))
            val clave = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CLAVE))
            val nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE))
            val direccion = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DIRECCION))
            val telefono1 = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TELEFONO1))
            val telefono2 = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TELEFONO2))
            val correo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CORREO))
            val idRol = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_ROL))
            val estadoUsuario = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ESTADO_USUARIO))
            val fechaCreacion = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FECHA_DE_CREACION))?.let { LocalDate.parse(it, DateTimeFormatter.ofPattern("dd-MM-yyyy")) }
            val fumUsuario = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FUM))?.let { LocalDate.parse(it, DateTimeFormatter.ofPattern("dd-MM-yyyy")) }
            Usuario(idUsuario, usuario, clave, nombre, direccion, telefono1, telefono2, correo, idRol, estadoUsuario, fechaCreacion, fumUsuario)
        } else {
            null
        }.also {
            cursor.close()
            db.close()
        }
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
