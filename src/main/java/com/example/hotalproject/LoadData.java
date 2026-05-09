package com.example.hotalproject;

import com.example.hotalproject.HotelCatalog.hotel.Hotel;
import com.example.hotalproject.HotelCatalog.hotel.HotelRepository;
import com.example.hotalproject.HotelCatalog.roomType.RoomType;
import com.example.hotalproject.HotelCatalog.roomType.RoomTypeRepository;
import com.example.hotalproject.security.AppUser;
import com.example.hotalproject.security.AppUserRepository;
import com.example.hotalproject.security.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class LoadData {

    private static final Logger log = LoggerFactory.getLogger(LoadData.class);

    @Bean
    CommandLineRunner seedData(HotelRepository hotelRepository,
                               RoomTypeRepository roomTypeRepository,
                               AppUserRepository appUserRepository,
                               PasswordEncoder passwordEncoder) {
        return args -> {

            if (appUserRepository.count() == 0) {
                appUserRepository.save(AppUser.builder()
                        .email("admin@hotel.local")
                        .password(passwordEncoder.encode("Admin@123"))
                        .userName("Hamad")
                        .role(Role.ADMIN)
                        .build());

                appUserRepository.save(AppUser.builder()
                        .email("manager1@gmail.com")
                        .password(passwordEncoder.encode("Manager@123"))
                        .userName("Mohammad")
                        .role(Role.MANAGER)
                        .build());

                appUserRepository.save(AppUser.builder()
                        .email("guest@hotel.local")
                        .password(passwordEncoder.encode("Guest@123"))
                        .userName("Saeed")
                        .role(Role.GUEST)
                        .build());

                log.info("Seeded demo users.");
            }

            if (hotelRepository.count() > 0) {
                return;
            }

            Hotel hotel1 = Hotel.builder()
                    .name("Hilton Amman")
                    .city("Amman, Jordan")
                    .address("Elia Abu Madi Street, Shmeisani, Amman 11194, Jordan")
                    .description("A modern 5-star hotel in Amman's Shmeisani business district, offering city-view rooms, on-site dining, spa facilities, an indoor pool, fitness center, meeting rooms, free WiFi and free parking.")
                    .managerEmail("manager1@gmail.com")
                    .imageUrl("https://ak-d.tripcdn.com/images/0221712000hnt6lyyC015_W_600_0_R5.webp")

                    .color("linear-gradient(135deg,#0f172a,#0369a1)")
                    .stars(5)
                    .rating(4.4)
                    .reviews(103)
                    .minPrice(101)
                    .amenities(List.of("🏊 Indoor Pool", "💪 Fitness Center", "🌿 Spa", "🍽 Restaurant", "🅿 Free Parking", "📶 Free WiFi"))
                    .build();

            hotelRepository.save(hotel1);

            roomTypeRepository.save(RoomType.builder()
                    .hotel(hotel1)
                    .name("King Guest Room")
                    .capacity(2)
                    .basePrice(BigDecimal.valueOf(101))
                    .amenities("🛏 King Bed, 🏙 City View, 📺 Flat-Screen TV, ☕ Minibar, 🧺 Ironing Facilities, 📶 Free WiFi")
                    .totalRooms(20)
                    .imageUrl("https://cf.bstatic.com/xdata/images/hotel/max1024x768/498853615.jpg?k=cde005430493edf65e084f996f21b57935ad13d9a64031baef9f882c914d3472&o=")
                    .build());

            roomTypeRepository.save(RoomType.builder()
                    .hotel(hotel1)
                    .name("Executive King Room")
                    .capacity(3)
                    .basePrice(BigDecimal.valueOf(145))
                    .amenities("🛏 King Bed, 🏙 City View, 🪑 Table & Chairs, 📺 Flat-Screen TV, ☕ Minibar, 📶 Free WiFi")
                    .totalRooms(10)
                    .imageUrl("https://cf.bstatic.com/xdata/images/hotel/max1024x768/803767734.jpg?k=cfa50bb1cf405ef9246c43878e1ff75996ef6963e816e38f23918c9274167c8d&o=")
                    .build());

            roomTypeRepository.save(RoomType.builder()
                    .hotel(hotel1)
                    .name("Two-Bedroom Suite with One King and Two Twin Beds")
                    .capacity(4)
                    .basePrice(BigDecimal.valueOf(230))
                    .amenities("🛏 1 King Bed + 2 Twin Beds, 🛋 Living Area, 🧊 Minibar, ☕ Tea/Coffee Maker, 🛁 Private Bathroom, 📶 Free WiFi")
                    .totalRooms(3)
                    .imageUrl("https://cf.bstatic.com/xdata/images/hotel/max1024x768/401964916.jpg?k=84766a00e1e75829fca5794ee4c72a3f9ffd66b8192d0f28a398d1768dc4a6b6&o=")
                    .build());


// ------------------------------------------------------------

            Hotel hotel2 = Hotel.builder()
                    .name("Corp Amman Hotel")
                    .city("Amman, Jordan")
                    .address("Queen Alia Street, Al Shmeisani, Amman, Jordan")
                    .description("A modern hotel in Amman's Shmeisani district, located across from the Royal Cultural Center, offering free WiFi, an outdoor pool, gym, restaurant and comfortable city or pool-view rooms.")
                    .managerEmail("manager1@gmail.com")
                    .imageUrl("https://content.r9cdn.net/rimg/himg/05/e2/d3/expedia_group-514066-9660f5-647360.jpg?width=1366&height=768&crop=true")

                    .color("linear-gradient(135deg,#7f1d1d,#f97316)")
                    .stars(4)
                    .rating(4.2)
                    .reviews(621)
                    .minPrice(75)
                    .amenities(List.of("🏊 Outdoor Pool", "💪 Gym", "📶 Free WiFi", "🍽 Restaurant", "🧖 Spa", "🅿 Parking"))
                    .build();

            hotelRepository.save(hotel2);

            roomTypeRepository.save(RoomType.builder()
                    .hotel(hotel2)
                    .name("Premium King Room")
                    .capacity(2)
                    .basePrice(BigDecimal.valueOf(75))
                    .amenities("🛏 King Bed, ❄ Air Conditioning, 📶 Free WiFi, 🧴 Free Toiletries, 🔐 In-Room Safe, ☕ Tea/Coffee Facilities")
                    .totalRooms(15)
                    .imageUrl("https://ak-d.tripcdn.com/images/1ik4n12000nytptagC3FE_R_696_392_R5.webp")
                    .build());

            roomTypeRepository.save(RoomType.builder()
                    .hotel(hotel2)
                    .name("Premium Twin")
                    .capacity(2)
                    .basePrice(BigDecimal.valueOf(115))
                    .amenities("🛏 2 Twin Beds, ❄ Air Conditioning, 📶 Free WiFi, 🧴 Free Toiletries, 🔐 Safety Deposit Box, ☕ Tea/Coffee Facilities")
                    .totalRooms(7)
                    .imageUrl("https://ak-d.tripcdn.com/images/0226n12000l0e9h10334B_R_696_392_R5.webp")
                    .build());

            roomTypeRepository.save(RoomType.builder()
                    .hotel(hotel2)
                    .name("Family Room")
                    .capacity(4)
                    .basePrice(BigDecimal.valueOf(160))
                    .amenities("👨‍👩‍👧 Family Room, 🛏 Premium Bedding, 🧊 Minibar, 🔐 In-Room Safe, 📺 LCD TV, 🛁 Private Bathroom")
                    .totalRooms(4)
                    .imageUrl("https://ak-d.tripcdn.com/images/0225w120009c3i47c81B5_R_696_392_R5.webp")
                    .build());


// ------------------------------------------------------------

            Hotel hotel3 = Hotel.builder()
                    .name("Seven Arches Hotel")
                    .city("Jerusalem")
                    .address("Mount of Olives, Jerusalem, 91190")
                    .description("A historic hotel on the Mount of Olives with panoramic views of Jerusalem's Old City, offering air-conditioned rooms, free parking, breakfast and an on-site dining room.")
                    .managerEmail("manager1@gmail.com")
                    .imageUrl("https://pix10.agoda.net/hotelImages/344939/0/04308c88fcd2b867f22ac82a50fa9fb6.jpeg?ce=0&s=414x232")

                    .color("linear-gradient(135deg,#7c2d12,#c2410c)")
                    .stars(3)
                    .rating(4.0)
                    .reviews(45)
                    .minPrice(98)
                    .amenities(List.of("🌄 Old City View", "🅿 Free Parking", "❄ Air Conditioning", "🍽 Restaurant", "📶 WiFi", "☕ Breakfast"))
                    .build();

            hotelRepository.save(hotel3);

            roomTypeRepository.save(RoomType.builder()
                    .hotel(hotel3)
                    .name("Double or Twin Room")
                    .capacity(2)
                    .basePrice(BigDecimal.valueOf(98))
                    .amenities("🛏 Double or Twin Beds, ❄ Air Conditioning, 📺 TV, 🛁 Private Bathroom, 🔐 In-Room Safe, 📶 Free WiFi")
                    .totalRooms(25)
                    .imageUrl("https://ak-d.tripcdn.com/images/0223g12000l0kbpze8C22_R_696_392_R5.webp")
                    .build());


// ------------------------------------------------------------

            Hotel hotel4 = Hotel.builder()
                    .name("Alhambra Palace Hotel Suites")
                    .city("Ramallah, Palestine")
                    .address("Irsal Street, Ramallah, Palestine")
                    .description("A heritage-style hotel in central Ramallah near Al Manara Square and Mukataa, offering family rooms and suites, a garden, terrace, free WiFi and spacious traditional interiors.")
                    .managerEmail("manager1@gmail.com")
                    .imageUrl("https://aw-d.tripcdn.com/images/0222x12000k7rtjmr5D11_R_960_660_R5_D.jpg")

                    .color("linear-gradient(135deg,#92400e,#78350f)")
                    .stars(3)
                    .rating(4.1)
                    .reviews(81)
                    .minPrice(104)
                    .amenities(List.of("🌳 Garden", "🏛 Heritage Building", "📶 Free WiFi", "🛏 Family Rooms", "🌇 Terrace", "📍 Central Location"))
                    .build();

            hotelRepository.save(hotel4);

            roomTypeRepository.save(RoomType.builder()
                    .hotel(hotel4)
                    .name("Standard Double Room")
                    .capacity(2)
                    .basePrice(BigDecimal.valueOf(104))
                    .amenities("🛏 1 Double Bed, ❄ Air Conditioning, 🛁 Bathtub, 🛋 Seating Area, 📺 Flat-Screen TV, 🧊 Fridge")
                    .totalRooms(4)
                    .imageUrl("https://ak-d.tripcdn.com/images/0225n12000k6cw8uc06CD_R_339_206_R5_D.jpg")
                    .build());

            roomTypeRepository.save(RoomType.builder()
                    .hotel(hotel4)
                    .name("Standard Twin Room")
                    .capacity(4)
                    .basePrice(BigDecimal.valueOf(155))
                    .amenities("🛏 2 Twin Beds, ❄ Air Conditioning, 🛁 Private Bathroom, 🛋 Seating Area, 📺 Flat-Screen TV, ☕ Electric Kettle")
                    .totalRooms(9)
                    .imageUrl("https://ak-d.tripcdn.com/images/0225n12000k6cw8uc06CD_R_339_206_R5_D.jpg")
                    .build());



// ------------------------------------------------------------

            Hotel hotel5 = Hotel.builder()
                    .name("Abu Mazen Hotel")
                    .city("Hebron, Palestine")
                    .address("Namera Street, Hebron, Palestine")
                    .description("A 4-star hotel in Hebron offering comfortable air-conditioned rooms, free WiFi, free parking, buffet breakfast, an on-site restaurant, business facilities and 24-hour front desk service.")
                    .managerEmail("manager1@gmail.com")
                    .imageUrl("https://media-cdn.tripadvisor.com/media/photo-s/09/27/91/d5/abu-mazen-hotel.jpg")

                    .color("linear-gradient(135deg,#78350f,#b45309)")
                    .stars(4)
                    .rating(4.9)
                    .reviews(7)
                    .minPrice(120)
                    .amenities(List.of("📶 Free WiFi", "🅿 Free Parking", "🍽 Restaurant", "☕ Breakfast", "💼 Business Center", "🕛 24h Front Desk"))
                    .build();

            hotelRepository.save(hotel5);

            roomTypeRepository.save(RoomType.builder()
                    .hotel(hotel5)
                    .name("Triple Room with City View")
                    .capacity(3)
                    .basePrice(BigDecimal.valueOf(120))
                    .amenities("🛏 1 Double Bed + 1 Twin Bed OR 3 Twin Beds, 🏙 City View, ❄ Air Conditioning, 🛁 Private Bathroom, 📶 Free WiFi")
                    .totalRooms(18)
                    .imageUrl("https://ak-d.tripcdn.com/images/0223312000k2j5a9x662B_R_696_392_R5.webp")
                    .build());

            roomTypeRepository.save(RoomType.builder()
                    .hotel(hotel5)
                    .name("Twin Room with City View")
                    .capacity(2)
                    .basePrice(BigDecimal.valueOf(145))
                    .amenities("🛏 2 Twin Beds, 🏙 City View, ❄ Air Conditioning, 📺 LED TV, 🛁 Private Bathroom, 📶 Free WiFi")
                    .totalRooms(10)
                    .imageUrl("https://ak-d.tripcdn.com/images/0220112000k2j5fhtB980_R_600_400_R5.webp")
                    .build());

            roomTypeRepository.save(RoomType.builder()
                    .hotel(hotel5)
                    .name("Family Two-Bedroom Suite with City View")
                    .capacity(4)
                    .basePrice(BigDecimal.valueOf(210))
                    .amenities("🛏 2 Bedrooms, 🏙 City View, 📺 42 LED TV, ☕ Tea/Coffee Maker, 🛋 Family Suite, 📶 Free WiFi")
                    .totalRooms(5)
                    .imageUrl("https://ak-d.tripcdn.com/images/0226212000l182fsvF703_R_696_392_R5.webp")
                    .build());


// ------------------------------------------------------------

            Hotel hotel6 = Hotel.builder()
                    .name("Ocean Grand Hotel")
                    .city("Ramallah, Palestine")
                    .address("Rafat Street, Al-Masyoun Heights, Ramallah, Palestine")
                    .description("A modern hotel in Ramallah's Al-Masyoun area near Al Manara Square, offering restaurant and coffee shop services, free WiFi, free private parking, room service and a 24-hour front desk.")
                    .managerEmail("manager1@gmail.com")
                    .imageUrl("https://images.trvl-media.com/lodging/94000000/93470000/93462500/93462498/e0f23aa0.jpg?impolicy=resizecrop&rw=575&rh=575&ra=fill")

                    .color("linear-gradient(135deg,#1e3a8a,#0f766e)")
                    .stars(4)
                    .rating(8.5)
                    .reviews(81)
                    .minPrice(105)
                    .amenities(List.of("🍽 Restaurant", "☕ Coffee Shop", "📶 Free WiFi", "🅿 Free Parking", "🛎 Room Service", "🕛 24h Front Desk"))
                    .build();

            hotelRepository.save(hotel6);

            roomTypeRepository.save(RoomType.builder()
                    .hotel(hotel6)
                    .name("Superior Twin Room")
                    .capacity(2)
                    .basePrice(BigDecimal.valueOf(105))
                    .amenities("🛏 2 Twin Beds + 1 Sofa Bed, 📶 Free WiFi, 🚭 Non-Smoking")
                    .totalRooms(20)
                    .imageUrl("https://ak-d.tripcdn.com/images/0221i12000k5tayr012F0_R_696_392_R5.webp")
                    .build());

            roomTypeRepository.save(RoomType.builder()
                    .hotel(hotel6)
                    .name("Standard King Room With Sofa Bed")
                    .capacity(2)
                    .basePrice(BigDecimal.valueOf(145))
                    .amenities("🛏 1 King Bed + 1 Sofa Bed, ❄ Air Conditioning, 📺 Flat-Screen TV")
                    .totalRooms(9)
                    .imageUrl("https://ak-d.tripcdn.com/images/0227112000l6yx87h2ED1_R_696_392_R5.webp")
                    .build());

            roomTypeRepository.save(RoomType.builder()
                    .hotel(hotel6)
                    .name("Superior Triple Room")
                    .capacity(4)
                    .basePrice(BigDecimal.valueOf(250))
                    .amenities("🛏 3 Twin Beds, 📶 Free WiFi, 🚭 Non-Smoking, 🧹 Daily Housekeeping, ♿ Wheelchair Accessible")
                    .totalRooms(2)
                    .imageUrl("https://ak-d.tripcdn.com/images/0224n12000k5tac33DC12_R_696_392_R5.webp")
                    .build());

            roomTypeRepository.save(RoomType.builder()
                    .hotel(hotel6)
                    .name("Junior King Suite With Sofa Bed")
                    .capacity(2)
                    .basePrice(BigDecimal.valueOf(250))
                    .amenities("🛏 1 King Bed + 1 Sofa Bed, 🛌 Separate Bedroom")
                    .totalRooms(2)
                    .imageUrl("https://ak-d.tripcdn.com/images/0220r12000pkpbmhbD3CC_R_696_392_R5.webp")
                    .build());

            roomTypeRepository.save(RoomType.builder()
                    .hotel(hotel6)
                    .name("King Suite With Sofa Bed")
                    .capacity(2)
                    .basePrice(BigDecimal.valueOf(250))
                    .amenities("🛏 1 King Bed + 1 Sofa Bed, 🛋 Living Room, 🛌 Separate Bedroom, 📶 Free WiFi")
                    .totalRooms(2)
                    .imageUrl("https://ak-d.tripcdn.com/images/0220z12000l0yqp805A6F_R_696_392_R5.webp")
                    .build());

            log.info("Seeded hotels and room types.");
        };
    }
}