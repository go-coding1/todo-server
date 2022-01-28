package org.example.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.example.model.TodoModel;
import org.example.model.TodoRequest;
import org.example.service.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * | 1    | Todo 리스트 목록에 아이템을 추가       |
 * | ---- | -------------------------------------- |
 * | 2    | Todo 리스트 목록 중 특정 아이템을 조회 |
 * | 3    | Todo 리스트 전체 목록을 조회           |
 * | 4    | Todo 리스트 목록 중 특정 아이템을 수정 |
 * | 5    | Todo 리스트 목록 중 특정 아이템을 삭제 |
 * | 6    | Todo 리스트 전체 목록을 삭제           |
 */
@Service
@AllArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoModel add(TodoRequest request){
        TodoModel todoModel = new TodoModel();
        todoModel.setTitle(request.getTitle());
        todoModel.setOrder(request.getOrder());
        todoModel.setCompleted(request.getCompleted());

        return this.todoRepository.save(todoModel);
    }

    public TodoModel searchById(Long id){
        return this.todoRepository.findById(id)
            .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    public List<TodoModel> searchAll(){
        return this.todoRepository.findAll();
    }

    public TodoModel updateById(Long id, TodoRequest request){
        TodoModel todoModel = this.searchById(id);
        if(request.getTitle() != null){
            todoModel.setTitle(request.getTitle());
        }
        if(request.getOrder() != null){
            todoModel.setOrder(request.getOrder());
        }
        if(request.getCompleted() != null){
            todoModel.setCompleted(request.getCompleted());
        }
        return this.todoRepository.save(todoModel);
    }

    public void deleteById(Long id){
        this.todoRepository.deleteById(id);
    }

    public void deleteAll(){
        this.todoRepository.deleteAll();
    }
}
