package company.withrooms.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import company.withrooms.data.Developer
import company.withrooms.data.DeveloperRepository
import company.withrooms.data.DeveloperRoomDatabase

class DeveloperViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: DeveloperRepository
    val allDevelopers: LiveData<List<Developer>>

    init {
        val wordsDao = DeveloperRoomDatabase.getDatabase(application, viewModelScope).developerDao()
        repository = DeveloperRepository(wordsDao)
        allDevelopers = repository.allDevelopers
    }
}
