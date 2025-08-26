package com.example.extension_guard.domain;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FileExtensionBlockMapper implements RowMapper<FileExtensionBlock> {

    @Override
    public FileExtensionBlock mapRow(ResultSet rs, int rowNum) throws SQLException {
        return FileExtensionBlock.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .isFixed(rs.getBoolean("is_fixed"))
                .isChecked(rs.getBoolean("is_checked"))
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                .build();
    }
}
