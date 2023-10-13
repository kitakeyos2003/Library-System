/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eaut.edu.vn.database;

import eaut.edu.vn.util.Log;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/**
 *
 * @author kitakeyos - Hoàng Hữu Dũng
 */
public class DbInserter {

    private final QueryRunner queryRunner;

    public DbInserter(QueryRunner queryRunner) {
        this.queryRunner = queryRunner;
    }

    public <T> T insertResultAsObj(String sql, Object... params) {
        try {
            return this.queryRunner.insert(sql, new ScalarHandler<T>(), params);
        } catch (Exception e) {
            Log.error("insertResultAsObj() EXCEPTION: " + e.getMessage());
            return null;
        }
    }
}
