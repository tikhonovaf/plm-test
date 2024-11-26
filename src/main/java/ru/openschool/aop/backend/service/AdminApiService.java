package ru.openschool.aop.backend.service;//package ru.openschool.aspect.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.openschool.aop.backend.dto.Plm;
import ru.openschool.aop.backend.mapper.PlmMapper;
import ru.openschool.aop.backend.model.PlmTreeView;
import ru.openschool.aop.backend.repository.PlmTreeViewRepository;
import ru.openschool.aop.backend.api.AdminApiDelegate;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
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
        List<PlmTreeView> treeViewList = plmTreeViewRepository.findAll();
        ListIterator<PlmTreeView> listIterator = treeViewList.listIterator();
        Plm tree = new Plm();
        tree.setName("Справочник");
        tree.setLevel(0l);
        tree.setClassName("Корень");

        List<Plm> children = new ArrayList<>();
        Long curLevel = 1L;

        buildTree(treeViewList, listIterator, null, tree, null, 0l);
        List<Plm> plmList = new ArrayList<>();
        plmList.add(tree);

        return ResponseEntity.ok(plmList);
    }

    private void buildTree(List<PlmTreeView> treeViewList, ListIterator<PlmTreeView> listIterator, Plm nodeUp, Plm node, PlmTreeView curElement, Long curLevel) {
        List<Plm> children = new ArrayList<>();
        if (curElement != null ) {
            children.add(plmMapper.fromViewToDto(curElement));
        }
        while (listIterator.hasNext()) {
            PlmTreeView nextElement = (PlmTreeView) listIterator.next();
            if (nextElement.getLevel().equals(curLevel)) {
                children.add(plmMapper.fromViewToDto(nextElement));
            } else if (nextElement.getLevel() > curLevel) {
                Plm prevNode = node;
                if (listIterator.hasPrevious()) {
                    listIterator.previous();
                    if (listIterator.hasPrevious()) {
                        prevNode = plmMapper.fromViewToDto((PlmTreeView) listIterator.previous());
                    }
                }
                listIterator.next();
                buildTree(treeViewList, listIterator,  prevNode, prevNode, nextElement, nextElement.getLevel());
                if (children.size() > 0 )  {
                    children.remove(children.size()-1);
                }
                children.add(prevNode);
            } else if (nextElement.getLevel() < curLevel) {
                break;
            }
        }
        if (nodeUp != null) {
            nodeUp.setChildren(children);
        }
    }
}


