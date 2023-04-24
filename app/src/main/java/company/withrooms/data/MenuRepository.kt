package company.withrooms.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

/**
 * Abstracted Repository as promoted by the Architecture Guide.
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */
class MenuRepository(private val menuItemDao: MenuItemDao) {

    // LiveData observer will notify when the data has changed
    // Room executes all the queries on a separate threads
    val allMenuItems: LiveData<List<MenuItem>> = menuItemDao.getMenuItems()

    //Insertion has to be on a non-UI thread - in opposite applicaiton will crash. So we're making this a
    // suspend function so the caller methods know this.

    // For insertion here we're using "suspend" keyword for Coroutines
    // This way the caller methods are aware of using of non-UI threads

    // AsyncTask is also one of alternatives

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(menuItem: MenuItem) {
        menuItemDao.insert(menuItem)
    }
}
