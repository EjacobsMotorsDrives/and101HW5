package com.example.and101hw5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.and101hw5.databinding.ActivityMainBinding
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var pokeImageURL1= ""
    var pokeImageURL2=""
    var pokeImageURL3=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.selectPokemon.setOnClickListener { summonPokemon() }



    }

    private fun summonPokemon()
    {

        val client = AsyncHttpClient()
        client["https://pokeapi.co/api/v2/pokemon/charizard", object : JsonHttpResponseHandler()
        {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON)
            {


                pokeImageURL1 = json.jsonObject.getJSONArray("abilities").getJSONObject(0).toString()
                Log.d("ability", json.jsonObject.getJSONArray("abilities").toString())
                Log.d("ability", json.jsonObject.getJSONArray("abilities").toString())
                binding.pokemonName.text = pokeImageURL1.substring(20,25)
                pokeImageURL2 = json.jsonObject.getJSONArray("forms").getJSONObject(0).getString("name")
                binding.pokemonType.text = pokeImageURL2

                pokeImageURL3 = json.jsonObject.getJSONArray("types").getJSONObject(0).toString()
                binding.title.text = pokeImageURL3.substring(26,30)


            }

            override fun onFailure(statusCode: Int, headers: Headers?, errorResponse: String, throwable: Throwable?)
            {
                Log.d("Dog Error", errorResponse)
            }
        }]


    }




}