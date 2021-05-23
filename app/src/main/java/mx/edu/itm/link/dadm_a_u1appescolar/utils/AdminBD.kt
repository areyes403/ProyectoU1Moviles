package mx.edu.itm.link.dadm_a_u1appescolar.utils

import android.content.Intent
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import mx.edu.itm.link.dadm_a_u1appescolar.R
import mx.edu.itm.link.dadm_a_u1appescolar.adapters.RecyclerKardexAdapter
import org.json.JSONArray
import org.json.JSONObject

class AdminBD {

    fun generaCalificaciones(stringBD: String, strSemestre: String) : JSONArray {
        try {
            val semestre = strSemestre.toInt()

            // Se prepara el json final de materias para el alumno
            val jsonAlumnoMaterias = JSONArray()

            // Se obtienen las materias
            val jsonBD = JSONObject(stringBD)
            val jsonArrayMaterias = jsonBD.getJSONArray("materias")
            println("JSON Array Materias: " + jsonArrayMaterias)


            for(i in 0..semestre-1-1) {
                val jsonMaterias = jsonArrayMaterias.getJSONObject(i)
                println("JSON Materias: " + jsonMaterias)

                // Se obtienen las materias hasta el semestre cursado del alumno
                val materias = jsonMaterias.getJSONArray("semestre-${i+1}")
                println("Materias: " + materias)

                for(j in 0..materias.length()-1) {
                    val materia = materias.getJSONObject(j)
                    println("Materia: " + materia)

                    // Se generan las calificaciones al azar
                    val calificacion = JSONObject()
                    calificacion.put("materia", materia.getString("S${i+1}${j+1}").replace("_"," "))
                    calificacion.put("calificacion", (50..100).random())
                    calificacion.put("semestre",i+1)

                    jsonAlumnoMaterias.put(calificacion)
                }
            }

            return jsonAlumnoMaterias
        } catch (e: Exception) {
            e.printStackTrace()

            return JSONArray()
        }
    }

    fun generaHorario(stringBD: String, strSemestre: String) : JSONArray {
        try {
            val semestre = strSemestre.toInt()

            // Se prepara el json final de materias para el alumno
            var jsonAlumnoMaterias = JSONArray()

            // Se obtienen las materias
            val jsonBD = JSONObject(stringBD)
            val jsonArrayMaterias = jsonBD.getJSONArray("materias")
            println("JSON Array Materias: " + jsonArrayMaterias)

            for(i in 0..jsonArrayMaterias.length()-1) {
                val jsonMaterias = jsonArrayMaterias.getJSONObject(i)
                println("Valor:\n$jsonMaterias")
                if(jsonMaterias.has("semestre-$semestre")) {
                    val horario = jsonMaterias.getJSONArray("semestre-$semestre")
                    println("Semestre-$semestre:\n$horario")
                    var hora = 900
                    for(j in 0..horario.length()-1) {
                        val json = horario.getJSONObject(j)

                        val dia = JSONObject()
                        dia.put("dia", when((0..4).random()) {
                            0 -> "Lunes"
                            1 -> "Martes"
                            2 -> "Miercoles"
                            3 -> "Jueves"
                            else -> "Viernes"
                        })
                        dia.put("hora", hora)
                        dia.put("materia", json.getString("S${i+1}${j+1}").replace("_", " "))
                        hora += 100

                        jsonAlumnoMaterias.put(dia)
                    }
                    break
                }
            }

            println("Hoario\n$jsonAlumnoMaterias")

            return jsonAlumnoMaterias
        } catch (e: Exception) {
            e.printStackTrace()

            return JSONArray()
        }
    }

    fun calificarExamen(stringBD: String, strSemestre: String): JSONArray {
        try {
            val semestre = strSemestre.toInt()

            // Se prepara el json final de materias para el alumno
            val jsonAlumnoMaterias = JSONArray()

            // Se obtienen las materias
            val jsonBD = JSONObject(stringBD)
            val jsonArrayMaterias = jsonBD.getJSONArray("materias")
            println("JSON Array Materias: " + jsonArrayMaterias)
            

            for(i in 0..semestre) {
                val jsonMaterias = jsonArrayMaterias.getJSONObject(i)
                println("JSON Materias: " + jsonMaterias)

                // Se obtienen las materias hasta el semestre cursado del alumno
                val materias = jsonMaterias.getJSONArray("semestre-${i+1}")
                println("Materias: " + materias)

                for(j in 0..materias.length()-1) {
                    val materia = materias.getJSONObject(j)
                    println("Materia: " + materia)

                    // Se generan las calificaciones al azar
                    val calificacion = JSONObject()
                    calificacion.put("materia", materia.getString("S${i+1}${j+1}").replace("_"," "))
                    calificacion.put("calificacion", (50..100).random())
                    calificacion.put("semestre",i+1)

                    jsonAlumnoMaterias.put(calificacion)
                }
            }

            return jsonAlumnoMaterias
        } catch (e: Exception) {
            e.printStackTrace()

            return JSONArray()
        }

    }
}