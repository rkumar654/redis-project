package com.mrk.redis.respository.product;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.mrk.redis.entity.EnumData;

@Repository
public class EnumDataRespository {

	public static final String HASH_KEY = "enum_data";
	
    @Autowired
    private RedisTemplate template;

    @CachePut(value = HASH_KEY)
    public EnumData save(EnumData enumData){
        template.opsForHash().put(HASH_KEY,enumData.getKey(),enumData);
        return enumData;
    }
    
    @Cacheable(value = HASH_KEY)
    public List<EnumData> findAll(){
    	System.out.print("Fetching Data from DB");
        return template.opsForHash().values(HASH_KEY);
    }
    @Cacheable(key = "#id",value = HASH_KEY)
    public EnumData findEnumDataById(String id){
        return (EnumData) template.opsForHash().get(HASH_KEY,id);
    }
    
    public void update(EnumData enumData) {
     
    }
}
