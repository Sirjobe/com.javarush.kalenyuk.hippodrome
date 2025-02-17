import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class HippodromeTest {


        @Test
        public void testHippodromeNull(){
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                new Hippodrome(null);
            });
            Assertions.assertEquals("Horses cannot be null.", exception.getMessage());
        }
        @Test
        public void testHippodromeListNull(){
            List<Horse> horses = List.of();
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                new Hippodrome(horses);
            });
            Assertions.assertEquals("Horses cannot be empty.", exception.getMessage());
        }
        @Test
        public void testAddHorses(){
            List<Horse> horses = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                horses.add(new Horse("Horses"+i,1.1+i-0.7,2.1+i-0.7));
            }
            Hippodrome h = new Hippodrome(horses);
            for (int i = 0; i < horses.size(); i++) {
                Assertions.assertEquals(horses.get(i), h.getHorses().get(i));
            }
        }
        @Test
        public void testMove(){
            List<Horse> mockHorses = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                mockHorses.add(mock(Horse.class));
            }
            Hippodrome h = new Hippodrome(mockHorses);
            h.move();
            for(Horse horse : mockHorses){
                Mockito.verify(horse,times(1)).move();
            }
        }
        @Test
    public void testGetWinner(){
        List<Horse> horses = new ArrayList<>();
            horses.add(new Horse("Horses"+1,1.1+2-0.7,2.1+3-0.7));
            horses.add(new Horse("Horses"+2,2.1+2-0.7,1.1+3-0.7));
            horses.add(new Horse("Horses"+3,5.1+2-0.7,43.1+3-0.7));

            Hippodrome h = new Hippodrome(horses);
            Optional<Horse> leader = Optional.ofNullable(h.getWinner());
            Assertions.assertTrue(leader.isPresent());
            Horse expectedLeader = new Horse("Horses"+3,5.1+2-0.7,43.1+3-0.7);
            assertEquals(expectedLeader.getDistance(),leader.get().getDistance());
    }
}



