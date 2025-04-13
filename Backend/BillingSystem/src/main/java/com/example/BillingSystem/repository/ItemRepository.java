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
	        item.setItemId(rs.getInt("itemId"));
	        item.setName(rs.getString("name"));
	        item.setManufacturer(rs.getString("manufacturer"));
	        item.setHsn(rs.getString("hsn"));
	        item.setStock(rs.getInt("stock"));
	        item.setGst(rs.getDouble("gst"));
	        item.setTax(rs.getString("tax"));
	        item.setDiscount(rs.getDouble("discount"));
	        item.setSellingPrice(rs.getDouble("sellingPrice"));
	        item.setExpiryDate(rs.getDate("expirydate").toLocalDate()); // if using LocalDate
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
	        return jdbcTemplate.query(sql, (rs, rowNum) ->
	                new Item(
	                        rs.getInt("item_id"),
	                        rs.getString("name"),
	                        rs.getString("manufacturer"),
	                        rs.getString("hsn"),
	                        rs.getInt("stock"),
	                        rs.getDouble("gst"),
	                        rs.getString("tax"),
	                        rs.getDouble("discount"),
	                        rs.getInt("sellingPrice"),
	                        rs.getDate("expiryDate").toLocalDate()
	                        ));
	        				
	    }


	 // Check if an item exists by name
	    public boolean existsByName(String name) {
	        Integer count = jdbcTemplate.queryForObject(
	                "SELECT COUNT(*) FROM items WHERE name = ?", Integer.class, name);
	        return count != null && count > 0;
	    }

	 // Update
	    public int update(Item item) {
	        return jdbcTemplate.update("UPDATE items SET item_id=?, name=?, manufacturer=?, hsn=?, stock=?, gst=?, tax=?, discount=?, sellingprice=?, expirydate=? WHERE item_id=?",
	            item.getItemId(), item.getName(), item.getManufacturer(), item.getHsn(), item.getStock(), item.getGst(),
	                item.getTax(), item.getDiscount(), item.getSellingPrice(), item.getExpiryDate(), item.getItemId());
	    }

		
	    public boolean existsById(int itemId) {
	        Integer count = jdbcTemplate.queryForObject(
	            "SELECT COUNT(*) FROM items WHERE item_id = ?", 
	            Integer.class, 
	            itemId
	        );
	        return count != null && count > 0;
	    }
	
		
		
		
	    }

	    

