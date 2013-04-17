package org.aftx.holers.android.xreader.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.aftx.holers.android.xreader.db.model.Book;
import org.aftx.holers.android.xreader.db.model.Collection;
import org.aftx.holers.android.xreader.db.model.History;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.inject.Singleton;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

@Singleton
public class DbHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME    = "XReader.db";
    private static final int    DATABASE_VERSION = 1;

    private List<Class<?>>      entities;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        entities = new ArrayList<Class<?>>();
        entities.add(Book.class);
        entities.add(Collection.class);
        entities.add(History.class);
    }

    private void CreateTables(ConnectionSource connectionSource) {
        for (Class<?> tmp : entities) {
            try {
                TableUtils.createTable(connectionSource, tmp);
            } catch (SQLException e) {
                Log.e(DbHelper.class.getName(), "Create Database Failed", e);
                e.printStackTrace();
            }
        }
    }

    private void UpgradeTables(ConnectionSource connectionSource) {
        for (Class<?> tmp : entities) {
            try {
                TableUtils.dropTable(connectionSource, tmp, true);
            } catch (SQLException e) {
                Log.e(DbHelper.class.getName(), "Update Database Failed", e);
                e.printStackTrace();
            }
        }

        CreateTables(connectionSource);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        CreateTables(connectionSource);
        try {
            Collections().create(new Collection("д╛хо"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
            int arg2, int arg3) {
        UpgradeTables(connectionSource);
    }

    @Override
    public void close() {
        super.close();
    }

    public Dao<?, ?> getDao(String name) {
        try {
            return getDao(Class
                    .forName("org.aftx.holers.android.xreader.db.model." + name));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Dao<Book, Integer> Books() {
        return (Dao<Book, Integer>) getDao("Book");
    }

    public Dao<Collection, Integer> Collections() {
        return (Dao<Collection, Integer>) getDao("Collection");
    }
    
    public Dao<History, Integer> Histories() {
        return (Dao<History, Integer>) getDao("History");
    }
}