package pl.jstk.repository;


import java.util.List;

import pl.jstk.entity.BookEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.jstk.enumerations.BookStatus;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Query("select book from BookEntity book where upper(book.title) like concat(upper(:title), '%')")
    List<BookEntity> findBookByTitle(@Param("title") String title);

    @Query("select book from BookEntity book where upper(book.authors) like concat('%', upper(:author), '%')")
    List<BookEntity> findBookByAuthor(@Param("author") String author);

    @Query("select book from BookEntity book where (:status is null or :status = book.status) and upper(book.authors) like concat('%', upper(:author), '%') and upper(book.title) like concat(upper(:title), '%')")
    List<BookEntity> findBookByAllFields(@Param("author") String author, @Param("title") String title, @Param("status")BookStatus status);

    @Query("select book from BookEntity book where id = :id")
    BookEntity findBookById(@Param("id") long id);

    @Query("select book from BookEntity book where status=:status")
    BookEntity findBookByStatus(@Param("status") BookStatus status);
}
//upper(book.status) like concat('%', upper(:status), '%') and
//, @Param("status")BookStatus status