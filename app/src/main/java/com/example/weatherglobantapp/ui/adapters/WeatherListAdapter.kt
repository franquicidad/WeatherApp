package com.example.weatherglobantapp.ui.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherglobantapp.R
import com.example.weatherglobantapp.data.converters.Converters
import com.example.weatherglobantapp.dataModel.landing.Weather
import com.example.weatherglobantapp.dataModel.landing.WeatherX
import com.example.weatherglobantapp.databinding.ItemLandingWeatherBinding
import com.example.weatherglobantapp.databinding.ItemListWeatherBinding

class WeatherListAdapter(private val list: List<WeatherX>?) :
    RecyclerView.Adapter<WeatherListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_landing_weather, parent, false)
        return WeatherListViewHolder(view)
    }


    override fun getItemCount(): Int {
        list?.let {
            return it.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: WeatherListViewHolder, position: Int) {
        list?.let { holder.bind(it, position) }
    }
}

class WeatherListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val convert = Converters()
    private val binding = ItemLandingWeatherBinding.bind(itemView)
    private fun inflateDrawable(weatherIcon: Int): Drawable? {
        return ResourcesCompat.getDrawable(binding.root.resources, weatherIcon, null)
    }

    fun bind(list: List<WeatherX>, position: Int) {
        binding.hour.text = convert.convertTimeToClock(returnGivenString(list,position).toLong()).toString()
        binding.degrees.text = convert.convertFarenheitToDegree(returnGivenString(list,position).toInt()).toString()
        when(list[position].icon) {
             "01n" ->{  binding.iconViewRecyclerview.setImageDrawable(inflateDrawable(R.drawable.w01n))}

             "02n" -> { binding.iconViewRecyclerview.setImageDrawable(inflateDrawable(R.drawable.w02n))}
             "03n" -> { binding.iconViewRecyclerview.setImageDrawable(inflateDrawable(R.drawable.w03n))}
             "04n" -> { binding.iconViewRecyclerview.setImageDrawable(inflateDrawable(R.drawable.w04n))}
             "09n" -> { binding.iconViewRecyclerview.setImageDrawable(inflateDrawable(R.drawable.w09n))}
             "10n" -> { binding.iconViewRecyclerview.setImageDrawable(inflateDrawable(R.drawable.w10n))}
             "11n" -> { binding.iconViewRecyclerview.setImageDrawable(inflateDrawable(R.drawable.w11n))}
             "13n" -> { binding.iconViewRecyclerview.setImageDrawable(inflateDrawable(R.drawable.w13n))}
             "50n" -> { binding.iconViewRecyclerview.setImageDrawable(inflateDrawable(R.drawable.w50n))}
        }
    }

    fun returnGivenString(list: List<WeatherX>, position: Int): String {
        return list[position].toString()
    }
}
