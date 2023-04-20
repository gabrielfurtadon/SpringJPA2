package br.com.Gabriel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.Gabriel.entities.Payment;


public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
