package co.com.sofka.wsscore.infra.handle;

import co.com.sofka.wsscore.domain.program.command.AddCourseCommand;

import co.com.sofka.wsscore.infra.CommandController;
import co.com.sofka.wsscore.infra.generic.UseCaseHandle;
import co.com.sofka.wsscore.usecases.AddCourseUseCase;

import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;


@ApplicationScoped
public class AddCourseUseCaseHandle extends UseCaseHandle {
    private final AddCourseUseCase addCourseUseCase;
    private Logger LOGGER = Logger.getLogger(String.valueOf(AddCourseUseCaseHandle.class));

    public AddCourseUseCaseHandle(AddCourseUseCase addCourseUseCase) {
        this.addCourseUseCase = addCourseUseCase;
    }

    @ConsumeEvent(value = "sofkau.program.addcourse")
    void consumeBlocking(AddCourseCommand command) {
        var events = addCourseUseCase.apply(command);
        LOGGER.info("events: " + events);
        saveProgram(command.getProgramId(), events);
    }


}