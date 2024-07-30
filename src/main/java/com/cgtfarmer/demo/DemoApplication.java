package com.cgtfarmer.demo;

import com.cgtfarmer.demo.accessor.EnvironmentAccessor;
import com.cgtfarmer.demo.factory.LiquibaseClientFactory;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;

public class DemoApplication {

	public static void main(String[] args) throws Exception {
    System.out.println("Hello, world!");

    EnvironmentAccessor environmentAccessor = new EnvironmentAccessor();

    Liquibase liquibaseClient = new LiquibaseClientFactory(environmentAccessor).create();

    liquibaseClient.update(new Contexts(), new LabelExpression());

    liquibaseClient.close();
  }
}
