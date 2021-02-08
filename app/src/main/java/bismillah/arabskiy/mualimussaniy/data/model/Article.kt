package bismillah.arabskiy.mualimussaniy.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "book")

class Article (
    @PrimaryKey
    val id: Int?,

    @ColumnInfo(name = "article")
    val article: String?

    )