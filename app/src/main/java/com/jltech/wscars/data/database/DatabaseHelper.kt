import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.jltech.wscars.utils.Constants


class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION) {

    companion object {

        private const val CREATE_TABLE_ORDER =
            "CREATE TABLE ${Constants.TABLE_ORDER} (" +
                    "${Constants.ORDER_ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${Constants.ORDER_CREATED_AT} DATE, " +
                    "${Constants.CLIENT_ID_REF} INTEGER, " +
                    "FOREIGN KEY(${Constants.CLIENT_ID_REF}) REFERENCES ${Constants.TABLE_CLIENT}(${Constants.CLIENT_ID}))"

        private const val CREATE_TABLE_CLIENT =
            "CREATE TABLE ${Constants.TABLE_CLIENT} (" +
                    "${Constants.CLIENT_ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${Constants.CLIENT_NAME} TEXT, " +
                    "${Constants.CLIENT_EMAIL} TEXT, " +
                    "${Constants.CLIENT_PHONE} TEXT, " +
                    "${Constants.CLIENT_BIRTHDAY} TEXT)"

        private const val CREATE_TABLE_CAR =
            "CREATE TABLE ${Constants.TABLE_CAR} (" +
                    "${Constants.CAR_ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${Constants.CAR_ID_MODEL} INTEGER, " +
                    "${Constants.CAR_YEAR} INTEGER, " +
                    "${Constants.CAR_FUEL_TYPE} TEXT, " +
                    "${Constants.CAR_NUM_DOORS} INTEGER, " +
                    "${Constants.CAR_COLOR} TEXT, " +
                    "${Constants.CAR_NAME_MODEL} TEXT, " +
                    "${Constants.CAR_PRICE} REAL, " +
                    "${Constants.CLIENT_ID_REF} INTEGER, " +
                    "FOREIGN KEY(${Constants.CLIENT_ID_REF}) REFERENCES ${Constants.TABLE_CLIENT}(${Constants.CLIENT_ID}))"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_CLIENT)
        db?.execSQL(CREATE_TABLE_CAR)
        db?.execSQL(CREATE_TABLE_ORDER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${Constants.TABLE_ORDER}")
        db?.execSQL("DROP TABLE IF EXISTS ${Constants.TABLE_CLIENT}")
        db?.execSQL("DROP TABLE IF EXISTS ${Constants.TABLE_CAR}")
        onCreate(db)
    }
}

