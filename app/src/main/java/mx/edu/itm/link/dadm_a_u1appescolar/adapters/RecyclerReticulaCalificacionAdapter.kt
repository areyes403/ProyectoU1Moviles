package mx.edu.itm.link.dadm_a_u1appescolar.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import mx.edu.itm.link.dadm_a_u1appescolar.R
import org.json.JSONArray
import org.json.JSONObject

class RecyclerReticulaCalificacionAdapter(val c: Context, val r: Int, val calificaciones:JSONArray) : RecyclerView.Adapter<RecyclerReticulaCalificacionAdapter.CalificacionRecticulaVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalificacionRecticulaVH {
        return CalificacionRecticulaVH( LayoutInflater.from(c).inflate(r,null) )
    }

    override fun onBindViewHolder(holder: CalificacionRecticulaVH, position: Int) {
        val json = calificaciones.getJSONObject(position)
        holder.bind(json)
    }

    override fun getItemCount(): Int {
        return calificaciones.length()
    }

    inner class CalificacionRecticulaVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(json : JSONObject) {
            val tvMateria = itemView.findViewById<TextView>(R.id.tvRowMateriaReticula)
            val tvCalificacion = itemView.findViewById<TextView>(R.id.tvRowCalificacionReticula)
            val cardView = itemView.findViewById<CardView>(R.id.cardRowMateriasReticula)

            tvMateria.text = json.getString("materia")
            if( ! json.getString("calificacion").equals("-")) {
                tvCalificacion.text = json.getString("calificacion")
                if (json.getString("calificacion").toInt() >= 70) {
                    cardView.setCardBackgroundColor(Color.parseColor("#00cc00"))
                } else {
                    cardView.setCardBackgroundColor(Color.parseColor("#E6D00C"))
                }
            } else {
                tvCalificacion.text = "X Cursar"
            }
        }
    }

}