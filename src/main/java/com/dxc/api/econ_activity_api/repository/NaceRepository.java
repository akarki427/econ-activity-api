package com.dxc.api.econ_activity_api.repository;

import com.dxc.api.econ_activity_api.entity.Nace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NaceRepository extends JpaRepository<Nace, Long> {
    List<Nace> findAllByCode(String code);
}
