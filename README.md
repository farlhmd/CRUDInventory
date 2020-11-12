# CRUDInventory
 Project ini ditujukan untuk memenuhi Tugas Besar 2 Mata Kuliah Mobile Programming Android
 
 Tutorial:
 —————————————————————————————

 // Pembuatan project
     Create new project dengan nama CRUD Inventory, lalu ubah nama packages nya menjadi com.kelompok2.crudinventory.
 Pastikan bahasa yang digunakan adalah Java, tapi pada project ini akan menggunakan Kotlin juga. cara menambahkannya akan kami bahas nanti.

     Lalu, pastikan juga minimum SDK versionnya adalah 21, agar dapat menjangkau lebih banyak device dan juga tentunya lebih banyak library yang diperlukan. setelah itu, create project dan biarkan android studio melakukan indexing untuk yang pertama kali. setelah selesai, maka kita akan mulai mengoding.

 —————————————————————————————

 // Melengkapi gradle dengan depedencies dan library yang dibutuhkan (Buka file build.gradle(:app) sama 
 build.gradle(CRUD Inventory))

 / /Buka file build.gradle(CRUD Inventory)
     Pertama, lengkapi seluruh dependencies serta library yang kita butuhkan untuk aplikasi ini.

     Karena aplikasi ini menggunakan bahasa pemrograman Java dan Kotlin, kita akan menambahkan plugin dan extentsionsnya pada build.gradle terlebih dahulu. pada gradle project(CRUD Inventory) tambahkan depedencies untuk plugin kotlin dan juga google services nya. 

     Setelah itu menambahkan library agar kita dapat mencostumize gambar, kita menggunakan maven dari amulyakhare. 

     Sekarang kita akan memastikan apakah plugin dan dependenciesnya sudah benar dengan cara Sync Gradle.
 // Coba Sync Gradle nya, kalau error coba baca errornya(pastiin  gaada yang typo), kalau engga error:

     Oke, gradle nya berhasil di Sync, kita lanjut ke build.gradle(:app) /*buka build.gradle(:app)*/ disini kita memerlukan 3 plugin lagi untuk memastikan kode kotlin dapat dijalankan pada project ini. /*tambahin dah tu 3*/

     Setelah pluginnya di tambahkan, kami akan menambahkan library untuk memenuhi keperluan aplikasi. /*tambahin 6 library yang ada di kodingan yang udah jadi, baca juga keterangannya*/
     Kalau sudah menambahkan semua, coba Sync Now. /*kalau gagal coba liat lagi ada typo atau engga.. kalau berhasil, mantap*/

 —————————————————————————————

 // Melengkapi keperluan koneksi internet
 // Buka file manifests/AndroidManifest.xml

     Setelah selesai Sync gradle, kita membutuhkan internet untuk menjalankan aplikasi, karena aplikasi kita menggunakan realtime database, yaitu firebase. juga tambahkan cleartexttraffic untuk dapat mengakses ke http

 —————————————————————————————

 // Membuat folder packages agar project lebih rapi
 // klik kanan folder com.kelompok2.crudinventory

     Buat 4 folder packages dengan nama “activities, adapter, holder dan model”. Lalu, pindahkan class MainActivity ke dalam folder activities, dan lakukan refactor.

 //lanjutttt
 —————————————————————————————

 // Ngoding layout activity_main.xml 
 // buka file res/layout/activity_main.xml

     ganti androidx.constraintlayout.widget.ConstraintLayout
 jadi RelativeLayout.
     
     terus, hapus tag TextView nya, ganti sama kodingan ini (copas codingan di file).Habis di copas bakal banyak error.
     
     Pertama, copy file yang ada di drawable/background.xml, paste ke folder drawable project yang lagi di kerjain.

     Kedua, copy kodingan yang ada di file res/values/styles.xml, lalu paste sesuai instruksi

 // Kalau udah gaada error, next.


 —————————————————————————————

 //Next, bikin Kotlin Class namanya ‘ModelBarang’ di folder model

     langsung copas semua, dimulai dari declare class nya ampe bawah. package nya gausah copas.

     // tujuan disini tu untuk bikin constructor inventorynya (inventorynya punya attribute apa aja)

     habis tu tinggal import satu satu (alt + enter) yang merah merah.

 //kalau udah gaada merah merah, gas

 —————————————————————————————

 // gass bikin layout namanya item_barang

     Copy aja semua dari file, paste ke project baru. kalo mo tanyaa tanya aee

 //habis tu next

 —————————————————————————————

 //nextt, bikin Kotlin Class namanya ‘MainViewHolder’

     Ini tu buat bikin view layout dari inputan barang baru itu kaya gimana. makanya viewnya itu pakai Cardview. 
     Habis tu tinggal import satu satu (alt + enter) yang merah merah.

 // kalo dah, lanjutt

 —————————————————————————————

 // lanjut, bikin Kotlin Class namanya ‘MainAdapter’di folder adapter

     Langsung aja copas semua dari “class MainAdapter(…” sampe bawah, kalo bingung tanya besok aee. habis tu tinggal import satu satu (alt + enter) yang merah merah.

 —————————————————————————————

 // MainAdapter kelar, baru edit MainActivity nya

     tambahin implements di deklarasi classnya jadi: “public class MainActivity extends AppCompatActivity implements MainAdapter.FirebaseDataListener”

     Declare semua variablenya, jangan lupa import (alt + enter). Ikutin instruksi yang ada di file.
     
     Keterangannya juga ada di file.
     

 —————————————————————————————


 // habis ini, bikin fragment layout buat nampilin pilihan edit barangnya
 // Bikin layout namanya ‘layout_edit_barang’

     Trus copy semua aja


     
 —————————————————————————————

 // terakhir, pergi ke firebase buat nyiapin databasenya.












