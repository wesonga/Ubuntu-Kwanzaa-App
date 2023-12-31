package com.next.goldentime.ui.screens.home.article.diseaseDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.next.goldentime.usecase.article.ArticleUseCase
import com.next.goldentime.util.generateArticleUseCase

class DiseaseDetailViewModel(
    private val diseaseId: Int,
    private val articleUseCase: ArticleUseCase = generateArticleUseCase()
) : ViewModel() {
    private val _disease = articleUseCase.getDisease(diseaseId)

    val disease = _disease.asLiveData()
}

class DiseaseDetailViewModelFactory(
    private val diseaseId: Int
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DiseaseDetailViewModel::class.java)) {
            return DiseaseDetailViewModel(diseaseId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}