package com.bookbuddy.service;

import com.bookbuddy.dto.BookDto;
import com.bookbuddy.exception.notfound.BookNotFoundException;
import com.bookbuddy.mapper.BookMapper;
import com.bookbuddy.model.Book;
import com.bookbuddy.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto create(BookDto dto) {
        Book book = bookMapper.toEntity(dto);
        Book saved = bookRepository.save(book);
        return bookMapper.toDto(saved);
    }

    @Override
    public BookDto getById(UUID id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
        return bookMapper.toDto(book);
    }

    @Override
    public Page<BookDto> getAll(int page, int size, String sortBy) {
        return bookRepository.findAll(
                PageRequest.of(page,size, Sort.by(sortBy))
        ).map(bookMapper::toDto);
    }

    @Override
    public BookDto update(UUID id, BookDto dto) {

        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));

        existing.setTitle(dto.getTitle());
        existing.setAuthor(dto.getAuthor());
        existing.setPublishedYear(dto.getPublishedYear());
        existing.setGenre(dto.getGenre());

        Book updated = bookRepository.save(existing);

        return bookMapper.toDto(updated);
    }

    @Override
    public void delete(UUID id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        bookRepository.delete(book);
    }
}
