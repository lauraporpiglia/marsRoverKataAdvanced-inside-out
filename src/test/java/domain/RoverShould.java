package domain;

import domain.CommandConverter;
import domain.Rover;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class RoverShould {
    private Rover rover;


    @BeforeEach
    void initialize() {
        CommandConverter commandConverter = new CommandConverter();
        rover = new Rover(commandConverter);
    }

    @Test
    void should_give_direction_N_when_no_commands() {

        assertEquals("N", rover.direction());

    }

    // MMRMMLM
    @ParameterizedTest
    @CsvSource( {"RL,N", "RRRR,N", "R,E", "L,W", "RR,S"  })
    // left(), right()
    void should_give_direction_when_command(String command,String direction) {
        //act
        rover.moves(command);

        //assert
        assertEquals(direction, rover.direction());
    }

    @Test
    void should_give_error_when_command_isNull() {
        assertThrows(IllegalArgumentException.class, () -> rover.moves(null) , "domain.Command is null" );
    }

    @Test
    void should_give_error_when_command_is_not_allowed() {
        assertThrows(
            IllegalArgumentException.class,
            () -> rover.moves("Z") ,
            "domain.Command is invalid"
        );
    }




}