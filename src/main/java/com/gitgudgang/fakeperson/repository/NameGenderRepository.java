package com.gitgudgang.fakeperson.repository;

import com.gitgudgang.fakeperson.entity.NameGender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NameGenderRepository extends JpaRepository<NameGender, UUID> {

    @Query("SELECT ng.id FROM NameGender ng")
    List<UUID> getAllUUIDs();
}
