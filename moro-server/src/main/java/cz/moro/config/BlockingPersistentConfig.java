package cz.moro.config;

//@Configuration
//@EnableTransactionManagement
//public class BlockingPersistentConfig {
//
//    @Value("${spring.datasource.driver-class-name}")
//    private String driverClassName;
//
//    @Value("${spring.datasource.url}")
//    private String jdbcUrl;
//
//    @Value("${spring.datasource.usernameDb}")
//    private String usernameDb;
//
//    @Value("${spring.datasource.password}")
//    private String password;
//
//    @Value("${spring.jpa.properties.hibernate.dialect}")
//    private String hibernateDialect;
//
//    @Value("${spring.datasource.jpa.hibernate.ddl-auto}")
//    private String hibernateHbm2ddlAuto;
//
//    @Value("${spring.jpa.properties.hibernate.naming.physical-strategy}")
//    private String hibernateEjbNamingStrategy;
//
//    @Value("${spring.jpa.properties.hibernate.show_sql}")
//    private String hibernateShowSql;
//
//    @Value("${spring.jpa.properties.hibernate.format_sql}")
//    private String hibernateFormatSql;
//    @Bean(destroyMethod = "close")
//    HikariDataSource dataSource() {
//        HikariConfig dataSourceConfig = new HikariConfig();
//        dataSourceConfig.setDriverClassName(driverClassName);
//        dataSourceConfig.setJdbcUrl(jdbcUrl);
//        dataSourceConfig.setUsername(usernameDb);
//        dataSourceConfig.setPassword(password);
//        return new HikariDataSource(dataSourceConfig);
//    }
//
//    @Bean
//    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//        entityManagerFactoryBean.setDataSource(dataSource);
//        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        entityManagerFactoryBean.setPackagesToScan("cz.moro.repository");
//
//        Properties jpaProperties = new Properties();
//
//        //Configures the used database dialect. This allows Hibernate to create SQL
//        //that is optimized for the used database.
//        jpaProperties.put("hibernate.dialect", hibernateDialect);
//
//        //Specifies the action that is invoked to the database when the Hibernate
//        //SessionFactory is created or closed.
//        jpaProperties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
//
//        //Configures the naming strategy that is used when Hibernate creates
//        //new database objects and schema elements
//        jpaProperties.put("hibernate.ejb.naming_strategy", hibernateEjbNamingStrategy);
//
//        //If the value of this property is true, Hibernate writes all SQL
//        //statements to the console.
//        jpaProperties.put("hibernate.show_sql", hibernateShowSql);
//
//        //If the value of this property is true, Hibernate will format the SQL
//        //that is written to the console.
//        jpaProperties.put("hibernate.format_sql", hibernateFormatSql);
//
//        entityManagerFactoryBean.setJpaProperties(jpaProperties);
//
//        return entityManagerFactoryBean;
//    }
//
//    @Bean
//    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory);
//        return transactionManager;
//    }
//}
