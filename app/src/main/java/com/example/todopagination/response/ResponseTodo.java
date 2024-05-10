package com.example.todopagination.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseTodo{

	@SerializedName("total")
	private int total;

	@SerializedName("limit")
	private int limit;

	@SerializedName("skip")
	private int skip;

	@SerializedName("todos")
	private List<TodosItem> todos;

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setLimit(int limit){
		this.limit = limit;
	}

	public int getLimit(){
		return limit;
	}

	public void setSkip(int skip){
		this.skip = skip;
	}

	public int getSkip(){
		return skip;
	}

	public void setTodos(List<TodosItem> todos){
		this.todos = todos;
	}

	public List<TodosItem> getTodos(){
		return todos;
	}
}