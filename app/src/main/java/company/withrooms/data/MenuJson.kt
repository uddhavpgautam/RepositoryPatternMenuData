package company.withrooms.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomWarnings
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "menu_json_table")
@SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
data class MenuJson(
        @PrimaryKey
        @SerializedName("id")
        @Expose
        var id: String,
        @SerializedName("date")
        @Expose
        var email: String?,
        @SerializedName("menuData")
        @Expose
        @Embedded
        var menuData: MenuData? = null
)