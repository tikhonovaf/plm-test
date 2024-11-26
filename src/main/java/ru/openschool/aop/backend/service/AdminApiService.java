package ru.openschool.aop.backend.service;//package ru.openschool.aspect.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.openschool.aop.backend.dto.Plm;
import ru.openschool.aop.backend.mapper.PlmMapper;
import ru.openschool.aop.backend.repository.PlmTreeViewRepository;
import ru.openschool.aop.backend.api.AdminApiDelegate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для выполнения функций rest сервисов (GET, POST, PATCH, DELETE)
 *
 * @author Аркадий Тихонов
 */
@Service
@RequiredArgsConstructor
public class AdminApiService implements AdminApiDelegate {
    private final PlmTreeViewRepository plmTreeViewRepository;
    private final PlmMapper plmMapper;


//    Справочники

    /**
     * Список ролей
     */
    @Override
    public ResponseEntity<List<Plm>> getPlmTree() {
        List<Plm> result =
                plmTreeViewRepository
                        .findAll()
                        .stream()
                        .map(plmMapper::fromViewToDto)
                        .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

}

