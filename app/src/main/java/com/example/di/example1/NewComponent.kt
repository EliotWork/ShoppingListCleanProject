package com.example.di.example1

import dagger.Component

@Component
interface NewComponent {

//    fun getKeyBoard():Keyboard
//    fun getKeyMouse():Mouse
//    fun getKeyMonitor():Monitor

    fun inject(activity: Activity)

}