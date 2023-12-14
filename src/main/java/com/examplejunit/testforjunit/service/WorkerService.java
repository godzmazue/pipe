package com.examplejunit.testforjunit.service;

import com.examplejunit.testforjunit.dao.WorkerDao;
import com.examplejunit.testforjunit.entity.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {

    WorkerDao workerDao;

    public WorkerService(WorkerDao workerDao) {
        this.workerDao=workerDao;
    }

    public String upsert(Worker worker){
        workerDao.save(worker);
        return "success";


    }
    public List<Worker> getAll(){
        return workerDao.findAll();

    }
    public Worker getById(Integer id){
        return workerDao.findById(id).get();
    }
    public String delById(Integer id){
        workerDao.deleteById(id);
        return "delete success";
    }


    public List<Worker> getBySalary(Double workerSalary){
        return workerDao.findByworkerSalary(workerSalary);
    }
}
