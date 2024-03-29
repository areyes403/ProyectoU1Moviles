package mx.edu.itm.link.dadm_a_u1appescolar

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.edu.itm.link.dadm_a_u1appescolar.adapters.RecyclerKardexAdapter
import mx.edu.itm.link.dadm_a_u1appescolar.adapters.RecyclerMostrarMateriasAdapter
import mx.edu.itm.link.dadm_a_u1appescolar.utils.AdminBD
import org.json.JSONArray
import org.json.JSONObject

class MostrarMateriasActivity : AppCompatActivity() {

    lateinit var recyclerMostrarMaterias : RecyclerView
    lateinit var btnRealizarExamen: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_materias)


        //Se obtiene la base de datos
        var stringBD = intent.getStringExtra("bd")
        if(stringBD == null) {
            stringBD = resources.getString(R.string.jsonAlumnos)
        }

        val stringAlumno = intent.getStringExtra("alumno")

        val bd = JSONObject(stringBD)

        // Se obtiene el semestre
        val jsonAlumno = JSONObject(stringAlumno)
        val strSemestre = jsonAlumno.getString("semestre")

        // Se verifica si tiene kardex
        var jsonAlumnoMaterias = JSONArray()
        if( ! jsonAlumno.has("kardex")) {
            val admin = AdminBD()
            jsonAlumnoMaterias = admin.calificarExamen(stringBD, strSemestre)

            if(jsonAlumnoMaterias.length() > 0) {
                // Colocamos el resultado en la UI
                recyclerMostrarMaterias = findViewById(R.id.recyclerMostrarMaterias)
                recyclerMostrarMaterias.adapter = RecyclerMostrarMateriasAdapter(this, R.layout.row_mostrar_materias, jsonAlumnoMaterias)
                recyclerMostrarMaterias.layoutManager = LinearLayoutManager(this)

                // Se colocan los resultados
                jsonAlumno.put("kardex",jsonAlumnoMaterias)

                val alumnos = bd.getJSONArray("alumnos")
                for(i in 0..alumnos.length()-1) {
                    val alumno = alumnos.getJSONObject(i)
                    if(alumno.getString("no_control").equals(jsonAlumno.getString("no_control"))) {
                        alumnos.remove(i)
                        alumnos.put(jsonAlumno)
                    }
                }
                println("Nueva BD:\n$bd")

            } else {
                Toast.makeText(this,"Datos incorrectos", Toast.LENGTH_LONG).show()
            }
        } else {
            jsonAlumnoMaterias = jsonAlumno.getJSONArray("kardex")

            // Colocamos el resultado en la UI
            recyclerMostrarMaterias = findViewById(R.id.recyclerMostrarMaterias)
            recyclerMostrarMaterias.adapter = RecyclerKardexAdapter(this, R.layout.row_mostrar_materias, jsonAlumnoMaterias)
            recyclerMostrarMaterias.layoutManager = LinearLayoutManager(this)
        }

        // Se agregan al intent los resultados
        val intent = Intent()
        intent.putExtra("bd", bd.toString())
        intent.putExtra("alumno", jsonAlumno.toString())
        setResult(Activity.RESULT_OK,intent)

        //invocando al activity examen
        btnRealizarExamen=findViewById(R.id.btnRealizarExamen)
        btnRealizarExamen.setOnClickListener {
            val intent = Intent(this,ExamenActivity::class.java)
            startActivity(intent)
        }

    }

}