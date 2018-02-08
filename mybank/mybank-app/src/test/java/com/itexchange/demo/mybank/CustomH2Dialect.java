package com.itexchange.demo.mybank;

import org.hibernate.dialect.H2Dialect;

public class CustomH2Dialect extends H2Dialect {
	public CustomH2Dialect() {
        registerInt();
        registerDouble();
    }

    private void registerInt() {
        registerColumnType(java.sql.Types.BOOLEAN, "TINYINT");
    }
    
    private void registerDouble() {
    	registerColumnType(java.sql.Types.DOUBLE, "DECIMAL");
    }
}
