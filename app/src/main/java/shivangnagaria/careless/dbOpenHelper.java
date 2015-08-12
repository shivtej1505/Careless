package shivangnagaria.careless;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// DB dedicated class

public class dbOpenHelper extends SQLiteOpenHelper {


    // db details
    final static String TABLE_NAME = "carelessDB";
    final static String COLUMN_ID = "docId";
    final static String COLUMN_TYPE = "docType";
    final static String COLUMN_MAMOUNT = "mAmount";
    final static String COLUMN_MDATE = "mDate";
    final static String COLUMN_PAMOUNT = "pAmount";
    final static String COLUMN_PDATE = "pDate";
    final static String COLUMN_SPCF = "specifications";

    // db specifics;
    final static Integer VERSION = 1;
    final static String dbName = "carelesslyHeldDB";

    // TODO: create table if not exists

    final private static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
            " ( " + COLUMN_ID + " INTEGER NOT NULL " + ", " +
            COLUMN_TYPE + " TEXT NOT NULL " + ", " +
            COLUMN_MAMOUNT + " TEXT NOT NULL " + ", " +
            COLUMN_MDATE + " DATE NOT NULL " + ", " +
            COLUMN_PAMOUNT + " TEXT " + ", " +
            COLUMN_PDATE + " DATE " + ", " +
            COLUMN_SPCF + " TEXT NOT NULL " + ");" ;

    final private Context mContext;

    public static String[] columns = {COLUMN_ID,COLUMN_TYPE,COLUMN_MAMOUNT,COLUMN_MDATE,
            COLUMN_PAMOUNT,COLUMN_PDATE,COLUMN_SPCF};

    public dbOpenHelper(Context context) {
        super(context, dbName,null,VERSION);
        this.mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO: drop table and create table
    }
}
