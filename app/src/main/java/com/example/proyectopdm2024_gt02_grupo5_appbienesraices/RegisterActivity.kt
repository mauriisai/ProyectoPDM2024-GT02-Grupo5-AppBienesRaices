package com.example.proyectopdm2024_gt02_grupo5_appbienesraices

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectopdm2024_gt02_grupo5_appbienesraices.data.DatabaseHelper
import com.example.proyectopdm2024_gt02_grupo5_appbienesraices.data.Usuario
import java.time.LocalDate

class RegisterActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_registro)

        databaseHelper = DatabaseHelper(this)

        val usuarioEditText: EditText = findViewById(R.id.editTextUsuario)
        val claveEditText: EditText = findViewById(R.id.editTextContraseña)
        val nombreEditText: EditText = findViewById(R.id.editTextNombre)
        val telefono1EditText: EditText = findViewById(R.id.editTextTelefonoPrincipal)
        val telefono2EditText: EditText = findViewById(R.id.editTextTelefonoSecundario)
        val correoEditText: EditText = findViewById(R.id.editTextCorreo)
        val tipoUsuarioSpinner: Spinner = findViewById(R.id.spinnerTipoUsuario)
        val registerButton: Button = findViewById(R.id.buttonRegistrar)


        ArrayAdapter.createFromResource(
            this,
            R.array.tipo_usuario_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            tipoUsuarioSpinner.adapter = adapter
        }

        registerButton.setOnClickListener {
            val usuario = usuarioEditText.text.toString().trim()
            val clave = claveEditText.text.toString().trim()
            val nombre = nombreEditText.text.toString().trim()
            val telefono1Str = telefono1EditText.text.toString().trim()
            val telefono2Str = telefono2EditText.text.toString().trim()
            val correo = correoEditText.text.toString().trim()
            val tipoUsuario = tipoUsuarioSpinner.selectedItem.toString()


            val telefono1 = telefono1Str.toIntOrNull()
            val telefono2 = telefono2Str.toIntOrNull()


            val idRol = when (tipoUsuario) {
                "Comprador" -> 2
                "Vendedor" -> 3
                else -> 0
            }


            val estadoUsuario = "Activo"
            val fechaCreacion = LocalDate.now()
            val fumUsuario = LocalDate.now()

            if (usuario.isEmpty() || clave.isEmpty() || nombre.isEmpty() || telefono1 == null || correo.isEmpty()) {
                Toast.makeText(this, "Por favor ingrese todos los detalles", Toast.LENGTH_SHORT).show()
            } else {
                val nuevoUsuario = Usuario(
                    usuario = usuario,
                    clave = clave,
                    nombre = nombre,
                    direccion = "",
                    telefono1 = telefono1,
                    telefono2 = telefono2 ?: 0,
                    correo = correo,
                    idRol = idRol,
                    estadoUsuario = estadoUsuario,
                    fechaCreacion = fechaCreacion,
                    fumUsuario = fumUsuario
                )

                val isInserted = databaseHelper.insertUsuario(nuevoUsuario)
                if (isInserted != -1L) {
                    Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show()

                }

            }

        }
    }
}