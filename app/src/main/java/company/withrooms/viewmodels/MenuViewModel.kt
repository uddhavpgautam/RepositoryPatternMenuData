package company.withrooms.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import company.withrooms.data.MenuDatabase
import company.withrooms.data.MenuItem
import company.withrooms.data.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    @ApplicationContext application: Context,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val repository: MenuRepository
    val allMenuItems: LiveData<List<MenuItem>>

    init {
        val wordsDao = MenuDatabase.getDatabase(application, viewModelScope).menuItemDao()
        repository = MenuRepository(wordsDao)
        allMenuItems = repository.allMenuItems
    }
}



