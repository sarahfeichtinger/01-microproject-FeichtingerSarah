package entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class RecipeStep extends PanacheEntity {

    @JsonbProperty("step_num")
    private int stepNum;
    private int amount;
    private String unit;
    private String instruction;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "INGREDIENT_ID")
    private Ingredient ingredient;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DISH_ID")
    private Dish dish;


    public RecipeStep(int step_num, int amount, String unit, String instruction, Ingredient ingredient, Dish dish) {
        this.stepNum = step_num;
        this.amount = amount;
        this.unit = unit;
        this.instruction = instruction;
        this.ingredient = ingredient;
        this.dish = dish;
    }

    public RecipeStep() {

    }

    @Override
    public String toString() {
        return "RecipeStep{" +
                "step_num=" + stepNum +
                ", amount=" + amount +
                ", unit='" + unit + '\'' +
                ", instruction='" + instruction + '\'' +
                ", ingredient=" + ingredient +
                ", dish=" + dish +
                '}';
    }
}

