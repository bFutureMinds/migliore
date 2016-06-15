package hello.controllers;

import hello.models.CustomerTransaction;
import hello.services.RulesProcessorService;
import hello.services.SystemGeneratedEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Krishna on 6/11/2016.
 */
@RestController()
@RequestMapping("transaction")
public class TransactionController {
    @Autowired
    private RulesProcessorService rulesProcessorService;

    @Autowired
    private SystemGeneratedEvents systemGeneratedEvents;

    @RequestMapping(value = "/debit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postTransactions(@RequestBody CustomerTransaction transaction) {
        rulesProcessorService.processRules(transaction);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity getStatus(){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
