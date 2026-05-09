package com.example.hotalproject.HotelCatalog.hotel;

import com.example.hotalproject.HotelCatalog.roomType.RoomTypeResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HotelMapper {

    public static Hotel toEntity(HotelRequestDto request) {
        return Hotel.builder()
                .name(request.getName())
                .city(request.getCity())
                .address(request.getAddress())
                .description(request.getDescription())
                .managerEmail(request.getManagerEmail())
                .imageUrl(request.getImageUrl())
                .color(request.getColor())
                .stars(request.getStars())
                .rating(request.getRating())
                .reviews(request.getReviews())
                .minPrice(request.getMinPrice())
                .amenities(request.getAmenities())
                .build();
    }

    public static void updateEntity(Hotel hotel, HotelRequestDto request) {
        hotel.setName(request.getName());
        hotel.setCity(request.getCity());
        hotel.setAddress(request.getAddress());
        hotel.setDescription(request.getDescription());
        hotel.setManagerEmail(request.getManagerEmail());
        hotel.setImageUrl(request.getImageUrl());

        hotel.setColor(request.getColor());
        hotel.setStars(request.getStars());
        hotel.setRating(request.getRating());
        hotel.setReviews(request.getReviews());
        hotel.setMinPrice(request.getMinPrice());
        hotel.setAmenities(request.getAmenities());
    }

    public static HotelResponseDto toResponse(Hotel hotel) {
        return toResponse(hotel, null);
    }

    public static HotelResponseDto toResponse(Hotel hotel, List<RoomTypeResponseDto> roomTypes) {
        return HotelResponseDto.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .city(hotel.getCity())
                .address(hotel.getAddress())
                .description(hotel.getDescription())
                .managerEmail(hotel.getManagerEmail())
                .imageUrl(hotel.getImageUrl())

                .color(hotel.getColor())
                .createdAt(hotel.getCreatedAt())
                .updatedAt(hotel.getUpdatedAt())
                .roomTypes(roomTypes)
                .stars(hotel.getStars())
                .rating(hotel.getRating())
                .reviews(hotel.getReviews())
                .minPrice(hotel.getMinPrice())
                .amenities(hotel.getAmenities())
                .build();
    }
}