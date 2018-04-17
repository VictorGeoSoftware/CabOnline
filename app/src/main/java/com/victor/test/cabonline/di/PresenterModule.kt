package com.victor.test.cabonline.di

import com.victor.test.cabonline.presenter.data.DataPresenter
import com.victor.test.cabonline.repository.JsonRepositoryImpl
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by victorpalmacarrasco on 15/4/18.
 * ${APP_NAME}
 */

@Module(includes = [AppModule::class])
class PresenterModule {

    @Provides
    fun provideDataPresenter(): DataPresenter = DataPresenter(AndroidSchedulers.mainThread(), Schedulers.newThread(), JsonRepositoryImpl())
}