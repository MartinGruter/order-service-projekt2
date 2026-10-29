package se.iths.martin.orderserviceprojekt2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.martin.orderserviceprojekt2.model.Order;

/**
 * Databasåtkomst för order via Spring Data JPA:s standardoperationer.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
