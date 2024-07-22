package com.example.erp_java_app.Repository;

import com.example.erp_java_app.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Integer> {
}
