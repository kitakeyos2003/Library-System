/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eaut.edu.vn.database;

import eaut.edu.vn.util.Log;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author kitakeyos - Hoàng Hữu Dũng
 */
public class DbExecutor {

    private final QueryRunner queryRunner;

    public DbExecutor(QueryRunner queryRunner) {
        this.queryRunner = queryRunner;
    }

    public <T> List<T> selectResultAsListObj(String sql, Class<T> clazz, Object... params) {
        try {
            ResultSetHandler<List<T>> resultSetHandler = new BeanListHandler(clazz, new BasicRowProcessor(new GenerousBeanProcessor()));
            return this.queryRunner.query(sql, resultSetHandler, params);
        } catch (Exception e) {
            Log.error("selectResultAsListObj() EXCEPTION: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
