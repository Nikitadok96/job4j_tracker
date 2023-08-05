package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {

    private Connection cn;

    public SqlTracker() {
        init();
    }

    public SqlTracker(Connection cn) {
        this.cn = cn;
    }

    private void init() {
        try (InputStream in = SqlTracker.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement statement = cn.prepareStatement(
                "INSERT INTO items(name, created) VALUES (?, ?);",
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            statement.execute();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    item.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean rsl = true;
        try (PreparedStatement statement = cn.prepareStatement(
                "UPDATE items SET id = ? WHERE id = ?;")) {
            statement.setInt(1, id);
            statement.setInt(2, item.getId());
            statement.execute();
            if (statement.getUpdateCount() > 0) {
                rsl = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public boolean delete(int id) {
        boolean rsl = true;
        try (PreparedStatement statement = cn.prepareStatement(
                "DELETE from items WHERE id = ?")) {
            statement.setInt(1, id);
            statement.execute();
            if (statement.getUpdateCount() > 0) {
                rsl = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement = cn.prepareStatement("SELECT * FROM items")) {
            setItemList(items, statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement = cn.prepareStatement(
                "SELECT * FROM items WHERE name = ?")) {
            statement.setString(1, key);
            statement.execute();
            setItemList(items, statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    private void setItemList(List<Item> items, PreparedStatement statement) throws SQLException {
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Item item = new Item();
                item.setName(resultSet.getString("name"));
                item.setId(resultSet.getInt("id"));
                Timestamp timestamp  = resultSet.getTimestamp("created");
                LocalDateTime localDateTime = timestamp.toLocalDateTime();
                item.setCreated(localDateTime);
                items.add(item);
            }
        }
    }

    @Override
    public Item findById(int id) {
        Item item = new Item();
        try (PreparedStatement statement = cn.prepareStatement(
                "SELECT * FROM items WHERE id = ?")) {
            statement.setInt(1, id);
            statement.execute();
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    item = new Item();
                    item.setName(resultSet.getString("name"));
                    item.setId(resultSet.getInt("id"));
                    Timestamp timestamp  = resultSet.getTimestamp("created");
                    LocalDateTime localDateTime = timestamp.toLocalDateTime();
                    item.setCreated(localDateTime);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }
}
