package boundary;

import controller.IngredientRepository;
import entity.Dish;
import entity.Ingredient;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.security.identity.SecurityIdentity;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Path("/ingredient")
@GraphQLApi
@ApplicationScoped
public class IngredientService {

    @Inject
    SecurityIdentity securityIdentity;

    @Inject
    IngredientRepository repository;

    @GET
    @RolesAllowed("user")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getIngredients() {
        return Templates.ingredient(repository.findAll().stream().collect(Collectors.toList()));
    }

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance ingredient(List<Ingredient> ingredients);
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("user")
    @Produces(MediaType.APPLICATION_JSON)
    public Ingredient getIngredientById(@PathParam("id") long id) {
        return repository.findById(id);
    }

    @GET
    @Path("/getAll")
    @Query("allIngredients")
    @Description("Get all Ingredients available for recipes")
    @RolesAllowed("user")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ingredient> getAllIngredients() {
        return repository.listAll();
    }

    @POST
    @RolesAllowed("admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createIngredient(@Context UriInfo info, Ingredient ingredient) {
        ingredient.persist();
        return Response.created(URI.create(info.getPath() + "/" + ingredient.id)).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateIngredient(@PathParam("id") long id, Ingredient ingredient) {
        Ingredient entity = Ingredient.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        entity.setName(ingredient.getName());
        entity.setComment(ingredient.getComment());

        return Response.ok(entity).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response delete(@PathParam("id") long id) {
        Ingredient entity = Ingredient.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
        return Response.ok().build();
    }
}
