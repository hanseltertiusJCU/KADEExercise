package com.example.footballclubapi.view

import android.content.Context
import android.view.View
import com.example.footballclubapi.model.Team
import org.jetbrains.anko.AnkoContext

interface TeamsView {
    fun createView(ui : AnkoContext<Context>) : View
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data : List<Team>)
}