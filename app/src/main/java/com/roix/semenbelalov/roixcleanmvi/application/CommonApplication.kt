package com.roix.semenbelalov.roixcleanmvi.application

import android.app.Application
import com.roix.semenbelalov.roixcleanmvi.di.main.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


/**
 * Created by roix template
 * https://github.com/roixa/RoixArchitectureTemplates
 */
class CommonApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Android context
            androidContext(this@CommonApplication)
            // modules
            modules(mainModule)
        }

    }
}