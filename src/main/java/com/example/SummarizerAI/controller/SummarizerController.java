package com.example.SummarizerAI.controller;

import com.example.SummarizerAI.entities.ContentEntity;
import com.example.SummarizerAI.service.SummarizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class SummarizerController {





        @Autowired
        private SummarizerService summarizerService;




        @GetMapping
        public String healthcheck(){
            return "All okay";
        }


        @PostMapping("/response")
        public ResponseEntity<?> response(@RequestBody ContentEntity content){
            String result=summarizerService.processContent(content);
            return ResponseEntity.ok(result);

        }

    }



