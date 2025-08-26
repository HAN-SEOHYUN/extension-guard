package com.example.extension_guard.domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileExtensionBlockDaoImpl implements FileExtensionBlockDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FileExtensionBlockDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<FileExtensionBlock> findAll() {
        String sql = "SELECT * FROM file_extension_block";
        return jdbcTemplate.query(sql, new FileExtensionBlockMapper());
    }

    @Override
    public void updateFixedExtensionStatus(String name, boolean isChecked) {
        String sql = "UPDATE file_extension_block SET is_checked = ? WHERE name = ?";
        jdbcTemplate.update(sql, isChecked, name);
    }

    @Override
    public List<FileExtensionBlock> findFixedExtensions() {
        String sql = "SELECT * FROM file_extension_block WHERE is_fixed = TRUE";
        return jdbcTemplate.query(sql, new FileExtensionBlockMapper());
    }


    @Override
    public List<FileExtensionBlock> findCustomExtensions() {
        String sql = "SELECT * FROM file_extension_block WHERE is_fixed = FALSE";
        return jdbcTemplate.query(sql, new FileExtensionBlockMapper());
    }

    @Override
    public void save(FileExtensionBlock request) {
        String sql = "INSERT INTO file_extension_block (name, is_fixed, is_checked, created_at) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                request.getName(),
                request.isFixed(),
                request.isChecked(),
                request.getCreatedAt());
    }

    @Override
    public void deleteByName(String name) {
        String sql = "DELETE FROM file_extension_block WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }
}
