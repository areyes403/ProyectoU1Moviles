package mx.edu.itm.link.dadm_a_u1appescolar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {

    lateinit var editRegistroNumControl : EditText
    lateinit var editRegistroNombre : EditText
    lateinit var editRegistroCarrera : EditText
    lateinit var editRegistroSemestre : EditText
    lateinit var editRegistroPass : EditText
    lateinit var btnGuardarRegistro : Button
    lateinit var btnSalir : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        editRegistroNumControl = findViewById(R.id.editRegistroNumControl)
        editRegistroNombre = findViewById(R.id.editRegistroNombre)
        editRegistroCarrera = findViewById(R.id.editRegistroCarrera)
        editRegistroSemestre = findViewById(R.id.editRegistroSemestre)
        editRegistroPass = findViewById(R.id.editRegistroPass)
        btnGuardarRegistro = findViewById(R.id.btnGuardarRegistro)
        btnSalir = findViewById(R.id.btnSalir)

        btnSalir.setOnClickListener { finish() }

        val stringBD = intent.getStringExtra("bd")

        var jsonBD = JSONObject(stringBD)

        btnGuardarRegistro.setOnClickListener {
            val json = JSONObject()
            json.put("no_control", editRegistroNumControl.text.toString())
            json.put("nombre", editRegistroNombre.text.toString())
            json.put("carrera", editRegistroCarrera.text.toString())
            json.put("semestre", editRegistroSemestre.text.toString().toInt())
            json.put("contrasenia", editRegistroPass.text.toString())

            val alumnos = jsonBD.getJSONArray("alumnos")
            alumnos.put(json)

            jsonBD = JSONObject()
            jsonBD.put("alumnos", alumnos)

            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("alumno", json.toString())
            intent.putExtra("bd", jsonBD.toString())
            startActivity(intent)
            finish()
        }
    }

}