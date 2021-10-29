package co.com.sofka.wsscore.usecases;

import co.com.sofka.wsscore.domain.generic.DomainEvent;
import co.com.sofka.wsscore.domain.program.Program;
import co.com.sofka.wsscore.domain.program.command.CreateProgramCommand;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;

@Dependent
public class CreateProgramUseCase  implements Function<CreateProgramCommand, List<DomainEvent>> {

    private Logger LOGGER = Logger.getLogger(String.valueOf(AddCourseUseCase.class));

    @Override
    public List<DomainEvent> apply(CreateProgramCommand command) {
        var program = new Program(command.getProgramId(), command.getName());
        LOGGER.info("program: " + program.getUncommittedChanges());
        return program.getUncommittedChanges();
    }
}
