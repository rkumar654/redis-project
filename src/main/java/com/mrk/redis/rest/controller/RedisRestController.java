package com.mrk.redis.rest.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrk.redis.entity.EnumData;
import com.mrk.redis.entity.Product;
import com.mrk.redis.respository.product.EnumDataRespository;
import com.mrk.redis.respository.product.ProductRespository;

@RestController
@RequestMapping("/redis/product")
@CrossOrigin("*")
public class RedisRestController {

	@Autowired
	private ProductRespository productRespository; 
	
	@Autowired
	private EnumDataRespository enumDataRepository;
	
	@PostMapping
    public Product save(@RequestBody Product product) {
        return productRespository.save(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRespository.findAll();
    }

    @GetMapping("/{id}")
    public Product findProduct(@PathVariable int id) {
        return productRespository.findProductById(id);
    }
    @DeleteMapping("/{id}")
    public String remove(@PathVariable int id)   {
    	return productRespository.deleteProduct(id);
	}
    @Scheduled(cron="0 * * * 1 *")
    @GetMapping("/json/data")
    public ResponseEntity<Object> jsonDtls(){
    	System.out.println("7263428765");
    	Map<String,List<String>> result= enumDataRepository.findAll().stream().collect(Collectors.toMap(EnumData::getKey, EnumData::getValue));
    	return new ResponseEntity<Object>(result, HttpStatus.OK);
    }
    
    @PostMapping("/json/data")
    public ResponseEntity<Object> saveJsonDtls(@RequestBody EnumData body){
    	enumDataRepository.save(body);
    	return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
