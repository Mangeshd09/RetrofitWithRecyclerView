package com.example.retrofitwithrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"


class MainActivity : AppCompatActivity() {

    lateinit var myAdapter: MyAdapter
    lateinit var linearLayout: LinearLayoutManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)

        recyclerView.setHasFixedSize(true)
        linearLayout = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayout
        getMyData()
    }

    private fun getMyData() {
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)

        val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>
            ) {
                val responseBody = response.body()

                if (responseBody != null){
                    myAdapter = MyAdapter(baseContext, responseBody)
                    myAdapter.notifyDataSetChanged()
                    recyclerView.adapter = myAdapter

                }
                else{
                    Toast.makeText(applicationContext, "Null Error", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: "+ t.message)
            }
        })
    }
}