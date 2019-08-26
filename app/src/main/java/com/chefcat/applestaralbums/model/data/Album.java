package com.chefcat.applestaralbums.model.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Album implements Serializable, Comparable<Album> {

    @PrimaryKey
    public Long trackId;

    @ColumnInfo(name = "trackName")
    public String trackName;

    @ColumnInfo(name = "trackPrice")
    public String trackPrice;

    @ColumnInfo(name = "trackHdPrice")
    public String trackHdPrice;

    @ColumnInfo(name = "trackHdRentalPrice")
    public String trackHdRentalPrice;

    @ColumnInfo(name = "artworkUrl30")
    public String artworkUrl30;

    @ColumnInfo(name = "artworkUrl60")
    public String artworkUrl60;

    @ColumnInfo(name = "artworkUrl100")
    public String artworkUrl100;

    @ColumnInfo(name = "primaryGenreName")
    public String primaryGenreName;

    @ColumnInfo(name = "collectionId")
    public String collectionId;

    @ColumnInfo(name = "collectionName")
    public String collectionName;

    @ColumnInfo(name = "collectionPrice")
    public String collectionPrice;

    @ColumnInfo(name = "longDescription")
    public String longDescription;

    @ColumnInfo(name = "viewed")
    public String viewed;

    @ColumnInfo(name = "lastViewed")
    public String lastViewed;

    // Other Details
    @ColumnInfo(name = "image")
    public String wrapperType;

    @ColumnInfo(name = "kind")
    public String kind;

    @ColumnInfo(name = "artistName")
    public String artistName;

    @ColumnInfo(name = "collectionCensoredName")
    public String collectionCensoredName;

    @ColumnInfo(name = "trackCensoredName")
    public String trackCensoredName;

    @ColumnInfo(name = "collectionArtistId")
    public String collectionArtistId;

    @ColumnInfo(name = "collectionArtistViewUrl")
    public String collectionArtistViewUrl;

    @ColumnInfo(name = "collectionViewUrl")
    public String collectionViewUrl;

    @ColumnInfo(name = "trackViewUrl")
    public String trackViewUrl;

    @ColumnInfo(name = "previewUrl")
    public String previewUrl;

    @ColumnInfo(name = "trackRentalPrice")
    public String trackRentalPrice;

    @ColumnInfo(name = "collectionHdPrice")
    public String collectionHdPrice;

    @ColumnInfo(name = "releaseDate")
    public String releaseDate;

    @ColumnInfo(name = "collectionExplicitness")
    public String collectionExplicitness;

    @ColumnInfo(name = "trackExplicitness")
    public String trackExplicitness;

    @ColumnInfo(name = "discCount")
    public String discCount;

    @ColumnInfo(name = "discNumber")
    public String discNumber;

    @ColumnInfo(name = "trackCount")
    public String trackCount;

    @ColumnInfo(name = "trackNumber")
    public String trackNumber;

    @ColumnInfo(name = "trackTimeMillis")
    public String trackTimeMillis;

    @ColumnInfo(name = "country")
    public String country;

    @ColumnInfo(name = "currency")
    public String currency;

    @ColumnInfo(name = "contentAdvisoryRating")
    public String contentAdvisoryRating;

    @ColumnInfo(name = "shortDescription")
    public String shortDescription;

    @ColumnInfo(name = "hasITunesExtras")
    public String hasITunesExtras;

    public String getLargeImage() {
        return artworkUrl100.replace("100x100bb.jpg", "250x250bb.jpg");
    }

    @Override
    public int compareTo(@NonNull Album album) {
        return getTrackName().compareTo(album.getTrackName());
    }

    public static Album headerDateAlbum(String date){
        Album album = new Album();
        album.setReleaseDate(date);

        return album;
    }
}
