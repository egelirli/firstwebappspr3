package com.egelirli.firstwebappspr3.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

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

	public void addNewTodo(String userName, 
							String descript,  
						   LocalDate targetDate, 
						   boolean isCompleted) {
		
		todos.add(new Todo(++idToDo, userName,descript,targetDate,isCompleted));
				
	}

	public boolean deleteById(int id) {
		
		Predicate<? super Todo> predicate = 
				t -> t.getId() == id;
		return todos.removeIf(predicate);
		
//		Todo t =  findTodoById(id);
//		if(t != null) {
//			ret = todos.remove(t);
//		}
		
	}	
	
	public Todo findTodoById(int id) {
		
		Predicate<? super Todo> predicate = 
				t -> t.getId() == id;
		return todos.stream().filter(predicate).findFirst().get();
//		Todo ret = null;
//		
//		for (Todo todo : todos) {
//			if(todo.getId() == id) {
//				ret = todo;
//				break;
//			}
//		}
//		
//		return ret;
	}

	public boolean modifyTodo(@Valid Todo todo) {
		Todo t =  findTodoById(todo.getId());
		
		if(t != null) {
			t.setDescription(todo.getDescription());
			return true;
		}else {
			return false;
		}
		
	}
}
