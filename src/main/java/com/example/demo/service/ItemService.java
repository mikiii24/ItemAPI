package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Item;

public interface ItemService {
	
	List<Item> findAll();

	List<Item> searchItem(String word);

	void addItem(Item item);

	void updateItem(Item item);

	Item findById(int id);

	void deleteItem(int id);

}
