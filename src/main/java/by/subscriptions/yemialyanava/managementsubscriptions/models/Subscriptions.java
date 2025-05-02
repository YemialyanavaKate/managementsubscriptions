package by.subscriptions.yemialyanava.managementsubscriptions.models;

import by.subscriptions.yemialyanava.managementsubscriptions.dto.SubscriptionsSumDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy
                ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
                : o.getClass();
        Class<?> thisEfectiveClass = this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
                : this.getClass();
        if (thisEfectiveClass != oEffectiveClass){
            return false;
        }
        Subscriptions that = (Subscriptions) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "(" +
                "id=" + id +
                ")" ;
    }
}
