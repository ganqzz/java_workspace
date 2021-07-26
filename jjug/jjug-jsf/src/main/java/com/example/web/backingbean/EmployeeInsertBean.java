package com.example.web.backingbean;

import com.example.persistence.entity.Employee;
import com.example.persistence.entity.Department;
import com.example.service.DepartmentService;
import com.example.service.EmployeeService;
import com.example.web.util.FacesUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author tada
 */
@Named
@ConversationScoped
public class EmployeeInsertBean implements Serializable {
    
    @Inject
    private EmployeeService employeeService;
    @Inject
    private DepartmentService departmentService;
    @Inject
    private Conversation conversation;
    
    @NotNull(message = "{notnull}")
    @Size(min = 1, max = 40, message = "{size.string}")
    @Pattern(regexp = "[a-zA-Z\\s]*", message = "{pattern.alphabet.or.space}")
    private String name;
    @NotNull(message = "{notnull}")
    private Date joinedDate;
    @NotNull(message = "{notnull}")
    @Pattern(regexp = "[1-9][0-9]*", message = "{pattern.integer}")
    private String deptId;
    private String deptName;
    
    private List<Department> departmentList;
    
    @PostConstruct
    public void init() {
        if (conversation.isTransient()) {
            conversation.setTimeout(60000);
            conversation.begin();
            departmentList = departmentService.findAll();
        }
    }
    
    public String confirm() {
        deptName = departmentService.findById(Integer.valueOf(deptId)).getName();
        return "insert-confirm.xhtml";
    }
    
    public String cancel() {
        return "insert.xhtml";
    }
    
    public String insert() {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setJoinedDate(joinedDate);
        
        Department department = new Department();
        department.setDeptId(Integer.valueOf(deptId));
        department.setName(deptName);
        employee.setDepartment(department);
        
        employeeService.insert(employee);
        
        conversation.end();
        
        return "index.xhtml" + FacesUtil.REDIRECT;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the joinedDate
     */
    public Date getJoinedDate() {
        return joinedDate;
    }

    /**
     * @param joinedDate the joinedDate to set
     */
    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    /**
     * @return the deptId
     */
    public String getDeptId() {
        return deptId;
    }

    /**
     * @param deptId the deptId to set
     */
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    /**
     * @return the deptName
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * @return the departmentList
     */
    public List<Department> getDepartmentList() {
        return departmentList;
    }
    
    
}
