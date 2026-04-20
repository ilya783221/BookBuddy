package com.bookbuddy.dto;

import jakarta.validation.constraints.*;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {

    private UUID id;
    private String title;
    private String author;
    private Integer publishedYear;
    private String genre;
}
