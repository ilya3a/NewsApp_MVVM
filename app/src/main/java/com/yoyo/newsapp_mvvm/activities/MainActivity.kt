package com.yoyo.newsapp_mvvm.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yoyo.newsapp_mvvm.R
import com.yoyo.newsapp_mvvm.fragments.FragmentLowerList

class MainActivity : AppCompatActivity() {


    private val fragmentManager = supportFragmentManager
    private val fragmentLowerList: FragmentLowerList= FragmentLowerList()
    private val NEWS_FRAGMENT_TAG = "news_fragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        fragmentManager.beginTransaction().add(R.id.lowerFragmentHolder,fragmentLowerList,NEWS_FRAGMENT_TAG).commit()

/*
val forecastList = ArrayList<CardModel>()
forecastList.add(CardModel("Sunday","10/12/20","","Rain","10C","100mph","80%","10%"))
forecastList.add(CardModel("Sunday","10/12/20","","Rain","10C","100mph","80%","10%"))
forecastList.add(CardModel("Sunday","10/12/20","","Rain","10C","100mph","80%","10%"))
forecastList.add(CardModel("Sunday","10/12/20","","Rain","10C","100mph","80%","10%"))
forecastList.add(CardModel("Sunday","10/12/20","","Rain","10C","100mph","80%","10%"))
forecastList.add(CardModel("Sunday","10/12/20","","Rain","10C","100mph","80%","10%"))
forecastList.add(CardModel("Sunday","10/12/20","","Rain","10C","100mph","80%","10%"))
forecastList.add(CardModel("Sunday","10/12/20","","Rain","10C","100mph","80%","10%"))
forecastList.add(CardModel("Sunday","10/12/20","","Rain","10C","100mph","80%","10%"))
*/
    }
}
