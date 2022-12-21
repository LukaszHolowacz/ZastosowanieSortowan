package com.example.zastosowaniesortowan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Stopwatch
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
    }
    fun heapify(tab: MutableList<Int>, size: Int, i: Int){
        var largest: Int
        val temp: Int
        val l = 2*i
        val r = (2*i)+1
        if (l<=size && tab[l]>tab[i]) {
            largest = l
        }
        else {
            largest = i
        }
        if (r<=size && tab[r]>tab[largest]) {
            largest = r
        }
        if(largest!=i){
            temp=tab[largest]
            tab[largest]=tab[i]
            tab[i]=temp
            heapify(tab,size,largest)
        }
    }
    fun nie_wiem_jak_nazwac(tab: MutableList<Int>, size: Int){
        for(i in size/2 downTo 1 ){
            heapify(tab,size,i)
        }
    }
    fun sortowanie_heapsort(tab: MutableList<Int>, _size: Int){
        var size = _size
        var temp: Int
        nie_wiem_jak_nazwac(tab, size)
        for (i in size downTo 2){
            temp=tab[i]
            tab[i] = tab[1]
            tab[i] = temp
            size -= 1
            heapify(tab,size,1)
        }
    }
    fun wypisywanie(textView: TextView, tab: MutableList<Int>) {
        var tekst = ""
        val sw: Stopwatch = Stopwatch.createStarted()
        sortowanie_heapsort(tab, tab.size-1)
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