package com.example.hotalproject.HotelCatalog.hotel;

import com.example.hotalproject.HotelCatalog.roomType.RoomTypeResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelResponseDto {

    private Long id;

    private String name;

    private String city;

    private String address;

    private String description;

    private String managerEmail;

    private String imageUrl;

    // للإيموجي مثل: 🏙 🕌 🕋
    private String imageEmoji;

    // للـ gradient color في الكارد
    private String color;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<RoomTypeResponseDto> roomTypes;

    private int stars;

    private double rating;

    private int reviews;

    // أقل سعر غرفة في الفندق
    // للعرض فقط: From $380
    private double minPrice;

    private List<String> amenities;
}