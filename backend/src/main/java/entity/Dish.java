package entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Dish extends PanacheEntity{

    private String name;
    private String description;
    private int duration;
    @Enumerated(EnumType.STRING)
    private Tag tag;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    public Dish(String name, String description, int duration, Tag tag, Difficulty difficulty)
    {
        this.setName(name);
        this.setDescription(description);
        this.setDuration(duration);
        this.tag = tag;
        this.difficulty = difficulty;
    }

    public Dish() {

    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", tag='" + tag.ordinal() + '\'' +
                ", difficulty='" + difficulty.ordinal() + '\'' +
                '}';
    }
}

