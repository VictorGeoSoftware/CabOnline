package com.victor.test.cabonline.data.models

import android.support.annotation.Size

/**
 * Created by victorpalmacarrasco on 15/4/18.
 * ${APP_NAME}
 */
data class StoreDto (
    var id: Long,
    var precode: String,
    @Size(min = 1, max = 6)
    var code: String,
    var label: String,
    var selfScanningAvailable: Boolean,
    var thumbnail: String,
    var displayLoyaltyBarcode: Boolean,
    var active: Boolean,
    var visible: Boolean,
    var fixProductName: Boolean,

    @Size(min = 1, max = 255)
    var street: String,

    @Size(min = 1, max = 255)
    var city: String,

    @Size(min = 1, max = 16)
    var zipCode: String,

    @Size(min = 1, max = 128)
    var province: String,
    var country: String,

    var provinceId: Long = 0,
    var regionId: Long = 0,
    var regionDesc: String,

    var longitude: Double,

    var latitude: Double,

    @Size(min = 9, max = 22)
    var faxNumber: String,

    @Size(min = 9, max = 22)
    var phoneNumber: String,

    @Size(min = 1, max = 200)
    var email: String,

    var storeImage: String,
    var businessHours: ArrayList<BusinessHourDto>,
    var comingHolidays: ArrayList<String>,
    var turn: Boolean,
    var ableOnlineShopping: Boolean,
    var urlOnlineShopping: String,

    @Size(max = 500)
    var ipAddressV4: String,

    @Size(min = 1, max = 500)
    var ipBmc: String,

    @Size(min = 1, max = 500)
    var ipMasterCashRegister: String,

    @Size(min = 1, max = 500)
    var ipMirrorCashRegister: String,

    @Size(min = 1, max = 255)
    var userCashRegister: String,

    @Size(min = 1, max = 255)
    var passCashRegister: String,
    @Size(min = 1, max = 255)
    var portCashRegister: String
)