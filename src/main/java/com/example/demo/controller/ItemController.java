package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.domain.Item;
import com.example.demo.service.ItemService;

@RestController
public class ItemController {
	@Autowired
	private final ItemService service;
	

	public ItemController(ItemService service) {
		this.service = service;
	}
	
	@GetMapping(path = "/items", produces = "application/json")
	public List<Item> findAll(){
		List<Item> items = service.findAll();
		return items;
	}
	
	@GetMapping(path = "/item/{id}", produces = "application/json")
	public Item findById(@PathVariable("id") int id) {
		try {
			Item item = service.findById(id);
			if(item == null) {
				throw new ItemNotFoundException("Item not found for id: " + id);
			} else {
		        return item;
			}
		} catch(ItemNotFoundException ex) {
			// ItemNotFoundExceptionが発生した場合はHTTPステータス404を返す
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found", ex);
			
		}catch(Exception ex) {
			// その他の例外が発生した場合はHTTPステータス500を返す。
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
		}
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
    public class ItemNotFoundException extends RuntimeException {
        public ItemNotFoundException(String message) {
            super(message);
        }
    }
	
	@GetMapping(path = "/item/search/{word}", produces = "application/json")
	public List<Item> searchItem(@PathVariable("word") String word){
		List<Item> items = service.searchItem(word);
		return items;
	}
	
	@PostMapping("/item")
	public ResponseEntity<String> addItem(@RequestBody Item item) {
		service.addItem(item);
		return ResponseEntity.ok("success");
	}
	
	@PutMapping("/item/{id}")
	public ResponseEntity<String> updateItem(@PathVariable("id") int id, @RequestBody Item item) {
		item.setId(id);
		service.updateItem(item);
		return ResponseEntity.ok("success");
	}
	
	@DeleteMapping("/item/{id}")
	public ResponseEntity<String> deleteItem(@PathVariable("id") int id){
		service.deleteItem(id);
		return ResponseEntity.ok("Success");
	}

}
