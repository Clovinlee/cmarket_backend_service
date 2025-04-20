package com.chris.cmarket.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;

import com.chris.cmarket.Dtos.ProductDTO;
import com.chris.cmarket.Requests.GetProductRequest;
import com.chris.cmarket.Responses.APIResponse;
import com.chris.cmarket.Services.ProductService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class HomeController {

    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<APIResponse<?>> getProducts(
            @Valid @ModelAttribute GetProductRequest request,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(APIResponse.failed(bindingResult.getAllErrors()));
        }

        Page<ProductDTO> products = this.productService.getProducts(request);

        if (products.isEmpty()) {
            return ResponseEntity.status(404)
                    .body(APIResponse.notFound());
        }

        return ResponseEntity.ok(APIResponse.success(products));
    }
}
