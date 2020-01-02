package com.example.restfullspring.controller;

import com.example.restfullspring.exceptions.ResourceNotFoundException;
import com.example.restfullspring.model.PengaduanModel;
import com.example.restfullspring.repository.PengaduanRepository;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class PengaduanController {
    private final PengaduanRepository dat;

    Map<String, Object> body = new LinkedHashMap<>();

    public PengaduanController(PengaduanRepository dat) {
        this.dat = dat;
    }

    @CrossOrigin
    @PostMapping(path = "/pengaduan")
    public ResponseEntity<Map<String, Object>> postPengaduan(@RequestBody PengaduanModel newPengaduan) {
        dat.save(newPengaduan);

        body.put("code", "1");
        body.put("status", "success");
        body.put("message", "Data berhasil disimpan");

//        return ResponseEntity.status(HttpStatus.OK).body(body);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(path = "/pengaduan")
    public ResponseEntity<Map<String, Object>> getPengaduan(){
//        return (List<UserModel>)dao.findAll();
        List<PengaduanModel> d = (List<PengaduanModel>) dat.findAll();

        body.put("code", "1");
        body.put("status", "success");
        body.put("message", "Data retrieved");
        body.put("total", d.size());
        body.put("data", d);

//        return ResponseEntity.status(HttpStatus.OK).body(body);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping("/pengaduan/{id}")
    public ResponseEntity<Map<String, Object>> getPengaduanById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        PengaduanModel d = dat.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pengaduan not found for this id :: " + id));

        body.put("code", "1");
        body.put("status", "success");
        body.put("message", "Login Success");
        body.put("data", d);

//        return ResponseEntity.status(HttpStatus.OK).body(body);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PutMapping("/pengaduan/{id}")
    public ResponseEntity<Map<String, Object>> updatePengaduan(@PathVariable(value = "id") Integer id,
                                                               @Valid @RequestBody PengaduanModel pDetails) throws ResourceNotFoundException {
        PengaduanModel d = dat.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pengaduan not found for this id :: " + id));

        d.setPenyedia(pDetails.getPenyedia());
        d.setLayanan(pDetails.getLayanan());
        d.setGangguan(pDetails.getGangguan());
        d.setUser_id(pDetails.getUser_id());
        d.setNotiket(pDetails.getNotiket());
        d.setKeluhan(pDetails.getKeluhan());
        d.setGambar(pDetails.getGambar());
        d.setNohp(pDetails.getNohp());
        d.setLat(pDetails.getLat());
        d.setLng(pDetails.getLng());
        d.setAlamat(pDetails.getAlamat());
        d.setIs_active(pDetails.getIs_active());
        d.setDate_created(pDetails.getDate_created());
        d.setDate_updated(pDetails.getDate_updated());

        final PengaduanModel updatedUser = dat.save(d);

        body.put("code", "1");
        body.put("status", "success");
        body.put("message", "Data berhasil diupdate");

//        return ResponseEntity.status(HttpStatus.OK).body(body);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @DeleteMapping("/pengaduan/{id}")
    public ResponseEntity<?> deleteLayanan(@PathVariable(value = "id") Integer Id) throws ResourceNotFoundException {
        PengaduanModel d = dat.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Pengaduan not found for this id :: " + Id));

        dat.delete(d);

        body.put("code", "1");
        body.put("status", "success");
        body.put("message", "Data berhasil didelete");

//        return ResponseEntity.status(HttpStatus.OK).body(body);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
