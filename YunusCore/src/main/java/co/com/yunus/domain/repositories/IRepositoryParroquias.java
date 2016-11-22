package co.com.yunus.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.yunus.application.dto.Parroquia;

public interface IRepositoryParroquias extends JpaRepository<Parroquia,Long> {
}
