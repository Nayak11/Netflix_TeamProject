package com.moviecentral.mc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviecentral.mc.entity.Attributes;

public interface AttributesRepository extends JpaRepository<Attributes, Integer> {

	Attributes findByValue(String value);

}
