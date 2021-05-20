package mx.edu.itm.link.dadm_a_u1appescolar

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MenuActivity : AppCompatActivity() {
    lateinit var btnKardex : Button
    lateinit var btnHorario : Button
    lateinit var btnReticula : Button
    lateinit var btnPersonales : Button
    lateinit var btnExamen : Button

    lateinit var stringBD : String
    lateinit var stringAlumno : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        btnKardex = findViewById(R.id.btnKardex)
        btnHorario = findViewById(R.id.btnHorario)
        btnReticula = findViewById(R.id.btnReticula)
        btnPersonales = findViewById(R.id.btnPersonales)
        btnExamen=findViewById(R.id.btnExamen)

        stringBD = intent.getStringExtra("bd")!!
        if(stringBD == null) {
            stringBD = resources.getString(R.string.jsonAlumnos)
        }

        stringAlumno = intent.getStringExtra("alumno")!!

        println("Alumno:")
        println(stringAlumno)

        println("BD:")
        println(stringBD)

        btnKardex.setOnClickListener {
            invocarActivity(KardexActivity::class.java)
        }

        btnReticula.setOnClickListener {
            invocarActivity(AcademicAdvanceActivity::class.java)
        }

        btnHorario.setOnClickListener {
            invocarActivity(ScheduleActivity::class.java)
        }
        btnExamen.setOnClickListener {
            invocarActivity(MostrarMaterias::class.java)
        }

    }

    private fun invocarActivity(clase : Class<*>) {
        val intent = Intent(this,clase)
        intent.putExtra("bd",stringBD)
        intent.putExtra("alumno",stringAlumno)
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            data?.getStringExtra("alumno")?.let {
                stringAlumno = it
                println(it)
            }
            data?.getStringExtra("bd")?.let {
                stringBD = it
                println(it)
            }
        }
    }

}