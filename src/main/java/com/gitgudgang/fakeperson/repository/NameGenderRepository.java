package com.gitgudgang.fakeperson.repository;

import com.gitgudgang.fakeperson.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface NameGenderRepository extends JpaRepository<Person, UUID>  {
}
