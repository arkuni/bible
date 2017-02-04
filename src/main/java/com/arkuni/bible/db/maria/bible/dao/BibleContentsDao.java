package com.arkuni.bible.db.maria.bible.dao;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arkuni.bible.db.maria.bible.mapper.BibleContentsMapper;

@Repository
public class BibleContentsDao {
	@Autowired
	private BibleContentsMapper mapper;
	
	public HashMap<String, Object> getData() {
		return mapper.getData();
	}

	public HashMap<String, Object> today() {
		return mapper.today();
	}
}
