package vn.BAITAP.TestAPI.controller;

import org.springframework.web.bind.annotation.RestController;

import vn.BAITAP.TestAPI.domain.Company;
import vn.BAITAP.TestAPI.domain.Job;
import vn.BAITAP.TestAPI.domain.JobCompany;
import vn.BAITAP.TestAPI.domain.User;
import vn.BAITAP.TestAPI.domain.dto.JobCompanyDTO;
import vn.BAITAP.TestAPI.domain.dto.ObjectPaginate;
import vn.BAITAP.TestAPI.domain.dto.TableManagerCreated;
import vn.BAITAP.TestAPI.service.CompanyService;
import vn.BAITAP.TestAPI.service.JobCompanyService;
import vn.BAITAP.TestAPI.service.JobService;
import vn.BAITAP.TestAPI.service.UserService;
import vn.BAITAP.TestAPI.service.Except.NotExistUserById;
import vn.BAITAP.TestAPI.service.Spec.CompanySpecification;
import vn.BAITAP.TestAPI.service.Spec.JobSpecification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class JobCompanyController {
    private JobCompanyService jobCompanyService;
    private JobService jobService;
    private CompanyService companyService;
    private UserService userService;
    private JobSpecification jSpecification;
    private CompanySpecification companySpecification;

    public JobCompanyController(JobCompanyService jobCompanyService, JobService jobService, UserService userService,
            CompanyService companyService, JobSpecification jSpecification, CompanySpecification companySpecification) {
        this.jobCompanyService = jobCompanyService;
        this.jobService = jobService;
        this.companyService = companyService;
        this.userService = userService;
        this.jSpecification = jSpecification;
        this.companySpecification = companySpecification;
    }

    @PostMapping("/manager-add-job-company")
    public ResponseEntity<?> postMethodName(@RequestBody JobCompanyDTO jobCompanyDTO) {
        Job job = new Job();
        if (jobCompanyDTO.getNameJob() != null) {
            job.setNameJob(jobCompanyDTO.getNameJob());
        }
        if (jobCompanyDTO.getDescriptionJob() != null) {
            job.setDescription(jobCompanyDTO.getDescriptionJob());
        }
        if (jobCompanyDTO.getRequireJob() != null) {
            job.setJobRequire(jobCompanyDTO.getRequireJob());
        }
        if (jobCompanyDTO.getBenefitJob() != null) {
            job.setBenefit(jobCompanyDTO.getBenefitJob());
        }
        job.setLimitPeopleForJob(jobCompanyDTO.getLimitPeopleForJob());
        Job jobSave = this.jobService.saveJob(job);

        // Company company = new Company();
        // if (jobCompanyDTO.getNameComp() != null) {
        // company.setName(jobCompanyDTO.getNameComp());
        // }
        // if (jobCompanyDTO.getAddressComp() != null) {
        // company.setAddress(jobCompanyDTO.getAddressComp());
        // }
        // if (jobCompanyDTO.getLeaderComp() != null) {
        // company.setLeader(jobCompanyDTO.getLeaderComp());
        // }
        // if (jobCompanyDTO.getLeaderComp() != null) {
        // company.setSize(jobCompanyDTO.getLeaderComp());
        // }
        // Company companySave = this.companyService.saveCompany(company);

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = this.userService.findUserByEmail(email);
        if (user == null) {
            throw new NotExistUserById("Not exists this user");
        }

        JobCompany jc = new JobCompany();
        jc.setJob(jobSave);
        jc.setCompany(user.getCompany());
        jc.setUser(user);
        // jc.setUser(user);
        JobCompany jcSave = this.jobCompanyService.createJobCom(jc);

        return ResponseEntity.status(HttpStatus.CREATED.value()).body(jcSave);
    }

    // hiển thị tất cả nhưng tìm theo user -> để cho manager xem xông việc mà họ đã
    // thêm
    @GetMapping("/findAll-jc-haspage")
    public ResponseEntity<?> getMethodName(@RequestParam("current") int current,
            @RequestParam("limit") int limit) {
        Pageable pageable = PageRequest.of(current - 1, limit);

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = this.userService.findUserByEmail(email);
        if (user == null) {
            throw new NotExistUserById("Not exists this user");
        }
        List<JobCompany> jobCompanies = this.jobCompanyService.findAllByUser(user);

        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), jobCompanies.size());
        final Page<JobCompany> page = new PageImpl<>(jobCompanies.subList(start, end), pageable, jobCompanies.size());

        List<JobCompany> data = page.getContent();

        ObjectPaginate op = new ObjectPaginate<>();
        op.setCurrent(current);
        op.setData(data);
        op.setLimit(limit);
        op.setPages(page.getTotalPages());
        op.setSumObj(jobCompanies.size());
        return ResponseEntity.ok().body(op);
    }

    @GetMapping("/find-jc-by-id/{id}")
    public ResponseEntity<?> getMethodName(@PathVariable("id") long id) {
        JobCompany JC = this.jobCompanyService.findJCById(id);
        if (JC == null) {
            throw new NotExistUserById("Not exist JC này");
        } else {
            return ResponseEntity.ok().body(JC);
        }
    }

    // xoa day truoc xong moi xoa o job tai vi day la khoa ngoai
    @DeleteMapping("/jobs-comp/del/{idJob}")
    @Transactional
    public ResponseEntity<?> deleteJobss(@PathVariable("idJob") long idJob) {

        Job j = this.jobService.findJobById(idJob);
        if (j != null) {
            // xoa du lieu job-com truoc
            List<JobCompany> JC = this.jobCompanyService.findAllNoHasPage();
            boolean check = JC.stream().anyMatch(x -> j.getId() == x.getJob().getId());
            if (!check) {
                throw new NotExistUserById("Khong ton tai job nay trong job-comp");
            }
            this.jobCompanyService.deleteJCByJob(j);

            // xoa du lieu trong job
            this.jobService.deleteJobById(idJob);
            return ResponseEntity.ok().body("Ban da xoa job nay trong com-job va job nay trong job table");
        } else {
            throw new NotExistUserById("Khong ton tai job nay");
        }
    }

    // hien thi tat ca cac job-com cho user de no dki
    @GetMapping("/findAll-JC-hasPage-forUser")
    public ResponseEntity<?> getMethodName(@RequestParam("current") String current,
            @RequestParam("limit") String limit) {
        int limit_int = Integer.parseInt(limit);
        int current_int = Integer.parseInt(current);
        Pageable pageable = PageRequest.of(current_int - 1, limit_int);
        Page<JobCompany> jcs = this.jobCompanyService.findAllJobComps(pageable);
        List<JobCompany> dataPagi = jcs.getContent();

        List<JobCompany> allData = this.jobCompanyService.findAllNoHasPage();

        ObjectPaginate<Object> op = new ObjectPaginate<>();
        op.setCurrent(current_int);
        op.setData(dataPagi);
        op.setLimit(limit_int);
        op.setPages(jcs.getTotalPages());
        op.setSumObj(allData.size());

        return ResponseEntity.ok().body(op);
    }

    // tim kiem nhung job_com nho hon 1500$
    @GetMapping("/range-salary")
    public ResponseEntity<?> getMethodNames(@RequestParam(value = "money", required = false) String money,
            @RequestParam(value = "typeJob", required = false, defaultValue = "") String typeJob,
            @RequestParam(value = "nameJob", required = false, defaultValue = "") String nameJob,
            @RequestParam("current") String current,
            @RequestParam("limit") String limit) {
        if (money.equals("be-hon-bang-1500")) {
            long salary = 1500;
            Specification<Job> spec = this.jSpecification.lessThanCon(salary, typeJob, nameJob);
            List<Job> listJ = this.jobService.findAllToGetIdAfterFindJC(spec);

            List<JobCompany> jcs = new ArrayList<>();
            for (Job j : listJ) {
                if (j.getSalary() != 0) {
                    JobCompany jc = this.jobCompanyService.findJCByJob(j);
                    jcs.add(jc);
                }
            }

            int limit_int = Integer.parseInt(limit);
            int current_int = Integer.parseInt(current);
            Pageable pageable = PageRequest.of(current_int - 1, limit_int);
            final int start = (int) pageable.getOffset();
            final int end = Math.min((start + pageable.getPageSize()), jcs.size());
            final Page<JobCompany> page = new PageImpl<>(jcs.subList(start, end), pageable,
                    jcs.size());
            List<JobCompany> data = page.getContent();
            ObjectPaginate op = new ObjectPaginate<>();
            op.setCurrent(current_int);
            op.setData(data);
            op.setLimit(limit_int);
            op.setPages(page.getTotalPages());
            op.setSumObj(jcs.size());

            return ResponseEntity.ok().body(op);
        }
        if (money.equals("lon-hon-1500-va-be-hon-3000")) {
            long salary1 = 1500;
            long salary2 = 3000;
            Specification<Job> spec = this.jSpecification.lessThanAndGreaterThanCons(salary1, salary2, typeJob,
                    nameJob);
            List<Job> listJ = this.jobService.findAllToGetIdAfterFindJC(spec);

            List<JobCompany> jcs = new ArrayList<>();
            for (Job j : listJ) {
                if (j.getSalary() != 0) {
                    JobCompany jc = this.jobCompanyService.findJCByJob(j);
                    if (jc != null) {
                        jcs.add(jc);
                    }
                }
            }

            int limit_int = Integer.parseInt(limit);
            int current_int = Integer.parseInt(current);
            Pageable pageable = PageRequest.of(current_int - 1, limit_int);
            final int start = (int) pageable.getOffset();
            final int end = Math.min((start + pageable.getPageSize()), jcs.size());
            final Page<JobCompany> page = new PageImpl<>(jcs.subList(start, end), pageable,
                    jcs.size());
            List<JobCompany> data = page.getContent();
            ObjectPaginate op = new ObjectPaginate<>();
            op.setCurrent(current_int);
            op.setData(data);
            op.setLimit(limit_int);
            op.setPages(page.getTotalPages());
            op.setSumObj(jcs.size());

            return ResponseEntity.ok().body(op);
        }
        if (money.equals("lon-hon-bang-3000")) {
            long salary = 3000;
            Specification<Job> spec = this.jSpecification.GreaterThanCon(salary, typeJob, nameJob);
            List<Job> listJ = this.jobService.findAllToGetIdAfterFindJC(spec);

            List<JobCompany> jcs = new ArrayList<>();
            for (Job j : listJ) {
                if (j.getSalary() != 0) {
                    JobCompany jc = this.jobCompanyService.findJCByJob(j);
                    if (jc != null) {
                        jcs.add(jc);
                    }
                }
            }

            int limit_int = Integer.parseInt(limit);
            int current_int = Integer.parseInt(current);
            Pageable pageable = PageRequest.of(current_int - 1, limit_int);
            final int start = (int) pageable.getOffset();
            final int end = Math.min((start + pageable.getPageSize()), jcs.size());
            final Page<JobCompany> page = new PageImpl<>(jcs.subList(start, end), pageable,
                    jcs.size());
            List<JobCompany> data = page.getContent();
            ObjectPaginate op = new ObjectPaginate<>();
            op.setCurrent(current_int);
            op.setData(data);
            op.setLimit(limit_int);
            op.setPages(page.getTotalPages());
            op.setSumObj(jcs.size());

            return ResponseEntity.ok().body(op);
        }

        // if (money.length() == 0) {
        // long salary = 0;
        // Specification<Job> spec = this.jSpecification.GreaterThanCon(salary, typeJob,
        // nameJob);
        // List<Job> listJ = this.jobService.findAllToGetIdAfterFindJC(spec);

        // List<JobCompany> jcs = new ArrayList<>();
        // for (Job j : listJ) {
        // if (j.getSalary() != 0) {
        // JobCompany jc = this.jobCompanyService.findJCByJob(j);
        // jcs.add(jc);
        // }
        // }

        // int limit_int = Integer.parseInt(limit);
        // int current_int = Integer.parseInt(current);
        // Pageable pageable = PageRequest.of(current_int - 1, limit_int);
        // final int start = (int) pageable.getOffset();
        // final int end = Math.min((start + pageable.getPageSize()), jcs.size());
        // final Page<JobCompany> page = new PageImpl<>(jcs.subList(start, end),
        // pageable,
        // jcs.size());
        // List<JobCompany> data = page.getContent();
        // ObjectPaginate op = new ObjectPaginate<>();
        // op.setCurrent(current_int);
        // op.setData(data);
        // op.setLimit(limit_int);
        // op.setPages(page.getTotalPages());
        // op.setSumObj(jcs.size());

        // return ResponseEntity.ok().body(op);
        // }

        int limit_int = Integer.parseInt(limit);
        int current_int = Integer.parseInt(current);
        Pageable pageable = PageRequest.of(current_int - 1, limit_int);
        Page<JobCompany> jcs = this.jobCompanyService.findAllJobComps(pageable);
        List<JobCompany> dataPagi = jcs.getContent();

        List<JobCompany> allData = this.jobCompanyService.findAllNoHasPage();

        ObjectPaginate<Object> op = new ObjectPaginate<>();
        op.setCurrent(current_int);
        op.setData(dataPagi);
        op.setLimit(limit_int);
        op.setPages(jcs.getTotalPages());
        op.setSumObj(allData.size());

        return ResponseEntity.ok().body(op);

    }

}
