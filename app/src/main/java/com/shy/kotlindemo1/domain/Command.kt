package com.shy.kotlindemo1.domain

public interface Command<T> {

    fun execute() : T
}