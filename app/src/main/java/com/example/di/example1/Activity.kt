package com.example.di.example1


class Activity {

    lateinit var keyboard: Keyboard

    init {
        Component().inject(this)
    }

}