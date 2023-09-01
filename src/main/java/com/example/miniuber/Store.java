package com.example.miniuber;

import java.sql.SQLException;

public interface Store {
    void store(String SQL) throws SQLException;
}
