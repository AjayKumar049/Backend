package com.example.billing.repository;
import java.util.List;



import java.util.ArrayList;
import java.util.Collections;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.example.billing.exception.BillingSystemInternalException;
import com.example.billing.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Repository
//Class Declaration
public class ItemRepository {
	
	//Dependency Injection
    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ItemRepository.class);

    public ItemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    //RowMapper for converting ResultSet into Item object
    private final RowMapper<Item> itemRowMapper = (rs, rowNum) -> {
        Item item = new Item();
        item.setItemId(rs.getLong("item_id"));
        item.setName(rs.getString("name"));
        item.setManufacturer(rs.getString("manufacturer"));
        item.setHsn(rs.getString("hsn_code"));
        item.setStock(rs.getInt("stock"));
        item.setGst(rs.getDouble("gst"));
        item.setTax(rs.getString("tax_status"));
        item.setDiscount(rs.getDouble("discount"));
        item.setSellingPrice(rs.getDouble("selling_price"));
        item.setExpiryDate(rs.getDate("expiry_date").toLocalDate());
        return item;
    };
    
    //Create, Read, Update, Delete, and other operation are mentioned below
    // CREATE
    public int save(Item item) {
        String sql = "INSERT INTO item (name, manufacturer, hsn_code, stock, gst, tax_status, discount, selling_price, expiry_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
        	logger.error("Error saving item", e);
            return 0;
        }
    }

    // READ
    public List<Item> findAll() {
        String sql = "SELECT * FROM item";
        try {
            return jdbcTemplate.query(sql, itemRowMapper);
        } catch (DataAccessException e) {
        	logger.error("Error while fetching item", e);
            return Collections.emptyList();
        }
    }

    // EXISTS BY NAME
    public boolean existsByName(String name) {
        try {
            Integer count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM item WHERE name = ?", Integer.class, name);
            return count != null && count > 0;
        } catch (DataAccessException e) {
        	logger.error("Error checking exist by name", e);
            return false;
        }
    }

    // UPDATE
    public int update(Item item) {
        String sql = "UPDATE item SET name=?, manufacturer=?, hsn_code=?, stock=?, gst=?, tax_status=?, discount=?, selling_price=?, expiry_date=? WHERE item_id=?";
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
        	logger.error("Error updating item", e);
            return 0;
        }
    }

    // EXISTS BY ID
    public boolean existsById(Long itemId) {
        try {
            Integer count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM item WHERE item_id = ?",
                    Integer.class,
                    itemId);
            return count != null && count > 0;
        } catch (DataAccessException e) {
            throw new BillingSystemInternalException("Error accessing DB while checking item existence: " + e.getMessage());
        }
    }
    
    // EXISTS BY HSN CODE
    public boolean existsByHsnCode(String hsnCode) {
        try {
            Integer count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM item WHERE hsn_code = ?",
                    Integer.class,
                    hsnCode);
            return count != null && count > 0;
        } catch (DataAccessException e) {
            throw new BillingSystemInternalException("Error accessing DB while checking HSN code existence: " + e.getMessage());
        }
    }



	
    
    //Searching item through name
    public List<Item> findByName(String name) {
        List<Item> itemList = new ArrayList<>();

        try {
            if (name == null || name.trim().isEmpty()) {
                return itemList; // Return empty list if input is invalid
            }

            String sql = "SELECT * FROM item WHERE TRIM(LOWER(name)) = TRIM(LOWER(?))";
            itemList = jdbcTemplate.query(sql, itemRowMapper, name.trim());
        } catch (DataAccessException e) {
        	logger.error("Error while fetching item by name", e);
           
        }

        return itemList;
    }
    
 // DELETE
    public int delete(Item item) {
        String sql = "DELETE FROM item WHERE item_id=?";
        try {
            return jdbcTemplate.update(sql, item.getItemId());
        } catch (DataAccessException e) {
        	logger.error("Error deleting item: {}", e.getMessage());


            return 0;
        }
    }

}
