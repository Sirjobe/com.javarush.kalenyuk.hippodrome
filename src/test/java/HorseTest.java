
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import org.junit.jupiter.params.provider.MethodSource;

import org.mockito.MockedStatic;
import org.mockito.Mockito;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;


public class HorseTest {
     private static Stream<Arguments> horseData() {
          return Stream.of(
                  Arguments.of(null, 1.2, 2.1, "Name cannot be null."),          // null
                  Arguments.of("", 3.2, 1.1, "Name cannot be blank."),           // Пустая строка
                  Arguments.of(" ", 1.2, 2.1, "Name cannot be blank."),          // Пробел
                  Arguments.of("\t", 3.2, 1.1, "Name cannot be blank."),         // Табуляция
                  Arguments.of("\n", 2.2, 45.1, "Name cannot be blank."),        // Перевод строки
                  Arguments.of("\r", -1, 1.1, "Name cannot be blank."),          // Возврат каретки
                  Arguments.of("panda", -1, 1.1, "Speed cannot be negative."),   // Отрицательная скорость
                  Arguments.of("groundhog", 5.1, -211.1, "Distance cannot be negative.") // Отрицательное расстояние
          );
     }
     @ParameterizedTest
     @MethodSource("horseData")
     public void testHorse(String name, double speed, double distance, String expected) {
          Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
               new Horse(name, speed, distance);
          });
          Assertions.assertEquals(expected, exception.getMessage());
     }

     @Test
     public void testGetName() {
          String name = "Horse";
          Horse horse = new Horse(name, 1.2, 2.1);
          Assertions.assertEquals(name, horse.getName());
     }

     @Test
     public void testGetSpeed() {
          double speed = 2.3;
          Horse horseThree = new Horse("Horse", speed, 1.2);
          Horse horseTwo = new Horse("Horse", speed);
          Assertions.assertEquals(speed, horseThree.getSpeed());
          Assertions.assertEquals(speed, horseTwo.getSpeed());
     }

     @Test
     public void testGetDistance() {
          double distance = 2.3;
          Horse horseThree = new Horse("Horse",1.3,distance);
          Horse horseTwo = new Horse("Horse",1.2);
          Assertions.assertEquals(distance, horseThree.getDistance());
          Assertions.assertEquals(0, horseTwo.getDistance());
     }

     @Test
     public void testGetMove(){
          try (MockedStatic<Horse> getRandomDouble = Mockito.mockStatic(Horse.class)) {
               getRandomDouble.when(()-> Horse.getRandomDouble(anyDouble(), anyDouble())).thenReturn(1.2);
               double result = Horse.getRandomDouble(0.2, 0.9);
               Assertions.assertEquals(1.2,result);
               Horse horse = new Horse("Horse",1.2,2.1);
               getRandomDouble.verify(()-> Horse.getRandomDouble(0.2, 0.9));
               double resultMove = horse.getDistance()+horse.getSpeed()*result;
               horse.move();
               Assertions.assertEquals(resultMove,horse.getDistance());
          }







     }

}
