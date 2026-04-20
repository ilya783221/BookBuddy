package com.bookbuddy.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCreateDto {

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title too long")
    private String title;

    @NotBlank(message = "Author is required")
    @Size(max = 255, message = "Author too long")
    private String author;

    @NotNull(message = "Published year is required")
    @Min(value = 1000, message = "Year must be after 1000")
    @Max(value = 2026, message = "Year must be realistic")
    private Integer publishedYear;

    @Size(max = 50, message = "Genre too long")
    private String genre;
}
