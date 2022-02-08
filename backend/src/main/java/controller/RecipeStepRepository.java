package controller;

import entity.RecipeStep;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RecipeStepRepository implements PanacheRepository<RecipeStep> {
}
