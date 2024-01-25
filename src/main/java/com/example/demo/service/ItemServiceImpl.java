package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Item;
import com.example.demo.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private final ItemRepository repository;
	

	public ItemServiceImpl(ItemRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Item> findAll() {
		List<Item> items = new ArrayList<>();
		items = repository.findAll();
		return items;
	}
	
	@Override
	public Item findById(int id) {
		Item item = repository.findById(id);
		return item;
	}

	@Override
	public List<Item> searchItem(String word) {
		List<Item> items = new ArrayList<>();
		items = repository.searchItem("%" + word + "%");
		return items;
	}

	@Override
	public void addItem(Item item) {
		repository.addItem(item);
		
	}

	@Override
	public void updateItem(Item item) {
		repository.updateItem(item);
		
	}

	@Override
	public void deleteItem(int id) {
		repository.deleteItem(id);		
	}

	

}
