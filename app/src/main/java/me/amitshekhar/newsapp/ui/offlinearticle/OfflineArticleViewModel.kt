package me.amitshekhar.newsapp.ui.offlinearticle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import me.amitshekhar.newsapp.data.local.entity.Article
import me.amitshekhar.newsapp.data.repository.OfflineArticleRepository
import me.amitshekhar.newsapp.ui.base.UiState
import me.amitshekhar.newsapp.utils.AppConstant.COUNTRY
import me.amitshekhar.newsapp.utils.NetworkHelper
import javax.inject.Inject

@HiltViewModel
class OfflineArticleViewModel @Inject constructor(
    networkHelper: NetworkHelper,
    private val offlineArticleRepository: OfflineArticleRepository
) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    init {
        if (networkHelper.isNetworkConnected()) {
            fetchArticles()
        } else {
            fetchArticlesDirectlyFromDB()
        }
    }

    private fun fetchArticles() {
        viewModelScope.launch {
            offlineArticleRepository.getArticles(COUNTRY)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

    private fun fetchArticlesDirectlyFromDB() {
        viewModelScope.launch {
            offlineArticleRepository.getArticlesDirectlyFromDB()
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

}