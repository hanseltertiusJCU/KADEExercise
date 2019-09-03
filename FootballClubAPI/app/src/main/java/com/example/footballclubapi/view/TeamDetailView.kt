package com.example.footballclubapi.view

import com.example.footballclubapi.model.Team

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>)
}