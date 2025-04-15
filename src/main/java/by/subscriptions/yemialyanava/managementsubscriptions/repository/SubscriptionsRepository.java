package by.subscriptions.yemialyanava.managementsubscriptions.repository;

import by.subscriptions.yemialyanava.managementsubscriptions.dto.SubscriptionsSumDto;
import by.subscriptions.yemialyanava.managementsubscriptions.models.Subscriptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionsRepository extends JpaRepository<Subscriptions, Integer> {

    @Query(value = "SELECT s.service AS service, COUNT(s.service) AS frequency " +
            "FROM Subscriptions s " +
            "GROUP BY s.service " +
            "ORDER BY frequency DESC " +
            "LIMIT 3", nativeQuery = true)
    List<SubscriptionsSumDto> findTop3();

    List<Subscriptions> findByUsers_Id(Integer id);

}
