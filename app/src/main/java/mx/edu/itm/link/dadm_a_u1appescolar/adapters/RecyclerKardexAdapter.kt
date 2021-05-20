package mx.edu.itm.link.dadm_a_u1appescolar.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.edu.itm.link.dadm_a_u1appescolar.R
import org.json.JSONArray
import org.json.JSONObject

class RecyclerKardexAdapter(val context:Context, val res:Int, val calificaciones:JSONArray) : RecyclerView.Adapter<RecyclerKardexAdapter.KardexVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KardexVH {
        return KardexVH(
            LayoutInflater.from(context).inflate(res,null)
        )
    }

    override fun onBindViewHolder(holder: KardexVH, position: Int) {
        holder.bind(calificaciones.getJSONObject(position))
    }

    override fun getItemCount(): Int {
        return calificaciones.length()
    }

    inner class KardexVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(calif: JSONObject) {
            val tvSemestre = itemView.findViewById<TextView>(R.id.tvRowKardexSemestre)
            val tvMateria = itemView.findViewById<TextView>(R.id.tvRowKardexMateria)
            val tvCalificacion = itemView.findViewById<TextView>(R.id.tvRowKardexCalificacion)

            tvSemestre.text = "Semestre ${calif.getInt("semestre")}"
            tvMateria.text = calif.getString("materia")

            val calificacion = calif.getInt("calificacion")
            tvCalificacion.text = "$calificacion"
            tvCalificacion.setTextColor(
                if(calificacion>69) Color.BLUE
                else Color.RED
            )
        }

    }

}