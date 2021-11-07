package com.mycompany.personaltechweb;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import static java.sql.DriverManager.getConnection;
import java.sql.SQLException;
import org.dbunit.database.DatabaseConfig;
import static org.dbunit.database.DatabaseConfig.PROPERTY_DATATYPE_FACTORY;
import static org.dbunit.database.DatabaseConfig.PROPERTY_METADATA_HANDLER;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.dbunit.operation.DatabaseOperation;
import static org.dbunit.operation.DatabaseOperation.CLEAN_INSERT;

public class DbUnitUtil {
    private static final String XML_FILE = "/dbunit/dataset.xml";
    
    @SuppressWarnings("UseSpecificCatch")
    public static void inserirDados() {
        Connection conn = null;
        IDatabaseConnection db_conn = null;
        try {
            conn = getConnection(
                    "jdbc:mysql://localhost:3306/PERSONALTECH", "root", "root");
            db_conn = new DatabaseConnection(conn, "PERSONALTECH");
            DatabaseConfig dbConfig = db_conn.getConfig();
            dbConfig.setProperty(PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
            dbConfig.setProperty(PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());
            FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
            builder.setColumnSensing(true);
            InputStream in = DbUnitUtil.class.getResourceAsStream(XML_FILE);
            IDataSet dataSet = builder.build(in);
            CLEAN_INSERT.execute(db_conn, dataSet);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                
                if (db_conn != null) {
                    db_conn.close();
               }
            } catch (SQLException ex) {
                throw new RuntimeException(ex.getMessage(), ex);
            }
        }
    }
}
