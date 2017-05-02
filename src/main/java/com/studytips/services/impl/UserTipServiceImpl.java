package com.studytips.services.impl;

import com.studytips.entities.UserTip;
import com.studytips.repositories.UserTipRepository;
import com.studytips.services.UserTipService;
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
public class UserTipServiceImpl implements UserTipService{

    @Autowired
    private UserTipRepository userTipRepository;

    @Override
    public UserTip save(UserTip entity) {
        return userTipRepository.saveAndFlush(entity);
    }

    @Override
    public UserTip update(UserTip entity) {
        return userTipRepository.saveAndFlush(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public UserTip findById(Integer id) {
        return userTipRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserTip> findAll() {
        return userTipRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        userTipRepository.delete(id);
    }

    @Override
    public Page<UserTip> findAllPageable(Pageable pageable) {
        return userTipRepository.findAll(pageable);
    }
}
