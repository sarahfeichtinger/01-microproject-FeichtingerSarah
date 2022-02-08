package controller;

import entity.Ingredient;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IngredientRepository implements PanacheRepository<Ingredient> {
}
