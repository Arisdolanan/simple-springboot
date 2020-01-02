package com.example.restfullspring.controller;

import com.example.restfullspring.exceptions.ResourceNotFoundException;
import com.example.restfullspring.model.LayananModel;
import com.example.restfullspring.model.PengaduanModel;
import com.example.restfullspring.repository.LayananRepository;
import com.example.restfullspring.repository.PengaduanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class LayananController {
    private final LayananRepository dataa;

    Map<String, Object> body = new LinkedHashMap<>();

    public LayananController(LayananRepository dat) {
        this.dataa = dat;
    }

    @PostMapping(path = "/layanan")
    public ResponseEntity<Map<String, Object>> postLayanan(@RequestBody LayananModel newLayanan) {
        dataa.save(newLayanan);

        body.put("code", "1");
        body.put("status", "success");
        body.put("message", "Data berhasil disimpan");

//        return ResponseEntity.status(HttpStatus.OK).body(body);
        return new ResponseEntity<>(body, HttpStatus.OK);

    }

    @GetMapping(path = "/layanan")
    public ResponseEntity<Map<String, Object>> getLayanan(){
//        return (List<LayananModel>)dao.findAll();

        List<LayananModel> d = (List<LayananModel>) dataa.findAll();

        body.put("code", "1");
        body.put("status", "success");
        body.put("message", "Data retrieved");
        body.put("total", d.size());
        body.put("data", d);

//        return ResponseEntity.status(HttpStatus.OK).body(body);
        return new ResponseEntity<>(body, HttpStatus.OK);

    }

    @GetMapping("/layanan/{id}")
    public ResponseEntity<Map<String, Object>> getLayananById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        LayananModel d = dataa.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Layanan not found for this id :: " + id));

        body.put("code", "1");
        body.put("status", "success");
        body.put("message", "Login Success");
        body.put("data", d);

//        return ResponseEntity.status(HttpStatus.OK).body(body);
        return new ResponseEntity<>(body, HttpStatus.OK);

    }

    @PutMapping("/layanan/{id}")
    public ResponseEntity<Map<String, Object>> updateLayanan(@PathVariable(value = "id") Integer id,
                                                               @Valid @RequestBody LayananModel pDetails) throws ResourceNotFoundException {
        LayananModel d = dataa.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Layanan not found for this id :: " + id));

        d.setNama_layanan(pDetails.getNama_layanan());
        d.setIs_active(pDetails.getIs_active());
        d.setDate_created(pDetails.getDate_created());
        d.setDate_updated(pDetails.getDate_updated());

        final LayananModel updatedLayanan = dataa.save(d);

        body.put("code", "1");
        body.put("status", "success");
        body.put("message", "Data berhasil diupdate");

        return new ResponseEntity<>(body, HttpStatus.OK);
//        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @DeleteMapping("/layanan/{id}")
    public ResponseEntity<?> deleteLayanan(@PathVariable(value = "id") Integer Id) throws ResourceNotFoundException {
        LayananModel d = dataa.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Layanan not found for this id :: " + Id));

        dataa.delete(d);

        body.put("code", "1");
        body.put("status", "success");
        body.put("message", "Data berhasil didelete");

        return new ResponseEntity<>(body, HttpStatus.OK);
//        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

}
