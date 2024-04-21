package com.example.weatherglobantapp.ui.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherglobantapp.R
import com.example.weatherglobantapp.data.converters.Converters
import com.example.weatherglobantapp.dataModel.landing.WeatherX
import com.example.weatherglobantapp.dataModel.landing.forecast.Detail
import com.example.weatherglobantapp.dataModel.landing.forecast.Forecast
import com.example.weatherglobantapp.databinding.ItemLandingWeatherBinding

class WeatherListAdapter(private val list: List<WeatherX>, private val forecast: Forecast) :
    RecyclerView.Adapter<WeatherListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_landing_weather, parent, false)
        return WeatherListViewHolder(view)
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: WeatherListViewHolder, position: Int) {
        list?.let { holder.bind(it, position, forecast) }
    }
}

class WeatherListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemLandingWeatherBinding.bind(itemView)
    private fun inflateDrawable(weatherIcon: Int): Drawable? {
        return ResourcesCompat.getDrawable(binding.root.resources, weatherIcon, null)
    }

    fun bind(list: List<WeatherX>, position: Int, forecast: Forecast) {
        val convert =Converters()

        val clock = convert.convertTimeToClock(forecast.list[position].dt.toLong()).toString()
        val newClock = clock.substring(11,16)
        val faren =convert.convertFarenheitToDegree(forecast.list[position].main.temp).toString()
        val newFaren = faren.substring(0,7)
        binding.hour.text =  newClock
        binding.degrees.text = newFaren + " Fº"
        when (list[position].icon) {
          "01n" -> {
                binding.iconViewRecyclerview.setImageDrawable(inflateDrawable(R.drawable.w01n))
            }

            "02n" -> {
                binding.iconViewRecyclerview.setImageDrawable(inflateDrawable(R.drawable.w02n))
            }

            "03n" -> {
                binding.iconViewRecyclerview.setImageDrawable(inflateDrawable(R.drawable.w03n))
            }

            "04n" -> {
                binding.iconViewRecyclerview.setImageDrawable(inflateDrawable(R.drawable.w04n))
            }

            "09n" -> {
                binding.iconViewRecyclerview.setImageDrawable(inflateDrawable(R.drawable.w09n))
            }

            "10n" -> {
                binding.iconViewRecyclerview.setImageDrawable(inflateDrawable(R.drawable.w10n))
            }

            "11n" -> {
                binding.iconViewRecyclerview.setImageDrawable(inflateDrawable(R.drawable.w11n))
            }

            "13n" -> {
                binding.iconViewRecyclerview.setImageDrawable(inflateDrawable(R.drawable.w13n))
            }

            "50n" -> {
                binding.iconViewRecyclerview.setImageDrawable(inflateDrawable(R.drawable.w50n))
            }

            "01d" -> {
                binding.iconViewRecyclerview.setImageDrawable(inflateDrawable(R.drawable.w01d))
            }

            "02d" -> {
                binding.iconViewRecyclerview.setImageDrawable(inflateDrawable(R.drawable.w02d))
            }

            "03d" -> {
                binding.iconViewRecyclerview.setImageDrawable(inflateDrawable(R.drawable.w03d))
            }

            "04d" -> {
                binding.iconViewRecyclerview.setImageDrawable(inflateDrawable(R.drawable.w04d))
            }

            "09d" -> {
                binding.iconViewRecyclerview.setImageDrawable(inflateDrawable(R.drawable.w09d))
            }

            "10d" -> {
                binding.iconViewRecyclerview.setImageDrawable(inflateDrawable(R.drawable.w10d))
            }

            "11d" -> {
                binding.iconViewRecyclerview.setImageDrawable(inflateDrawable(R.drawable.w11d))
            }

            "13d" -> {
                binding.iconViewRecyclerview.setImageDrawable(inflateDrawable(R.drawable.w13d))
            }

            "50d" -> {
                binding.iconViewRecyclerview.setImageDrawable(inflateDrawable(R.drawable.w50d))
            }
        }
    }

    fun returnGivenString(list: List<WeatherX>, position: Int): String {
        return list[position].toString()
    }
}
