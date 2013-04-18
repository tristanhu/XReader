package org.aftx.holers.android.xreader.db.model;

import com.j256.ormlite.field.*;
import com.j256.ormlite.table.*;

@DatabaseTable(tableName = "Collections")
public class Collection {
    @DatabaseField(columnName = "Id", generatedId = true)
    private int    id;
    @DatabaseField(columnName = "Name", canBeNull = false)
    private String name;

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

    public Collection() {
    }

    public Collection(String name) {
        this.name = name;
    }
}