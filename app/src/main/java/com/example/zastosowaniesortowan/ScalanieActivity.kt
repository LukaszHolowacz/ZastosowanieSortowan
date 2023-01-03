package com.example.zastosowaniesortowan

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.MultiAutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Stopwatch
import java.util.concurrent.TimeUnit

class ScalanieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scalanie)

        val liczba = findViewById<EditText>(R.id.podana_liczba5)
        val removeLast = findViewById<Button>(R.id.removeLast5)
        val add = findViewById<Button>(R.id.dodaj5)
        val clear = findViewById<Button>(R.id.wyczysc5)
        val wynik = findViewById<TextView>(R.id.wynik5)
        val back = findViewById<Button>(R.id.back5)
        val lista: MutableList<Int> = mutableListOf()

        add.setOnClickListener {
            if(liczba.text.isEmpty()){
                Toast.makeText(applicationContext, "Musisz podać jakąś liczbę!", Toast.LENGTH_SHORT).show()
            }
            else{
                lista.add(liczba.text.toString().toInt())
                wypisywanie(wynik, lista)
                liczba.text.clear()
            }
        }
        removeLast.setOnClickListener {
            if(lista.isEmpty()){
                Toast.makeText(applicationContext, "Aby coś usunąć z listy musi się coś w niej znajdować!", Toast.LENGTH_SHORT).show()
            }
            else{
                lista.removeLast()
                wypisywanie(wynik, lista)
            }
        }
        clear.setOnClickListener {
            if(lista.isEmpty()){
                Toast.makeText(applicationContext, "Lista jest pusta nie ma co czyścić!", Toast.LENGTH_SHORT).show()
            }
            else{
                lista.clear()
                wynik.text = getString(R.string.posortowana)
            }
        }
        back.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
    fun merge(arr: MutableList<Int>, l: Int, m: Int, r: Int) {
        val n1 = m - l + 1
        val n2 = r - m
        val L = IntArray(n1)
        val R = IntArray(n2)

        for (i in 0 until n1) L[i] = arr[l + i]
        for (j in 0 until n2) R[j] = arr[m + 1 + j]

        var i = 0
        var j = 0

        var k = l
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i]
                i++
            } else {
                arr[k] = R[j]
                j++
            }
            k++
        }

        while (i < n1) {
            arr[k] = L[i]
            i++
            k++
        }

        while (j < n2) {
            arr[k] = R[j]
            j++
            k++
        }
    }
    fun sort(arr: MutableList<Int>, l: Int, r: Int) {
        if (l < r) {
            val m = l + (r - l) / 2

            sort(arr, l, m)
            sort(arr, m + 1, r)

            merge(arr, l, m, r)
        }
    }

    fun wypisywanie(textView: TextView, tab: MutableList<Int>) {
        var tekst = ""
        val sw: Stopwatch = Stopwatch.createStarted()
        sort(tab, 0, tab.size - 1)
        sw.stop()
        for(i in 0 until tab.size){
            if(tab.size-1 == i){
                tekst += tab[i].toString() + "\n"
            }
            else {
                tekst += tab[i].toString() + ", "
            }
        }
        val czas: Long = sw.elapsed(TimeUnit.MICROSECONDS)
        tekst += "Posortowano w czasie: $czas μs"
        textView.text = tekst
    }
}