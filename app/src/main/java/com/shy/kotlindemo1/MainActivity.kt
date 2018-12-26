package com.shy.kotlindemo1

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.UiThread
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.shy.kotlindemo1.data.Request
import com.shy.kotlindemo1.domain.ForecastDataMapper
import com.shy.kotlindemo1.domain.RequestForecastCommand
import com.shy.kotlindemo1.rxjavaandretrofit.NetFactory
import com.shy.kotlindemo1.rxjavaandretrofit.ShyObserver
import com.shy.kotlindemo1.rxjavaandretrofit.WeatherInfoModel
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    //定义天气数组数组
    val weaList = listOf<String>(
            "北京 -0 -  微风",
            "北京 -10 - 微风",
            "北京 -13 - 微风",
            "北京 -20 - 微风",
            "北京 -5 -  微风"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val recycler : RecyclerView = find(R.id.recycler)
//        val recycler = findViewById(R.id.recycler) as RecyclerView
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                toast("click")
            }
        })
//        recycler.adapter = ForecastListAdapter(weaList)

        //1.简单请求
//        val url = "https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22"
//        doAsync() {
//            Request(url).run()
//            uiThread { toast("Reauest") }
//        }

        //2.封装后的请求
//        doAsync {
//            val result = RequestForecastCommand("94043").execute()
//            uiThread {
//                recycler.adapter = ForecastListAdapter(result)
//            }
//        }

        //添加了点击事件
        doAsync {
            val execute = RequestForecastCommand("94043").execute()
            uiThread {
                recycler.adapter = ForecastListAdapter(execute, object :ForecastListAdapter.onItemClickListenr{
                    override fun invoke(forecast: ForecastDataMapper.Forecast) {
                        toast("点击了item")
                    }
                })
            }
        }


        //rxjava+retrofit
        NetFactory.SERVER_API.getWeather("101190301").subscribe(object : ShyObserver<WeatherInfoModel>(){
            override fun onSuccess(model: WeatherInfoModel) {
                toast("success" + model.weatherinfo.toString())
            }

            override fun onError(errorMsg: String?) {
                toast("error$errorMsg")
            }
        })
    }

    fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

}
