package com.javajuniorready.domain.numberreceiver;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class InMemoryTicketRepositoryImpl implements NumberTicketRepository {

    private final List<Ticket> ticketsDatabase = new ArrayList<>();


    @Override
    public Optional<Ticket> findTicketById(ObjectId id) {
        return ticketsDatabase.stream()
                .filter(ticket -> ticket.id().equals(id))
                .findFirst();
    }

    @Override
    public <S extends Ticket> S save(S entity) {
        ticketsDatabase.add(entity);
        return entity;
    }

    @Override
    public <S extends Ticket> List<S> saveAll(Iterable<S> entities) {
        List<S> savedEntities = new ArrayList<>();

        for (S entity : entities) {
            savedEntities.add(entity);
        }

        return savedEntities;
    }

    @Override
    public Optional<Ticket> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public Optional<Ticket> findById(String id) {
        return ticketsDatabase.stream().findFirst();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<Ticket> findAll() {
        return ticketsDatabase;
    }

    @Override
    public Iterable<Ticket> findAllById(Iterable<Integer> integers) {
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
    public void delete(Ticket entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Ticket> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Ticket> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Ticket> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Ticket> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Ticket> List<S> insert(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public <S extends Ticket> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Ticket> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Ticket> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Ticket> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Ticket> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Ticket> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Ticket, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
};
