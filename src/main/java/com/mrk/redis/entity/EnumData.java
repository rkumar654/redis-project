package com.mrk.redis.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("enum_data")
public class EnumData implements Serializable{

	@Id
	private String key;
	private List<String> value;
	
}
