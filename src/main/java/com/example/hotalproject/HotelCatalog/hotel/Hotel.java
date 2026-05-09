package com.example.hotalproject.HotelCatalog.hotel;

import com.example.hotalproject.HotelCatalog.roomType.RoomType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hotels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Burj Al-Qasr Palace
    @Column(nullable = false)
    private String name;

    // Dubai, UAE
    @Column(nullable = false)
    private String city;


    private String address;

    // وصف الفندق
    @Column(length = 2000)
    private String description;

    // إيميل المدير
    @Column(nullable = false)
    private String managerEmail;

    // لو عندك صورة حقيقية
    @Column(length = 500)
    private String imageUrl;




    // اللون/الخلفية تبعت الكارد
    @Column(length = 500)
    private String color;

    // عدد النجوم
    private int stars;

    // التقييم
    private double rating;

    // عدد المراجعات
    private int reviews;

    // أقل سعر غرفة في الفندق
    // هذا مش سعر الفندق كله، هذا فقط للعرض: Starting from
    private double minPrice;

    @ElementCollection
    @CollectionTable(
            name = "hotel_amenities",
            joinColumns = @JoinColumn(name = "hotel_id")
    )
    @Column(name = "amenity")
    @Builder.Default
    private List<String> amenities = new ArrayList<>();

    @OneToMany(
            mappedBy = "hotel",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    @JsonIgnore
    private List<RoomType> rooms = new ArrayList<>();

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}