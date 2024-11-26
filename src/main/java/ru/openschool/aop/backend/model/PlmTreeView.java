package ru.openschool.aop.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
// import javax.validation.constraints.NotNull;

/**
 * Created by Tikhonov Arkadiy
 */
@Entity
@Getter
@Setter
public class PlmTreeView {

    /**
     * Идентификатор
     */
    @Id
    Long sPk;

    /**
     * Идентификатор вышестоящего узла
     */
    Long sParentid;

    /**
     * Наименование
     */
    String name;

    /**
     * Описание
     */
    String description ;

    /**
     * Наименование класса
     */
    String className;

    /**
     * Уровень вложенности узла
     */
    Long level;

    /**
     * Полный путь
     */
    String path;

}
