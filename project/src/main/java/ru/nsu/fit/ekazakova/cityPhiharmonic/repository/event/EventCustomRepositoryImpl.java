package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.event;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.Event;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EventCustomRepositoryImpl implements EventCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Event> findEventInPeriod(LocalDate startDate, LocalDate endDate) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Event> criteriaQuery = criteriaBuilder.createQuery(Event.class);

        Root<Event> eventRoot = criteriaQuery.from(Event.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.greaterThan(eventRoot.get("date"), startDate));
        predicates.add(criteriaBuilder.lessThan(eventRoot.get("date"), endDate));

        criteriaQuery.select(eventRoot).where(predicates.toArray(new Predicate[]{}));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Event> findEventInPeriodByOrganizer(LocalDate startDate, LocalDate endDate, String organizer) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Event> criteriaQuery = criteriaBuilder.createQuery(Event.class);

        Root<Event> eventRoot = criteriaQuery.from(Event.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.greaterThan(eventRoot.get("date"), startDate));
        predicates.add(criteriaBuilder.lessThan(eventRoot.get("date"), endDate));
        predicates.add(criteriaBuilder.equal(eventRoot.get("organizer").get("name"), organizer));

        criteriaQuery.select(eventRoot).where(predicates.toArray(new Predicate[]{}));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Event> findEventByCulturalBuilding(String culturalBuilding) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Event> criteriaQuery = criteriaBuilder.createQuery(Event.class);

        Root<Event> eventRoot = criteriaQuery.from(Event.class);

        criteriaQuery.select(eventRoot).where(criteriaBuilder.equal(
                eventRoot.get("culturalBuilding"), culturalBuilding));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
