package com.bookbuddy.service;

import com.bookbuddy.dto.ReviewDto;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

    ReviewDto create(ReviewDto dto);

    List<ReviewDto> getBookId(UUID bookId);
}
