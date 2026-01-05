package ma.fsts.AGEP_BTP.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import ma.fsts.AGEP_BTP.entity.Materiau;

import java.util.Optional;

public interface MateriauRepository extends JpaRepository<Materiau, Long> {

    Optional<Materiau> findByNom(String nom);
}

