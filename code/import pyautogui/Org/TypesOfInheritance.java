package Org;

// Base class
class Employee {
    protected String name;
    protected int employeeId;

    public Employee(String empName, int empId) {
        this.name = empName;
        this.employeeId = empId;
    }

    public void display() {
        System.out.println("Employee: " + name + ", ID: " + employeeId);
    }
}

// Single Inheritance
class Developer extends Employee {
    private String programmingLanguage;

    public Developer(String empName, int empId, String lang) {
        super(empName, empId);
        this.programmingLanguage = lang;
    }

    public void show() {
        display();
        System.out.println("Specialization: Developer, Programming Language: " + programmingLanguage);
    }
}

// Interfaces for Multiple Inheritance
interface ProjectManager {
    void manageProject();
}

interface TeamLead {
    void leadTeam();
}

// Multiple Inheritance using interfaces
class TechLead extends Employee implements ProjectManager, TeamLead {
    private String projectManaged;
    private int teamSize;

    public TechLead(String empName, int empId, String project, int teamSize) {
        super(empName, empId);
        this.projectManaged = project;
        this.teamSize = teamSize;
    }

    public void displayInfo() {
        display();
        manageProject();
        leadTeam();
    }

    public void manageProject() {
        System.out.println("Project Manager managing project: " + projectManaged);
    }

    public void leadTeam() {
        System.out.println("Team Lead leading a team of " + teamSize + " members.");
    }
}

// Multi-level Inheritance
class HRManager extends Employee {
    public HRManager(String empName, int empId) {
        super(empName, empId);
    }

    public void handleHRDuties() {
        System.out.println("HR Manager handling human resources duties.");
    }
}

class HRDirector extends HRManager {
    public HRDirector(String empName, int empId) {
        super(empName, empId);
    }

    public void manageHRDepartment() {
        System.out.println("HR Director managing the HR department.");
    }
}

// Hierarchical Inheritance
class Executive extends Employee {
    public Executive(String empName, int empId) {
        super(empName, empId);
    }

    public void makeExecutiveDecisions() {
        System.out.println("Executive making executive decisions.");
    }
}

class CEO extends Executive {
    public CEO(String empName, int empId) {
        super(empName, empId);
    }

    public void leadCompany() {
        makeExecutiveDecisions();
        System.out.println("CEO leading the company.");
    }
}

// Hybrid Inheritance
class MarketingManager extends Employee {
    public MarketingManager(String empName, int empId) {
        super(empName, empId);
    }

    public void createMarketingStrategy() {
        System.out.println("Marketing Manager creating a marketing strategy.");
    }
}

// Convert SalesManager to an interface
interface SalesManager {
    void boostSales();
}

class BusinessDevelopmentManager extends MarketingManager implements SalesManager {
    public BusinessDevelopmentManager(String empName, int empId) {
        super(empName, empId);
    }

    public void coordinateBusinessDevelopment() {
        createMarketingStrategy();
        boostSales();
        System.out.println("Business Development Manager coordinating business development efforts.");
    }

    @Override
    public void boostSales() {
        System.out.println("Sales Manager boosting sales.");
    }
}


public class TypesOfInheritance {
    public static void main(String[] args) {
//        // Single Inheritance
//        Developer dev = new Developer("Ramu Kaka", 101, "Java");
//        dev.show();

        // // Multiple Inheritance
//         TechLead techLead = new TechLead("Anna Dev", 202, "Project X", 5);
//         techLead.displayInfo();

        // // Multi-level Inheritance
         HRDirector hrDirector = new HRDirector("Lucy Madam", 303);
         hrDirector.handleHRDuties();
         hrDirector.manageHRDepartment();

        // // Hierarchical Inheritance
//         CEO ceo = new CEO("Devi Lal", 404);
//         ceo.leadCompany();
//
//        // // Hybrid Inheritance
         BusinessDevelopmentManager bdManager = new BusinessDevelopmentManager("Sam Wilson", 606);
         bdManager.coordinateBusinessDevelopment();
    }
}
