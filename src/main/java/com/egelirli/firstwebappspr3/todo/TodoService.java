package com.egelirli.firstwebappspr3.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

	private static List<Todo> todos = new ArrayList<>();
	
	private static  int idToDo = 0;
	static {
		todos.add(new Todo(++idToDo, "in28minutes","Learn AWS", 
							LocalDate.now().plusYears(1), false ));
		todos.add(new Todo(++idToDo, "in28minutes","Learn DevOps", 
				LocalDate.now().plusYears(2), false ));
		todos.add(new Todo(++idToDo, "in28minutes","Learn Full Stack Development", 
				LocalDate.now().plusYears(3), false ));
	}

	public List<Todo> findByUsername(String  userName) {
		
		return todos;
	}

	public void addNewTodo(String userName, String descript,  
						   LocalDate targetDate, boolean isCompleted) {
		
		todos.add(new Todo(++idToDo, userName,descript,targetDate,isCompleted));
				
	}	
}
