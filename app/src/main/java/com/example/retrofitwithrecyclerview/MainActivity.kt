package com.example.retrofitwithrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitwithrecyclerview.databinding.ActivityMainBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"


class MainActivity : AppCompatActivity() {

    private lateinit var viewAdapter : MyAdapter
    private lateinit var list: ArrayList<MyDataItem>

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        list = ArrayList()
        viewAdapter = MyAdapter( list )
        binding.recyclerView.adapter = viewAdapter

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        getMyData()
    }

    private fun getMyData() {

        val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<ArrayList<MyDataItem>?> {
            override fun onResponse(
                call: Call<ArrayList<MyDataItem>?>,
                response: Response<ArrayList<MyDataItem>?>
            ) {

                if (response.body() != null){
                    val responseBody = response.body()!!

                    list.addAll(responseBody)
                    viewAdapter.notifyItemRangeInserted(10, list.size)

                }
                else{
                    Toast.makeText(applicationContext, "Null Error", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<MyDataItem>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: "+ t.message)
            }
        })
    }
}