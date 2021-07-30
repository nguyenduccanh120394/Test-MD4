package com.codegym.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GeneralService<T> {
    Page<T> findAll(Pageable pageable);
    Iterable<T> findAll();
    Optional<T> fidById(Long id);
    void save(T t);
    void remove(Long id);
    Iterable<T>findByName(String name);


}