package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.domain.Item;
import com.example.demo.service.ItemServiceImpl;

class ItemControllerTest {

	@Mock
	private ItemServiceImpl service;

	@InjectMocks
	private ItemController controller;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAll() {
		List<Item> mockItems = List.of(new Item(1, "Laptop", "1500"), new Item(2, "iPad", "100000"));
		when(service.findAll()).thenReturn(mockItems);
		List<Item> items = controller.findAll();
		assertEquals(mockItems, items);

	}

	@Test
	@Disabled
	void testFindById() {
		fail("まだ実装されていません");
	}

	@Test
	@Disabled
	void testSearchItem() {
		fail("まだ実装されていません");
	}

	@Test
	void testAddItem() {
		Item mockItem = new Item(4, "iPad", "100000");
		doNothing().when(service).addItem(any());
		ResponseEntity<String> response = controller.addItem(mockItem);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void testUpdateItem() {
		Item mockItem = new Item(1, "iPad Pro", "150000");
		doNothing().when(service).updateItem(any());
		ResponseEntity<String> response = controller.updateItem(1, mockItem);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void testDeleteItem() {
		Item mockItem = new Item(1, "iPad Pro", "150000");
		doNothing().when(service).deleteItem(1);
		ResponseEntity<String> response = controller.deleteItem(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

}
