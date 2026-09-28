package learn.spring.springbatch.batch2csv.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {
    private Long id;
    private String name;
    private String department;
    private Double salary;
}
