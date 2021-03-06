package test.restapi.controller;

import test.restapi.dto.ProductDto;
import test.restapi.dto.RegisterRequest;
import test.restapi.service.PropsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/edit")
public class PropsController {

    private PropsService propsService;

    @Autowired
    public PropsController(PropsService propsService) {
        this.propsService = propsService;
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public String editUser(@RequestBody RegisterRequest registerRequest) {
        return propsService.editUser(registerRequest);
    }

    @RequestMapping(value = "product/{id}", method = RequestMethod.PUT)
    public String editProduct(@RequestBody ProductDto productDto,
                              @PathVariable("id") long id) {
        return propsService.editProduct(productDto, id);
    }

    @RequestMapping(value = "order/{id}", method = RequestMethod.DELETE)
    public String cancelOrder(@PathVariable("id") long id) {
        return propsService.cancelOrder(id);
    }

}
