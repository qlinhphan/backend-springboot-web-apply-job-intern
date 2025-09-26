package vn.BAITAP.TestAPI.controller;

import org.springframework.web.bind.annotation.RestController;

import vn.BAITAP.TestAPI.domain.Company;
import vn.BAITAP.TestAPI.domain.dto.ObjectPaginate;
import vn.BAITAP.TestAPI.service.CompanyService;
import vn.BAITAP.TestAPI.service.Except.NotExistUserById;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/company")
    public ResponseEntity<?> postMethodName(@RequestBody Company com) {
        Company comSave = this.companyService.saveCompany(com);

        return ResponseEntity.status(HttpStatus.CREATED.value()).body(comSave);
    }

    @GetMapping("/company/hasPage")
    public ResponseEntity<?> getMethodName(@RequestParam("current") String current,
            @RequestParam("limit") String limit) {
        int current_int = Integer.parseInt(current);
        int limit_int = Integer.parseInt(limit);
        List<Company> companies = this.companyService.findAllCompanies();
        Pageable pageable = PageRequest.of(current_int - 1, limit_int);
        Page<Company> companyPages = this.companyService.findAllHasPage(pageable);
        List<Company> listComs = companyPages.getContent();
        ObjectPaginate op = new ObjectPaginate<>();
        op.setCurrent(current_int);
        op.setData(listComs);
        op.setLimit(limit_int);
        op.setSumObj(companies.size());
        op.setPages(companyPages.getTotalPages());
        return ResponseEntity.ok().body(op);
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<?> getMethodName(@PathVariable("id") String id) {
        long ids = Long.parseLong(id);
        Company com = this.companyService.findCompById(ids);
        if (com != null) {
            return ResponseEntity.ok().body(com);
        } else {
            throw new NotExistUserById("Khong ton tai cong ty nay");
        }
    }

    @PutMapping("company/update")
    public ResponseEntity<?> putMethodName(@RequestBody Company company) {
        Company comUpdate = this.companyService.findCompById(company.getId());
        if (comUpdate == null) {
            throw new NotExistUserById("Khong ton tai cong ty nay");
        }
        if (company.getName() != null) {
            comUpdate.setName(company.getName());
        }
        if (company.getAddress() != null) {
            comUpdate.setAddress(company.getAddress());
        }
        if (company.getLeader() != null) {
            comUpdate.setLeader(company.getLeader());
        }
        if (company.getSize() != null) {
            comUpdate.setSize(company.getSize());
        }
        Company overrideData = this.companyService.saveCompany(comUpdate);
        return ResponseEntity.ok().body(overrideData);
    }

    @DeleteMapping("/company/del/{id}")
    public ResponseEntity<?> deleteCom(@PathVariable("id") String id) {
        long ids = Long.parseLong(id);
        Company comTodel = this.companyService.findCompById(ids);
        if (comTodel == null) {
            throw new NotExistUserById("Khong ton tai company nay de xoa");
        } else {
            this.companyService.deleteCompanyById(ids);
            return ResponseEntity.ok().body("Xoa cong ty thanh cong");
        }

    }

}
