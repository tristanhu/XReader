package org.aftx.holers.android.xreader.service.binder;

import java.sql.SQLException;
import java.util.List;

import org.aftx.holers.android.xreader.db.DbHelper;
import org.aftx.holers.android.xreader.db.model.Book;
import org.aftx.holers.android.xreader.db.model.Collection;
import org.aftx.holers.android.xreader.db.model.History;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import android.os.Binder;

@Singleton
public class DbBinder extends Binder {
    @Inject
    @Named("ProvideDbHelper")
    private DbHelper helper;

    public void AddBook(Book book) {
        try {
            helper.Books().create(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteBook(int id) {
        try {
            helper.Books().deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteBook(Book book) {
        try {
            helper.Books().delete(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Book GetBook(int id) {
        try {
            return helper.Books().queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Book> GetBooks(int collection) {
        try {
            return helper.Books().queryForEq("Collection", collection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Book> GetAllBooks() {
        try {
            return helper.Books().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void AddCollection(Collection collection) {
        try {
            helper.Collections().create(collection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteCollection(int id) {
        try {
            List<Book> books = helper.Books().queryForEq("Collection", id);
            Collection defColl = helper.Collections().queryForEq("Name", "д╛хо")
                    .get(0);
            for (Book book : books) {
                book.setCollection(defColl.getId());
                helper.Books().update(book);
            }
            helper.Collections().deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteCollection(String name) {
        try {
            DeleteCollection(helper.Collections().queryForEq("Name", name)
                    .get(0).getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Collection> GetAllCollections() {
        try {
            return helper.Collections().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void AddHistory(History history) {
        try {
            for (History tmp : helper.Histories().queryForEq("Book",
                    history.getBook())) {
                helper.Histories().deleteById(tmp.getId());
            }
            helper.Histories().create(history);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<History> GetAllHistories() {
        try {
            return helper.Histories().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}