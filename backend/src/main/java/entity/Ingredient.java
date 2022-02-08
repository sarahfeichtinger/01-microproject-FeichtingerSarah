package entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Ingredient extends PanacheEntity {

    private String name;
    private String comment;

    public Ingredient(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

    public Ingredient() {

    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
