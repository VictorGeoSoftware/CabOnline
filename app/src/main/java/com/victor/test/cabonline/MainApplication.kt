package com.victor.test.cabonline

import android.app.Application
import com.victor.test.cabonline.di.AppComponent
import com.victor.test.cabonline.di.AppModule
import com.victor.test.cabonline.di.DaggerAppComponent
import io.realm.Realm

/**
 * Created by victorpalmacarrasco on 15/4/18.
 * ${APP_NAME}
 */
class MainApplication: Application() {
    val component: AppComponent by lazy { DaggerAppComponent.builder().appModule(AppModule(this)).build() }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
        Realm.init(this)
    }
}