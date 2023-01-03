 package com.example.zastosowaniesortowan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Stopwatch
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

 class BabelkoweActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_babelkowe)

        val liczba = findViewById<EditText>(R.id.podana_liczba)
        val removeLast = findViewById<Button>(R.id.removeLast)
        val add = findViewById<Button>(R.id.dodaj)
        val clear = findViewById<Button>(R.id.wyczysc)
        val wynik = findViewById<TextView>(R.id.wynik)
        val back = findViewById<Button>(R.id.back)
        val lista: MutableList<Int> = mutableListOf()

        add.setOnClickListener {
            if(liczba.text.isEmpty()){
                Toast.makeText(applicationContext, "Musisz podać jakąś liczbę!", Toast.LENGTH_SHORT).show()
            }
            else{
                lista.add(liczba.text.toString().toInt())
                val (posortowana, czas) = sortowanie_babelkowe(lista, lista.size - 1)
                zwracanie_tekstu(posortowana, czas, wynik)
                liczba.text.clear()
            }
        }
        removeLast.setOnClickListener {
            if(lista.isEmpty()){
                Toast.makeText(applicationContext, "Aby coś usunąć z listy musi się coś w niej znajdować!", Toast.LENGTH_SHORT).show()
            }
            else{
                lista.removeLast()
                val (posortowana, czas) = sortowanie_babelkowe(lista, lista.size - 1)
                zwracanie_tekstu(posortowana, czas, wynik)
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
     fun sortowanie_babelkowe(tab: MutableList<Int>, size: Int): Pair<MutableList<Int>, Long> {
         var pom: Int
         val sw: Stopwatch = Stopwatch.createStarted()
         for(i in 0..size){
             for (j in 1..size){
                 if(tab[j-1]>tab[j]){
                     pom = tab[j]
                     tab[j] = tab[j-1]
                     tab[j-1] = pom
                 }
             }
         }
         sw.stop()
         val czas: Long = sw.elapsed(TimeUnit.MICROSECONDS)
         return tab to czas
     }
     fun zwracanie_tekstu(tab: MutableList<Int>, czas: Long, textView: TextView){
         var tekst = ""
         for(i in 0 until tab.size){
             if(tab.size-1 == i){
                 tekst += tab[i].toString() + "\n"
             }
             else {
                 tekst += tab[i].toString() + ", "
             }
         }
         tekst += "Posortowano w czasie: $czas μs"
         textView.text = tekst
     }
}