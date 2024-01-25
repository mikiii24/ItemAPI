package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.Item;

@Mapper
public interface ItemMapper {

	List<Item> findAll();
	
	Item findById(int id);

	List<Item> searchItem(String word);

	void addItem(Item item);

	void updateItem(Item item);

	void deleteItem(int id);

}
