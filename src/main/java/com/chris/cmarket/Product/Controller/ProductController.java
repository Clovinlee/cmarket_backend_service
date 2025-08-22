package com.chris.cmarket.Product.Controller;

import com.chris.cmarket.Common.Dto.CustomPageImplDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import com.chris.cmarket.Common.Response.APIResponse;
import com.chris.cmarket.Product.Dto.ProductDTO;
import com.chris.cmarket.Product.Request.GetProductRequest;
import com.chris.cmarket.Product.Service.ProductService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<APIResponse<?>> getProducts(
            @Valid @ModelAttribute GetProductRequest request,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(APIResponse.failed(bindingResult.getAllErrors()));
        }

        CustomPageImplDto<ProductDTO> products = this.productService.getProducts(request);

        if (products.isEmpty()) {
            return ResponseEntity.status(404)
                    .body(APIResponse.notFound());
        }

        return ResponseEntity.ok(APIResponse.success(products));
    }

    @GetMapping("{slug}")
    public ResponseEntity<APIResponse<?>> getProductBySlug(@PathVariable String slug) {
        Optional<ProductDTO> productSlug = productService.getProductBySlug(slug);

        if (productSlug.isEmpty()) return ResponseEntity.status(404).body(APIResponse.notFound());

        return ResponseEntity.ok(APIResponse.success(productSlug.get()));

    }

}
