package com.victor.test.cabonline.data.models

/**
 * Created by victorpalmacarrasco on 15/4/18.
 * ${APP_NAME}
 */
data class BusinessHourDto(
        private val day: Int,
        private val openTime: String,
        private val closeTime: String,
        private val openTimeAfternoon: String,
        private val closeTimeAfternoon: String,
        private val comment: String
)