package ru.job4j.tracker;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

public class SqlTrackerTest {
    private static Connection connection;

    @BeforeAll
    public static void initConnection() {
        try (InputStream in = new FileInputStream("db/liquibase_test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @AfterEach
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId())).isEqualTo(item);
    }

    @Test
    public void whenAddItemAndFindById() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId()).getName()).isEqualTo("item");
    }

    @Test
    public void whenAddItemAndFindByName() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("Item_1");
        tracker.add(item);
        assertThat(tracker.findByName("Item_1").get(0)).isEqualTo(item);
    }

    @Test
    public void whenAddItemAndReplace() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("Item_1");
        tracker.add(item);
        tracker.replace(item.getId(), new Item("Item_2"));
        assertThat(tracker.findById(item.getId()).getName()).isEqualTo("Item_2");
    }

    @Test
    public void whenAddItemAndDelete() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("Item_1");
        tracker.add(item);
        assertThat(tracker.findById(item.getId())).isEqualTo(item);
        tracker.delete(item.getId());
        assertThat(tracker.findById(item.getId())).isNull();
    }

    @Test
    public void whenAddThreeItemAndFindAll() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item1 = new Item("Item_1");
        Item item2 = new Item("Item_2");
        Item item3 = new Item("Item_3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        List<Item> itemList = List.of(item1, item2, item3);
        List<Item> rslList = tracker.findAll();
        assertThat(itemList).isEqualTo(rslList);
    }
}
