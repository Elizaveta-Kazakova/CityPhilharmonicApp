package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.artist;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Artist;

import java.util.List;

@Repository
public class ArtistCustomRepositoryImpl implements ArtistCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Artist> findArtistsByGenre(String genre) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Artist> criteriaQuery = criteriaBuilder.createQuery(Artist.class);

        Root<Artist> artist = criteriaQuery.from(Artist.class);

        criteriaQuery
                .select(
                        artist.join("genre"))
                .where(
                        criteriaBuilder.equal(artist.join("genre").get("name"), genre));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Artist> findArtistsByImpresario(String impresario) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Artist> criteriaQuery = criteriaBuilder.createQuery(Artist.class);

        Root<Artist> artist = criteriaQuery.from(Artist.class);

        criteriaQuery
                .select(
                        artist.join("impresario"))
                .where(
                        criteriaBuilder.equal(artist.join("impresario"), impresario));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
