package com.studytips.repositories;

import com.studytips.entities.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by comp-dev on 4/21/17.
 */

@Repository
public interface AreaRepository extends JpaRepository<Area, Integer> {
}
