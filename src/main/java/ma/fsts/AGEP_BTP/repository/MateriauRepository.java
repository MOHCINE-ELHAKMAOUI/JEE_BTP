package ma.fsts.agep_btp.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import ma.fsts.agep_btp.entity.Materiau;

import java.util.Optional;

public interface MateriauRepository extends JpaRepository<Materiau, Long> {

    Optional<Materiau> findByNom(String nom);
}

