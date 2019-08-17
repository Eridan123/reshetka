package com.metal.reshetka.repository;

import com.metal.reshetka.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
