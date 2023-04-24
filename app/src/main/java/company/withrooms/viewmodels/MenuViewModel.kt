package company.withrooms.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import company.withrooms.data.MenuItem
import company.withrooms.data.MenuRepository
import company.withrooms.data.MenuDatabase

class MenuViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MenuRepository
    val allDevelopers: LiveData<List<MenuItem>>

    init {
        val wordsDao = MenuDatabase.getDatabase(application, viewModelScope).menuItemDao()
        repository = MenuRepository(wordsDao)
        allDevelopers = repository.allDevelopers
    }
}
