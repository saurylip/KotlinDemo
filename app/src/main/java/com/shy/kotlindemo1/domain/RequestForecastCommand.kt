package com.shy.kotlindemo1.domain

import com.shy.kotlindemo1.data.Request


/*
* 准备就绪
* */
class RequestForecastCommand(private val zipCode : String) : Command<ForecastDataMapper.ForecastList> {
    override fun execute(): ForecastDataMapper.ForecastList {
        return ForecastDataMapper().convertFromDataModel(Request(zipCode).execute())
    }
}