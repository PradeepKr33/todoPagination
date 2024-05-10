package com.example.todopagination.response;

import com.google.gson.annotations.SerializedName;

public class TodosItem{

	@SerializedName("todo")
	private String todo;

	@SerializedName("id")
	private int id;

	@SerializedName("completed")
	private boolean completed;

	@SerializedName("userId")
	private int userId;

	public void setTodo(String todo){
		this.todo = todo;
	}

	public String getTodo(){
		return todo;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCompleted(boolean completed){
		this.completed = completed;
	}

	public boolean isCompleted(){
		return completed;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}
}