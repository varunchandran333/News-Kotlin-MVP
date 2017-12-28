/*
 * Copyright (C) 2017 Rohit Sahebrao Surwase.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rohitss.news.homeMVP

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.rohitss.news.R
import kotlinx.android.synthetic.main.activity_news_home.*

/**
 * Created by RohitSS on 27-12-2017.
 */
class NewsHomeViewActivity : AppCompatActivity(), NewsHomeView {

    private val newsHomePresenter: NewsHomePresenter = NewsHomePresenterImpl(this, NewsHomeInteracterImpl())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_home)
        progressBar.visibility = View.GONE
        recyclerView.setHasFixedSize(true)
        requestNewsUpdates()
        fab.setOnClickListener { requestNewsUpdates() }
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun noNetworkError() {
        Toast.makeText(this, "Network Not Available", Toast.LENGTH_SHORT).show()
    }

    override fun requestNewsUpdates() {
        newsHomePresenter.setNewsUpdates()
    }

    override fun receivedNewsUpdates() {
        recyclerView.adapter = NewsRecyclerViewAdapter(newsHomePresenter.setNewsUpdates())
    }

    override fun failedNewsUpdates() {
        Toast.makeText(this, "Failed To Get News Updates", Toast.LENGTH_SHORT).show()
    }
}
