package com.studytips.services.impl;

import com.studytips.entities.Area;
import com.studytips.repositories.AreaRepository;
import com.studytips.services.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Created by comp-dev on 4/21/17.
 *
 */
@Service
@Transactional
public class AreaServiceImpl implements AreaService{

    @Autowired
    private AreaRepository areaRepository;

    @Override
    public Area save(Area entity) {
        return areaRepository.saveAndFlush(entity);
    }

    @Override
    public Area update(Area entity) {
        return areaRepository.saveAndFlush(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Area findById(Integer id) {
        return areaRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Area> findAll() {
        return areaRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        areaRepository.delete(id);
    }

    @Override
    public Page<Area> findAllPageable(Pageable pageable) {
        return areaRepository.findAll(pageable);
    }

}
