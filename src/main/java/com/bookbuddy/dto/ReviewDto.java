package com.bookbuddy.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {

    private UUID id;
    private UUID userId;
    private UUID bookId;
    private Integer rating;
    private String comment;
}
