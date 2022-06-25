package de.hse.ss22.vs.dao;

import de.hse.ss22.vs.model.TodoItem;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class TodoItemDao {

    @Inject
    EntityManager em;

    public TodoItem getTodoItem(String todo){
        return em.find(TodoItem.class, todo);
    }
    public List<TodoItem> getAllTodoItems(){
        Query q = em.createQuery("SELECT t FROM TodoItem t");
        return q.getResultList();
    }

    @Transactional
    public TodoItem save(TodoItem todoItem){
        if(em.find(TodoItem.class, todoItem.getTodo()) != null){
            todoItem = em.merge(todoItem);
        } else {
            em.persist(todoItem);
        }
        return todoItem;
    }

    @Transactional
    public TodoItem delete(String todo){
        TodoItem t = em.find(TodoItem.class, todo);
        if(t != null)
            em.remove(t);
        return t;
    }
}
