package pl.domanski.product;

public enum ProductType {
    MALE("5"),FEMALE("5"),KID("10");

    private String message;

    ProductType() {
    }

    ProductType(String message) {
        this.message = message;
    }
}
