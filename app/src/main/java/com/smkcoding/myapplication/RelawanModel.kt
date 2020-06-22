package com.smkcoding.myapplication

import androidx.room.*

@Entity(tableName = "relawan_covid")
data class RelawanModel (

    var nama: String,
    var email: String,
    var telp: String,
    var alamat: String,
    @PrimaryKey var key: String

) {
    constructor() : this("","","","", "")
}