package br.com.system.modulos.log;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRepository extends JpaRepository<Log, Long> {
    List<Log> findAllByOrderByVezesDesc();
}
