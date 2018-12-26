package com.shy.kotlindemo1

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.shy.kotlindemo1.domain.ForecastDataMapper
import org.jetbrains.anko.find

import kotlinx.android.synthetic.main.item_forecast.view.*

class ForecastListAdapter(val items : ForecastDataMapper.ForecastList,
                          val itemCLick : onItemClickListenr) :
        RecyclerView.Adapter<ForecastListAdapter.ViewHolder>(){


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(items.dailyForecast[position]){
//            holder.textView.text = items.[position]
//            holder.textView.text = "$date -  $description  -  $high/$low"
            holder.bindForecast(items.dailyForecast[position])
        }
    }

    override fun getItemCount(): Int {
        return items.dailyForecast.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast,parent,false)
        return ViewHolder(view,itemCLick)
    }


    class ViewHolder(view : View , val onItemClick : onItemClickListenr) : RecyclerView.ViewHolder(view){

//        private val iconView : ImageView
//        private val dateView : TextView
//        private val descriptionView : TextView
//        private val maxTemperatureView : TextView
//        private val minTemperatureView : TextView

        init {
//            iconView = view.find(R.id.icon)
//            dateView = view.find(R.id.date)
//            descriptionView = view.find(R.id.description)
//            maxTemperatureView = view.find(R.id.maxTemperature)
//            minTemperatureView = view.find(R.id.minTemperature)
        }

        fun bindForecast(forecast : ForecastDataMapper.Forecast ){
            with(forecast){
//                Glide.with(itemView.context).load(iconUrl).into(iconView)
//                dateView.text = date
//                descriptionView.text = description
//                maxTemperatureView.text = high.toString()
//                minTemperatureView.text = low.toString()
                //使用extensions简化代码
                Glide.with(itemView.context).load(iconUrl).into(itemView.icon)
                itemView.date.text = date
                itemView.description.text = description
                itemView.maxTemperature.text = high.toString()
                itemView.minTemperature.text = low.toString()
                itemView.setOnClickListener(){
                    onItemClick(forecast)
                }
            }
        }
    }


    public interface onItemClickListenr{
        operator fun invoke(forecast : ForecastDataMapper.Forecast)
    }
}