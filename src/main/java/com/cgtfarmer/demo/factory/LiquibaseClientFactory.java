package com.cgtfarmer.demo.factory;

import com.cgtfarmer.demo.accessor.EnvironmentAccessor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

public class LiquibaseClientFactory {

  EnvironmentAccessor environmentAccessor;

  public LiquibaseClientFactory(EnvironmentAccessor environmentAccessor) {
    this.environmentAccessor = environmentAccessor;
  }

  public Liquibase create() throws Exception {
    String url = this.environmentAccessor.get("DB_JDBC_URL");
    String username = this.environmentAccessor.get("DB_USERNAME");
    String password = this.environmentAccessor.get("DB_PASSWORD");
    String changelogFile = this.environmentAccessor.get("DB_CHANGELOG_FILE");

    Connection conn = DriverManager.getConnection(url, username, password);

    Liquibase liquibaseClient = new Liquibase(
        changelogFile,
        new ClassLoaderResourceAccessor(),
        new JdbcConnection(conn)
    );

    return liquibaseClient;
  }
}
