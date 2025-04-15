package com.example.BillingSystem.repository;
import java.util.List;
import java.util.Collections;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.example.BillingSystem.model.Item;

@Repository
//Class Declaration
public class ItemRepository {
	
	//Dependency Injection
    private final JdbcTemplate jdbcTemplate;

    public ItemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    //RowMapper for converting ResultSet into Item object
    private final RowMapper<Item> itemRowMapper = (rs, rowNum) -> {
        Item item = new Item();
        item.setItemId(rs.getInt("item_id"));
        item.setName(rs.getString("name"));
        item.setManufacturer(rs.getString("manufacturer"));
        item.setHsn(rs.getString("hsn"));
        item.setStock(rs.getInt("stock"));
        item.setGst(rs.getDouble("gst"));
        item.setTax(rs.getString("tax"));
        item.setDiscount(rs.getDouble("discount"));
        item.setSellingPrice(rs.getDouble("sellingPrice"));
        item.setExpiryDate(rs.getDate("expiryDate").toLocalDate());
        return item;
    };
    
    //Create, Read, Update, Delete, and other operation are mentioned below
    // CREATE
    public int save(Item item) {
        String sql = "INSERT INTO items (name, manufacturer, hsn, stock, gst, tax, discount, sellingPrice, expiryDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            return jdbcTemplate.update(sql,
                    item.getName(),
                    item.getManufacturer(),
                    item.getHsn(),
                    item.getStock(),
                    item.getGst(),
                    item.getTax(),
                    item.getDiscount(),
                    item.getSellingPrice(),
                    item.getExpiryDate());
        } catch (DataAccessException e) {
            System.err.println("Error saving item: " + e.getMessage());
            return 0;
        }
    }

    // READ
    public List<Item> findAll() {
        String sql = "SELECT * FROM items";
        try {
            return jdbcTemplate.query(sql, itemRowMapper);
        } catch (DataAccessException e) {
            System.err.println("Error fetching items: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    // EXISTS BY NAME
    public boolean existsByName(String name) {
        try {
            Integer count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM items WHERE name = ?", Integer.class, name);
            return count != null && count > 0;
        } catch (DataAccessException e) {
            System.err.println("Error checking existence by name: " + e.getMessage());
            return false;
        }
    }

    // UPDATE
    public int update(Item item) {
        String sql = "UPDATE items SET name=?, manufacturer=?, hsn=?, stock=?, gst=?, tax=?, discount=?, sellingPrice=?, expiryDate=? WHERE item_id=?";
        try {
            return jdbcTemplate.update(sql,
                    item.getName(),
                    item.getManufacturer(),
                    item.getHsn(),
                    item.getStock(),
                    item.getGst(),
                    item.getTax(),
                    item.getDiscount(),
                    item.getSellingPrice(),
                    item.getExpiryDate(),
                    item.getItemId());
        } catch (DataAccessException e) {
            System.err.println("Error updating item: " + e.getMessage());
            return 0;
        }
    }

    // EXISTS BY ID
    public boolean existsById(int itemId) {
        try {
            Integer count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM items WHERE item_id = ?",
                    Integer.class,
                    itemId);
            return count != null && count > 0;
        } catch (DataAccessException e) {
            System.err.println("Error checking existence by ID: " + e.getMessage());
            return false;
        }
    }

    // DELETE
    public int delete(Item item) {
        String sql = "DELETE FROM items WHERE item_id=?";
        try {
            return jdbcTemplate.update(sql, item.getItemId());
        } catch (DataAccessException e) {
            System.err.println("Error deleting item: " + e.getMessage());
            return 0;
        }
    }

    //Searching item through name
    public List<Item> findByName(String name) {
        List<Item> itemList = new ArrayList<>();

        try {
            if (name == null || name.trim().isEmpty()) {
                return itemList; // Return empty list if input is invalid
            }

            String sql = "SELECT * FROM items WHERE TRIM(LOWER(name)) = TRIM(LOWER(?))";
            itemList = jdbcTemplate.query(sql, itemRowMapper, name.trim());
        } catch (DataAccessException e) {
            System.out.println("Error while fetching students by name: " + e.getMessage());
           
        }

        return itemList;
    }
}
