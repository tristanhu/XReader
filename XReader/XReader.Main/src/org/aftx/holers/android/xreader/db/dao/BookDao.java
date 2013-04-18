package org.aftx.holers.android.xreader.db.dao;

import java.sql.SQLException;
import java.util.List;

import org.aftx.holers.android.xreader.db.model.Book;

public class BookDao extends BaseDao {
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
}
