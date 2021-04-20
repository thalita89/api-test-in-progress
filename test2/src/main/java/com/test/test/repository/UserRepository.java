package com.test.test.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.test.test.model.User;

//improvements of SprintBoot (old dao)
//JPA - already has method implemented
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByCpf(Long cpf);

}
	