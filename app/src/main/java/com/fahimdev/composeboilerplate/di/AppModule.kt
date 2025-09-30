package com.fahimdev.composeboilerplate.di

import com.fahimdev.composeboilerplate.MainActivityViewModel
import com.fahimdev.composeboilerplate.presentation.authentication.AuthenticationViewModel
import com.fahimdev.composeboilerplate.presentation.movie.category.MovieCategoryViewModel
import com.fahimdev.composeboilerplate.presentation.movie.list.MovieListViewModel
import com.fahimdev.composeboilerplate.presentation.settings.SettingsViewModel
import com.fahimdev.core.manager.DataStoreManager
import com.fahimdev.domain.usecase.GetPopularMovieListUseCase
import com.fahimdev.domain.usecase.GetTrendingMovieListUseCase
import com.fahimdev.domain.usecase.GetUpcomingMovieListUseCase
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        DataStoreManager(context = get())
    }

    factory { GetTrendingMovieListUseCase(get()) }
    factory { GetPopularMovieListUseCase(get()) }
    factory { GetUpcomingMovieListUseCase(get()) }

    viewModel<MainActivityViewModel> {
        MainActivityViewModel(get())
    }

    viewModel<AuthenticationViewModel> {
        AuthenticationViewModel(authRepository = get(), dataStoreManager = get())
    }

    viewModel<SettingsViewModel> {
        SettingsViewModel(get())
    }

    viewModel<MovieCategoryViewModel> {
        MovieCategoryViewModel(
            movieRepository = get()
        )
    }

    viewModel<MovieListViewModel> {
        MovieListViewModel(
            getPopularMovieListUseCase = get(),
            getTrendingMovieListUseCase = get(),
            getUpcomingMovieListUseCase = get()
        )
    }
}