package com.example.hotalproject.HotelCatalog.hotel;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelRequestDto {

    @NotBlank(message = "Hotel name is required")
    private String name;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Address is required")
    private String address;

    private String description;

    @NotBlank(message = "Manager email is required")
    @Email(message = "Manager email must be valid")
    private String managerEmail;

    private String imageUrl;

    // الإيموجي مثل 🏙 🕌 🕋
    // لون الكارد / gradient
    private String color;

    @Min(value = 1, message = "Stars must be at least 1")
    @Max(value = 5, message = "Stars must be at most 5")
    private int stars;

    @Min(value = 0, message = "Rating must be at least 1")
    @Max(value = 10, message = "Rating must be at most 10")
    private double rating;

    @Min(value = 0, message = "Reviews cannot be negative")
    private int reviews;

    @Min(value = 0, message = "Min price cannot be negative")
    private double minPrice;

    private List<String> amenities;
}