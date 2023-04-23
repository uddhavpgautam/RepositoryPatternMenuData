package company.withrooms.data

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import company.withrooms.data.Developer


@Dao
interface DeveloperDao {

    @Query("SELECT * from developer_table")
    fun getDevelopers(): LiveData<List<Developer>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(developer: Developer)

    @Query("DELETE FROM developer_table")
    fun deleteAll()
}
