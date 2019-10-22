package com.devika.smf_fibtest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devika.smf_fibtest.databinding.FibonacciNumListBinding
import java.math.BigInteger

class FibonacciNumListAdapter(var fibonacciNum: MutableList<BigInteger>) : RecyclerView.Adapter<FibonacciNumListAdapter.FibonacciNumViewHolder>() {

    fun updateData(fibonacciNum: MutableList<BigInteger>) {
        this.fibonacciNum = fibonacciNum
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FibonacciNumViewHolder {
        val container = LayoutInflater.from(parent.context)
        val binding = FibonacciNumListBinding.inflate(container)
        return FibonacciNumViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return fibonacciNum.size
    }

    override fun onBindViewHolder(holder: FibonacciNumViewHolder, position: Int) {
        holder.setData(fibonacciNum[position])
    }


    inner class FibonacciNumViewHolder(private val binding: FibonacciNumListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(fibNum: BigInteger) {
            binding.numItem.text = "$fibNum"
        }
    }
}