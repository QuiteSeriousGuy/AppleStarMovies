package com.chefcat.applestaralbums.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.chefcat.applestaralbums.model.data.Album;
import com.chefcat.applestaralbums.model.db.dao.AlbumDao;
import com.chefcat.applestaralbums.util.Constant;

@Database(entities = { Album.class }, version = 1)
public abstract class AppDBService extends RoomDatabase {
    public abstract AlbumDao albumDao();

    private static AppDBService albumDB;

    public static AppDBService getInstance(Context context) {
        if (null == albumDB) {
            albumDB = buildDatabaseInstance(context);
        }
        return albumDB;
    }

    private static AppDBService buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                AppDBService.class,
                Constant.DB_NAME)
                .allowMainThreadQueries().build();
    }

    public void cleanUp(){
        albumDB = null;
    }
}
