package com.example.BillingSystem.repository;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.example.BillingSystem.model.Item;
@Repository
public class ItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public ItemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Item> itemRowMapper = (rs, rowNum) -> {
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

    // CREATE
    public int save(Item item) {
        String sql = "INSERT INTO items (name, manufacturer, hsn, stock, gst, tax, discount, sellingPrice, expiryDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
    }

    // READ
    public List<Item> findAll() {
        String sql = "SELECT * FROM items";
        return jdbcTemplate.query(sql, itemRowMapper);
    }

    // Check if an item exists by name
    public boolean existsByName(String name) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM items WHERE name = ?", Integer.class, name);
        return count != null && count > 0;
    }

    // UPDATE
    public int update(Item item) {
        String sql = "UPDATE items SET name=?, manufacturer=?, hsn=?, stock=?, gst=?, tax=?, discount=?, sellingPrice=?, expiryDate=? WHERE item_id=?";
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
    }

    // Check if item exists by ID
    public boolean existsById(int itemId) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM items WHERE item_id = ?",
                Integer.class,
                itemId);
        return count != null && count > 0;
    }

    // DELETE
    public int delete(Item item) {
        String sql = "DELETE FROM items WHERE item_id=?";
        return jdbcTemplate.update(sql, item.getItemId());
    }
}
