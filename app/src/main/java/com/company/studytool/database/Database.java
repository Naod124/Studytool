package com.company.studytool.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private final static String table = "`app_db_1`.`user`";

    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    static {
        createTable();
    }

    private static void createTable() {
        try {
            execute(Type.ONEWAY,
                    "CREATE TABLE IF NOT EXISTS `school_tools`.`user` (\n" +
                            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                            "  `name` VARCHAR(100) NOT NULL,\n" +
                            "  `middlename` VARCHAR(100) NOT NULL,\n" +
                            "  `surname` VARCHAR(100) NOT NULL,\n" +
                            "  `birthday` DATE NOT NULL,\n" +
                            "  `phone` VARCHAR(45) NULL,\n" +
                            "  PRIMARY KEY (`id`));" +
                            "CREATE TABLE IF NOT EXISTS `school_tools`.`account` (\n" +
                            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                            "  `email` VARCHAR(100) NOT NULL,\n" +
                            "  `password` VARCHAR(150) NOT NULL,\n" +
                            "  `user_id` INT NOT NULL,\n" +
                            "  PRIMARY KEY (`id`),\n" +
                            "  INDEX `fk_accounts_user_idx` (`user_id` ASC) VISIBLE,\n" +
                            "  CONSTRAINT `fk_accounts_user`\n" +
                            "    FOREIGN KEY (`user_id`)\n" +
                            "    REFERENCES `school_tools`.`user` (`id`)\n" +
                            "    ON DELETE CASCADE\n" +
                            "    ON UPDATE CASCADE);" +
                            "CREATE TABLE IF NOT EXISTS `school_tools`.`description` (\n" +
                            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                            "  `location` VARCHAR(45) NOT NULL,\n" +
                            "  `photo` LONGBLOB NOT NULL,\n" +
                            "  `text` LONGTEXT NOT NULL,\n" +
                            "  PRIMARY KEY (`id`));" +
                            "CREATE TABLE IF NOT EXISTS `school_tools`.`item` (\n" +
                            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                            "  `name` VARCHAR(45) NOT NULL,\n" +
                            "  `quantity` INT NOT NULL,\n" +
                            "  `price` DECIMAL(15,2) NOT NULL DEFAULT 0.00,\n" +
                            "  `description_id` INT NOT NULL,\n" +
                            "  `account_id` INT NOT NULL,\n" +
                            "  PRIMARY KEY (`id`),\n" +
                            "  INDEX `fk_items_descriptions1_idx` (`description_id` ASC) VISIBLE,\n" +
                            "  INDEX `fk_item_account1_idx` (`account_id` ASC) VISIBLE,\n" +
                            "  CONSTRAINT `fk_items_descriptions1`\n" +
                            "    FOREIGN KEY (`description_id`)\n" +
                            "    REFERENCES `school_tools`.`description` (`id`)\n" +
                            "    ON DELETE CASCADE\n" +
                            "    ON UPDATE CASCADE,\n" +
                            "  CONSTRAINT `fk_item_account1`\n" +
                            "    FOREIGN KEY (`account_id`)\n" +
                            "    REFERENCES `school_tools`.`account` (`id`)\n" +
                            "    ON DELETE CASCADE\n" +
                            "    ON UPDATE CASCADE);" +
                            "CREATE TABLE IF NOT EXISTS `school_tools`.`order` (\n" +
                            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                            "  `account_id` INT NOT NULL,\n" +
                            "  `item_id` INT NOT NULL,\n" +
                            "  PRIMARY KEY (`id`),\n" +
                            "  INDEX `fk_account_has_item_item1_idx` (`item_id` ASC) VISIBLE,\n" +
                            "  INDEX `fk_account_has_item_account1_idx` (`account_id` ASC) VISIBLE,\n" +
                            "  CONSTRAINT `fk_account_has_item_account1`\n" +
                            "    FOREIGN KEY (`account_id`)\n" +
                            "    REFERENCES `school_tools`.`account` (`id`)\n" +
                            "    ON DELETE CASCADE\n" +
                            "    ON UPDATE CASCADE,\n" +
                            "  CONSTRAINT `fk_account_has_item_item1`\n" +
                            "    FOREIGN KEY (`item_id`)\n" +
                            "    REFERENCES `school_tools`.`item` (`id`)\n" +
                            "    ON DELETE CASCADE\n" +
                            "    ON UPDATE CASCADE);"
            );
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static Object execute(Type type, String query)
    {
        return execute(type, query, null, null);
    }

    public static Object execute(Type type, String query, Object[] parameters, int commonType){//java.sql.Types
        int[] types = new int[parameters.length];
        Arrays.fill(types, commonType);

        return execute(type, query, parameters, types);
    }

    public static Object execute(Type type, String query, Object[] parameters, int[] types)//java.sql.Types
    {
        if(parameters != null && parameters.length != types.length)
            return null;
        Object result = null;
        try(Connection connection = getConnection();
            PreparedStatement command = connection.prepareStatement(query)
        )
        {
            if(parameters != null)
                for(int i = 0; i < parameters.length; i++)
                    command.setObject(i + 1, parameters[i], types[i]);

            if(type == Type.READER){
                ResultSet set = command.executeQuery();
                ResultSetMetaData metaData = set.getMetaData();
                Map<String, ArrayList<Object>> results = new HashMap<>();
                int count = metaData.getColumnCount();
                for(int i = 1; i < count + 1; i++)
                    results.put(metaData.getColumnName(i), new ArrayList<>());
                while (set.next())
                    for(int i = 1; i < count + 1; i++)
                        results.get(metaData.getColumnName(i)).add(getObject(set, i, metaData.getColumnType(i)));

                result = results;
            }
            else if(type == Type.UPDATE){
                result = command.executeUpdate();
            }
            else
                command.execute();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return result;
    }

    private static Object getObject(ResultSet set, int col, int type) throws SQLException {
        switch (type){
            case -7 :
            case -6 :
            case 5 :
            case 4 :
                return set.getInt(col);
            case -1 :
            case 1 :
            case 12 :
                return set.getString(col);
            case 91 :
                return set.getDate(col);
            case 2004 :
            case -2 :
            case -3 :
            case -4 :
                return set.getBlob(col);
            case 0:
                return null;
            default:
                return set.getObject(col);
        }
    }

    private static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/?user=javafx-app-1/app_db_1?&allowMultiQueries=true&serverTimezone=Europe/Stockholm&autoReconnect=true&useSSL=true"
                    , "javafx-app-1",
                    "123qwerty123"
            );
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return conn;
    }

    public enum Type {
        UPDATE,
        READER,
        ONEWAY
    }
}
