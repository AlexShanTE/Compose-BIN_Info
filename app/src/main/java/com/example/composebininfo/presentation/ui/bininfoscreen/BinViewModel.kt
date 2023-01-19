package com.example.composebininfo.presentation.ui.bininfoscreen

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composebininfo.R
import com.example.composebininfo.di.IoDispatcher
import com.example.composebininfo.domain.BinInfoModel
import com.example.composebininfo.domain.BinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BinViewModel @Inject constructor(
    private val repository: BinRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(BinInfoUiState())
    val state: StateFlow<BinInfoUiState> = _state.asStateFlow()

    var binInfo by mutableStateOf(BinInfoModel())
        private set

    init {
        _state.value = BinInfoUiState(State.Idle)
    }

    fun getInfo(bin: String) {
        viewModelScope.launch {
            withContext(dispatcher) {
                _state.update { state -> state.copy(status = State.Loading) }
                try {
                    binInfo = repository.getInfo(bin)
                    _state.update { state -> state.copy(status = State.Loaded(binInfo)) }
                } catch (error: Throwable) {
                    val message = error.message ?: error.toString()
                    _state.update { state -> state.copy(status = State.Error(message)) }
                }
            }
        }
    }

    fun startActivityWithIntent(context: Context, type: String, data: String) {
        if (data !== "null") {
            var chooser = Intent()
            val title: String
            when (type) {
                GEO -> {
                    val location: Uri = Uri.parse("$GEO:$data?z=14") // z param is zoom level
                    title = context.getString(R.string.location)
                    chooser = Intent.createChooser(Intent(Intent.ACTION_VIEW, location), title)
                }
                URL -> {
                    val link: Uri = Uri.parse("https://$data")
                    title = context.getString(R.string.url)
                    chooser = Intent.createChooser(Intent(Intent.ACTION_VIEW, link), title)

                }
                PHONE -> {
                    val phone: Uri = Uri.parse("tel:$data")
                    title = context.getString(R.string.phone)
                    chooser = Intent.createChooser(Intent(Intent.ACTION_DIAL, phone), title)
                }
            }
            try {
                startActivity(context, chooser, null)
            } catch (e: ActivityNotFoundException) {
                val noOneActivitySupport = context.getString(R.string.no_one_supports)
                Toast.makeText(context,noOneActivitySupport, Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val GEO: String = "geo"
        const val URL: String = "url"
        const val PHONE: String = "phone"
    }
}