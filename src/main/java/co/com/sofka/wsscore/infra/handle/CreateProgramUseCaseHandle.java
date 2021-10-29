package co.com.sofka.wsscore.infra.handle;

import co.com.sofka.wsscore.domain.program.command.CreateProgramCommand;
import co.com.sofka.wsscore.infra.generic.UseCaseHandle;
import co.com.sofka.wsscore.usecases.CreateProgramUseCase;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

@ApplicationScoped
public class CreateProgramUseCaseHandle extends UseCaseHandle {

    private final CreateProgramUseCase createProgramUseCase;
    private Logger LOGGER = Logger.getLogger(String.valueOf(CreateProgramUseCase.class));

    public CreateProgramUseCaseHandle(CreateProgramUseCase createProgramUseCase) {
        this.createProgramUseCase = createProgramUseCase;
    }

    @ConsumeEvent(value = "sofkau.program.createprogram", blocking = true)
    void consumeBlocking(CreateProgramCommand command) {
        var events = createProgramUseCase.apply(command);
        LOGGER.info("events: " + events);
        saveProgram(command.getProgramId(), events);
    }
}