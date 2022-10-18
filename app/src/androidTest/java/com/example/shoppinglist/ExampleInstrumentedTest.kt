<<<<<<< HEAD:app/src/androidTest/java/com/example/coroutinestart/ExampleInstrumentedTest.kt
package com.example.coroutinestart
=======
package com.example.shoppinglist
>>>>>>> 8f4d2d1e0c00317f31c433681df1325aa140212b:app/src/androidTest/java/com/example/shoppinglist/ExampleInstrumentedTest.kt

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.sumin.shoppinglist", appContext.packageName)
    }
}