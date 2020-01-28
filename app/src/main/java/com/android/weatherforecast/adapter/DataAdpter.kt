package kotlincodes.com.retrofitwithkotlin.adapters

import WeatherList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.weatherforecast.R
import kotlinx.android.synthetic.main.list_item.view.*

class DataAdpter(var weatherList: ArrayList<WeatherList>) :
    RecyclerView.Adapter<DataAdpter.DataViewHolder>() {
    fun updateList(list: List<WeatherList>) {
        weatherList.clear()
        weatherList.addAll(list)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = DataViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
    )
    override fun getItemCount() = weatherList.size
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }
    class DataViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtTitle = view.txt_title

        fun bind(weatherList: WeatherList) {
            txtTitle.text = "Temp: "+weatherList.main.temp

        }
    }
}