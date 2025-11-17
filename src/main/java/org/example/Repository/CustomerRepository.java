package org.example.Repository;

import org.example.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    // You can define custom queries if needed
    // Example:
    // Optional<Customer> findByEmail(String email);
}
