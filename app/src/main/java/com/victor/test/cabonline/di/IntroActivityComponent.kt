package com.victor.test.cabonline.di

import com.victor.test.cabonline.ui.intro.IntroActivity
import com.victor.test.cabonline.ui.intro.IntroActivityModule
import dagger.Subcomponent

/**
 * Created by victorpalmacarrasco on 15/4/18.
 * ${APP_NAME}
 */

@Subcomponent(modules = [IntroActivityModule::class])
interface IntroActivityComponent {
    fun inject(introActivity: IntroActivity)
}