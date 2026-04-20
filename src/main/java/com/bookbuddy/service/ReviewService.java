package com.bookbuddy.service;

import com.bookbuddy.dto.ReviewCreateDto;
import com.bookbuddy.dto.ReviewDto;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

    ReviewDto create(ReviewCreateDto dto);

    List<ReviewDto> getByBookId(UUID bookId);
}
