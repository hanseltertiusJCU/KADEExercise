package com.example.footballclubapi.presenter

import com.example.footballclubapi.coroutine.TestContextProvider
import com.example.footballclubapi.model.Team
import com.example.footballclubapi.model.TeamResponse
import com.example.footballclubapi.service.ApiRepository
import com.example.footballclubapi.view.TeamsView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamsPresenterTest {

    @Mock
    private lateinit var view : TeamsView

    @Mock
    private lateinit var gson : Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse : Deferred<String>

    private lateinit var presenter: TeamsPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = TeamsPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetTeamList() {
        val teams : MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val league = "English Premier League"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString())).thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(gson.fromJson("", TeamResponse::class.java)).thenReturn(response)

            presenter.getTeamList(league)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamList(teams)
            Mockito.verify(view).hideLoading()
        }
    }
}