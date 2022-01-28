package org.example.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.example.model.TodoModel;
import org.example.model.TodoRequest;
import org.example.service.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks    // 위 todoRepository Mock을 주입받아서 사용할 todoService 정의
    private TodoService todoService;

    @Test
    void add() {
        when(this.todoRepository.save(any(TodoModel.class)))
                .then(AdditionalAnswers.returnsFirstArg()); //TodoRepository가 save()메소드를 호출해서 TodoEntity값을 받으면 받은 엔티티 값을 반환하도록 설정

        TodoRequest expected = new TodoRequest();
        expected.setTitle("Test Title");

        TodoModel actual = this.todoService.add(expected);

        assertEquals(expected.getTitle(), actual.getTitle());
    }

    @Test
    void searchById() {
        TodoModel entity = new TodoModel();
        entity.setId(123L);
        entity.setTitle("Title");
        entity.setOrder(0L);
        entity.setCompleted(false);
        Optional<TodoModel> optional = Optional.of(entity);

        given(this.todoRepository.findById(anyLong()))
            .willReturn(optional);

        TodoModel actual = this.todoService.searchById(123L);

        TodoModel expected = optional.get();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getOrder(), actual.getOrder());
        assertEquals(expected.getCompleted(), actual.getCompleted());

    }

    @Test
    public void searchByIdFailed(){
        given(this.todoRepository.findById(anyLong()))
            .willReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, ()->{
            this.todoService.searchById(123L);
        });
    }
}