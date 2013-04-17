package org.aftx.holers.android.xreader.service.binder;

import java.util.List;

import org.aftx.holers.android.xreader.db.model.Book;
import org.aftx.holers.android.xreader.db.model.Collection;
import org.aftx.holers.android.xreader.db.model.History;

import android.os.Binder;

public class LogBinder extends Binder {
    private DbBinder binder = null;

    public LogBinder(DbBinder binder) {
        this.binder = binder;
    }

    public void AddBook(Book book) {
        binder.AddBook(book);
    }

    public void DeleteBook(int id) {
        binder.DeleteBook(id);
    }

    public void DeleteBook(Book book) {
        binder.DeleteBook(book);
    }

    public Book GetBook(int id) {
        return binder.GetBook(id);
    }

    public List<Book> GetBooks(int collection) {
        return binder.GetBooks(collection);
    }

    public List<Book> GetBooks(Collection collection) {
        return GetBooks(collection.getId());
    }

    public List<Book> GetAllBooks() {
        return binder.GetAllBooks();
    }

    public void AddCollection(Collection collection) {
        binder.AddCollection(collection);
    }

    public void DeleteCollection(int id) {
        binder.DeleteCollection(id);
    }

    public void DeleteCollection(String name) {
        binder.DeleteCollection(name);
    }

    public List<Collection> GetAllCollections() {
        return binder.GetAllCollections();
    }

    public void AddHistory(History history) {
        binder.AddHistory(history);
    }

    public List<History> GetAllHistories() {
        return binder.GetAllHistories();
    }
}