package com.chefcat.applestaralbums.model.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.chefcat.applestaralbums.model.data.Album;

import java.util.List;

@Dao
public interface AlbumDao {
    @Query("SELECT * FROM album ORDER BY trackName")
    List<Album> getAll();

    @Query("SELECT * FROM album WHERE viewed = '1' ORDER BY lastViewed DESC")
    List<Album> getAllViewed();

    @Query("SELECT * FROM album WHERE trackId = :id")
    Album getAlbumWithId(long id);

    @Insert
    void insert(Album album);

    @Insert
    void insertAll(List<Album> albums);

    @Delete
    void delete(Album card);

    @Query("DELETE FROM album")
    void deleteAll();

    @Query("UPDATE Album SET viewed = :viewed, lastViewed = :lastViewed  WHERE trackId = :id")
    int updateAlbumViewed(String viewed, String lastViewed, long id);
}
