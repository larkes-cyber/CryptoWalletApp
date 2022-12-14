package com.example.cryptowalletapp.presentation.adapters.top_coins_adapter
import android.system.Os.bind
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptowalletapp.R
import com.example.cryptowalletapp.data.retrofit.model.CoinTop
import com.example.cryptowalletapp.databinding.ActivityMainBinding.bind
import com.example.cryptowalletapp.databinding.FragmentSubBinding.bind
import com.example.cryptowalletapp.domain.model.CoinInfo
import com.example.cryptowalletapp.presentation.fragment.TopCoinFragment
import com.squareup.picasso.Picasso
import java.lang.Double

class TopCoinsAdapter : RecyclerView.Adapter<TopCoinsAdapter.TasksHolder>() {

    var coinList:MutableList<CoinInfo> = ArrayList()

    class TasksHolder(val item: View):RecyclerView.ViewHolder(item){

        fun roundForPerc(number: kotlin.Double): String? {
            return "%.2f".format(number)
        }
        fun findPerc(num1:Int, num2:Int):String{
            val perc = (100 * num2).toDouble() / num1
            return roundForPerc(Double.max(100.0, perc) - 100.0.coerceAtMost(perc))!!
        }

        fun bind(data:CoinInfo){

            val name = item.findViewById<TextView>(R.id.curr_name)
            val img = item.findViewById<ImageView>(R.id.image)
            val perc = item.findViewById<TextView>(R.id.curr_perc)
            val price = item.findViewById<TextView>(R.id.price)

            name.text = data.name
            Picasso.get().load(data.src).into(img)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_top_coin, parent, false)
        return TasksHolder(view)
    }

    override fun onBindViewHolder(holder: TasksHolder, position: Int) {
        holder.bind(coinList[position])
    }

    override fun getItemCount(): Int {
        return coinList.size
    }

    fun addTasks(tasks:MutableList<CoinInfo>){
        coinList = tasks
    }

}