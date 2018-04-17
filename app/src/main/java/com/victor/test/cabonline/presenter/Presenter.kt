package com.victor.test.cabonline.presenter

/**
 * Created by victorpalmacarrasco on 15/4/18.
 * ${APP_NAME}
 */

abstract class Presenter<T1> {
    var view: T1? = null


    open fun destroy() {
        System.out.println("check onDestroy!")
    }
}