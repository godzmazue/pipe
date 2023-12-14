package com.examplejunit.testforjunit.dao;

import com.examplejunit.testforjunit.entity.Worker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest

class WorkerDaoTest {
    @Autowired
    WorkerDao workerDao;
    Worker worker;

    @BeforeEach
    void setUp() {
        worker =new Worker(1,"nithin", 10000.0);
        workerDao.save(worker);

    }

    @AfterEach
    void tearDown() {
        workerDao.deleteAll();
    }


    @Test
    void testFindByworkerSalary_found() {
        List<Worker> workerList=  workerDao.findByworkerSalary(10000.0);
        assertThat(workerList.get(0).getWorkerName()).isEqualTo(worker.getWorkerName());
        assertThat(workerList.isEmpty()).isFalse();


    }
    @Test
    void testFindByworkerSalary_notFound() {
        List<Worker> workerList=  workerDao.findByworkerSalary(1000098.0);
        assertThat(workerList.isEmpty()).isTrue();


    }
}