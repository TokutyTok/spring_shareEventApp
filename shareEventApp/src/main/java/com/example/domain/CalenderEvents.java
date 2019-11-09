package com.example.domain;

import java.util.ArrayList;
import java.util.List;

public class CalenderEvents {

	private List<List<CalenderEvent>> titleLists = new ArrayList<List<CalenderEvent>>();

	private List<CalenderEvent> lists = new ArrayList<>();

	public List<CalenderEvent> getLists() {
		return lists;
	}

	public void setLists(List<CalenderEvent> lists) {
		this.titleLists.add(lists);
	}

	public List<List<CalenderEvent>> getTitleLists() {
		return titleLists;
	}

	public void setTitleLists(List<List<CalenderEvent>> titleLists) {
		this.titleLists = titleLists;
	}



}