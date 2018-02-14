package net.slipp.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserId(String userId); // userId로 조회를 하기위해서

}
