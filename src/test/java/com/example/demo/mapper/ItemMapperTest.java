package com.example.demo.mapper;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;

import com.example.demo.domain.Item;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ItemMapperTest {

	@Autowired
	private ItemMapper itemMapper;

	@Test
	@Sql(scripts = "/test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	void testFindAll() {
		List<Item> items = itemMapper.findAll();
		assertEquals(3, items.size());
	}

	@Test
	@Sql(scripts = "/test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	void testFindById() {
		// ここでは /test.sql によりデータベースには id=5 が存在すると仮定しています
		Item item = itemMapper.findById(1);
		System.out.println(item.getId());
		var itemName = item.getName();
		var itemPrice = item.getPrice();
		assertEquals("iPad", itemName);
		assertEquals("100000", itemPrice);
	}

	@Test
	@Sql(scripts = "/test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	void testSearchItem() {
		List<Item> items = itemMapper.searchItem("%i%");
		assertEquals(2, items.size());
	}

	@Test
	@Sql(scripts = "/test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	void testAddItem() {
		Item item = new Item(4, "airPods", "50000");
		//        item.setId(4);
		//        item.setName("airPods");
		//        item.setPrice("50000");
		itemMapper.addItem(item);
		assertEquals("airPods", item.getName());
		assertThat(itemMapper.findById(4)).isNotNull();
	}

	@Test
	@Sql(scripts = "/test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	void testUpdateItem() {
		Item item = itemMapper.findById(1);
		item.setName("iPad Pro");
		item.setPrice("150000");
		itemMapper.updateItem(item);
		Item updatedItem = itemMapper.findById(1);
		assertEquals(1, updatedItem.getId());
		assertEquals("iPad Pro", updatedItem.getName());
	}

	@Test
	@Sql(scripts = "/test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	void testDeleteItem() {
		itemMapper.deleteItem(1);
		Item item = itemMapper.findById(1);
		System.out.println(item);
		assertThat(item).isNull();
	}
}