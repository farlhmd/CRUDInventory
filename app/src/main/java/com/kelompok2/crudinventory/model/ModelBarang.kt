package com.kelompok2.crudinventory.model

class ModelBarang {

    var key: String? = null
    var nama: String? = null
    var stok: String? = null
    var harga: String? = null

    constructor() {}

    constructor(namaBarang: String?, stokBarang: String?, hargaBarang: String?) {
        nama = namaBarang
        stok = stokBarang
        harga = hargaBarang
    }
}