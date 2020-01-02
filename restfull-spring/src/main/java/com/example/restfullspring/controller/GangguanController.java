package com.example.restfullspring.controller;

import com.example.restfullspring.exceptions.ResourceNotFoundException;
import com.example.restfullspring.model.GangguanModel;
import com.example.restfullspring.model.LayananModel;
import com.example.restfullspring.repository.GangguanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class GangguanController {
    private final GangguanRepository dataa;

    Map<String, Object> body = new LinkedHashMap<>();

    public GangguanController(GangguanRepository dataa) {
        this.dataa = dataa;
    }

    @PostMapping(path = "/gangguan")
    public ResponseEntity<Map<String, Object>> postGangguan(@RequestBody GangguanModel newGangguan) {
        dataa.save(newGangguan);

        body.put("code", "1");
        body.put("status", "success");
        body.put("message", "Data berhasil disimpan");

        return new ResponseEntity<>(body, HttpStatus.OK);
//        return ResponseEntity.status(HttpStatus.OK).body(body);

    }

    @GetMapping(path = "/gangguan")
    public ResponseEntity<Map<String, Object>> getGangguan(){

        List<GangguanModel> d = (List<GangguanModel>) dataa.findAll();

        body.put("code", "1");
        body.put("status", "success");
        body.put("message", "Data retrieved");
        body.put("total", d.size());
        body.put("data", d);

        return new ResponseEntity<>(body, HttpStatus.OK);
//        return ResponseEntity.status(HttpStatus.OK).body(body);

    }

    @GetMapping("/gangguan/{id}")
    public ResponseEntity<Map<String, Object>> getGangguanById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        GangguanModel d = dataa.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gangguan not found for this id :: " + id));

        body.put("code", "1");
        body.put("status", "success");
        body.put("message", "Login Success");
        body.put("data", d);

        return new ResponseEntity<>(body, HttpStatus.OK);
//        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @PutMapping("/gangguan/{id}")
    public ResponseEntity<Map<String, Object>> updateGangguan(@PathVariable(value = "id") Integer id,
                                                               @Valid @RequestBody GangguanModel pDetails) throws ResourceNotFoundException {
        GangguanModel d = dataa.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gangguan not found for this id :: " + id));

        d.setId_produk(pDetails.getId_produk());
        d.setUser_id(pDetails.getUser_id());
        d.setNama_gangguan(pDetails.getNama_gangguan());
        d.setDetail_gangguan(pDetails.getDetail_gangguan());
        d.setHarga(pDetails.getHarga());
        d.setIs_active(pDetails.getIs_active());
        d.setDate_created(pDetails.getDate_created());
        d.setDate_updated(pDetails.getDate_updated());

        final GangguanModel updatedGangguan = dataa.save(d);

        body.put("code", "1");
        body.put("status", "success");
        body.put("message", "Data berhasil diupdate");

        return new ResponseEntity<>(body, HttpStatus.OK);
//        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @DeleteMapping("/gangguan/{id}")
    public ResponseEntity<?> deleteGangguan(@PathVariable(value = "id") Integer Id) throws ResourceNotFoundException {
        GangguanModel d = dataa.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Gangguan not found for this id :: " + Id));

        dataa.delete(d);

        body.put("code", "1");
        body.put("status", "success");
        body.put("message", "Data berhasil didelete");

        return new ResponseEntity<>(body, HttpStatus.OK);
//        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
