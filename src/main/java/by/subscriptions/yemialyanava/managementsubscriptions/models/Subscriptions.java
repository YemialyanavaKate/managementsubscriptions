package by.subscriptions.yemialyanava.managementsubscriptions.models;

import by.subscriptions.yemialyanava.managementsubscriptions.dto.SubscriptionsSumDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SqlResultSetMapping(
        name = "SubscriptionsSumMapping",
        classes = @ConstructorResult(
                targetClass = SubscriptionsSumDto.class,
                columns = {
                        @ColumnResult(name = "service", type = String.class),
                        @ColumnResult(name = "frequency", type = Integer.class)
                }
        )
)
public class Subscriptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Service is a required field")
    private String service;
    @ManyToMany(mappedBy = "subscriptions", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @ToString.Exclude
    private List<Users> users;
}
