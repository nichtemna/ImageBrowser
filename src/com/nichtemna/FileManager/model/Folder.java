package com.nichtemna.FileManager.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shishova Galina
 * nichtemna@gmail.com
 */
public class Folder implements Parcelable {
    private String path;
    private List<String> files = new ArrayList<String>();

    public Folder(String path, List<String> files) {
        this.path = path;
        this.files = files;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.path);
        dest.writeList(this.files);
    }

    private Folder(Parcel in) {
        this.path = in.readString();
        this.files = new ArrayList<String>();
        in.readList(this.files, File.class.getClassLoader());
    }

    public static Parcelable.Creator<Folder> CREATOR = new Parcelable.Creator<Folder>() {
        public Folder createFromParcel(Parcel source) {
            return new Folder(source);
        }

        public Folder[] newArray(int size) {
            return new Folder[size];
        }
    };
}
