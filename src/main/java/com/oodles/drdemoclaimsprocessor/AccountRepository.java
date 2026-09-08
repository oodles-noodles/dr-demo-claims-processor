package com.oodles.drdemoclaimsprocessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class AccountRepository {

    private final DataSource dataSource;

    public AccountRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<String> findByRegion(String region) throws Exception {
        List<String> out = new ArrayList<>();
        try (Connection conn = dataSource.getConnection(); Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT name FROM accounts WHERE region = '" + region + "'");
            while (rs.next()) {
                out.add(rs.getString(1));
            }
        }
        return out;
    }

    public String findById(long id) throws Exception {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT name FROM accounts WHERE id = ?")) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getString(1) : null;
        }
    }
}
