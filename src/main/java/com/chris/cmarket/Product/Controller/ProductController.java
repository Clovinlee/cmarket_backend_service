package com.chris.cmarket.Product.Controller;

import com.chris.cmarket.Common.Dto.CustomPageImplDto;
import com.chris.cmarket.Common.Response.APIResponse;
import com.chris.cmarket.Product.Dto.ProductDTO;
import com.chris.cmarket.Product.Request.GetProductRequest;
import com.chris.cmarket.Product.Request.OrderProductRequest;
import com.chris.cmarket.Product.Service.ProductService;
import com.chris.cmarket.User.Model.UserModel;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @PostMapping("{slug}/order")
    @ResponseStatus(HttpStatus.OK)
    public void productOrder(
            @PathVariable String slug,
            @Valid @RequestBody OrderProductRequest orderProductRequest,
            @AuthenticationPrincipal(expression = "user") UserModel user,
            @RequestHeader("Authorization") String jwtToken
    ) {
        this.productService.placeOrder(slug, orderProductRequest.getQuantity(), user, jwtToken);
    }
}
