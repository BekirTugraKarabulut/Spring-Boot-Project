package com.tugra.repository;

import com.tugra.model.Sepet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SepetRepository extends JpaRepository<Sepet,Long> {

}
