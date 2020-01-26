package by.javatr.bicrent.service;
import by.javatr.bicrent.entity.VirtualCard;

import java.math.BigDecimal;
import java.util.List;

public interface VirtualCardService {

    Integer save(VirtualCard virtualCard) throws ServiceException;

    List<VirtualCard>  findByUserId(Integer userId);

    List<VirtualCard>  findByUserPassportId(String userPassportId) throws ServiceException;

    BigDecimal readBalance(String cardId);

    VirtualCard read(Integer id);

    Boolean topUp(Integer cardId, BigDecimal ammount);
}
