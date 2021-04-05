package ru.kpfu.itis.group907.farkhutdinova.myspringwebapplication.repos;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.itis.group907.farkhutdinova.myspringwebapplication.domain.Message;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {
    List<Message> findByTag(String tag);
    Message findById(Integer id);
}
