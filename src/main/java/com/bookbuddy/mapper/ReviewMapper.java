package com.bookbuddy.mapper;

import com.bookbuddy.dto.ReviewDto;
import com.bookbuddy.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "book.id", target = "bookId")
    ReviewDto toDto(Review review);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "book", ignore = true)
    Review toEntity(ReviewDto dto);
}

