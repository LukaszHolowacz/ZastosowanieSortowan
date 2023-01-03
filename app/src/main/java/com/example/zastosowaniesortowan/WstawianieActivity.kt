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

class WstawianieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wstawianie)

        val liczba = findViewById<EditText>(R.id.podana_liczba3)
        val removeLast = findViewById<Button>(R.id.removeLast3)
        val add = findViewById<Button>(R.id.dodaj3)
        val clear = findViewById<Button>(R.id.wyczysc3)
        val wynik = findViewById<TextView>(R.id.wynik3)
        val back = findViewById<Button>(R.id.back3)
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

    fun sort(arr: MutableList<Int>, n: Int) {
        for (i in 1 until n) {
            val key = arr[i]
            var j = i - 1
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j]
                j = j - 1
            }
            arr[j + 1] = key
        }
    }

    fun wypisywanie(textView: TextView, tab: MutableList<Int>) {
        var tekst = ""
        val sw: Stopwatch = Stopwatch.createStarted()
        sort(tab, tab.size)
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