package com.kelompok2.crudinventory.activities

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kelompok2.crudinventory.presentation.chatroom.ChatRoomActivity
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.kelompok2.crudinventory.adapter.MainAdapter
import com.kelompok2.crudinventory.model.ModelBarang
import java.util.*
import com.kelompok2.crudinventory.R;
import com.kelompok2.crudinventory.presentation.LauncherActivity

class MainActivity : AppCompatActivity(), MainAdapter.FirebaseDataListener {
    // dibawah ini declare variable
    private var mFloatingActionButton: ExtendedFloatingActionButton? = null
    private var mEditNama: EditText? = null
    private var mEditMerk: EditText? = null
    private var mEditHarga: EditText? = null
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: MainAdapter? = null
    private var daftarBarang: ArrayList<ModelBarang?>? = null
    private var mDatabaseReference: DatabaseReference? = null
    private var mFirebaseInstance: FirebaseDatabase? = null

    // kelar declare variable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //pada bagian ini merupakan window manager untuk menampilkan layout
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            }
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }

        // declare setiap recyclerview nya
        mRecyclerView = findViewById(R.id.recycler_view)
        mRecyclerView?.setHasFixedSize(true)
        mRecyclerView?.setLayoutManager(LinearLayoutManager(this))

        // declare setiap referensi database dari firebase
        FirebaseApp.initializeApp(this)
        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabaseReference = mFirebaseInstance!!.getReference("barang")
        mDatabaseReference!!.child("data_barang").addValueEventListener(object : ValueEventListener {
            //function untuk merubah data
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                daftarBarang = ArrayList()
                for (mDataSnapshot in dataSnapshot.children) {
                    val barang = mDataSnapshot.getValue(ModelBarang::class.java)!!
                    barang.key = mDataSnapshot.key
                    daftarBarang!!.add(barang)
                }
                mAdapter = MainAdapter(this@MainActivity, daftarBarang)
                mRecyclerView?.setAdapter(mAdapter)
            }

            //function kalo database eror
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@MainActivity,
                        databaseError.details + " " + databaseError.message, Toast.LENGTH_LONG).show()
            }
        })

        //declare untuk button tambah barang
        mFloatingActionButton = findViewById(R.id.tambah_barang)
        mFloatingActionButton?.setOnClickListener(View.OnClickListener { dialogTambahBarang() })
        mFloatingActionButton = findViewById(R.id.chat)
        mFloatingActionButton?.setOnClickListener(View.OnClickListener { goToChat()})
        mFloatingActionButton = findViewById(R.id.logout)
        mFloatingActionButton?.setOnClickListener { logOut() }
    }

     fun logOut() {

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Logout")
            builder.setMessage("Yakin ingin logout?")
            builder.setPositiveButton("YES") { _, _ ->
                val auth = FirebaseAuth.getInstance()
                auth.signOut()

        startActivity(Intent(this, LauncherActivity::class.java))
                finish()
            }
            builder.setNegativeButton("NO", null)
            builder.create().show()

    }


    // function untuk crud data
    override fun onDataClick(barang: ModelBarang?, position: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Edit Psikolog")
        builder.setPositiveButton("UPDATE") { dialog, id -> dialogUpdateBarang(barang) }
        builder.setNegativeButton("HAPUS") { dialog, id -> hapusDataBarang(barang) }
        builder.setNeutralButton("BATAL") { dialog, id -> dialog.dismiss() }
        val dialog: Dialog = builder.create()
        dialog.show()
    }

    // declare logika tambah barang
    private fun dialogTambahBarang() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Tambah Psikolog")
        val view = layoutInflater.inflate(R.layout.layout_edit_barang, null)
        mEditNama = view.findViewById(R.id.nama_barang)
        mEditMerk = view.findViewById(R.id.stok_barang)
        mEditHarga = view.findViewById(R.id.harga_barang)
        builder.setView(view)
        builder.setPositiveButton("SIMPAN") { dialog, id ->
            val namaBarang = mEditNama?.getText().toString()
            val merkBarang = mEditMerk?.getText().toString()
            val hargaBarang = mEditHarga?.getText().toString()
            if (!namaBarang.isEmpty() && !merkBarang.isEmpty() && !hargaBarang.isEmpty()) {
                submitDataBarang(ModelBarang(namaBarang, merkBarang, hargaBarang))
            } else {
                Toast.makeText(this@MainActivity, "Data harus di isi!", Toast.LENGTH_LONG).show()
            }
        }
        builder.setNegativeButton("BATAL") { dialog, id -> dialog.dismiss() }
        val dialog: Dialog = builder.create()
        dialog.show()
    }

    // declare logika update stock
    private fun dialogUpdateBarang(barang: ModelBarang?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Update Psikolog")
        val view = layoutInflater.inflate(R.layout.layout_edit_barang, null)
        mEditNama = view.findViewById(R.id.nama_barang)
        mEditMerk = view.findViewById(R.id.stok_barang)
        mEditHarga = view.findViewById(R.id.harga_barang)
        mEditNama?.setText(barang!!.nama)
        mEditMerk?.setText(barang?.stok)
        mEditHarga?.setText(barang?.harga)
        builder.setView(view)
        if (barang != null) {
            builder.setPositiveButton("SIMPAN") { dialog, id ->
                barang.nama = mEditNama?.getText().toString()
                barang.stok = mEditMerk?.getText().toString()
                barang.harga = mEditHarga?.getText().toString()
                updateDataBarang(barang)
            }
        }
        builder.setNegativeButton("BATAL") { dialog, id -> dialog.dismiss() }
        val dialog: Dialog = builder.create()
        dialog.show()
    }

    //function untuk mengirim data ke database apakah datanya tambah,update,hapus
    private fun submitDataBarang(barang: ModelBarang) {
        mDatabaseReference!!.child("data_barang").push()
                .setValue(barang).addOnSuccessListener(this) { Toast.makeText(this@MainActivity, "Data barang berhasil di simpan !", Toast.LENGTH_LONG).show() }
    }

    private fun updateDataBarang(barang: ModelBarang) {
        mDatabaseReference!!.child("data_barang").child(barang.key!!)
                .setValue(barang).addOnSuccessListener { Toast.makeText(this@MainActivity, "Data berhasil di update !", Toast.LENGTH_LONG).show() }
    }

    private fun hapusDataBarang(barang: ModelBarang?) {
        if (mDatabaseReference != null) {
            mDatabaseReference!!.child("data_barang").child(barang!!.key!!)
                    .removeValue().addOnSuccessListener { Toast.makeText(this@MainActivity, "Data berhasil di hapus !", Toast.LENGTH_LONG).show() }
        }
    }
    private fun goToChat() {
        val intent = Intent(this, ChatRoomActivity::class.java)
        startActivity(intent)
    }

    companion object {
        fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
            val win = activity.window
            val winParams = win.attributes
            if (on) {
                winParams.flags = winParams.flags or bits
            } else {
                winParams.flags = winParams.flags and bits.inv()
            }
            win.attributes = winParams
        }
    }

}