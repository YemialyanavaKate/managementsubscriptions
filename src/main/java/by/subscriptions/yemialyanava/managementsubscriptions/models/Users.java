package by.subscriptions.yemialyanava.managementsubscriptions.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Name is a required field")
    private String name;
    @NotNull(message = "Email is a required field")
    @Email(message = "Email should be valid")
    private String email;
    private LocalDateTime created;
    private Boolean updated;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "users_subscriptions",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "subscriptions_id")
    )
    @ToString.Exclude
    private List<Subscriptions> subscriptions;

}
