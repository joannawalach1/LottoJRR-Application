package com.javajuniorready.numbergenerator;

import com.javajuniorready.domain.numbergenerator.WinningNumbers;
import com.javajuniorready.domain.numbergenerator.WinningNumbersRepository;
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
    public WinningNumbers saveWinningNumbers(WinningNumbers winningNumbers) {
        winningNumbersDatabase.add(winningNumbers);
        return winningNumbers;
    }

    @Override
    public <S extends WinningNumbers> S save(S entity) {
        return null;
    }

    @Override
    public <S extends WinningNumbers> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<WinningNumbers> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<WinningNumbers> findAll() {
        return winningNumbersDatabase;
    }

    @Override
    public List<WinningNumbers> findAllById(Iterable<Integer> integers) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(WinningNumbers entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends WinningNumbers> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Optional<WinningNumbers> findWinningNumbersByWinningNumbersDrawDate(LocalDateTime lottoDrawDate) {
        return Optional.ofNullable(winningNumbersDatabase.stream()
                .filter(winningNumber -> winningNumber.WinningNumbersDrawDate().isEqual(lottoDrawDate))
                .findFirst()
                .orElse(null));
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

@Override
public List<WinningNumbers> findAll(Sort sort) {
    return List.of();
}

@Override
public Page<WinningNumbers> findAll(Pageable pageable) {
    return null;
}
    };

