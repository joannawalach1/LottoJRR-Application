package com.javajuniorready.domain.numbergenerator;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class InMemoryWinningNumbersRepositoryImpl implements WinningNumbersRepository {
    private final List<WinningNumbers> winningNumbersDatabase = new ArrayList<>();



    @Override
    public Optional<WinningNumbers> findWinningNumbersByWinningNumbersDrawDate(LocalDateTime lottoDrawDate) {
        return Optional.ofNullable(winningNumbersDatabase.stream()
                .filter(winningNumber -> winningNumber.WinningNumbersDrawDate().isEqual(lottoDrawDate))
                .findFirst()
                .orElse(null));
    }



    @Override
    public <S extends WinningNumbers> S save(S entity) {
            if (entity == null) {
                throw new IllegalArgumentException("Winning numbers cannot be null");
            }
            winningNumbersDatabase.add(entity);
            return entity;
    }

    @Override
    public <S extends WinningNumbers> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<WinningNumbers> findById(ObjectId objectId) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(ObjectId objectId) {
        return false;
    }

    @Override
    public List<WinningNumbers> findAll() {
        return List.of();
    }

    @Override
    public Iterable<WinningNumbers> findAllById(Iterable<ObjectId> objectIds) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(ObjectId objectId) {

    }

    @Override
    public void delete(WinningNumbers entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends ObjectId> objectIds) {

    }

    @Override
    public void deleteAll(Iterable<? extends WinningNumbers> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<WinningNumbers> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<WinningNumbers> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends WinningNumbers> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends WinningNumbers> List<S> insert(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public <S extends WinningNumbers> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends WinningNumbers> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends WinningNumbers> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends WinningNumbers> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends WinningNumbers> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends WinningNumbers> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends WinningNumbers, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
};

