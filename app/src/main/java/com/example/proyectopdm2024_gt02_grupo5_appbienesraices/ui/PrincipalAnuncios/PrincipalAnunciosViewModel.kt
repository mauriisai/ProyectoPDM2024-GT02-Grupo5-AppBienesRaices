package com.example.proyectopdm2024_gt02_grupo5_appbienesraices.ui.PrincipalAnuncios

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PrincipalAnunciosViewModel: ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
}