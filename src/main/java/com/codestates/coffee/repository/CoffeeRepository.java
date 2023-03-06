package com.codestates.coffee.repository;

import com.codestates.coffee.entity.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> { // 수정된 부분
    Optional<Coffee> findByCoffeeCode(String coffeeCode);

    // 수정된 부분
//    @Query(value = "FROM Coffee c WHERE c.coffeeId = :coffeeId")
//    @Query(value = "SELECT * FROM COFFEE WHERE coffee_Id = :coffeeId", nativeQuery =true)
    @Query(value = "SELECT c FROM Coffee c WHERE c.coffeeId = :coffeeId")
    Optional<Coffee> findByCoffee(long coffeeId);
//    Optional<Coffee> findByCoffeeId(long coffeeId); // 위 메서드랑 같은거
}
