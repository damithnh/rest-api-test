package test.restapi.controller;

import test.restapi.dto.ProductDto;
import test.restapi.model.Product;
import test.restapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/get")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @RequestMapping("/getid/{id}")
    public Product getProductById(@PathVariable("id") long id) {
        return productService.getProductById(id);
    }

    @RequestMapping("/getname/{name}")
    public List<Product> getProductByName(@PathVariable("name") String name) {
        return productService.getProductByName(name);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addProduct(@RequestBody ProductDto productDto) {
        return productService.addProduct(productDto);
    }
}
