package com.example.weatherglobantapp.ui.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.graphics.toColorInt
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
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            forecast?.let {
                items(
                    items = it.list,
                ) { forecast ->
                    WeatherItem(forecast)
                }
            }
        }

    }

    companion object {
        const val DATE = "date"

        const val HIGH_TEMP = "highTemp"
        const val LOW_TEMP = "LowTemp"

        const val WIND_T = "windText"
        const val WIND_VALUE = "wind"
        const val NW = "north_west"

        const val ICON = "icon"
        const val W_DESCRIPTION = "weather"
    }

    @Composable
    fun WeatherItem(forecast: Detail?) {
        val converter = Converters()
        val clock = forecast?.dt?.let { converter.convertTimeToClock(it.toLong()).toString() }
        val newClock = clock?.substring(0, 10)
        val fontSizeView = 13.sp
        val listWeather = forecast?.weather
        val currentWeatherDes = listWeather?.get(0)?.description
        val trim = currentWeatherDes?.substring(
            1,
            currentWeatherDes?.length?.minus(1) ?: currentWeatherDes.length
        )
        ConstraintLayout(modifier = Modifier.background(Color("#008D75".toColorInt())
        ).fillMaxSize()) {
            val (date,
                highTemp,
                lowTemp,
                windText,
                windValue,
                nw,
                icon,
                description) = createRefs()


            Text(
                text = "${newClock}",
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .constrainAs(date) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            Text(
                text = "High Temp:${forecast?.main?.temp_max}º",
                fontSize = fontSizeView,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .constrainAs(highTemp) {
                        start.linkTo(parent.start)
                        start.linkTo(parent.start)
                    }.padding(top = 40.dp, start = 10.dp)
            )
            Spacer(modifier = Modifier.padding(start = 60.dp))
            Text(
                text = "Wind Speed",
                fontSize = fontSizeView,
                modifier = Modifier
                    .constrainAs(windText) {
                        start.linkTo(highTemp.end)
                        top.linkTo(date.bottom)

                    }
                    .padding(start = 20.dp, top = 10.dp)
            )
            getIcon(forecast?.weather?.map { it.icon })?.let { painterResource(it) }?.let {
                Icon(
                    modifier = Modifier
                        .constrainAs(icon) {
                            start.linkTo(windText.end)
                        }.padding(top=30.dp, start = 100.dp), painter = it,
                    contentDescription = ""
                )
            }

            Text(
                text = "Low Temp:${forecast?.main?.temp_min}º",
                fontSize = fontSizeView,
                modifier = Modifier.constrainAs(lowTemp) {
                    top.linkTo(highTemp.bottom)
                    start.linkTo(parent.start)
                }.padding(start = 10.dp)
            )
            Text(
                text = "${forecast?.wind?.speed} mph",
                fontSize = fontSizeView,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .constrainAs(windValue) {
                        top.linkTo(windText.bottom)
                        start.linkTo(windText.start)
                        end.linkTo(windText.end)
                    }
            )
            Text(
                text = "NW",
                fontSize = fontSizeView,
                modifier = Modifier
                    .constrainAs(nw) {
                        top.linkTo(windValue.bottom)
                        start.linkTo(windValue.start)
                        end.linkTo(windValue.end)
                    }
            )
            Text(
                text = "${if (listWeather?.size!! < 1) trim else currentWeatherDes}",
                fontSize = fontSizeView,
                modifier = Modifier
                    .constrainAs(
                        description
                    ){
                        top.linkTo(icon.bottom)
                        end.linkTo(icon.end)
                        start.linkTo(icon.start)
                    }.padding(start = 100.dp)
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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

        "03n" -> {
            return R.drawable.w03n
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



