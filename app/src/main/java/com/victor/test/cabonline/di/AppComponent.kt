package com.victor.test.cabonline.di

import android.app.Application
import com.victor.test.cabonline.ui.intro.IntroActivityModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by victorpalmacarrasco on 15/4/18.
 * ${APP_NAME}
 */

@Singleton
@Component(modules = [AppModule::class, PresenterModule::class])
interface AppComponent {
    fun inject(application: Application)
    fun plus(introActivityModule: IntroActivityModule): IntroActivityComponent
}