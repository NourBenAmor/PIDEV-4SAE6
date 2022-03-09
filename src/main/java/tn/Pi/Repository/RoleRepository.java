package tn.Pi.Repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.Pi.entities.Role;
import tn.Pi.entities.UserRole;



@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
	Optional<Role> findByName(UserRole name);
}
