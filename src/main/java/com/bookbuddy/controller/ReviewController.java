package com.bookbuddy.controller;

import com.bookbuddy.dto.ReviewDto;
import com.bookbuddy.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("book/{bookId}")
    public List<ReviewDto> getReviewsByBookId(@PathVariable UUID bookId){
        return reviewService.getBookId(bookId);
    }

    @PostMapping
    public ReviewDto createReview(@RequestBody @Valid ReviewDto dto){
        return reviewService.create(dto);
    }
}
