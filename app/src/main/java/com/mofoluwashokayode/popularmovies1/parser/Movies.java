package com.mofoluwashokayode.popularmovies1.parser;


import android.os.Parcel;
import android.os.Parcelable;


public class Movies implements Parcelable {

    public static final Parcelable.Creator<Movies> CREATOR = new Parcelable.Creator<Movies>() {

        @Override
        public Movies createFromParcel(Parcel parcel) {
            return new Movies(parcel);
        }

        @Override
        public Movies[] newArray(int i) {
            return new Movies[i];
        }
    };
    private final String originalTitle;
    private final String posterPath;
    private final String plotSynopsis;
    private final String userRating;
    private final String releaseDate;

    public Movies(String originalTitle, String posterPath, String plotSynopsis, String userRating, String releaseDate) {
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
        this.plotSynopsis = plotSynopsis;
        this.userRating = userRating;
        this.releaseDate = releaseDate;
    }

    private Movies(Parcel parcel) {
        originalTitle = parcel.readString();
        posterPath = parcel.readString();
        plotSynopsis = parcel.readString();
        userRating = parcel.readString();
        releaseDate = parcel.readString();
    }

    public String getTitle() {
        return originalTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

    public String getVoteAverage() {
        return userRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(originalTitle);
        parcel.writeString(posterPath);
        parcel.writeString(plotSynopsis);
        parcel.writeString(userRating);
        parcel.writeString(releaseDate);
    }
}
