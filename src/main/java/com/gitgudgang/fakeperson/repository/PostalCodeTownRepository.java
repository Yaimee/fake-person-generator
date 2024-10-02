package com.gitgudgang.fakeperson.repository;

import com.gitgudgang.fakeperson.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostalCodeTownRepository extends JpaRepository<Address, Long> {

}
