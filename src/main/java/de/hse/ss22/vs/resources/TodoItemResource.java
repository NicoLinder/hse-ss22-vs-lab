package de.hse.ss22.vs.resources;

import de.hse.ss22.vs.dao.TodoItemDao;
import de.hse.ss22.vs.model.TodoItem;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/todos")
public class TodoItemResource {

    @Inject
    TodoItemDao todoItemDao;

    @Operation(summary = "Gets a list of all ToDo items")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TodoItem> getTodos(){
        return todoItemDao.getAllTodoItems();
    }

    @Operation(summary = "Get a ToDo item by its ID (todo text)")
    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodoByName(@PathParam(value = "name") String name){
        TodoItem todoItem = todoItemDao.getTodoItem(name);

        if(todoItem == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }

        return Response.ok(todoItem).build();
    }

    @Operation(summary = "Add a new ToDo item")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TodoItem createTodoItem(TodoItem todoItem){
        return todoItemDao.save(todoItem);
    }

    @Operation(summary = "Change the priority of a ToDo item")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTodoItem(TodoItem updatedTodoItem){
        TodoItem todoItem = todoItemDao.getTodoItem(updatedTodoItem.getTodo());

        if(todoItem == null){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        todoItem = todoItemDao.save(updatedTodoItem);
        return Response.ok(todoItem).build();
    }

    @Operation(summary = "Delete a ToDo item by its ID (todo text)")
    @DELETE
    @Path("/{name}")
    public Response deleteTodoItem(@PathParam(value = "name") String name){
        TodoItem deletedItem = todoItemDao.delete(name);

        if(deletedItem == null){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        return Response.ok().build();
    }
}
