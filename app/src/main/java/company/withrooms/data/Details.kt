package company.withrooms.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "details_table")
data class Details constructor(
        @PrimaryKey
        @ColumnInfo(name = "tech")
        @SerializedName("tech")
        @Expose
        var technologies: List<String>,

        @SerializedName("management")
        @Expose
        @ColumnInfo(name = "management")
        var employeeLevels: List<String>
)