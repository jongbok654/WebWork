package com.example.spring09.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter

public class Dept {//부서 정보를 저장할 Entity
	@Id
	private Integer deptno;
	private String dname;
	private String loc;
}
