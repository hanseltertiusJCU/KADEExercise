package com.example.footballclubapi.presenter

import com.example.footballclubapi.model.TeamResponse
import com.example.footballclubapi.service.ApiRepository
import com.example.footballclubapi.service.TheSportDBApi
import com.example.footballclubapi.view.TeamDetailView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamDetailPresenter(private val view : TeamDetailView,
                          private val apiRepository: ApiRepository,
                          private val gson : Gson){
    fun getTeamDetail(teamId : String){
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamDetail(teamId)).await(),
                TeamResponse::class.java
            )

            view.hideLoading()
            view.showTeamDetail(data.teams)
        }
    }
}