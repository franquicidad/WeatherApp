package com.example.weatherglobantapp.ui.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weatherglobantapp.R
import com.example.weatherglobantapp.Util.UserLocation
import com.example.weatherglobantapp.data.converters.Converters
import com.example.weatherglobantapp.dataModel.landing.forecast.Detail
import com.example.weatherglobantapp.databinding.FragmentForecastBinding
import com.example.weatherglobantapp.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForecastFragment : Fragment() {

    private var _binding: FragmentForecastBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentForecastBinding.inflate(inflater, container, false)
        val root: View = binding.root



        binding.composeView.apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                // In Compose world
                MaterialTheme {
                    PopulateRecyclerview(homeViewModel, container, this@ForecastFragment)

                }
            }
        }
        return binding.root
    }

    @Composable
    private fun PopulateRecyclerview(
        homeViewModel: HomeViewModel,
        container: ViewGroup?,
        forecastFragment: ForecastFragment
    ) {
        val userLocation = UserLocation()
        container?.let {
            userLocation.getUserLocationClassMethod(it, forecastFragment) { lat, long ->
                homeViewModel.getWeatherList(lat, long)
            }
        }
        val forecast by homeViewModel.forecast.collectAsState()
        LazyColumn {
            forecast?.let {
                items(
                    items = it.list,
                ) { forecast ->
                    WeatherItem(forecast)
                }
            }
        }

    }

    @Composable
    fun WeatherItem(forecast: Detail?) {
        val converter = Converters()
        val clock = forecast?.dt?.let { converter.convertTimeToClock(it.toLong()).toString() }
        val newClock = clock?.substring(0, 10)
        val fontSizeView = 13.sp
        val currentWeatherDes = forecast?.weather?.map { it.description }

        Card(Modifier.padding(3.dp).background(Color.Green).fillMaxSize(),) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(3.dp).background(Color.Green)
            ) {

                Text(
                    text = "${newClock}",
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
                Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "High Temp:${forecast?.main?.temp_max}º", fontSize = fontSizeView, textAlign = TextAlign.Justify)
                    Spacer(modifier = Modifier.padding(start = 60.dp))
                    Text(text = "Wind Speed", fontSize = fontSizeView, textAlign = TextAlign.Justify,)
                    getIcon(forecast?.weather?.map { it.icon })?.let { painterResource(it) }?.let {
                        Icon(
                            modifier = Modifier.padding(start = 70.dp,),
                            painter = it,
                            contentDescription = ""
                        )
                    }
                }
                Row (horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth()){
                    Text(text = "Low Temp:${forecast?.main?.temp_min}º", fontSize =  fontSizeView)
                    Text(text = "${forecast?.wind?.speed} mph", fontSize = fontSizeView,textAlign = TextAlign.Justify, modifier = Modifier.padding(start =70.dp))
                }
                Row (horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()){
                    Text(text = "NW", fontSize = fontSizeView,textAlign = TextAlign.Justify, modifier = Modifier.padding(start = 190.dp))
                    Text(text = "${forecast?.weather?.map { it.description }}", fontSize = fontSizeView, modifier = Modifier.padding(start = 60.dp))
                }
            }
        }


    }

    private fun getIcon(listIcon: List<String>?): Int? {
        val elements = listIcon
        var string = ""

        for (s in elements!!) {
            string += s
        }
        when (string) {
            "01n" -> return R.drawable.w01n


            "02n" -> {
                return R.drawable.w02n
            }

            "03n" -> {return R.drawable.w03n
            }

            "04n" -> {
                return R.drawable.w04n
            }

            "09n" -> {
                return R.drawable.w09n
            }

            "10n" -> {
                return R.drawable.w10n
            }

            "11n" -> {
                return R.drawable.w11n
            }

            "13n" -> {
                return R.drawable.w13n
            }

            "50n" -> {
                return R.drawable.w50n
            }

            "01d" -> {
                return R.drawable.w01d
            }

            "02d" -> {
                return R.drawable.w02d
            }

            "03d" -> {
                return R.drawable.w03d
            }

            "04d" -> {
                return R.drawable.w04d
            }

            "09d" -> {
                return R.drawable.w09d
            }

            "10d" -> {
                return R.drawable.w10d
            }

            "11d" -> {
                return R.drawable.w11d
            }

            "13d" -> {
                return R.drawable.w13d
            }

            "50d" -> {
                return R.drawable.w50d
            }
        }

        return null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
