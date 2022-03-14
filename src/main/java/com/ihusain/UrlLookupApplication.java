package com.ihusain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ihusain.model.Product;
import com.ihusain.repository.ProductRepository;

@SpringBootApplication
public class UrlLookupApplication implements CommandLineRunner {

	@Autowired
	ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(UrlLookupApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.productRepository.deleteAll();
		
		this.saveProductList();
	}

	private void saveProductList() {
		Product p1 = new Product();
		String url = "/Women/Shoes/?tag=5678";
		p1.setUrl(url);
		productRepository.save(p1);

		p1 = new Product();
		url = "/Women/Slipper/?tag=1234";
		p1.setUrl(url);
		productRepository.save(p1);

		p1 = new Product();
		url = "/Men/Shoes/?tag=5678";
		p1.setUrl(url);
		productRepository.save(p1);

		p1 = new Product();
		url = "/Men/Slipper/?tag=123";
		p1.setUrl(url);
		productRepository.save(p1);
	}
}
