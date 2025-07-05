package org.nofre.api.base.common.crud.repository;

import org.nofre.api.base.observability.annotation.ObservedRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
@ObservedRepository
public interface CommonRepository<E extends CommonEntity> extends JpaRepository<E, Long>,
        JpaSpecificationExecutor<E> {

    List<E> findAllByActiveIsTrue();

    List<E> findAllByActiveIsFalse();

    List<E> findAllByCreatedBy(String createdBy);

    List<E> findAllByUpdatedBy(String updatedBy);

    List<E> findAllByActiveIsTrueAndCreatedBy(String createdBy);

    List<E> findAllByActiveIsTrueAndUpdatedBy(String updatedBy);

    List<E> findAllByActiveIsFalseAndCreatedBy(String createdBy);

    List<E> findAllByActiveIsFalseAndUpdatedBy(String updatedBy);

}

