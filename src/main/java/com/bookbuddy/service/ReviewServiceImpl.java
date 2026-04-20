package com.bookbuddy.service;

import com.bookbuddy.dto.ReviewCreateDto;
import com.bookbuddy.dto.ReviewDto;
import com.bookbuddy.exception.notfound.BookNotFoundException;
import com.bookbuddy.exception.notfound.UserNotFoundException;
import com.bookbuddy.mapper.ReviewMapper;
import com.bookbuddy.model.Book;
import com.bookbuddy.model.Review;
import com.bookbuddy.model.User;
import com.bookbuddy.repository.BookRepository;
import com.bookbuddy.repository.ReviewRepository;
import com.bookbuddy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public ReviewDto create(ReviewCreateDto dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new BookNotFoundException("Book not found"));

        Review review = Review.builder()
                .user(user)
                .book(book)
                .rating(dto.getRating())
                .comment(dto.getComment())
                .build();

        return reviewMapper.toDto(reviewRepository.save(review));
    }

    @Override
    public List<ReviewDto> getByBookId(UUID bookId) {
        return reviewRepository.findByBookId(bookId)
                .stream()
                .map(reviewMapper::toDto)
                .toList();
    }
}
