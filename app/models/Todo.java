package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Todo extends Model{

    public String title;
    public Long id;

    public Todo(String title) {
        this.title = title;

    }

    public Todo(Long id) {
        this.id = id;
    }
}


