package com.leads.microcube.verifidadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
@ComponentScan(
        basePackages = {"com.leads.microcube", "com.leads.microcube.verifidadmin"},
        nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
@EnableTransactionManagement
@EnableJpaRepositories("com.leads.microcube.*")
@EntityScan("com.leads.microcube.*")
public class ProviderApplication {
  
  public static void main(String[] args) {
    SpringApplication.run(ProviderApplication.class, args);
  }
}
