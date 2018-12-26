package com.shy.kotlindemo1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

class TestOneActi : AppCompatActivity(){

    val i: Int = 7

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toast("打印吐司鸭")
        toast("打印吐司鸭",Toast.LENGTH_SHORT)

//        val s = "test"
//        val t = s[2]

        val s = "test"
        for (t in s){
            print(t)
        }

    }


    fun add(age1: Int ,age2 : Int) : Int{
        return age1+age2
    }

    fun add2(age1: Int , age2: Int) : Int = age1+age2

    fun toast(msg : String , length : Int = Toast.LENGTH_LONG){
        Toast.makeText(this,msg,length).show();
    }


    val d: Double = i.toDouble()

    val a : Char = 'c'
    val b : Int = a.toInt()

}