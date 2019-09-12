package com.example.footballclubapi.presenter

import com.example.footballclubapi.coroutine.CoroutineContextProvider
import com.example.footballclubapi.model.TeamResponse
import com.example.footballclubapi.service.ApiRepository
import com.example.footballclubapi.service.TheSportDBApi
import com.example.footballclubapi.view.TeamsView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamsPresenter(private val view : TeamsView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val context : CoroutineContextProvider = CoroutineContextProvider()){

    fun getTeamList(league : String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getTeams(league)).await(),
                TeamResponse::class.java
            )

            view.showTeamList(data.teams)
            view.hideLoading()

        }
    }
}