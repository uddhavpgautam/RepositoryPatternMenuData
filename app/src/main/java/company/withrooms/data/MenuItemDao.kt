package company.withrooms.data

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


@Dao
interface MenuItemDao {

    @Query("SELECT * from menu_item_table")
    fun getDevelopers(): LiveData<List<MenuItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(menuItem: MenuItem)

    @Query("DELETE FROM menu_item_table")
    fun deleteAll()
}
