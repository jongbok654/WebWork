package com.example.spring09.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.spring09.entity.Emp;



public interface EmpRepository extends JpaRepository<Emp,Integer>{
	//사원 이름에 대해서 오름차순 정렬된 결과를 리턴하는 메소드 후기
	public List<Emp>findAllByOrderByEnameAsc(); // 정해진 규칙으로 메소드명을 만든다.

	/*
	 * @Param("deptno") <=> :deptno:
	 */
	//실행할 query 문 (JPQL) 을 직접 작성한다
	@Query("SELECT e FROM Emp e WHERE e.dept.deptno = :deptno ORDER BY e.ename ASC")
	public List<Emp> findEmps(@Param("deptno") Integer deptno); //메소드명은 마음대로 만든다
	
	//메소드에 전달된 매개변수와 순서를 이용해서 값을 바인딩 할 수도 있다
	@Query("SELECT e FROM Emp e WHERE e.dept.deptno = ?1 ORDER BY e.ename ASC")
	public List<Emp> findEmps2(@Param("deptno") Integer deptno); //메소드명은 마음대로 만든다
	
	/*
	 * Emp entity 에
	 * @ManyToOne
	 * Dept dept;
	 * 
	 * 가 있기 때문에 이걸 활용해서 메소드 만들기
	 * 
	 * Dept_Deptno => Emp 의 dept 필드를 타고 들어가 Dept Entitiy 의 deptno 속성을 조건으로 사용
	 * 
	 * extends JpaResponsitory<emp,Integer> 에서 첫번째 generic type 이 emp 이기 떄문에
	 * Emp entity 에서 dept 라는 필드를 타고 들어가는 것이다
	 */
	
	//정해진 규칙으로 메소드명을 작성해서 위와 같은 결과 얻어내기
	public List<Emp> findByDept_deptnoOrderByEnameAsc(Integer deptno);
}

