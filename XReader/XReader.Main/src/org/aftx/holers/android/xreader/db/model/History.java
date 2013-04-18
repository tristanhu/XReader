package org.aftx.holers.android.xreader.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Histories")
public class History {
        @DatabaseField(columnName = "Id", generatedId = true)
        private int    id;
        @DatabaseField(columnName = "Book", canBeNull = false)
        private int book;
        @DatabaseField(columnName = "Page", canBeNull = false)
        private int page;

        public History() {
        }

        public History(int book, int page) {
            this.book = book;
            this.page = page;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getBook() {
            return book;
        }

        public void setBook(int book) {
            this.book = book;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }
}
