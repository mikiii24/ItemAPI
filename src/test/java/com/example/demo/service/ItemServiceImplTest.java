package com.example.demo.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.domain.Item;
import com.example.demo.repository.ItemRepository;


@ExtendWith(MockitoExtension.class)
public class ItemServiceImplTest {


	@Mock
    private ItemRepository repository;
    @InjectMocks
    private ItemServiceImpl service;
    
    @Captor
    private ArgumentCaptor<Integer> id;
    
    @Captor
    private ArgumentCaptor<String> word;
	

    @Test
    void testFindAll() {
    	
    	
        //repository.findAll()の挙動をモックする
        when(repository.findAll()).thenReturn(List.of(new Item(1, "iPad", "100000"), new Item(2, "iPhone14", "150000"), new Item(3, "PC", "200000")));

        //ItemServiceImplのfindAllメソッドを呼び出す
        List<Item> items = service.findAll();
        System.out.println(items);

        //アサーションと検証
        assertEquals(3, items.size());
        verify(repository, times(1)).findAll();
    }

	@Test
	void testFindById() {
		doReturn(new Item(1, "iPad", "100000")).when(repository).findById(anyInt());
		Item item = service.findById(1);
		assertThat(item).isNotNull();
		verify(repository, times(1)).findById(id.capture());
		var itemId = id.getValue();
		assertEquals(1, itemId);
	}

	@Test
	void testSearchItem() {
		doReturn(List.of(new Item (1, "iPad", "100000"), new Item(2, "iPhone14", "150000")))
		.when(repository).searchItem(anyString());
		List<Item> items = service.searchItem("keyword");
		assertEquals(2, items.size());
		verify(repository, times(1)).searchItem(word.capture());
		var itemWord = word.getValue();
		assertEquals("%keyword%", itemWord);
	}

	@Test
	void testAddItem() {
		doNothing().when(repository).addItem(any(Item.class));
    	Item item = new Item();
//		item.setId(4);
//		item.setName("airPods");
//		item.setPrice("50000");
		service.addItem(item);
//		assertEquals(4, item.getId());
		verify(repository, times(1)).addItem(item);
	}

	@Test
	void testUpdateItem() {
		doNothing().when(repository).updateItem(any(Item.class));
		Item item = new Item();
		service.updateItem(item);
		verify(repository, times(1)).updateItem(item);

	}

	@Test
	void testDeleteItem() {
		doNothing().when(repository).deleteItem(1);
		Item item = new Item();
		service.deleteItem(1);
		verify(repository, times(1)).deleteItem(1);
	}

}
