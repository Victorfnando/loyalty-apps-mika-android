/**
 * Copyright (C) 2020 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dre.loyalty.features.movies

import android.os.Bundle
import android.view.View
import com.dre.loyalty.R
import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.networking.exception.Failure.NetworkConnection
import com.dre.loyalty.core.networking.exception.Failure.ServerError
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.core.platform.extension.*
import com.dre.loyalty.core.platform.extension.failure
import com.dre.loyalty.features.movies.MovieFailure.NonExistentMovie
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class MovieDetailsFragment : BaseFragment() {

    companion object {
        private const val PARAM_MOVIE = "param_movie"

        fun forMovie(movie: MovieView?): MovieDetailsFragment {
            val movieDetailsFragment = MovieDetailsFragment()
            movie?.let {
                val arguments = Bundle()
                arguments.putParcelable(PARAM_MOVIE, it)
                movieDetailsFragment.arguments = arguments
            }
            return movieDetailsFragment
        }
    }

    @Inject lateinit var movieDetailsAnimator: MovieDetailsAnimator

    private lateinit var movieDetailsViewModel: MovieDetailsViewModel

//    override fun layoutId() = R.layout.fragment_movie_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        activity?.let { movieDetailsAnimator.postponeEnterTransition(it) }

        movieDetailsViewModel = viewModel(viewModelFactory) {
            observe(movieDetails, ::renderMovieDetails)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (firstTimeCreated(savedInstanceState)) {
            movieDetailsViewModel.loadMovieDetails((arguments?.get(PARAM_MOVIE) as MovieView).id)
        } else {
            movieDetailsAnimator.scaleUpView(moviePlay)
            movieDetailsAnimator.cancelTransition(moviePoster)
            moviePoster.loadFromUrl((arguments!![PARAM_MOVIE] as MovieView).poster)
        }
    }

    override fun onBackPressed() {
        movieDetailsAnimator.fadeInvisible(scrollView, movieDetails)
        if (moviePlay.isVisible())
            movieDetailsAnimator.scaleDownView(moviePlay)
        else
            movieDetailsAnimator.cancelTransition(moviePoster)
    }

    private fun renderMovieDetails(movie: MovieDetailsView?) {
        movie?.let {
            with(movie) {
                activity?.let {
                    moviePoster.loadUrlAndPostponeEnterTransition(poster, it)
                    it.toolbar.title = title
                }
                movieSummary.text = summary
                movieCast.text = cast
                movieDirector.text = director
                movieYear.text = year.toString()
                moviePlay.setOnClickListener { movieDetailsViewModel.playMovie(trailer) }
            }
        }
        movieDetailsAnimator.fadeVisible(scrollView, movieDetails)
        movieDetailsAnimator.scaleUpView(moviePlay)
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is NetworkConnection -> { notify(R.string.failure_network_connection); close() }
            is ServerError -> { notify(R.string.failure_server_error); close() }
            is NonExistentMovie -> { notify(R.string.failure_movie_non_existent); close() }
        }
    }
}
