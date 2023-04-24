package company.withrooms.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import company.withrooms.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream

@Database(
    entities = [MenuJson::class, MenuData::class, Details::class, MenuItem::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MenuDatabase : RoomDatabase() {
    abstract fun menuItemDao(): MenuItemDao

    companion object {
        @Volatile
        private var INSTANCE: MenuDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): MenuDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE
                ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                            context.applicationContext,
                            MenuDatabase::class.java,
                            "menu_json_database"
                        ).fallbackToDestructiveMigration()
                        .addCallback(
                            MenuDbCallback(context, scope)
                        )
                        .build()
                    INSTANCE = instance
                    instance
                }
        }

        private class MenuDbCallback(
            private val context: Context,
            private val scope: CoroutineScope
        ) : Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        val jsonObj = JsonParser().parse(
                            readJSONFromAsset(
                                context
                            )
                        ).asJsonObject

                        val menuJsonType = object : TypeToken<MenuJson>() {}.type
                        val menuJson: MenuJson = Gson().fromJson(jsonObj, menuJsonType)

                        populateDatabase(
                            database,
                            menuJson
                        )
                    }
                }
            }
        }

        // Populate the database from dynamicMenu.json file - needs to be in a new coroutine
        // If needed, you can parse the fields you want from the database and use them
        // This example shows a list by the Developer object/class

        fun populateDatabase(database: MenuDatabase, menuJson: MenuJson) {
            val menuItemDao = database.menuItemDao()

            // Empty database on first load
            menuItemDao.deleteAll()

            val menuItemList = menuJson.menuData?.menuItems
            menuItemList?.forEach {
                menuItemDao.insert(MenuItem(it.name, it.onclick))
            }
        }

        fun readJSONFromAsset(context: Context): String {
            val json: String
            try {
                val inputStream: InputStream = context.assets.open(
                    context.getString(
                        R.string.companyRes
                    )
                )
                json = inputStream.bufferedReader().use {
                    it.readText()
                }
            } catch (ex: Exception) {
                ex.localizedMessage
                return ""
            }
            return json
        }
    }
}

