package com.shy.kotlindemo1

class Dog {

    var color : Int = 3
        get() = field.toBigDecimal().intValueExact()
        set(value) {
            field = 3 + value
    }
}