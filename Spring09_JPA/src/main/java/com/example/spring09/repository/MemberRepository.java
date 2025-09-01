package com.example.spring09.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring09.entity.Member;
/*
 * JpaRepository 인터페이스를 상속받은 인터페이스를 정의하는 것 만으로 구현클래스가 만들어지고
 * 해당 클래스를 생성된 객체가 bean 으로 관리가 된다
 * Dao가 자동으로 만들어 진다고 생각하면 된다.
 * 
 * extends JpaRepository<Entitiy 클래스명, 해당 Entity 에서 PK 의 data type>
 */
public interface MemberRepository extends JpaRepository<Member, Integer>{

	
}
