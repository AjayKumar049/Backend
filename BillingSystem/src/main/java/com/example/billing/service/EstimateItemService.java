package com.example.billing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.billing.model.EstimateItem;
import com.example.billing.model.Item;

@Service
public class EstimateItemService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Item getItemDetailsByName(String name) {
        String sql = "SELECT * FROM item WHERE name = ?";
        List<Item> items = jdbcTemplate.query(sql, new Object[]{name}, (rs, rowNum) -> {
            Item item = new Item();
            item.setItemId(rs.getLong("item_id"));
            item.setName(rs.getString("name"));
            item.setGst(rs.getDouble("gst"));
            item.setTax(rs.getString("tax_status"));
            item.setDiscount(rs.getDouble("discount"));
            item.setSellingPrice(rs.getDouble("selling_price"));
            return item;
        });

        return items.isEmpty() ? null : items.get(0);
    }


    public void addEstimateItem(EstimateItem estimateItem) {
        double discountAmount = estimateItem.getUnitPrice() * estimateItem.getQuantity() * (estimateItem.getDiscount() / 100);
        double gstAmount = estimateItem.getUnitPrice() * estimateItem.getQuantity() * (estimateItem.getGst() / 100);
        double totalAmount = (estimateItem.getUnitPrice() * estimateItem.getQuantity()) - discountAmount + gstAmount;

        String sql = "INSERT INTO estimate_item (estimate_id, item_id, name,tax_status, gst, discount, quantity, unit_price, total_amount) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
        jdbcTemplate.update(sql,
                estimateItem.getEstimatedId(),
                estimateItem.getItemId(),
                estimateItem.getName(),
                estimateItem.getTax(),
                estimateItem.getGst(),
                estimateItem.getDiscount(),
                estimateItem.getQuantity(),
                estimateItem.getUnitPrice(),
                
                totalAmount
        );
    }
}

