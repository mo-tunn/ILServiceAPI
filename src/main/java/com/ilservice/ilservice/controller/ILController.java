package com.ilservice.ilservice.controller;

import com.ilservice.ilservice.exception.IlAlreadyExistsException;
import com.ilservice.ilservice.exception.IlNotFoundException;
import com.ilservice.ilservice.model.Il;
import com.ilservice.ilservice.service.IlService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/iller")
@AllArgsConstructor
public class ILController {

    private final IlService ilService;


    @GetMapping
    public ResponseEntity<List<Il>> getIller(@RequestParam(required = false) String name) {
        return new ResponseEntity<>(ilService.getIller(name), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Il> getIl(@PathVariable String id) {
        return new ResponseEntity<>(getIlById(id), OK);
    }

    @PostMapping
    public ResponseEntity<Il> createIl(@RequestBody Il newIl) {
        return new ResponseEntity<>(ilService.createIl(newIl), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> getIl(@PathVariable String id, @RequestBody Il newIl) {
        ilService.updateIl(id, newIl);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIl(@PathVariable String id) {
        ilService.deleteIl(id);
        return new ResponseEntity<>(OK);
    }

    private Il getIlById(String id) {
        return ilService.getIlById(id);
    }

    @ExceptionHandler(IlNotFoundException.class)
    public ResponseEntity<String> handleIlNotFoundException(IlNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler(IlAlreadyExistsException.class)
    public ResponseEntity<String> handleIlIlAlreadyExistsException(IlAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), CONFLICT);
    }
}
