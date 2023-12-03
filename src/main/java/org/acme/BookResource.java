package org.acme;

import java.util.List;
import java.util.Optional;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.acme.model.Book;

@Path("library")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    @GET
    public List<Book> findAll(@DefaultValue("0") @QueryParam("page") int page,
            @DefaultValue("10") @QueryParam("size") int size) {
        return Book.findAll().page(page, size).list();
    }

    @GET
    @Path("{id}")
    public Book findById(@PathParam("id") long id) {
        return Book.<Book>findByIdOptional(id)
                .orElseThrow(NotFoundException::new);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Book create(Book book) {
        Optional.ofNullable(book)
            .map(b -> b.title)
            .orElseThrow(BadRequestException::new);
        book.persist();
        return book;
    }

    @PUT
    @Path("{id}/price")
    @Transactional
    public Book updatePrice(@PathParam("id") long id, @QueryParam("price") double price) {
        final var book = Book.<Book>findByIdOptional(id)
            .orElseThrow(NotFoundException::new);
        book.price = price;
        book.persist();
        return book;
    }

}
