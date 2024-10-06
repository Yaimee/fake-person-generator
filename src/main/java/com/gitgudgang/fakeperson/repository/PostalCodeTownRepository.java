package com.gitgudgang.fakeperson.repository;

import com.gitgudgang.fakeperson.entity.PostalCodeTown;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostalCodeTownRepository extends JpaRepository<PostalCodeTown, UUID> {
    @Query("SELECT pct.id FROM PostalCodeTown pct")
    List<UUID> getAllUUIDs();
}
