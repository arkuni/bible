package com.arkuni.bible.search.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkuni.bible.db.maria.bible.dao.BibleContentsDao;

@Service
public class BibleSearchService {
	@Autowired
	private BibleContentsDao dao;
	
	public HashMap<String, Object> getData() {
		return dao.getData();
	}

	public List<HashMap<String, Object>> today() {
		return dao.today();
	}
}
