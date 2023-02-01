package com.mrk.redis.respository.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.mrk.redis.entity.Product;

@Repository
public class ProductRespository {

	
	public static final String HASH_KEY = "product";
    @Autowired
    private RedisTemplate template;

    public Product save(Product product){
        template.opsForHash().put(HASH_KEY,product.getId(),product);
        return product;
    }

    public List<Product> findAll(){
        return template.opsForHash().values(HASH_KEY);
    }

    @Cacheable(key = "#id",value = HASH_KEY)
    public Product findProductById(int id){
    	System.out.println("Fetching data from DB");
        return (Product) template.opsForHash().get(HASH_KEY,id);
    }


    public String deleteProduct(int id){
         template.opsForHash().delete(HASH_KEY,id);
        return "product removed !!";
    }
}
