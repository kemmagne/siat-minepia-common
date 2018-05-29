package org.guce.epayment.rest.controllers.jwt;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import org.guce.epayment.rest.dto.DefaultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tadzotsa
 */
@RestController
@RequestMapping("jwt")
public class ComputingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputingController.class);

    @ResponseBody
    @RequestMapping(path = "public/computing/amount/{actualAmount}/{newAmount}/{operation}", method = RequestMethod.GET)
    public ResponseEntity<DefaultDto> getUpdatedAmount(@PathVariable("actualAmount") BigDecimal actualAmount,
            @PathVariable("newAmount") BigDecimal newAmount, @PathVariable("operation") String operation) {

        try {

            final Method method = BigDecimal.class.getMethod(operation, BigDecimal.class);
            final Object result = method.invoke(actualAmount, newAmount);

            return ResponseEntity.ok(DefaultDto.of(result.toString()));
        } catch (Exception ex) {
            LOGGER.error(null, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
