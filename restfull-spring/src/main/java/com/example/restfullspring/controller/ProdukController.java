package com.example.restfullspring.controller;

import com.example.restfullspring.exceptions.ResourceNotFoundException;
import com.example.restfullspring.model.GangguanModel;
import com.example.restfullspring.model.LayananModel;
import com.example.restfullspring.model.ProdukModel;
import com.example.restfullspring.repository.ProdukRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class ProdukController {
    private final ProdukRepository dataa;

    public ProdukController(ProdukRepository dataa) {
        this.dataa = dataa;
    }

    Map<String, Object> body = new LinkedHashMap<>();

    @PostMapping(path = "/produk")
    public ResponseEntity<Map<String, Object>> postProduk(@RequestBody ProdukModel newProduk) {
        dataa.save(newProduk);

        body.put("code", "1");
        body.put("status", "success");
        body.put("message", "Data berhasil disimpan");

//        return ResponseEntity.status(HttpStatus.OK).body(body);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping(path = "/produk")
    public ResponseEntity<Map<String, Object>> getProduk(){
//        return (List<ProdukModel>)dao.findAll();

        List<ProdukModel> d = (List<ProdukModel>) dataa.findAll();

        body.put("code", "1");
        body.put("status", "success");
        body.put("message", "Data retrieved");
        body.put("total", d.size());
        body.put("data", d);

//        return ResponseEntity.status(HttpStatus.OK).body(body);
        return new ResponseEntity<>(body, HttpStatus.OK);

    }

    @GetMapping("/produk/{id}")
    public ResponseEntity<Map<String, Object>> getProdukById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        ProdukModel d = dataa.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produk not found for this id :: " + id));

        body.put("code", "1");
        body.put("status", "success");
        body.put("message", "Login Success");
        body.put("data", d);

//        return ResponseEntity.status(HttpStatus.OK).body(body);
        return new ResponseEntity<>(body, HttpStatus.OK);

    }

    @PutMapping("/produk/{id}")
    public ResponseEntity<Map<String, Object>> updateProduk(@PathVariable(value = "id") Integer id,
                                                               @Valid @RequestBody ProdukModel pDetails) throws ResourceNotFoundException {
        ProdukModel d = dataa.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produk not found for this id :: " + id));

        d.setId_penyedia(pDetails.getId_penyedia());
        d.setUser_id(pDetails.getUser_id());
        d.setNama_produk(pDetails.getNama_produk());
        d.setIs_active(pDetails.getIs_active());
        d.setDate_created(pDetails.getDate_created());
        d.setDate_updated(pDetails.getDate_updated());

        final ProdukModel updatedLayanan = dataa.save(d);

        body.put("code", "1");
        body.put("status", "success");
        body.put("message", "Data berhasil diupdate");

//        return ResponseEntity.status(HttpStatus.OK).body(body);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @DeleteMapping("/produk/{id}")
    public ResponseEntity<?> deleteProduk(@PathVariable(value = "id") Integer Id) throws ResourceNotFoundException {
        ProdukModel d = dataa.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Produk not found for this id :: " + Id));

        dataa.delete(d);

        body.put("code", "1");
        body.put("status", "success");
        body.put("message", "Data berhasil didelete");

//        return ResponseEntity.status(HttpStatus.OK).body(body);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
