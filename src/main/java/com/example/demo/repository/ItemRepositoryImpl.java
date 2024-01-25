package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Item;
import com.example.demo.mapper.ItemMapper;

@Repository
public class ItemRepositoryImpl implements ItemRepository {
	@Autowired
	private final ItemMapper mapper;
	

	public ItemRepositoryImpl(ItemMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public Item findById(int id) {
		Item item = mapper.findById(id);
		return item;
	}

	@Override
	public List<Item> findAll() {
		List<Item> items = mapper.findAll();
		return items;
	}

	@Override
	public List<Item> searchItem(String word) {
		List<Item> items = mapper.searchItem(word);
		return items;
	}

	@Override
	public void addItem(Item item) {
		mapper.addItem(item);
		
	}

	@Override
	public void updateItem(Item item) {
		mapper.updateItem(item);
		
	}

	@Override
	public void deleteItem(int id) {
		mapper.deleteItem(id);
		
	}

	

}
