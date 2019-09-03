package com.example.footballclubapi.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.footballclubapi.R
import com.example.footballclubapi.model.Team
import com.example.footballclubapi.presenter.TeamDetailPresenter
import com.example.footballclubapi.service.ApiRepository
import com.example.footballclubapi.utils.invisible
import com.example.footballclubapi.utils.visible
import com.example.footballclubapi.view.TeamDetailView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {

    private lateinit var progressBar : ProgressBar
    private lateinit var swipeRefresh : SwipeRefreshLayout

    private lateinit var teamBadge : ImageView
    private lateinit var teamName : TextView
    private lateinit var teamFormedYear : TextView
    private lateinit var teamStadium : TextView
    private lateinit var teamDescription : TextView

    private lateinit var presenter : TeamDetailPresenter
    private lateinit var teams : Team
    private lateinit var id : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            backgroundColor = Color.WHITE

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)

                scrollView {
                    isVerticalScrollBarEnabled = false
                    relativeLayout {
                        lparams(width = matchParent, height = wrapContent)

                        relativeLayout {
                            lparams(width = matchParent, height = wrapContent)

                            linearLayout {
                                lparams(width = matchParent, height = wrapContent)
                                padding = dip(10)
                                orientation = LinearLayout.VERTICAL
                                gravity = Gravity.CENTER_HORIZONTAL

                                teamBadge = imageView().lparams(height = dip(75))

                                teamName = textView {
                                    this.gravity = Gravity.CENTER
                                    textSize = 20f
                                    textColor = ContextCompat.getColor(context, R.color.colorAccent)
                                }.lparams{
                                    topMargin = dip(5)
                                }

                                teamFormedYear = textView {
                                    this.gravity = Gravity.CENTER
                                }

                                teamStadium = textView{
                                    this.gravity = Gravity.CENTER
                                    textColor = ContextCompat.getColor(context, R.color.colorPrimaryText)
                                }

                                teamDescription = textView().lparams{
                                    topMargin = dip(20)
                                }
                            }

                            progressBar = progressBar{
                            }.lparams{
                                centerHorizontally()
                            }

                        }
                    }
                }
            }
        }

        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        id = intent.getStringExtra("id")
        val request =  ApiRepository()
        val gson = Gson()
        presenter = TeamDetailPresenter(this, request, gson)
        presenter.getTeamDetail(id)
        swipeRefresh.onRefresh {
            presenter.getTeamDetail(id)
        }

    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamDetail(data: List<Team>) {
        teams = Team(data.first().teamId, data.first().teamName, data.first().teamBadge)
        swipeRefresh.isRefreshing = false
        Picasso.get().load(data.first().teamBadge).into(teamBadge)
        teamName.text = data.first().teamName
        teamDescription.text = data.first().teamDescription
        teamFormedYear.text = data.first().teamFormedYear
        teamStadium.text = data.first().teamStadium
    }
}
