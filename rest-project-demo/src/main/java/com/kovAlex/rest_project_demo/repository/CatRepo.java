package com.kovAlex.rest_project_demo.repository;

import com.kovAlex.rest_project_demo.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRepo extends JpaRepository<Cat, Integer> {

}
