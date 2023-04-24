package company.withrooms.data

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "menu_data_table")
data class MenuData constructor(
        @PrimaryKey(autoGenerate = true)
        @SerializedName("id")
        @Expose
        var careerId: Int?,

        @SerializedName("version")
        @Expose
        @NonNull
        var version: String,

        @SerializedName("date")
        @Expose
        @NonNull
        var date: String,

        @SerializedName("menuItems")
        @Expose
        @NonNull
        var menuItems: List<MenuItem>,

        @SerializedName("maintainers")
        @Expose
        @NonNull
        var details: Details
)