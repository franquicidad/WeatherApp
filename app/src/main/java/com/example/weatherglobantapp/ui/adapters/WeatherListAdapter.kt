package com.example.weatherglobantapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherglobantapp.R
import com.example.weatherglobantapp.data.model.Weather
import com.example.weatherglobantapp.databinding.ItemListWeatherBinding

class WeatherListAdapter(private val list: List<Weather>) :
    RecyclerView.Adapter<WeatherListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_list_weather,parent,false)
        return WeatherListViewHolder(view)
    }


    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: WeatherListViewHolder, position: Int) {
        holder.bind(list,position)
    }
}

class WeatherListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding: ItemListWeatherBinding = ItemListWeatherBinding.bind(itemView)
    fun bind(list:List<Weather>,position: Int){
        binding.apply {
            weatherState.text= returnGivenString(list,position)
            temperature.text= returnGivenString(list,position)
            temperatureLow.text= returnGivenString(list,position)
            windSpeedValue.text= returnGivenString(list,position)
            coordinates.text= returnGivenString(list,position)
            date.text= returnGivenString(list,position)
        }

    }

    fun returnGivenString(list: List<Weather>, position: Int): String {
        return list[position].toString()
    }
}
