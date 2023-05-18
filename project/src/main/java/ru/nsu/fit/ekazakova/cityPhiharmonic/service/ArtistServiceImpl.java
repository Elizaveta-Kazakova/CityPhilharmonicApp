package ru.nsu.fit.ekazakova.cityPhiharmonic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.ArtistDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.exception.ArtistNotFoundException;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.artist.ArtistRepository;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.GenreRepository;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.impresario.ImpresarioRepository;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Artist;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Genre;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Impresario;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;
    private final GenreRepository genreRepository;
    private final ImpresarioRepository impresarioRepository;

    private ArtistDto toDto(Artist artist) {
        return new ArtistDto(artist.getName(),
                artist.getImpresarios().stream().map(Impresario::getName).toList(),
                artist.getGenres().stream().map(Genre::getName).toList());
    }

    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository,
                             GenreRepository genreRepository, ImpresarioRepository impresarioRepository) {
        this.artistRepository = artistRepository;
        this.genreRepository = genreRepository;
        this.impresarioRepository = impresarioRepository;
    }

    @Override
    @Transactional
    public void createArtist(ArtistDto artistDto) {
        final Artist artist = new Artist();
        artist.setName(artistDto.getName());
        artist.setGenres(artistDto.getGenres()
                .stream()
                .map(genreRepository::findGenreByName)
                .toList());
        artist.setImpresarios(artistDto.getImpresarios()
                .stream()
                .map(impresarioRepository::findImpresarioByName)
                .toList());
        artistRepository.save(artist);
    }

    @Override
    @Transactional
    public void updateArtist(ArtistDto artistDto, Long id) {
        Artist previousArtist = artistRepository.findById(id).orElseThrow(
                () -> new ArtistNotFoundException("artist with id = " + id + " not found"));

//        artistRepository.deleteAllById(artistDto.getId());

        previousArtist.setName(artistDto.getName());
        previousArtist.setGenres(artistDto.getGenres()
                .stream()
                .map(genreRepository::findGenreByName)
                .toList());
        previousArtist.setImpresarios(artistDto.getImpresarios()
                .stream()
                .map(impresarioRepository::findImpresarioByName)
                .toList());
        artistRepository.save(previousArtist);
    }

    @Override
    @Transactional
    public ArtistDto findArtistById(Long id) {
        return artistRepository.findById(id).map(this::toDto).orElseThrow(() ->
                new ArtistNotFoundException("artist with id = " + id + " not found"));
    }

    @Override
    @Transactional
    public List<ArtistDto> findArtistsByGenre(String genre) {
        return artistRepository.findArtistsByGenre(genre).stream().map(this::toDto).toList();
    }

    @Override
    @Transactional
    public List<ArtistDto> findArtistsByImpresario(String impresario) {
        return artistRepository.findArtistsByImpresario(impresario).stream().map(this::toDto).toList();
    }

    @Override
    @Transactional
    public List<ArtistDto> findArtistsWithMultiplyGenres() {
        return artistRepository.findArtistsWithMultiplyGenres().stream().map(this::toDto).toList();
    }

    @Override
    @Transactional
    public List<ArtistDto> findArtistsNotParticipatedInCompetitions() {
        return artistRepository.findArtistsNotParticipatedInCompetitions().stream().map(this::toDto).toList();
    }


}
