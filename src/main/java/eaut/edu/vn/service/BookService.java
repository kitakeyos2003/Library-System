package eaut.edu.vn.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import eaut.edu.vn.database.DbManager;
import eaut.edu.vn.interfaces.IService;
import eaut.edu.vn.model.Book;
import eaut.edu.vn.util.Log;


public class BookService implements IService<Book> {

    @Override
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        DbManager.getInstance().executeQuery("select * from sach", result -> {
            try {
                while (result.next()) {
                    Book sh = new Book();
                    sh.setId(result.getString(1));
                    sh.setName(result.getString(2));
                    sh.setAuthorName(result.getString(3));
                    sh.setPublishingCompany(result.getString(4));
                    sh.setCategory(result.getString(5));
                    sh.setQuantity(result.getInt(6));
                    sh.setPrice(result.getDouble(7));
                    books.add(sh);
                }
            } catch (SQLException e) {
                Log.error("getAll book err, ", e);
            }
        });
        return books;
    }


    public List<Book> getAllCategory() {
        List<Book> books = new ArrayList<>();
        DbManager.getInstance().executeQuery("select * from sach", result -> {
            try {
                while (result.next()) {
                    Book sh = new Book();
                    sh.setId(result.getString(1));
                    sh.setName(result.getString(2));
                    sh.setAuthorName(result.getString(3));
                    sh.setPublishingCompany(result.getString(4));
                    sh.setCategory(result.getString(5));
                    sh.setQuantity(result.getInt(6));
                    sh.setPrice(result.getDouble(7));
                    books.add(sh);
                }
            } catch (SQLException e) {
                Log.error("getAll book err, ", e);
            }
        });
        return books;
    }

    @Override
    public void add(Book book) {

    }

    @Override
    public void remove(Book book) {

    }

    @Override
    public Book find(Object obj) {
        return null;
    }

    public List<Book> search(String mapm) {
        List<Book> books = new ArrayList<>();
        DbManager.getInstance().executeQuery("Select * from sach a,ctpm b where a.MaSach=b.MaSach and b.MaPM=?", result -> {
            try {
                while (result.next()) {
                    Book sh = new Book();
                    sh.setId(result.getString(1));
                    sh.setName(result.getString(2));
                    sh.setAuthorName(result.getString(3));
                    sh.setPublishingCompany(result.getString(4));
                    sh.setCategory(result.getString(5));
                    sh.setQuantity(result.getInt(6));
                    sh.setPrice(result.getDouble(7));
                    books.add(sh);
                }
            } catch (SQLException e) {
                Log.error("getAll book err, ", e);
            }
        }, mapm);
        return books;
    }

    public static BookService getInstance() {
        return BookService.SINGLETON.INSTANCE;
    }

    public static final class SINGLETON {

        public static final BookService INSTANCE = new BookService();

    }

}
