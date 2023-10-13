/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eaut.edu.vn.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import eaut.edu.vn.util.Log;
import org.apache.commons.dbutils.QueryRunner;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Function;

public class DbManager {

    private static DbManager instance = null;
    private HikariDataSource hikariDataSource;
    private DbExecutor dbExecutor;
    private DbUpdater dbUpdater;
    private DbInserter dbInserter;
    private String host;
    private int port;
    private String dbname;
    private String username;
    private String passeword;
    private String driver;
    private int maxConnections;
    private int minConnections;

    public static DbManager getInstance() {
        if (instance == null) {
            instance = new DbManager();
        }
        return instance;
    }

    private DbManager() {
        try {
            FileInputStream input = new FileInputStream("database.properties");
            Properties props = new Properties();
            props.load(new InputStreamReader(input, StandardCharsets.UTF_8));
            this.driver = props.getProperty("driver");
            this.host = props.getProperty("host");
            this.port = Integer.parseInt(props.getProperty("port"));
            this.dbname = props.getProperty("dbname");
            this.username = props.getProperty("username");
            this.passeword = props.getProperty("password");
            this.maxConnections = Integer.parseInt(props.getProperty("max_connection"));
            this.minConnections = Integer.parseInt(props.getProperty("min_connection"));
        } catch (IOException ex) {
            Log.error("init err", ex);
        }

    }

    public Connection getConnection() throws SQLException {
        return this.hikariDataSource.getConnection();
    }

    public boolean start() {
        if (this.hikariDataSource != null) {
            Log.warn("DB Connection Pool has already been created.");
            return false;
        } else {
            try {
                HikariConfig config = new HikariConfig();
                config.setJdbcUrl("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dbname);
                config.setDriverClassName(this.driver);
                config.setUsername(this.username);
                config.setPassword(this.passeword);
                config.addDataSourceProperty("minimumIdle", this.minConnections);
                config.addDataSourceProperty("maximumPoolSize", this.maxConnections);
                config.addDataSourceProperty("cachePrepStmts", "true");
                config.addDataSourceProperty("prepStmtCacheSize", "250");
                config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

                this.hikariDataSource = new HikariDataSource(config);
                Log.debug("DB Connection Pool has created.");

                QueryRunner queryRunner = new QueryRunner(this.hikariDataSource);
                this.dbExecutor = new DbExecutor(queryRunner);
                this.dbUpdater = new DbUpdater(queryRunner);
                this.dbInserter = new DbInserter(queryRunner);

                return true;
            } catch (Exception e) {
                Log.error("DB Connection Pool Creation has failed.");
                return false;
            }
        }
    }

    public void shutdown() {
        try {
            if (this.hikariDataSource != null) {
                this.hikariDataSource.close();
                Log.debug("DB Connection Pool is shutting down.");
            }
            this.hikariDataSource = null;
        } catch (Exception e) {
            Log.warn("Error when shutting down DB Connection Pool");
        }
    }

    public <T> List<T> selectResultAsListObj(String sql, Class<T> clazz, Object... params) {
        return this.dbExecutor.selectResultAsListObj(sql, clazz, params);
    }

    public void executeQuery(String sqlStatement, Consumer<ResultSet> func, Object... params){
        executeQuery(sqlStatement, rs -> {
            func.accept(rs);
            return null;
        }, params);
    }

    public Object executeQuery(String sqlStatement, Function<ResultSet, Object> func, Object... params) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            int index = 1;
            for (Object param : params) {
                preparedStatement.setObject(index, param);
                ++index;
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            try {
                return func.apply(resultSet);
            } finally {
                preparedStatement.close();
                connection.close();
            }
        } catch (SQLException sQLException) {
            Log.error("executeQuery ex: ", sQLException);
        }
        return null;
    }

    public <T> T insertResultAsObj(String sql, Object... params) {
        return this.dbInserter.insertResultAsObj(sql, params);
    }

    public int update(String sql, Object... params) {
        return this.dbUpdater.update(sql, params);
    }
}
