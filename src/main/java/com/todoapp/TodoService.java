package com.todoapp;

import com.google.gson.Gson;
import com.mongodb.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by shekhargulati on 09/06/14.
 */
public class TodoService {

    private final DB db;
    private final DBCollection collection;

    public TodoService(DB db) {
        this.db = db;
        this.collection = db.getCollection("todos");
    }

    public List<Todo> findAll() {
        List<Todo> todos = new ArrayList<>();
        DBCursor dbObjects = collection.find();
        while (dbObjects.hasNext()) {
            DBObject dbObject = dbObjects.next();
            todos.add(new Todo((BasicDBObject) dbObject));
        }
        return todos;
    }

    public void createNewTodo(String body) {
        Todo todo = new Gson().fromJson(body, Todo.class);
        collection.insert(new BasicDBObject("title", todo.getTitle()).append("done", todo.isDone()).append("createdOn", new Date()));
    }

    public Todo find(String id) {
        return new Todo((BasicDBObject) collection.findOne(new BasicDBObject("_id", new ObjectId(id))));
    }

    public Todo update(String todoId, String body) {
        Todo todo = new Gson().fromJson(body, Todo.class);
        collection.update(new BasicDBObject("_id", new ObjectId(todoId)), new BasicDBObject("$set", new BasicDBObject("done", todo.isDone())));
        return this.find(todoId);
    }
}
