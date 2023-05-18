package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.culturalBuilding;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.Organizer;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.cultural_building.CulturalBuilding;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CulturalBuildingCustomRepositoryImpl implements CulturalBuildingCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CulturalBuilding> findCulturalBuildingByType(String tableName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CulturalBuilding> criteriaQuery = criteriaBuilder.createQuery(CulturalBuilding.class);

        Root<CulturalBuilding> culBuildingRoot = criteriaQuery.from(CulturalBuilding.class);

        criteriaQuery.select(culBuildingRoot.join(tableName, JoinType.RIGHT));

        return entityManager.createQuery(criteriaQuery).getResultList();

    }

    @Override
    public List<CulturalBuilding> findCulturalBuildingByNumOfSeats(Integer numOfSeats) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CulturalBuilding> criteriaQuery = criteriaBuilder.createQuery(CulturalBuilding.class);

        Root<CulturalBuilding> culBuildingRoot = criteriaQuery.from(CulturalBuilding.class);

        criteriaQuery.select(culBuildingRoot).where(criteriaBuilder.ge(culBuildingRoot.get("numOfSeats"), numOfSeats));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<CulturalBuildingDetails> findCulturalBuildingsInPeriod(LocalDate startDate, LocalDate endDate) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CulturalBuildingDetails> criteriaQuery = criteriaBuilder.createQuery(CulturalBuildingDetails.class);

        Root<CulturalBuilding> culturalBuildingRoot = criteriaQuery.from(CulturalBuilding.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder
                .greaterThan(culturalBuildingRoot.join("event", JoinType.INNER)
                        .get("date"), startDate));
        predicates.add(criteriaBuilder
                .lessThan(culturalBuildingRoot.join("event", JoinType.INNER)
                        .get("date"), endDate));

        criteriaQuery.multiselect(
                        culturalBuildingRoot, culturalBuildingRoot.join("event").get("date"))
                .where(predicates.toArray(new Predicate[]{}))
                .groupBy(culturalBuildingRoot);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }


}
