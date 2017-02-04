package com.arkuni.bible.db.maria.bible.mapper;

import java.util.HashMap;
import java.util.List;

public interface BibleContentsMapper {
	public HashMap<String, Object> getData();

	public List<HashMap<String, Object>> today();
}
