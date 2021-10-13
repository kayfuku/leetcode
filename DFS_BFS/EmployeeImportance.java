package leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeImportance {
  // fields and classes here.
  // private int count;

  public EmployeeImportance() {
    // Initialization here.
    // this.count = 0;
  }

  // O(N) time and space
  // Author: kei (AC)
  // Date : October 9, 2021
  private Map<Integer, Employee> idToEmployee;

  public int getImportance(List<Employee> employees, int id) {
    idToEmployee = new HashMap<>();
    for (Employee employee : employees) {
      idToEmployee.put(employee.id, employee);
    }

    Employee employee = idToEmployee.get(id);
    return dfs(employee);
  }

  private int dfs(Employee employee) {
    int sum = 0;
    sum += employee.importance;
    for (Integer id : employee.subordinates) {
      Employee e = idToEmployee.get(id);
      sum += dfs(e);
    }

    return sum;
  }

  class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    EmployeeImportance solution = new EmployeeImportance();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
