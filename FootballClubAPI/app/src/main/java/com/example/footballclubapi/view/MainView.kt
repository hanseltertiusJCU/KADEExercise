package com.example.footballclubapi.view

import com.example.footballclubapi.model.Team

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data : List<Team>)
}