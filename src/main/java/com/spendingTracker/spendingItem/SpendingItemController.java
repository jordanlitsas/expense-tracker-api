package com.spendingTracker.spendingItem;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SpendingItemController {

    private final SpendingItemRepository repository;
    private static final Logger log = LoggerFactory.getLogger(SpendingItemController.class);

    SpendingItemController(SpendingItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/spend/{userId}")
    @CrossOrigin(origins = {"https://expense-tracker-mobile.herokuapp.com/", "http://localhost:3000"})
    List<SpendingItem> withUserId(@PathVariable String userId) {
        List<SpendingItem> expenses = repository.findAll();
        List<SpendingItem> returnExpenses = new ArrayList<SpendingItem>();
        for (int i = 0; i < expenses.size(); i++){
            if (expenses.get(i).getUserId() == Long.parseLong(userId)){
                returnExpenses.add(expenses.get(i));
            }
        }
        return returnExpenses;
    }

    @GetMapping("/spend")
    @CrossOrigin(origins = {"https://expense-tracker-mobile.herokuapp.com/", "http://localhost:3000"})
    List<SpendingItem> all() {
        return repository.findAll();
    }
    @PostMapping(path = "/spend", consumes = "application/json")
    @CrossOrigin(origins = {"https://expense-tracker-mobile.herokuapp.com/", "http://localhost:3000"})
    SpendingItem spendingItem(@RequestBody SpendingItem spendingItem) {
        log.info(spendingItem.toString());
        return repository.save(spendingItem);
    }

}
