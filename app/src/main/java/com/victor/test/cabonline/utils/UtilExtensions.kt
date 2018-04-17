package com.victor.test.cabonline.utils

import android.app.Activity
import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.victor.test.cabonline.MainApplication

/**
 * Created by victorpalmacarrasco on 15/4/18.
 * ${APP_NAME}
 */

val Activity.app: MainApplication
    get() = application as MainApplication

fun trace(message: String) {
    System.out.println("CabOnline | $message ")
}

fun getDpFromValue(context: Context, value: Int): Int =
        Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value.toFloat(), context.resources.displayMetrics))

fun ViewGroup.inflate(layoutRes: Int): View =
        LayoutInflater.from(context).inflate(layoutRes, this, false)

inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)