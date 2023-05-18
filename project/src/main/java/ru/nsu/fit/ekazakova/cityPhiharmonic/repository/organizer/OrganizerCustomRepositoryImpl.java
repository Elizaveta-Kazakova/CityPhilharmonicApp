package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.organizer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.Organizer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrganizerCustomRepositoryImpl implements OrganizerCustomRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<OrganizerDetails> findOrganizersInPeriod(LocalDate startDate, LocalDate endDate) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrganizerDetails> criteriaQuery = criteriaBuilder.createQuery(OrganizerDetails.class);

        Root<Organizer> organizerRoot = criteriaQuery.from(Organizer.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder
                .greaterThan(organizerRoot.join("event", JoinType.INNER)
                        .get("date"), startDate));
        predicates.add(criteriaBuilder
                .lessThan(organizerRoot.join("event", JoinType.INNER)
                        .get("date"), endDate));

        criteriaQuery.multiselect(
                organizerRoot, criteriaBuilder.count(organizerRoot.join("event")))
                .where(predicates.toArray(new Predicate[]{}))
                .groupBy(organizerRoot);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
