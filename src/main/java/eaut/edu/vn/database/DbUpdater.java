/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eaut.edu.vn.database;

import eaut.edu.vn.util.Log;
import org.apache.commons.dbutils.QueryRunner;

/**
 *
 * @author kitakeyos - Hoàng Hữu Dũng
 */
public class DbUpdater {
    private final QueryRunner queryRunner;

    public DbUpdater(QueryRunner queryRunner) {
        this.queryRunner = queryRunner;
    }

    public int update(String sql, Object... params) {
        try {
            return this.queryRunner.update(sql, params);
        } catch (Exception e) {
            Log.error("update() EXCEPTION: " + e.getMessage());
            return -1;
        }
    }
}
