package com.studytips.services.impl;

import com.studytips.entities.WebTip;
import com.studytips.repositories.WebTipRepository;
import com.studytips.services.WebTipService;
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
public class WebTipServiceImpl implements WebTipService{

    @Autowired
    private WebTipRepository webTipRepository;

    @Override
    public WebTip save(WebTip entity) {
        return webTipRepository.saveAndFlush(entity);
    }

    @Override
    public WebTip update(WebTip entity) {
        return webTipRepository.saveAndFlush(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public WebTip findById(Integer id) {
        return webTipRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WebTip> findAll() {
        return webTipRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        webTipRepository.delete(id);
    }

    @Override
    public Page<WebTip> findAllPageable(Pageable pageable) {
        return webTipRepository.findAll(pageable);
    }
}
