package org.aftx.holers.android.xreader.db.model;

import com.j256.ormlite.field.*;
import com.j256.ormlite.table.*;

@DatabaseTable(tableName = "Books")
public class Book {
    @DatabaseField(columnName = "Id", generatedId = true)
    private int    id;
    @DatabaseField(columnName = "Name", canBeNull = false)
    private String name;
    @DatabaseField(columnName = "Path", canBeNull = false)
    private String path;
    @DatabaseField(columnName = "Collection")
    private int    collection;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }

    public Book() {
    }

    public Book(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public Book(String name, String path, int collection) {
        this.name = name;
        this.path = path;
        this.collection = collection;
    }
}