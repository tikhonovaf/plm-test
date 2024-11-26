package ru.openschool.aop.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.openschool.aop.backend.model.PlmTreeView;


public interface PlmTreeViewRepository extends JpaRepository<PlmTreeView, Long> {
}
