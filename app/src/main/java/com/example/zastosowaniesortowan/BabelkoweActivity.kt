 package com.example.zastosowaniesortowan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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
        val lista: MutableList<Int> = mutableListOf()

        add.setOnClickListener {
            if(liczba.text.isEmpty()){
                Toast.makeText(applicationContext, "Musisz podać jakąś liczbę!", Toast.LENGTH_SHORT).show()
            }
            else{
                lista.add(liczba.text.toString().toInt())
                zwracanie_tekstu(sortowanie_babelkowe(lista, lista.size - 1), wynik)
                liczba.text.clear()
            }
        }
        removeLast.setOnClickListener {
            if(lista.isEmpty()){
                Toast.makeText(applicationContext, "Aby coś usunąć z listy musi się coś w niej znajdować!", Toast.LENGTH_SHORT).show()
            }
            else{
                lista.removeLast()
                zwracanie_tekstu(sortowanie_babelkowe(lista, lista.size - 1), wynik)
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
    }
     fun sortowanie_babelkowe(tab: MutableList<Int>, size: Int): MutableList<Int>{
         var pom: Int
         for(i in 0..size){
             for (j in 1..size){
                 if(tab[j-1]>tab[j]){
                     pom = tab[j]
                     tab[j] = tab[j-1]
                     tab[j-1] = pom
                 }
             }
         }
         return tab
     }
     fun zwracanie_tekstu(tab: MutableList<Int>, textView: TextView){
         var tekst = ""
         for(i in 0 until tab.size){
             if(tab.size-1 == i){
                 tekst += tab[i].toString() + "\n"
             }
             else {
                 tekst += tab[i].toString() + ", "
             }
         }
         tekst += "Posortowano w czasie: "
         textView.text = tekst
     }
}