package com.example.SpringBootTutor.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.SpringBootTutor.model.Pelanggan;

public interface PelangganInterface extends JpaRepository<Pelanggan, Integer>{
    List<Pelanggan> findById(int id);
}
