package hello.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.rulesProcessor.contracts.TransactionStrategy;
import hello.rulesProcessor.enums.Operator;
import hello.rulesProcessor.models.RulesAdapter;
import hello.rulesProcessor.models.TransactionAmountRule;
import hello.rulesProcessor.models.TransactionalMasterStrategy;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krishna on 6/11/2016.
 */
@RestController
@RequestMapping("rules")
public class RulesController {

    @Autowired
    RulesAdapter rulesAdapter;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public ResponseEntity<List<TransactionalMasterStrategy>> getAllRules() throws JsonProcessingException {
        return new ResponseEntity<>(rulesAdapter.getAll(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addRule(@RequestBody TransactionalMasterStrategy strategy) {
        rulesAdapter.addRule(strategy);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/remove", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeRule(@RequestBody TransactionalMasterStrategy strategy) {
        rulesAdapter.deleteRule(strategy.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateRule(@RequestBody TransactionalMasterStrategy strategy) {
        rulesAdapter.updateRule(strategy);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
