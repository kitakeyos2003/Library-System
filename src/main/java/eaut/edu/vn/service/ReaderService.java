package eaut.edu.vn.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import eaut.edu.vn.database.DbManager;
import eaut.edu.vn.interfaces.IService;
import eaut.edu.vn.model.Reader;

public class ReaderService implements IService<Reader> {
    public ArrayList<Reader> getAll() {
        ArrayList<Reader> dsdg = new ArrayList<Reader>();
        try {
            String sql = "select * from docgia";
            PreparedStatement pre = DbManager.getInstance().getConnection().prepareStatement(sql);
            ResultSet result = pre.executeQuery();
            while (result.next()) {
                Reader reader = new Reader();
                reader.setId(result.getString(1));
                reader.setName(result.getString(2));
                reader.setPhoneNumber(result.getString(3));
                reader.setAddress(result.getString(4));
                reader.setSex(result.getString(5));
                reader.setBookId(result.getInt(6));
                dsdg.add(reader);
            }
            result.close();
            pre.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dsdg;
    }

    @Override
    public void add(Reader reader) {

    }

    @Override
    public void remove(Reader reader) {

    }

    @Override
    public Reader find(Object obj) {
        return null;
    }

    public static ReaderService getInstance() {
        return ReaderService.SINGLETON.INSTANCE;
    }

    public static final class SINGLETON {

        public static final ReaderService INSTANCE = new ReaderService();

    }

}
