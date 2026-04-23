package com.bookbuddy.controller;

import com.bookbuddy.api.ReviewApi;
import com.bookbuddy.dto.ReviewCreateDto;
import com.bookbuddy.dto.ReviewDto;
import com.bookbuddy.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController implements ReviewApi {

    private final ReviewService reviewService;

    @Override
    @GetMapping("/book/{bookId}")
    public List<ReviewDto> getReviewsByBookId(@PathVariable UUID bookId){
        return reviewService.getByBookId(bookId);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewDto createReview(@RequestBody @Valid ReviewCreateDto dto){
        return reviewService.create(dto);
    }
}
