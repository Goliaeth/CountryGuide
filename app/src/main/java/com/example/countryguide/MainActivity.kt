package com.example.countryguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.countryguide.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import java.io.File
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            val countryName = binding.countryNameEditText.text.toString()

            lifecycleScope.launch {
                try {
                    val countries = restCountriesService.getCountryByName(countryName)
                    val country = countries[0]

                    binding.countryNameTextView.text = country.name
                    binding.capitalTextView.text = country.capital
                    binding.populationTextView.text =
                        NumberFormat.getInstance().format(country.population)
                    binding.areaTextView.text = NumberFormat.getInstance().format(country.area)
                    binding.languagesTextView.text = country.languages.joinToString { it.name }
                    binding.imageView.load(country.flag) {
                        placeholder(R.drawable.ic_outline_emoji_emotions_24)
                        error(R.drawable.ic_baseline_error_outline_24)
                    }
                    binding.resultLayout.visibility = View.VISIBLE
                    binding.statusLayout.visibility = View.INVISIBLE
                } catch (e: Exception) {
                    binding.statusTextView.text = "Страна не найдена"
                    binding.statusImageView.setImageResource(R.drawable.ic_baseline_error_outline_24)
                    binding.resultLayout.visibility = View.INVISIBLE
                    binding.statusLayout.visibility = View.VISIBLE
                }
            }



        }

    }
}