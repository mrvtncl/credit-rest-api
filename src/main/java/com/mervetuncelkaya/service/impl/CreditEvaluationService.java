package com.mervetuncelkaya.service.impl;

import com.mervetuncelkaya.dto.ErrorDTO;
import com.mervetuncelkaya.dto.ResponseDTO;
import com.mervetuncelkaya.entitiy.CreditApp;
import com.mervetuncelkaya.entitiy.CreditNotif;
import com.mervetuncelkaya.exception.SystemException;
import com.mervetuncelkaya.service.ICreditAppService;
import com.mervetuncelkaya.service.ICreditEvaluationService;
import com.mervetuncelkaya.service.ICreditNotifService;
import com.mervetuncelkaya.service.ICreditScoreService;
import com.mervetuncelkaya.util.Constants;
import com.mervetuncelkaya.util.Enums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("creditevaluationservice")
public class CreditEvaluationService implements ICreditEvaluationService {

    @Autowired
    private ICreditScoreService creditScoreService;

    @Autowired
    private ICreditAppService creditAppService;

    @Autowired
    private ICreditNotifService creditNotifService;

    @Autowired
    private ErrorService errorService;

    @Override
    public ResponseDTO getCreditEvaluationResult(CreditApp param) {
        try {
            double customerIncome = param.getIncome();

            /*
             *Kullanıcının TCKN bilgisi ile kredi skor servisi çağrılır ve kullanıcıya ait kredi skoru alınır.
             */
            int creditScore = creditScoreService.getCreditScore(param.getTckn());
            param.setCreditScore(creditScore);

            /*
             *Kullanıcının kredi skoruna göre değerlendirme yapılır.
             *Kredi skoru 500'ün altındaki başvurular reddedilir.
             */
            if (creditScore < Constants.MIN_CREDIT_SCORE) {
                param.setCreditLimit(Constants.MIN_CREDIT_LIMIT);
                param.setCreditStatus(Enums.CreditStatusType.REJECTED);
            } else if (creditScore >= Constants.MIN_CREDIT_SCORE && creditScore < Constants.AVG_CREDIT_SCORE) {
            /*
             *Kullanıcının kredi skoru 500 ile 1000 puan arasında ise aylık gelir dikkate alınır.
             *Aylık geliri 5000TL altındaki kullanıcıların kredileri onaylanır ve 10.000TL limit atanır.
             *
             **Eksiklikler**
             * ** 1 **
             * Kredi skorunda olduğu gibi aylık gelirde de min. aylık gelir belirtilmelidir.
             * Bu kurallara göre aylık geliri 0 olan kullanıcılara da limit ataması yapılıp kredileri onaylanmaktadır.             *
             * Bu durumu düzenlemek için öncesinde şu şekilde kontrol eklenebilirdi.
             * Min aylık gelir belirlenip bu tutarın altındaki krediler reddedilir.
             * if (customerIncome < Constants.MIN_INCOME) {
                param.setCreditLimit(Constants.MIN_CREDIT_LIMIT);
                param.setCreditStatus(Enums.CreditStatusType.REJECTED);
                }
             *
             * ** 2 **
             * Kredi skoru 500 ile 1000 puan arasında ve aylık geliri 5000TL üzerindeki kullanıcıların kredilerinin
             * durumları ve limitleriyle ilgili bir kontrol belirtilmemiştir.
             * Bu kurala göre aylık geliri 5000TL üzerinde olan kullanıcıların kredileri onaylanmıyor ve limit ataması yapılamıyor.
             * Bu durumu düzenlemek için şu şekilde kontrol eklenebilirdi.
             * Kredi çarpanı 500 - 1000 puan arasındaki kullanıcılar için 2 alınabilir.
             * if (customerIncome >= Constants.AVG_INCOME) {
                param.setCreditStatus(Enums.CreditStatusType.APPROVED);
                param.setCreditLimit(customerIncome * Constants.MIN_CREDIT_LIMIT_PARAMETER);
               }
             *
             */

                if (customerIncome < Constants.AVG_INCOME) {
                    param.setCreditStatus(Enums.CreditStatusType.APPROVED);
                    param.setCreditLimit(Constants.AVG_CREDIT_LIMIT);
                }

            } else {
            /*
             **Eksiklikler**
             * ** 3 **
             * Kredi skorunda olduğu gibi aylık gelirde de min. aylık gelir belirtilmelidir.
             * Bu kurallara göre aylık geliri 0 olan kullanıcıların kredileri onaylanır fakat onaylı limitleri 0 olmaktadır.
             * Bu durumu düzenlemek için aşağıdaki gibi bir kontrol eklenebilir.
             * Min aylık gelir belirlenip bu tutarın altındaki krediler reddedilir.
             * if (customerIncome < Constants.MIN_INCOME) {
                param.setCreditLimit(Constants.MIN_CREDIT_LIMIT);
                param.setCreditStatus(Enums.CreditStatusType.REJECTED);
               }
             */
                param.setCreditStatus(Enums.CreditStatusType.APPROVED);
                param.setCreditLimit(customerIncome * Constants.MAX_CREDIT_LIMIT_PARAMETER);
            }

            /*
             * Değerlendirme kontrolleri yapıldıktan sonra kullanıcının başvuru bilgileri veritabanına kaydedilir.
             * Ardından kullanıcıya SMS gönderilmesi için bilgiler SMS servisine gönderilir.
             */

            CreditApp creditAppRec = creditAppService.save(param);
            CreditNotif creditAppNotif = creditNotifService.buildCreditNotif(creditAppRec.getId(), creditNotifService.createNotifMessage(param));
            creditAppNotif = creditNotifService.save(creditAppNotif);

            return new ResponseDTO(creditAppRec.getId(), creditAppRec.getCreditStatus() == Enums.CreditStatusType.APPROVED ? "ONAYLANDI" : "REDDEDİLDİ", creditAppRec.getCreditLimit(), creditAppNotif.getMessage(),creditAppRec.getCreditScore());
        } catch (Exception ex) {
            throw new SystemException(ex);
        }
    }
}
