package com.javajuniorready.domain.resultchecker;

import com.javajuniorready.domain.resultchecker.dto.ResultDto;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class InMemoryPlayerRepositoryImpl implements PlayerRepository<Player, Integer> {
    private final List<Player> playersDatabase = new ArrayList<>();

    @Override
    public <S extends Player> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Player> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<Player> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<Player> findAll() {
        return List.of();
    }

    @Override
    public Iterable<Player> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Player entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Player> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Player> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Player> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Player> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Player> List<S> insert(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public <S extends Player> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Player> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Player> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Player> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Player> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Player> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Player, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public Optional<Player> findById(String ticketId) {
        return playersDatabase.stream().findFirst();
    }
}
