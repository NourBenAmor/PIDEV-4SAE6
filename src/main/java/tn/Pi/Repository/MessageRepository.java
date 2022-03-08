package tn.Pi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.Pi.entities.Message;



public interface MessageRepository extends JpaRepository<Message, Long> {

}
