package com.microcompany.accountsservice;

@SpringBootApplication
public class App {
    private static final Logger logger = LoggerFactory.getLogger(ProductsServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ProductsServiceApplication.class, args);
    }
}
