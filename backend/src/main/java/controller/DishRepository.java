package controller;

import entity.Dish;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DishRepository implements PanacheRepository<Dish> {
}
