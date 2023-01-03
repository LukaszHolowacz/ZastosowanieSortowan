package com.example.zastosowaniesortowan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Stopwatch
import java.util.Collections.swap
import java.util.concurrent.TimeUnit

class HeapsortActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heapsort)

        val liczba = findViewById<EditText>(R.id.podana_liczba2)
        val removeLast = findViewById<Button>(R.id.removeLast2)
        val add = findViewById<Button>(R.id.dodaj2)
        val clear = findViewById<Button>(R.id.wyczysc2)
        val wynik = findViewById<TextView>(R.id.wynik2)
        val back = findViewById<Button>(R.id.back2)
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

    fun heapify(arr: MutableList<Int>, n: Int, i: Int){
        var largest = i
        val left = 2 * i + 1
        val right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest])
            largest = left

        if (right < n && arr[right] > arr[largest])
            largest = right

        if (largest != i) {
            val pom = arr[i]
            arr[i] = arr[largest]
            arr[largest] = pom
            heapify(arr, n, largest)
        }
    }

    fun HeapSort(arr: MutableList<Int>, n: Int) {
        for (i in n / 2 - 1 downTo 0) {
            heapify(arr, n, i)
        }

        for (i in n - 1 downTo 0) {
            val pom = arr[0]
            arr[0] = arr[i]
            arr[i] = pom
            heapify(arr, i, 0)
        }
    }

    fun wypisywanie(textView: TextView, tab: MutableList<Int>) {
        var tekst = ""
        val sw: Stopwatch = Stopwatch.createStarted()
        HeapSort(tab, tab.size)
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