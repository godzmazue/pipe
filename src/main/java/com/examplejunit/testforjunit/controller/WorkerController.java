package com.examplejunit.testforjunit.controller;

import com.examplejunit.testforjunit.entity.Worker;
import com.examplejunit.testforjunit.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class WorkerController {
    @Autowired
    WorkerService workerService;

    @GetMapping("/getAllWorkers")
    public ResponseEntity<List<Worker>>getAllWorkers(){

        return new ResponseEntity<>(workerService.getAll(), HttpStatus.OK);

    }
    @GetMapping("/getAllWorkers/{workerSalary}")
    public ResponseEntity<List<Worker>>getWorkersBySalary(@PathVariable Double workerSalary){

        return new ResponseEntity<>(workerService.getBySalary(workerSalary), HttpStatus.OK);

    }

    @GetMapping("/getWorker/{workerID}")
    public ResponseEntity<Worker>getById(@PathVariable Integer workerID){

        return  new ResponseEntity<>(workerService.getById(workerID),HttpStatus.OK);

    }
    @PostMapping("/addWorker")
    public  ResponseEntity<String>addWorker(@RequestBody Worker worker){
        return new ResponseEntity<>(workerService.upsert(worker),HttpStatus.CREATED);

    }
    @PutMapping("/updateWorker")
    public ResponseEntity<String>updateWorker(@RequestBody Worker worker){
        return new ResponseEntity<>(workerService.upsert(worker),HttpStatus.CREATED);

    }


    @DeleteMapping("/deleteWorker/{workerID}")
    public ResponseEntity<String>deleteWorker(@PathVariable Integer workerID){
        return new ResponseEntity<>(workerService.delById(workerID),HttpStatus.OK);

    }

}
