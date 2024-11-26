package ru.openschool.aop.backend.mapper;

import org.springframework.stereotype.Service;
import ru.openschool.aop.backend.dto.Plm;
import ru.openschool.aop.backend.model.PlmTreeView;
import ru.openschool.aop.backend.util.CoreUtil;

/**
 * Маппинг:
 * -  между entity и dto rest сервиса
 */
@Service
public class PlmMapper {

    /**
     *
     * Маппинг из entity в DTO
     *
     * @param view - строка из entity
     * @return Данные в структуре DTO
     */
    public Plm fromViewToDto(PlmTreeView view) {
        Plm result = new Plm();
        CoreUtil.patch(view, result);
        return result;
    }

}
