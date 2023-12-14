package com.examplejunit.testforjunit.controller;

import com.examplejunit.testforjunit.entity.Worker;
import com.examplejunit.testforjunit.service.WorkerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(WorkerController.class)
class WorkerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    WorkerService workerService;
    Worker w1;
    Worker w2;
    List<Worker> workerList=new ArrayList<>();

    @BeforeEach
    void setUp() {
        w1=new Worker(1,"nithin",1000.0);
        w2=new Worker(2,"nishanth",1000.0);
        workerList.add(w1);
        workerList.add(w2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllWorkers() throws Exception {
        when(workerService.getAll()).thenReturn(workerList);
        this.mockMvc.perform(get("/getAllWorkers")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getWorkersBySalary() throws Exception {
        when(workerService.getBySalary(1000.0)).thenReturn(workerList);
        this.mockMvc.perform(get("/getAllWorkers/1000.0")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        when(workerService.getById(1)).thenReturn(w1);
        this.mockMvc.perform(get("/getWorker/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void addWorker() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(w1);
        when(workerService.upsert(w1)).thenReturn("success");
        this.mockMvc.perform(post("/addWorker")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                        .andDo(print())
                        .andExpect(status().isCreated());


    }

    @Test
    void updateWorker() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(w1);
        when(workerService.upsert(w1)).thenReturn("success");
        this.mockMvc.perform(put("/updateWorker")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    void deleteWorker() throws Exception {
        when(workerService.delById(1)).thenReturn("delete success");
        this.mockMvc.perform(delete("/deleteWorker/1")).andDo(print()).andExpect(status().isOk());
    }
}