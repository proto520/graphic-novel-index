package net.nikaria.gni;

import lombok.Getter;
import lombok.Setter;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.StopWatch;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootApplication
@EnableProcessApplication
@EnableJpaAuditing
@EnableJpaRepositories
@ComponentScan(basePackages = {
        "net.nikaria.gni"
})
public class Program {

    @Autowired
    private RuntimeService runtimeService;

    @Getter
    @Setter
    private static long startupDurationMillis;

    public static void main(String... args) {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        SpringApplication.run(Program.class, args);

        stopWatch.stop();
        startupDurationMillis = stopWatch.getTotalTimeMillis();
    }

    // @EventListener
    // private void processPostDeploy(PostDeployEvent event) {
    //     //runtimeService.startProcessInstanceByKey("loanApproval");
    // }
}
