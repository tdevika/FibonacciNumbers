package com.devika.smf_fibtest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.math.BigInteger

class MainActivity : AppCompatActivity() {
    val numList = mutableListOf<BigInteger>()
    lateinit var fibonacciNumListAdapter: FibonacciNumListAdapter
    private val job = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + job)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()



        maxNumBtn.setOnClickListener {
            if(numList.isNotEmpty()){
                numList.clear()
            }
            val fibNum = maxNum.text.toString()
            var maxNub = try {
                fibNum.toInt()
            } catch (e: NumberFormatException) {
                0
            }
            uiScope.launch {
                withContext(Dispatchers.IO) {
                    Log.d("MainActivity","threadname:"+ Thread.currentThread())
                    getFebonacciNumber(maxNub, BigInteger("1"), BigInteger("0"))
                }
                fibonacciNumListAdapter.updateData(numList)
                Log.d("MainActivity","threadname:"+ Thread.currentThread())
            }

        }
    }

    tailrec suspend fun getFebonacciNumber(n: Int, a: BigInteger, b: BigInteger): BigInteger {
            return if (n == 0)
                b
            else {
                numList.add(a + b)
                getFebonacciNumber(n - 1, a + b, a)
            }
        }

    private fun initRecyclerView() {
        fibonacciNumListAdapter = FibonacciNumListAdapter(mutableListOf())
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        recyclerView.adapter = fibonacciNumListAdapter

    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}

