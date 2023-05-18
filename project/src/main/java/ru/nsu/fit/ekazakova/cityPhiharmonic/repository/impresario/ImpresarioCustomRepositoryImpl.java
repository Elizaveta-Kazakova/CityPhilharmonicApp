package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.impresario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Impresario;

import java.util.List;

@Repository
public class ImpresarioCustomRepositoryImpl implements ImpresarioCustomRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Impresario> findImpresariosByArtist(String artist) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Impresario> criteriaQuery = criteriaBuilder.createQuery(Impresario.class);

        Root<Impresario> impresarioRoot = criteriaQuery.from(Impresario.class);

        criteriaQuery
                .select(
                        impresarioRoot.join("artist"))
                .where(
                        criteriaBuilder.equal(impresarioRoot.join("artist").get("name"), artist));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Impresario> findImpresarioByGenre(String genre) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Impresario> criteriaQuery = criteriaBuilder.createQuery(Impresario.class);

        Root<Impresario> impresarioRoot = criteriaQuery.from(Impresario.class);

        criteriaQuery
                .select(
                        impresarioRoot.join("genre"))
                .where(
                        criteriaBuilder.equal(impresarioRoot.join("genre").get("name"), genre));

        return null;
    }
}
