package com.bookbuddy.mapper;

import com.bookbuddy.dto.ReviewDto;
import com.bookbuddy.model.Book;
import com.bookbuddy.model.Review;
import com.bookbuddy.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewMapperTest {

    @Autowired
    private ReviewMapper reviewMapper;

    @Test
    void shouldMapEntityToDtoCorrectly() {

        // given
        User user = new User();
        user.setId(UUID.randomUUID());

        Book book = new Book();
        book.setId(UUID.randomUUID());

        Review review = new Review();
        review.setId(UUID.randomUUID());
        review.setUser(user);
        review.setBook(book);
        review.setRating(5);
        review.setComment("Great book");

        // when
        ReviewDto dto = reviewMapper.toDto(review);

        // then
        assertNotNull(dto);
        assertEquals(review.getId(), dto.getId());
        assertEquals(review.getUser().getId(), dto.getUserId());
        assertEquals(review.getBook().getId(), dto.getBookId());
        assertEquals(review.getRating(), dto.getRating());
        assertEquals(review.getComment(), dto.getComment());
    }
}
