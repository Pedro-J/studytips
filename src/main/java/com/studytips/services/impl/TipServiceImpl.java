package com.studytips.services.impl;

import com.studytips.entities.Tip;
import com.studytips.repositories.TipRepository;
import com.studytips.services.TipService;
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
public class TipServiceImpl implements TipService{

    @Autowired
    private TipRepository tipRepository;

    @Override
    public Tip save(Tip entity) {
        return tipRepository.saveAndFlush(entity);
    }

    @Override
    public Tip update(Tip entity) {
        return tipRepository.saveAndFlush(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Tip findById(Integer id) {
        return tipRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tip> findAll() {
        return tipRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        tipRepository.delete(id);
    }

    @Override
    public Page<Tip> findAllPageable(Pageable pageable) {
        return tipRepository.findAll(pageable);
    }
}
