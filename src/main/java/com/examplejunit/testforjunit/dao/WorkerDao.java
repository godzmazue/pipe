package com.examplejunit.testforjunit.dao;

import com.examplejunit.testforjunit.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface WorkerDao extends JpaRepository<Worker,Integer> {

    List<Worker> findByworkerSalary(Double workerSalary);
}
