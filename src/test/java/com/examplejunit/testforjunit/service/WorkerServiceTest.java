package com.examplejunit.testforjunit.service;

import com.examplejunit.testforjunit.dao.WorkerDao;
import com.examplejunit.testforjunit.entity.Worker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkerServiceTest {
    @Mock
    WorkerDao workerDao;
    WorkerService workerService;

    Worker worker;


    @BeforeEach
    void setUp() {
        workerService=new WorkerService(workerDao);
        worker=new Worker(1,"nithin",1000.0);


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void upsert() {
        when(workerDao.save(worker)).thenReturn(worker);
        assertEquals("success",workerService.upsert(worker));


    }

    @Test
    void getAll() {
        when(workerDao.findAll()).thenReturn(new ArrayList<Worker>(Collections.singleton(worker)));
        assertEquals("nithin",workerService.getAll().get(0).getWorkerName());
        assertEquals(1,workerService.getAll().size());
    }

    @Test
    void getById() {
        when(workerDao.findById(1)).thenReturn(Optional.ofNullable(worker));
        assertEquals(worker.getWorkerName(),workerService.getById(1).getWorkerName());
    }

    @Test
    void delById() {
        workerService.delById(1);
        Mockito.verify(workerDao,times(1)).deleteById(1);


    }

    @Test
    void getBySalary() {
        when(workerDao.findByworkerSalary(1000.0)).thenReturn(new ArrayList<>(Collections.singleton(worker)));
        assertEquals(worker.getWorkerSalary(),workerService.getBySalary(1000.0).get(0).getWorkerSalary());
    }
}