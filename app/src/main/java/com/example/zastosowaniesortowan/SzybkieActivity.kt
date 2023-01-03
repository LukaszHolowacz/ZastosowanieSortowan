package com.example.zastosowaniesortowan

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Stopwatch
import java.util.concurrent.TimeUnit

class SzybkieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_szybkie)

        val liczba = findViewById<EditText>(R.id.podana_liczba4)
        val removeLast = findViewById<Button>(R.id.removeLast4)
        val add = findViewById<Button>(R.id.dodaj4)
        val clear = findViewById<Button>(R.id.wyczysc4)
        val wynik = findViewById<TextView>(R.id.wynik4)
        val back = findViewById<Button>(R.id.back4)
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
    fun swap(arr: MutableList<Int>, i: Int, j: Int) {
        val temp = arr[i]
        arr[i] = arr[j]
        arr[j] = temp
    }
    fun partition(arr: MutableList<Int>, low: Int, high: Int): Int {
        val pivot = arr[high]
        var i = low - 1
        for (j in low..high - 1) {
            if (arr[j] < pivot) {
                i++
                swap(arr, i, j)
            }
        }
        swap(arr, i + 1, high)
        return i + 1
    }
    fun quickSort(arr: MutableList<Int>, low: Int, high: Int) {
        if (low < high) {
            val pi = partition(arr, low, high)
            quickSort(arr, low, pi - 1)
            quickSort(arr, pi + 1, high)
        }
    }
    fun wypisywanie(textView: TextView, tab: MutableList<Int>) {
        var tekst = ""
        val sw: Stopwatch = Stopwatch.createStarted()
        quickSort(tab, 0, tab.size-1)
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