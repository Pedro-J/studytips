package com.studytips.services.impl;

import com.studytips.entities.BookTip;
import com.studytips.repositories.BookTipRepository;
import com.studytips.services.BookTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by comp-dev on 4/21/17.
 */

@Service
@Transactional
public class BookTipServiceImpl implements BookTipService{

    @Autowired
    private BookTipRepository bookTipRepository;

    @Override
    public BookTip save(BookTip entity) {
        return bookTipRepository.saveAndFlush(entity);
    }

    @Override
    public BookTip update(BookTip entity) {
        return bookTipRepository.saveAndFlush(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public BookTip findById(Integer id) {
        return findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookTip> findAll() {
        return findAll();
    }

    @Override
    public void delete(Integer id) {
        bookTipRepository.delete(id);
    }

    @Override
    public Page<BookTip> findAllPageable(Pageable pageable) {
        return bookTipRepository.findAll(pageable);
    }
}
