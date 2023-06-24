package com.example.SuViet.controller;

import com.example.SuViet.model.ResponseObject;
import com.example.SuViet.model.User;
import com.example.SuViet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllUserList() {
        List<User> users = userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Query successfully", users)
        );
    }

    @DeleteMapping("/ban/{id}")
    public ResponseEntity<ResponseObject> deleteMember(@PathVariable int id) {
        boolean checkDelete = userService.banAMember(id);
        if (!checkDelete) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("FAILED", "User does not exist", null)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Delete Successfully", null)
        );
    }
}