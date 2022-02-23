package tn.Pi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.Pi.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
